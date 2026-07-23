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

CrisisOps reads database settings from environment variables or Java system properties.

Environment variables:

- `CRISISOPS_DB_URL`
- `CRISISOPS_DB_USERNAME`
- `CRISISOPS_DB_PASSWORD`

Java system properties:

- `crisisops.db.url`
- `crisisops.db.username`
- `crisisops.db.password`

Defaults:

- Schema: `drs_enhanced`
- MySQL port: `3306`
- Username: `root`
- Password: empty
- Server port: `5000`

PowerShell example:

```powershell
$env:CRISISOPS_DB_USERNAME = "root"
$env:CRISISOPS_DB_PASSWORD = "your-mysql-password"
```

## Required Library

Add MySQL Connector/J to the NetBeans project libraries before running the backend.

## How to Run

1. Start MySQL Server.
2. Run `database/drs_enhanced_setup.sql` in MySQL Workbench.
3. Configure the local database environment variables or Java system properties.
4. In NetBeans, run `drsinitial.server.DRSServer`.
5. Confirm that the CrisisOps server has started on port `5000`.
6. Run `drsinitial.Main` to open the JavaFX client.

## Local Demonstration Accounts

- System Administrator: `admin / admin123`
- Emergency Control Centre: `ecc / ecc123`
- Public User: `public / public123`

These accounts are included for local demonstration only.

For fuller instructions, see [`docs/SETUP.md`](docs/SETUP.md).
