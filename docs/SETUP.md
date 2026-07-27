# CrisisOps Setup Guide

This guide explains how to run the completed JavaFX client, Java server, and MySQL database locally.

The portfolio repository intentionally preserves the Apache Ant and NetBeans structure used for the academic submission. Some local library paths may therefore need to be adjusted for your computer.

## Requirements

Install the following before opening the project:

- JDK 17
- NetBeans with Java support
- JavaFX SDK 17 or later
- MySQL Server
- MySQL Workbench
- MySQL Connector/J

## 1. Clone the repository

```bash
git clone https://github.com/JeraldBucud/crisisops-disaster-response-system.git
cd crisisops-disaster-response-system
```

The portfolio rebrand has already been merged. Use the default `main` branch unless you are reviewing a separate pull request.

## 2. Prepare the MySQL database

1. Start MySQL Server.
2. Open MySQL Workbench.
3. Open `database/drs_enhanced_setup.sql`.
4. Run the complete script.
5. Confirm that the `drs_enhanced` schema and its tables were created.

The legacy schema name is retained to avoid breaking the existing database and DAO integration while keeping the portfolio copy close to the submitted assessment.

## 3. Configure the database connection

CrisisOps reads the database connection from environment variables or Java system properties. Private MySQL credentials are not stored directly in the Java source code.

### Environment variables

| Setting | Environment variable | Default |
| --- | --- | --- |
| JDBC URL | `CRISISOPS_DB_URL` | Local `drs_enhanced` schema on port `3306` |
| Username | `CRISISOPS_DB_USERNAME` | `root` |
| Password | `CRISISOPS_DB_PASSWORD` | Empty |

PowerShell example:

```powershell
$env:CRISISOPS_DB_USERNAME = "root"
$env:CRISISOPS_DB_PASSWORD = "your-mysql-password"
```

Optional custom URL:

```powershell
$env:CRISISOPS_DB_URL = "jdbc:mysql://localhost:3306/drs_enhanced?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC"
```

The environment variables must be available to the Java process that starts the server. On Windows, they can also be added through **System Properties > Environment Variables** before opening NetBeans.

### Java system properties

The same values can be supplied as Java system properties:

```text
-Dcrisisops.db.username=root
-Dcrisisops.db.password=your-mysql-password
```

The optional URL property is:

```text
-Dcrisisops.db.url=jdbc:mysql://localhost:3306/drs_enhanced?allowPublicKeyRetrieval=true&useSSL=false&serverTimezone=UTC
```

## 4. Configure JavaFX in NetBeans

The project uses Apache Ant and NetBeans project configuration from the original academic build.

1. Open the project folder in NetBeans.
2. Confirm that the project platform uses JDK 17.
3. Add the JavaFX SDK JAR files to the project libraries when NetBeans cannot resolve JavaFX.
4. Confirm that the run configuration includes:

```text
--add-modules javafx.controls,javafx.fxml
```

A common error is:

```text
Module javafx.controls not found
```

This normally means the JavaFX SDK path in the local NetBeans configuration needs to be updated.

## 5. Add MySQL Connector/J

Add MySQL Connector/J to the NetBeans project libraries if it is not already detected.

Without the connector, the server may report:

```text
MySQL JDBC driver not found
```

## 6. Start the server

Run:

```text
drsinitial.server.DRSServer
```

The server should report:

```text
CrisisOps multi-threaded server started on port 5000
```

Keep the server running while using the desktop client.

## 7. Start the JavaFX client

Run:

```text
drsinitial.Main
```

The CrisisOps login window should open.

## 8. Local demonstration accounts

The database seed data includes:

| Role | Username | Password |
| --- | --- | --- |
| System Administrator | `admin` | `admin123` |
| Emergency Control Centre | `ecc` | `ecc123` |
| Public User | `public` | `public123` |

These credentials are retained for local demonstration of the academic project only.

## Recommended demonstration flow

1. Sign in as a public user and submit a disaster report.
2. Sign in as Emergency Control Centre staff.
3. Validate or register the reported incident.
4. Assess severity and assign or review priority.
5. Allocate an emergency resource or shelter.
6. Create or publish a public alert.
7. Search for the incident and update its status.
8. Sign in as the system administrator to review user management.

## Troubleshooting

### Client cannot connect to the server

- Confirm `DRSServer` is running.
- Confirm port `5000` is not blocked or already in use.
- Confirm the client connection is using `localhost:5000`.

### Database connection fails

- Confirm MySQL Server is running.
- Confirm the `drs_enhanced` schema exists.
- Confirm `CRISISOPS_DB_USERNAME` and `CRISISOPS_DB_PASSWORD` match the local MySQL account.
- Restart NetBeans after changing permanent Windows environment variables.
- Confirm MySQL Connector/J is available to the project.

### JavaFX classes or modules cannot be found

- Confirm the project uses JDK 17.
- Update the local JavaFX SDK path in NetBeans.
- Confirm both `javafx.controls` and `javafx.fxml` are included.

## Known setup limitations

- The project retains its original NetBeans and Apache Ant configuration.
- Bundled JavaFX and library files make the repository larger than a modern dependency-managed project.
- Some configuration values are Windows-oriented and may need local adjustment on macOS or Linux.
- A packaged desktop installer and automated build workflow are not included.

## Possible future improvements

A future modernisation branch could:

- Migrate the Ant build to Maven or Gradle
- Remove the bundled JavaFX SDK from repository history
- Add automated database configuration
- Add GitHub Actions for build and test checks
- Produce a packaged desktop release

These changes are intentionally deferred so the portfolio version remains close to the submitted academic project.
