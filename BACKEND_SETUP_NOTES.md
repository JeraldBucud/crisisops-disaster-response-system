# CrisisOps Backend Setup Notes

This document summarises the backend components and the local steps required to run the completed CrisisOps application.

## Backend Components

- `drsinitial.database.DatabaseConnection`
- `drsinitial.database.DatabaseInitializer`
- DAO classes for users, reports, incidents, resources, shelters, alerts, dispatch, and audit logs
- `drsinitial.server.DRSServer`
- `drsinitial.server.ClientHandler`
- `drsinitial.security.SecurityService`
- `drsinitial.security.EncryptionService`
- `database/drs_enhanced_setup.sql`
- Socket communication through `localhost:5000`
- Backend-connected login and public-user registration workflows

The legacy Java package and database names are retained during the portfolio rebrand to avoid breaking existing imports, FXML controller paths, tests, and database integration.

## MySQL Configuration

The connection values are currently defined in:

```text
src/drsinitial/database/DatabaseConnection.java
```

Before running the server, update the local database username and password to match your MySQL installation.

Default application expectations:

- Schema: `drs_enhanced`
- MySQL port: `3306`
- Server port: `5000`

The database configuration is intended for local development and demonstration. It should be moved to environment variables or an ignored configuration file before production use.

## Required Library

Add MySQL Connector/J to the NetBeans project libraries before running the backend.

## How to Run

1. Start MySQL Server.
2. Run `database/drs_enhanced_setup.sql` in MySQL Workbench.
3. Update the local values in `DatabaseConnection.java` when required.
4. In NetBeans, run `drsinitial.server.DRSServer`.
5. Confirm that the CrisisOps server has started on port `5000`.
6. Run `drsinitial.Main` to open the JavaFX client.

## Local Demonstration Accounts

- System Administrator: `admin / admin123`
- Emergency Control Centre: `ecc / ecc123`
- Public User: `public / public123`

These accounts are included for local demonstration only.

For fuller instructions, see [`docs/SETUP.md`](docs/SETUP.md).
