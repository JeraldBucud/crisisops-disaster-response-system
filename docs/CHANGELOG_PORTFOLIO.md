# Portfolio Rebrand Changes

## CrisisOps Portfolio Rebrand

This repository prepares the completed academic project for professional portfolio presentation while preserving its original assessment architecture and development history.

### Documentation

- Replaced the assessment-focused README with a recruiter-facing project overview
- Added a transparent academic origin and attribution statement
- Added a complete local setup guide
- Added a portfolio card and case-study description
- Added a testing and portfolio-review checklist
- Added current CrisisOps interface screenshots to the README showcase
- Added a known academic limitations section so the project is not presented as production-ready
- Corrected outdated branch and screenshot guidance

### Application Branding

- Changed the public-facing application name to **CrisisOps**
- Updated login and registration screens
- Updated JavaFX window titles
- Added runtime branding for legacy dashboard labels
- Updated server and database initialization console messages
- Updated the remaining client connection wording to use the CrisisOps name

### Configuration

- Removed the hard-coded private MySQL password from `DatabaseConnection.java`
- Added support for database environment variables and Java system properties
- Documented the local configuration process

### Minor Code Cleanup

- Removed a duplicated incident-priority request condition from the server request handler
- Preserved the submitted application workflows, database schema, package names, and overall client-server architecture

### Compatibility

The `drsinitial` package name and `drs_enhanced` database schema name remain unchanged. Retaining these legacy technical identifiers avoids breaking FXML controller paths, imports, tests, and database integration while keeping the portfolio copy close to the submitted assessment.

### Intentionally Deferred

- Major authentication or server-authorisation redesign
- Password-storage migration
- Completion or redesign of limited administrative update behaviour
- Removal of assessment-era technical identifiers
- Removal of the bundled JavaFX SDK from Git history
- Maven or Gradle migration
- Automated build workflow
- Packaged desktop release
- Demonstration video

These items are documented as limitations or possible future improvements rather than being introduced into this presentation-focused cleanup.
