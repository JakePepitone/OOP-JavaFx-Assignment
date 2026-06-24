# FSC CSC325 - Full Stack Project

A JavaFX desktop UI replicating the provided design: a menu bar (File, Edit,
Theme, Help), a left-hand profile picture panel, a center `TableView` of
student records, a right-hand entry form, and a bottom status bar.

## Tech Stack

- Java 17
- JavaFX 21 (Controls, FXML)
- Maven

## Project Structure

```
src/main/java/com/fsc/csc325/
    MainApp.java     # Application entry point, builds the full UI in Java code
    Student.java     # JavaFX bean / table row model
src/main/resources/com/fsc/csc325/
    style.css         # Stylesheet matching the design's colors and layout
pom.xml
```

## Running the Project

### Option 1: Maven (recommended)

```bash
mvn clean javafx:run
```

### Option 2: Manual compile/run with the JavaFX SDK

```bash
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls \
  -d out src/main/java/com/fsc/csc325/*.java

java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls \
  -cp out:src/main/resources com.fsc.csc325.MainApp
```

## Features Implemented

- `MenuBar` with `Menu`/`MenuItem`s for File, Edit, Theme, and Help
- `TableView` with `TableColumn`s for ID, First Name, Last Name, Department,
  Major, and Email, including the design's "No content in table" placeholder
- Right-hand form (`TextField`s for First Name, Last Name, Department, Major,
  Email, imageURL) and four action buttons: Clear, Add, Delete, Edit
- Working logic:
  - **Add** appends a new `Student` row from the form fields to the table
  - **Delete** removes the currently selected row
  - **Edit** writes the form fields back onto the selected row
  - **Clear** resets the form and table selection
  - Selecting a table row populates the form fields for editing
- CSS styling matching the mock's color scheme (brown menu bar, tan profile
  sidebar, gray table header, bright green form sidebar, brown buttons)

## Notes

This was built to visually replicate an instructor-provided design mock for
CSC 325. Sample/seed data was intentionally left empty to match the "No
content in table" placeholder state shown in the design; add rows via the
form at runtime to test functionality.
# OOP-JavaFx-Assignment
