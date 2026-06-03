# DisasterResponseSystem

COIT20258 Software Engineering
Disaster Response System

## Project Overview

DisasterResponseSystem is a JavaFX desktop application created for COIT20258 Software Engineering.

This project was developed as DRS-Initial for Assessment 2 and will be used as the base system for Assessment 3 group enhancement.

The enhanced version will improve the design, functionality, testing, and maintainability of the system.

## Purpose

The system supports disaster response activities by allowing users and emergency staff to manage disaster reports, incidents, response agencies, emergency resources, and incident updates.

## Main Features

* Report different types of disasters
* Register and manage incidents
* Assess disaster severity
* Recommend incident priority
* Coordinate emergency response agencies
* Track emergency resource availability
* Search and filter incident records
* Update incident status
* View response and incident information

## Creative Features

The following creative features were added in DRS-Initial:

1. Emergency Resource Availability Tracker
2. Incident Priority Recommendation System
3. Incident Search and Filter

## Technology Used

* Java
* JavaFX
* FXML
* Scene Builder
* NetBeans
* Ant
* JUnit

## Project Structure

* src: Java source code and FXML files
* test: JUnit test files
* nbproject: NetBeans project configuration
* build.xml: Ant build file
* manifest.mf: project manifest
* README.md: project information
* .gitignore: ignored generated files

## Requirements

Use the following tools to run the project:

* JDK 17
* NetBeans
* JavaFX SDK 11 or later
* Scene Builder 11 or later
* JUnit library for testing

## How to Run the Project

1. Clone the repository.
2. Open the project in NetBeans.
3. Check the Java platform is set to JDK 17.
4. Check JavaFX libraries are configured.
5. Clean and build the project.
6. Run the main application class.

## JavaFX Setup Note

If the project does not run and shows a JavaFX error, check the JavaFX library path in NetBeans.

Common error:

Module javafx.controls not found

To fix this, add the JavaFX SDK jar files to the project libraries or module path.

## Group Workflow

The main branch stores the stable project version.

Each group member should create a separate feature branch before making changes.

Example branch names:

* feature/incident-management
* feature/resource-tracker
* feature/search-filter
* feature/testing
* feature/ui-improvements

After finishing a task, the member should push the branch and open a pull request.

The group should review changes before merging into main.

## Suggested Team Task Division

Member 1:
Incident reporting, validation, and user interface improvements.

Member 2:
Priority recommendation, incident assessment, and search/filter improvements.

Member 3:
Resource availability tracker, response coordination, and JUnit testing.

## Author

Jerald Christopher Bucud
Student ID: 12301099
COIT20258 Software Engineering

## Status

Initial Assessment 2 prototype uploaded for Assessment 3 group enhancement.
