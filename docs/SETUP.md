# CrisisOps Setup Guide

This guide explains how to run the completed JavaFX client, Java server, and MySQL database locally.

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

For portfolio development, use the `portfolio-rebrand` branch until the rebrand pull request is merged.

## 2. Prepare the MySQL database

1. Start MySQL Server.
2. Open MySQL Workbench.
3. Open `database/drs_enhanced_setup.sql`.
4. Run the complete script.
5. Confirm that the `drs_enhanced` schema and its tables were created.

The legacy schema name is retained during the portfolio rebrand to avoid breaking the existing database and DAO integration.

## 3. Configure the database connection

Open:

```text
src/drsinitial/database/DatabaseConnection.java
```

Review the following local settings:

- MySQL host and port
- Schema name
- MySQL username
- MySQL password

Update them to match your own MySQL installation before starting the server.

The current connection class is intended for local demonstration and development. A future portfolio improvement will move credentials into environment variables or an ignored local configuration file.

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

The server should start on:

```text
localhost:5000
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

These credentials are for local demonstration only.

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
- Confirm the local username and password in `DatabaseConnection.java` are correct.
- Confirm MySQL Connector/J is available to the project.

### JavaFX classes or modules cannot be found

- Confirm the project uses JDK 17.
- Update the local JavaFX SDK path in NetBeans.
- Confirm both `javafx.controls` and `javafx.fxml` are included.

## Planned setup improvements

The portfolio version can later be improved by:

- Migrating the Ant build to Maven or Gradle
- Removing the bundled JavaFX SDK from repository history
- Moving database credentials out of source code
- Adding automated database configuration
- Adding GitHub Actions for build and test checks
- Producing a packaged desktop release
