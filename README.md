# CrisisOps

**Emergency Operations and Disaster Response Management System**

CrisisOps is a JavaFX desktop application for reporting disasters, managing incidents, assessing severity and priority, coordinating emergency resources, publishing public alerts and tracking evacuation shelters.

The project began as my individual work for **COIT20258: Software Engineering** and was later selected as the foundation for the final group assessment. The collaborative stage expanded the original JavaFX prototype into a MySQL-backed client-server application.

**Case study:** https://jeraldbucud.com/crisisops-case-study.html

## Project highlights

- Disaster reporting and incident registration
- Severity assessment and priority recommendations
- Incident status tracking, search and filtering
- Emergency resource availability management
- Emergency dispatch and response logging
- Public alert creation and publication
- Evacuation shelter availability management
- Public registration and authentication interfaces
- Role-aware JavaFX dashboards for public users, Emergency Control Centre staff and system administrators
- Administrative user-management interface
- MySQL persistence through DAO classes
- Multi-threaded socket communication
- JUnit tests for selected application components

## System showcase

<p align="center">
  <img src="docs/images/screenshots/crisis%20ops%20login%20page.png" alt="CrisisOps login screen" width="48%">
  <img src="docs/images/screenshots/ECC/Crisis%20Ops%20ECC%20Dashboard.png" alt="CrisisOps Emergency Control Centre dashboard" width="48%">
</p>

<p align="center">
  <img src="docs/images/screenshots/ECC/Crisis%20Ops%20ECC%20Resource%20Availability.png" alt="CrisisOps emergency resource availability screen" width="48%">
  <img src="docs/images/screenshots/System%20admin/Crisis%20Ops%20Admin%20User%20Management.png" alt="CrisisOps administrator user-management screen" width="48%">
</p>

<p align="center">
  <img src="docs/images/screenshots/public/Crisis%20Ops%20Public%20Report%20Disaster.png" alt="CrisisOps public disaster-reporting screen" width="48%">
  <img src="docs/images/screenshots/public/Crisis%20Ops%20Public%20Public%20Alerts.png" alt="CrisisOps public alerts screen" width="48%">
</p>

The screenshots use local demonstration data and show the completed academic application with its current CrisisOps interface branding.

## My contribution

**Original system developer and JavaFX integration contributor**

I completed the original requirements, use cases, sequence flows, MVC direction and working JavaFX prototype during the first two individual assessment stages. When the system was selected for the final group assessment, I continued contributing to JavaFX interfaces and client integration.

My work includes:

- Original application structure and JavaFX interface
- Disaster reporting and incident-management workflows
- Severity assessment and priority-recommendation behaviour
- Emergency resource availability tracking
- Incident search and filtering
- Login and public-user registration interfaces
- Role-aware dashboard navigation
- Public alert and evacuation-shelter interfaces
- Administrator user-management interface
- Frontend request components
- Selected frontend-to-server integration
- Repository setup, branding and documentation updates

The final server, database, DAO, testing and broader integration work includes collaborative contributions. The original Git history is retained so team contributions remain attributable.

## Project evolution

| Stage | Development type | Outcome |
| --- | --- | --- |
| Assessment 1 | Individual | Requirements, use cases, sequence diagrams, MVC architecture and preliminary interface designs |
| Assessment 2 | Individual | Working JavaFX prototype, resource availability tracker, incident search and filtering, and JUnit testing |
| Final group assessment | Collaborative | MySQL-backed client-server application with authentication interfaces, role-aware workflows, alerts, shelters and user management |

<p align="center">
  <img src="docs/images/project-evolution/assessment-1-context-model.webp" alt="Original Assessment 1 context model" width="48%">
  <img src="docs/images/project-evolution/assessment-2-resource-availability-tracker.webp" alt="Assessment 2 emergency resource availability tracker" width="48%">
</p>

These images retain the original **DRS** and **DRS-Initial** branding because they document the individual assessment stages.

[View the complete project evolution and evidence](docs/PROJECT_EVOLUTION.md)

## Technology stack

| Area | Technologies |
| --- | --- |
| Language | Java 17 |
| Desktop UI | JavaFX, FXML, CSS |
| Architecture | MVC-style client, socket-based client-server communication |
| Concurrency | One client-handler thread per accepted connection |
| Database | MySQL, JDBC, DAO pattern |
| Testing | JUnit 4 |
| Build and IDE | Apache Ant, NetBeans, Scene Builder |
| Collaboration | Git, GitHub branches, pull requests and code review |

## System roles

The JavaFX client stores the role returned after login and changes the visible dashboard functions for the following users:

| Role | Main capabilities |
| --- | --- |
| Public User | Register, sign in, report disasters and view published alerts |
| Emergency Control Centre | Manage incidents, severity, priority, dispatch, resources, shelters and alerts |
| System Administrator | Access emergency-management functions and manage system users |

Role separation is implemented primarily in the JavaFX client. The socket server does not currently issue session tokens or enforce role checks for every request.

## Architecture overview

CrisisOps uses a JavaFX desktop client connected to a multi-threaded Java server over sockets. The client and server exchange serialised requests and responses through object streams. Server request handlers call DAO classes that access MySQL.

```text
JavaFX Client
     |
     | Serialised socket requests and responses
     v
Multi-threaded Java Server
     |
     | Request routing and DAO calls
     v
MySQL Database
```

## Project structure

```text
database/              MySQL schema and seed data
lib/                   Libraries used by the original NetBeans build
src/drsinitial/        Java source code and JavaFX resources
test/drsinitial/       JUnit tests
nbproject/             NetBeans project configuration
build.xml              Apache Ant build file
docs/                  Setup, attribution and project-evolution documents
```

The legacy Java package name `drsinitial` and database schema name `drs_enhanced` are retained to avoid breaking FXML controller paths, imports, tests and database integration.

## Getting started

### Requirements

- JDK 17
- NetBeans with Java support
- JavaFX SDK 17 or later
- MySQL Server and MySQL Workbench
- MySQL Connector/J

### Run the application

1. Clone this repository.
2. Open the project in NetBeans.
3. Run `database/drs_enhanced_setup.sql` in MySQL Workbench.
4. Set `CRISISOPS_DB_USERNAME` and `CRISISOPS_DB_PASSWORD` for your local MySQL account.
5. Add JavaFX and MySQL Connector/J to the project libraries when required by the local NetBeans setup.
6. Run `drsinitial.server.DRSServer`.
7. Confirm that the server is listening on port `5000`.
8. Run `drsinitial.Main` to open the JavaFX client.

Detailed instructions are available in [docs/SETUP.md](docs/SETUP.md).

## Demonstration accounts

The included seed data provides local demonstration accounts:

| Role | Username | Password |
| --- | --- | --- |
| System Administrator | `admin` | `admin123` |
| Emergency Control Centre | `ecc` | `ecc123` |
| Public User | `public` | `public123` |

These credentials are retained only for local demonstration and must not be reused in a production environment.

## Testing evidence

A full local JUnit 4 run of the current CrisisOps repository completed with **40 tests passing**.

![CrisisOps JUnit run showing 40 passing tests](docs/images/screenshots/Crisisops_40test_Passed.webp)

The current suite covers selected domain models, service rules, validation behaviour, user-session state and the in-memory application repository. The screenshot records a local project test run; no automated continuous-integration workflow is currently configured.

The historical 28-test result for the individual Assessment 2 prototype remains documented in [docs/PROJECT_EVOLUTION.md](docs/PROJECT_EVOLUTION.md) as project-history evidence rather than a current result.

## Current constraints

- The socket protocol has no server-issued session token or per-request role authorisation.
- Local client-server traffic does not use TLS.
- Demonstration credentials and password compatibility remain from the academic build.
- Server-side user-account update handling is incomplete.
- The Ant and NetBeans project requires local JavaFX and MySQL configuration.
- No packaged desktop release or automated CI workflow is included.

## Next engineering steps

- Migrate the build to Maven or Gradle.
- Add GitHub Actions for automated build and test verification.
- Introduce authenticated sessions, request-level authorisation and encrypted communication.
- Complete account-update behaviour and add integration tests around server requests.
- Package the JavaFX client for simpler installation.

## Academic origin and attribution

CrisisOps originated from my individual assessment stages in **COIT20258: Software Engineering**. The original system was selected as the foundation for the final group assessment and was expanded collaboratively.

- [Project Evolution and Evidence](docs/PROJECT_EVOLUTION.md)
- [Academic Origin and Attribution](docs/ACADEMIC_ORIGIN.md)

The original academic repository is preserved at:

https://github.com/JeraldBucud/DisasterResponseSystem

## Project status

The completed academic version runs as a JavaFX client, Java server and MySQL application. The repository retains the submitted architecture and contributor history while providing external database configuration, setup instructions, screenshots and documented constraints.

## Author

**Jerald Christopher Bucud**  
Master of Information Technology candidate majoring in Software Design and Development, with a minor in Artificial Intelligence.
