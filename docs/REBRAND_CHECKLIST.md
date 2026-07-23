# CrisisOps Rebrand Review Checklist

Use this checklist before merging the `portfolio-rebrand` branch into `main`.

## Branding

- [ ] Login screen displays **CrisisOps**
- [ ] Registration screen displays **CrisisOps**
- [ ] Dashboard title and sidebar branding display **CrisisOps**
- [ ] Application window titles display **CrisisOps**
- [ ] Server console output displays **CrisisOps**
- [ ] Database initialization output displays **CrisisOps**

## Functional Verification

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

- [ ] Academic origin and collaboration are clearly disclosed
- [ ] Personal contributions are accurately described
- [ ] Student ID is not shown in portfolio-facing documentation
- [ ] No private credentials are stored in source code
- [ ] Screenshots use realistic demonstration data
- [ ] README screenshots are added
- [ ] Demonstration video link is added

## Future Cleanup

- [ ] Review assessment-only documents before removing them
- [ ] Remove the bundled JavaFX SDK from Git history
- [ ] Migrate the Ant project to Maven or Gradle
- [ ] Add an automated build and test workflow
- [ ] Create a packaged desktop release
