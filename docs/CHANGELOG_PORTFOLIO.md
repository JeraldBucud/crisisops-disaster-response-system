# Portfolio Rebrand Changes

## CrisisOps Portfolio Rebrand

This branch prepares the completed academic project for professional portfolio presentation.

### Documentation

- Replaced the assessment-focused README with a recruiter-facing project overview
- Added a transparent academic origin and attribution statement
- Added a complete local setup guide
- Added a portfolio card and case-study description
- Added a pre-merge testing and review checklist

### Application Branding

- Changed the application name to **CrisisOps**
- Updated login and registration screens
- Updated JavaFX window titles
- Added runtime branding for legacy dashboard labels
- Updated server and database initialization console messages

### Configuration

- Removed the hard-coded MySQL password from `DatabaseConnection.java`
- Added support for database environment variables and Java system properties
- Documented the new configuration process

### Compatibility

The `drsinitial` package name and `drs_enhanced` database schema name remain unchanged for now. Retaining these legacy technical identifiers avoids breaking FXML controller paths, imports, tests, and database integration during the presentation-focused rebrand.

### Not Included Yet

- Removal of assessment documents
- Removal of the bundled JavaFX SDK from Git history
- Maven or Gradle migration
- Automated build workflow
- Portfolio screenshots and demonstration video

These items require a separate cleanup and verification stage after the rebranded application has been tested locally.
