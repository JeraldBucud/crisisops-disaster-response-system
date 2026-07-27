# CrisisOps Portfolio Review Checklist

Use this checklist when reviewing future portfolio updates. Repository-presentation items can be checked from GitHub, while runtime items should be confirmed manually in the local JavaFX, server, and MySQL environment.

## Branding Verification

- [ ] Login screen displays **CrisisOps**
- [ ] Registration screen displays **CrisisOps**
- [ ] Dashboard title and sidebar branding display **CrisisOps**
- [ ] Application window titles display **CrisisOps**
- [ ] Server console output displays **CrisisOps**
- [ ] Database initialization output displays **CrisisOps**

## Local Functional Verification

- [ ] Project cleans and builds with JDK 17
- [ ] MySQL schema imports successfully
- [ ] Database environment variables are detected
- [ ] Server starts on port `5000`
- [ ] JavaFX client opens successfully
- [ ] Administrator login works
- [ ] Emergency Control Centre login works
- [ ] Public user login works
- [ ] Public user registration works
- [ ] Disaster reporting works
- [ ] Incident registration works
- [ ] Severity and priority workflow works
- [ ] Resource and shelter workflows work
- [ ] Public alert workflow works
- [ ] Logout returns to the CrisisOps login screen

## Portfolio Presentation

- [x] Academic origin and collaboration are clearly disclosed
- [x] Personal contributions are accurately described
- [x] Student ID is not shown in portfolio-facing documentation
- [x] Private MySQL credentials are not stored in Java source code
- [x] Screenshots use local demonstration data
- [x] Current CrisisOps screenshots are displayed in the README
- [x] Known academic and technical limitations are documented
- [x] Setup instructions refer to the current default branch
- [ ] Demonstration video link is added

## Intentionally Preserved

- [x] Original Git and contributor history
- [x] Legacy `drsinitial` Java package name
- [x] Legacy `drs_enhanced` database schema name
- [x] Original Apache Ant and NetBeans project structure
- [x] Assessment-era architecture and core workflows

## Possible Future Modernisation

- [ ] Review assessment-only documents before removing them
- [ ] Remove the bundled JavaFX SDK from Git history
- [ ] Migrate the Ant project to Maven or Gradle
- [ ] Add an automated build and test workflow
- [ ] Improve password storage and backend authorisation in a separate modernisation branch
- [ ] Create a packaged desktop release

Future modernisation should remain separate from presentation-focused updates so the portfolio repository continues to represent the submitted academic project accurately.
