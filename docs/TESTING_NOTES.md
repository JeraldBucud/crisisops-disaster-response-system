# Local Testing Notes

The GitHub rebrand changes were prepared without access to the user's local NetBeans, JavaFX, or MySQL environment. The branch must be tested locally before it is merged into `main`.

Use [`REBRAND_CHECKLIST.md`](REBRAND_CHECKLIST.md) to record the results.

Recommended first verification:

1. Pull the `portfolio-rebrand` branch.
2. Set the CrisisOps database environment variables.
3. Clean and build the project with JDK 17.
4. Start MySQL and import the schema.
5. Run `drsinitial.server.DRSServer`.
6. Run `drsinitial.Main`.
7. Confirm that login, registration, dashboard, logout, and all major workflows still operate correctly.
