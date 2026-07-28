# CrisisOps

**Emergency Operations and Disaster Response Management System**

CrisisOps is a JavaFX desktop application for reporting disasters, registering incidents, assessing severity and priority, coordinating emergency resources, publishing public alerts and tracking evacuation shelters.

The project began as my individual work for **COIT20258: Software Engineering**. It was selected as the foundation for the final group assessment and expanded collaboratively into a MySQL-backed client-server application.

## Main capabilities

- Public user registration and login
- Disaster reporting and incident registration
- Severity assessment and priority recommendations
- Incident status updates, search and filtering
- Emergency-resource availability tracking
- Resource dispatch and response logging
- Public alert creation and publication
- Evacuation-shelter management
- Role-aware JavaFX navigation for public, Emergency Control Centre and administrator users
- Administrator user-management interface
- MySQL persistence through JDBC and DAO classes
- Multi-threaded socket communication
- JUnit tests for selected models, services, validation rules, session state and repository behaviour

## Application preview

<p align="center">
  <img src="docs/images/screenshots/crisis%20ops%20login%20page.png" alt="CrisisOps login screen" width="48%">
  <img src="docs/images/screenshots/ECC/Crisis%20Ops%20ECC%20Dashboard.png" alt="CrisisOps Emergency Control Centre dashboard" width="48%">
</p>

<p align="center">
  <img src="docs/images/screenshots/ECC/Crisis%20Ops%20ECC%20Resource%20Availability.png" alt="CrisisOps emergency-resource availability screen" width="48%">
  <img src="docs/images/screenshots/System%20admin/Crisis%20Ops%20Admin%20User%20Management.png" alt="CrisisOps administrator user-management screen" width="48%">
</p>

<p align="center">
  <img src="docs/images/screenshots/public/Crisis%20Ops%20Public%20Report%20Disaster.png" alt="CrisisOps public disaster-reporting screen" width="48%">
  <img src="docs/images/screenshots/public/Crisis%20Ops%20Public%20Public%20Alerts.png" alt="CrisisOps public-alert screen" width="48%">
</p>

The screenshots use local demonstration data.

## Project stages

| Stage | Development type | Outcome |
| --- | --- | --- |
| Assessment 1 | Individual | Requirements, system scope, use cases, sequence diagrams, MVC direction and interface designs |
| Assessment 2 | Individual | Working JavaFX prototype, report-to-response workflow, resource tracking, search, filtering and JUnit testing |
| Final group assessment | Collaborative | Socket server, MySQL persistence, authentication interfaces, role-aware workflows, alerts, shelters and user administration |

The historical Assessment 1 and Assessment 2 images retain the original **DRS** and **DRS-Initial** branding.

[View the project evolution and evidence](docs/PROJECT_EVOLUTION.md)

## My contribution

**Original System Developer and JavaFX Integration Contributor**

I completed the original analysis, design and JavaFX prototype individually. My work included:

- System boundary, actors, requirements and use cases
- Use-case and sequence diagrams
- MVC-style application direction
- Disaster-reporting and incident-management workflows
- Severity assessment and priority-recommendation behaviour
- Emergency-resource availability tracking
- Incident search and filtering
- JUnit tests for the individual prototype

During the collaborative stage, I contributed to:

- Login and public-registration interfaces
- Role-aware JavaFX navigation
- Public-alert and evacuation-shelter interfaces
- Administrator user-management screens
- Frontend request components
- JavaFX-to-server integration for selected workflows
- Interface consistency, setup documentation and repository preparation

The final server, database, DAO, testing and integration work includes contributions from the project team. The original commit history remains available.

## Architecture

```text
JavaFX Client
     |
     | Serialised socket requests and responses
     v
Multi-threaded Java Server
     |
     | Request routing, application logic and DAO calls
     v
MySQL Database
```

The server creates a client-handler thread for each accepted connection. DAO classes separate SQL operations from the JavaFX controllers and request-handling code.

### Access model

After login, the JavaFX client stores the returned user role and shows or hides dashboard functions for that role.

This is a client-side academic access model. The socket server does not issue session tokens or enforce the user's role for every request.

## Technology stack

| Area | Technologies |
| --- | --- |
| Language | Java 17 |
| Desktop interface | JavaFX, FXML, CSS |
| Communication | Java sockets, object streams, multi-threading |
| Database access | MySQL, JDBC, DAO pattern |
| Testing | JUnit 4 |
| Build and IDE | Apache Ant, NetBeans, Scene Builder |
| Collaboration | Git, GitHub branches, pull requests and review |

## Project structure

```text
database/              MySQL schema and seed data
lib/                   Libraries used by the NetBeans build
src/drsinitial/        Java source code and JavaFX resources
test/drsinitial/       JUnit tests
nbproject/             NetBeans project configuration
build.xml              Apache Ant build file
docs/                  Setup, screenshots and project history
```

The legacy package name `drsinitial` and schema name `drs_enhanced` remain because changing them would affect FXML controller paths, imports, tests and database integration.

## Local setup

### Requirements

- JDK 17
- NetBeans with Java support
- JavaFX SDK 17 or later
- MySQL Server and MySQL Workbench
- MySQL Connector/J

### Run the application

1. Clone the repository.
2. Open the project in NetBeans.
3. Run `database/drs_enhanced_setup.sql` in MySQL Workbench.
4. Set `CRISISOPS_DB_USERNAME` and `CRISISOPS_DB_PASSWORD` for the local MySQL account.
5. Add JavaFX and MySQL Connector/J to the project libraries when required.
6. Run `drsinitial.server.DRSServer`.
7. Confirm that the server is listening on port `5000`.
8. Run `drsinitial.Main`.

Detailed instructions are available in [docs/SETUP.md](docs/SETUP.md).

## Demonstration accounts

| Role | Username | Password |
| --- | --- | --- |
| System Administrator | `admin` | `admin123` |
| Emergency Control Centre | `ecc` | `ecc123` |
| Public User | `public` | `public123` |

These accounts are provided only for local demonstration and must not be reused elsewhere.

## Current constraints

- The socket protocol has no server-issued session token or per-request role authorisation.
- Local client-server traffic does not use TLS.
- Demonstration accounts and password compatibility remain from the academic build.
- Server-side user-account update handling is not implemented.
- The Ant and NetBeans project requires local JavaFX and MySQL configuration.
- No packaged desktop release or automated CI workflow is included.

## Next engineering steps

- Migrate the build to Maven or Gradle.
- Add automated build and test verification through GitHub Actions.
- Introduce authenticated sessions, request-level authorisation and encrypted communication.
- Complete account-update behaviour and add server integration tests.
- Package the JavaFX application for simpler installation.

## Academic origin and attribution

CrisisOps originated from the individual assessment stages of **COIT20258: Software Engineering** and was expanded collaboratively in the final group assessment.

- [Project Evolution and Evidence](docs/PROJECT_EVOLUTION.md)
- [Academic Origin and Attribution](docs/ACADEMIC_ORIGIN.md)

The original academic repository is preserved at:

https://github.com/JeraldBucud/DisasterResponseSystem

## Author

**Jerald Christopher Bucud**  
Master of Information Technology candidate majoring in Software Design and Development, with a minor in Artificial Intelligence.
