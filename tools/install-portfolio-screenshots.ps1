$ErrorActionPreference = 'Stop'

$repositoryRoot = (Resolve-Path (Join-Path $PSScriptRoot '..')).Path
Set-Location $repositoryRoot

$currentBranch = (git branch --show-current).Trim()
if ($currentBranch -ne 'main') {
    throw "Switch to the main branch before running this installer. Current branch: $currentBranch"
}

if (-not (Test-Path '.github/screenshot-assets')) {
    throw 'The temporary screenshot asset parts were not found.'
}

$parts = Get-ChildItem '.github/screenshot-assets/part-*.b64' | Sort-Object Name
if ($parts.Count -eq 0) {
    throw 'No screenshot asset parts were found.'
}

$encoded = ($parts | ForEach-Object { (Get-Content $_.FullName -Raw).Trim() }) -join ''
$archiveBytes = [Convert]::FromBase64String($encoded)
$tempZip = Join-Path $env:TEMP 'crisisops-portfolio-screenshots.zip'
$tempExtract = Join-Path $env:TEMP 'crisisops-portfolio-screenshots'

[IO.File]::WriteAllBytes($tempZip, $archiveBytes)
Remove-Item $tempExtract -Recurse -Force -ErrorAction SilentlyContinue
Expand-Archive -Path $tempZip -DestinationPath $tempExtract -Force

$target = Join-Path $repositoryRoot 'docs/images/screenshots'
New-Item -ItemType Directory -Path $target -Force | Out-Null

$expected = @(
    'crisisops-login.webp',
    'admin-dashboard.webp',
    'admin-user-management.webp',
    'ecc-dashboard.webp',
    'ecc-resource-availability.webp',
    'public-alerts.webp',
    'public-disaster-reporting.webp',
    'mysql-schema.webp'
)

foreach ($name in $expected) {
    $source = Get-ChildItem $tempExtract -Recurse -File -Filter $name | Select-Object -First 1
    if (-not $source) {
        throw "Missing screenshot in archive: $name"
    }

    $bytes = [IO.File]::ReadAllBytes($source.FullName)
    $header = [Text.Encoding]::ASCII.GetString($bytes, 0, [Math]::Min(12, $bytes.Length))
    if (-not ($header.StartsWith('RIFF') -and $header.Substring(8, 4) -eq 'WEBP')) {
        throw "Invalid WebP screenshot: $name"
    }

    Copy-Item $source.FullName (Join-Path $target $name) -Force
}

$gallery = @'
# CrisisOps Screenshot Gallery

These screenshots document the tested portfolio version of CrisisOps. The application uses local demonstration data and role-based accounts.

## Secure Login

![CrisisOps secure login](images/screenshots/crisisops-login.webp)

## System Administrator

### Dashboard

![System Administrator dashboard](images/screenshots/admin-dashboard.webp)

### User Management

![Administrator user management](images/screenshots/admin-user-management.webp)

## Emergency Control Centre

### Operational Dashboard

![Emergency Control Centre dashboard](images/screenshots/ecc-dashboard.webp)

### Emergency Resource Availability

![Emergency resource availability tracker](images/screenshots/ecc-resource-availability.webp)

## Public User

### Public Alerts

![Public alerts](images/screenshots/public-alerts.webp)

### Report a Disaster

![Public disaster reporting](images/screenshots/public-disaster-reporting.webp)

## MySQL Database

![CrisisOps MySQL schema](images/screenshots/mysql-schema.webp)

The legacy Java package name `drsinitial` and schema name `drs_enhanced` are retained to preserve compatibility with the completed academic implementation.
'@
Set-Content 'docs/SCREENSHOTS.md' $gallery -Encoding utf8

$readmePath = Join-Path $repositoryRoot 'README.md'
$readme = Get-Content $readmePath -Raw
$showcase = @'
## Screenshots and Demo

<p align="center">
  <img src="docs/images/screenshots/crisisops-login.webp" alt="CrisisOps secure login" width="48%">
  <img src="docs/images/screenshots/admin-dashboard.webp" alt="CrisisOps system administrator dashboard" width="48%">
</p>

<p align="center">
  <img src="docs/images/screenshots/ecc-resource-availability.webp" alt="CrisisOps emergency resource availability tracker" width="48%">
  <img src="docs/images/screenshots/public-disaster-reporting.webp" alt="CrisisOps public disaster reporting" width="48%">
</p>

The gallery demonstrates role-based workflows for System Administrators, Emergency Control Centre staff, and Public Users, along with the MySQL schema used by the application.

[View the complete CrisisOps screenshot gallery](docs/SCREENSHOTS.md)

'@

$pattern = '(?s)## Screenshots and Demo\r?\n.*?(?=## Academic Origin and Attribution)'
$updatedReadme = [regex]::Replace($readme, $pattern, $showcase, 1)
if ($updatedReadme -eq $readme) {
    throw 'Could not update the README screenshot section.'
}
Set-Content $readmePath $updatedReadme -Encoding utf8

Remove-Item '.github/screenshot-assets' -Recurse -Force
Remove-Item '.github/workflows/install-portfolio-screenshots.yml' -Force -ErrorAction SilentlyContinue
Remove-Item $tempZip -Force -ErrorAction SilentlyContinue
Remove-Item $tempExtract -Recurse -Force -ErrorAction SilentlyContinue

$installerPath = $PSCommandPath
Remove-Item $installerPath -Force

git add -A
git commit -m 'docs: install actual CrisisOps portfolio screenshots'
git push origin main

Write-Host 'CrisisOps screenshots were installed and pushed successfully.' -ForegroundColor Green
