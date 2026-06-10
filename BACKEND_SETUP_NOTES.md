# DRS-Enhanced Backend Setup Notes

## Added backend components

This patch adds the backend work needed for Team Member 1:

- `drsinitial.database.DatabaseConnection`
- `drsinitial.database.DatabaseInitializer`
- DAO classes for users, reports, incidents, resources, shelters, alerts, dispatch and audit logs
- `drsinitial.server.DRSServer`
- `drsinitial.server.ClientHandler`
- `drsinitial.security.SecurityService`
- `drsinitial.security.EncryptionService`
- `database/drs_enhanced_setup.sql`
- Updated `ClientConnection` to use sockets on `localhost:5000`
- Updated Login and Public User Registration controllers to call the backend server

## MySQL settings

Default values in `DatabaseConnection.java`:

- Database: `drs_enhanced`
- Username: `root`
- Password: `root123`
- Port: `3306`

Change these if your local MySQL password is different.

## Required library

Add MySQL Connector/J to NetBeans project libraries before running backend.

## How to run

1. Start MySQL.
2. Run `database/drs_enhanced_setup.sql` in MySQL Workbench.
3. In NetBeans, run `drsinitial.server.DRSServer`.
4. Confirm output: `DRS-Enhanced multi-threaded server started on port 5000`.
5. Run the JavaFX app.
6. Login using:
   - `admin / admin123`
   - `ecc / ecc123`
   - `public / public123`

## Screenshot evidence to capture

- MySQL schema and tables.
- `users` table with admin/ecc/public.
- Server running on port 5000.
- Successful login through JavaFX.
- New public user registration saved in MySQL.
- `audit_logs` table showing timestamped actions.
