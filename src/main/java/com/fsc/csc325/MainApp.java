package com.fsc.csc325;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FSC CSC325 - Full Stack Project
 * <p>
 * Main application window: menu bar, left profile sidebar, center TableView,
 * right-hand input form with action buttons, and a bottom status bar.
 */
public class MainApp extends Application {

    private final ObservableList<Student> students = FXCollections.observableArrayList();

    private TableView<Student> tableView;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField departmentField;
    private TextField majorField;
    private TextField emailField;
    private TextField imageUrlField;
    private Label statusLabel;
    private ImageView profileImageView;

    private int nextId = 1;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setId("root-pane");

        root.setTop(buildMenuBar());
        root.setLeft(buildProfileSidebar());
        root.setCenter(buildTableView());
        root.setRight(buildFormSidebar());
        root.setBottom(buildStatusBar());

        Scene scene = new Scene(root, 1502, 1032);
        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        primaryStage.setTitle("FSC CSC325 _ Full Stack Project");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // ---------------------------------------------------------------
    // Menu Bar
    // ---------------------------------------------------------------
    private MenuBar buildMenuBar() {
        Menu fileMenu = new Menu("File");
        MenuItem newItem = new MenuItem("New");
        MenuItem saveItem = new MenuItem("Save");
        MenuItem exitItem = new MenuItem("Exit");
        exitItem.setOnAction(e -> System.exit(0));
        fileMenu.getItems().addAll(newItem, saveItem, new SeparatorMenuItem(), exitItem);

        Menu editMenu = new Menu("Edit");
        MenuItem cutItem = new MenuItem("Cut");
        MenuItem copyItem = new MenuItem("Copy");
        MenuItem pasteItem = new MenuItem("Paste");
        editMenu.getItems().addAll(cutItem, copyItem, pasteItem);

        Menu themeMenu = new Menu("Theme");
        MenuItem lightTheme = new MenuItem("Light");
        MenuItem darkTheme = new MenuItem("Dark");
        themeMenu.getItems().addAll(lightTheme, darkTheme);

        Menu helpMenu = new Menu("Help");
        MenuItem aboutItem = new MenuItem("About");
        aboutItem.setOnAction(e -> showAboutDialog());
        helpMenu.getItems().add(aboutItem);

        MenuBar menuBar = new MenuBar(fileMenu, editMenu, themeMenu, helpMenu);
        menuBar.setId("main-menu-bar");
        return menuBar;
    }

    private void showAboutDialog() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("FSC CSC325 - Full Stack Project");
        alert.setContentText("Student record management UI built with JavaFX.");
        alert.showAndWait();
    }

    // ---------------------------------------------------------------
    // Left sidebar - profile image
    // ---------------------------------------------------------------
    private VBox buildProfileSidebar() {
        VBox sidebar = new VBox();
        sidebar.setId("profile-sidebar");
        sidebar.setPrefWidth(210);
        sidebar.setAlignment(Pos.TOP_CENTER);
        sidebar.setPadding(new Insets(20, 0, 0, 0));

        StackPaneFrame frame = new StackPaneFrame();
        profileImageView = frame.getImageView();

        sidebar.getChildren().add(frame.getContainer());
        VBox.setVgrow(sidebar, Priority.ALWAYS);
        return sidebar;
    }

    /**
     * Small helper that builds the circular silhouette / framed profile picture
     * to match the design (white square frame containing a circular icon).
     */
    private static class StackPaneFrame {
        private final StackPane container = new StackPane();
        private final ImageView imageView = new ImageView();

        StackPaneFrame() {
            container.setId("profile-frame");
            container.setPrefSize(190, 190);

            javafx.scene.shape.Circle circleBg = new javafx.scene.shape.Circle(75);
            circleBg.setId("profile-circle");

            javafx.scene.shape.Circle head = new javafx.scene.shape.Circle(22);
            head.setId("profile-icon");
            head.setTranslateY(-28);

            javafx.scene.shape.Ellipse body = new javafx.scene.shape.Ellipse(40, 32);
            body.setId("profile-icon");
            body.setTranslateY(40);

            javafx.scene.Group personGroup = new javafx.scene.Group(circleBg, body, head);
            javafx.scene.shape.Rectangle clip = new javafx.scene.shape.Rectangle(190, 190);
            personGroup.setClip(clip);

            container.getChildren().add(personGroup);
        }

        StackPane getContainer() {
            return container;
        }

        ImageView getImageView() {
            return imageView;
        }
    }

    // ---------------------------------------------------------------
    // Center - TableView
    // ---------------------------------------------------------------
    private VBox buildTableView() {
        tableView = new TableView<>(students);
        tableView.setId("student-table");
        tableView.setPlaceholder(new Label("No content in table"));
        tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Student, Integer> idCol = new TableColumn<>("ID");
        idCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("id"));
        idCol.setPrefWidth(50);

        TableColumn<Student, String> firstNameCol = new TableColumn<>("First Name");
        firstNameCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("firstName"));

        TableColumn<Student, String> lastNameCol = new TableColumn<>("Last Name");
        lastNameCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("lastName"));

        TableColumn<Student, String> departmentCol = new TableColumn<>("Department");
        departmentCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("department"));

        TableColumn<Student, String> majorCol = new TableColumn<>("Major");
        majorCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("major"));

        TableColumn<Student, String> emailCol = new TableColumn<>("Email");
        emailCol.setCellValueFactory(new javafx.scene.control.cell.PropertyValueFactory<>("email"));

        tableView.getColumns().addAll(idCol, firstNameCol, lastNameCol, departmentCol, majorCol, emailCol);

        // Selecting a row populates the form fields (supports Edit/Delete workflow)
        tableView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                firstNameField.setText(newVal.getFirstName());
                lastNameField.setText(newVal.getLastName());
                departmentField.setText(newVal.getDepartment());
                majorField.setText(newVal.getMajor());
                emailField.setText(newVal.getEmail());
                imageUrlField.setText(newVal.getImageUrl());
            }
        });

        VBox container = new VBox(tableView);
        VBox.setVgrow(tableView, Priority.ALWAYS);
        container.setId("table-container");
        return container;
    }

    // ---------------------------------------------------------------
    // Right sidebar - form fields + action buttons
    // ---------------------------------------------------------------
    private VBox buildFormSidebar() {
        VBox sidebar = new VBox(12);
        sidebar.setId("form-sidebar");
        sidebar.setPrefWidth(340);
        sidebar.setPadding(new Insets(20, 20, 20, 20));

        firstNameField = new TextField();
        firstNameField.setPromptText("First Name");
        firstNameField.setId("first-name-field");
        firstNameField.getStyleClass().add("form-field");
        firstNameField.getStyleClass().add("focused-field");

        lastNameField = new TextField();
        lastNameField.setPromptText("Last Name");
        lastNameField.getStyleClass().add("form-field");

        departmentField = new TextField();
        departmentField.setPromptText("Department");
        departmentField.getStyleClass().add("form-field");

        majorField = new TextField();
        majorField.setPromptText("Major");
        majorField.getStyleClass().add("form-field");

        emailField = new TextField();
        emailField.setPromptText("Email");
        emailField.getStyleClass().add("form-field");

        imageUrlField = new TextField();
        imageUrlField.setPromptText("imageURL");
        imageUrlField.getStyleClass().add("form-field");

        VBox fieldsBox = new VBox(12, firstNameField, lastNameField, departmentField, majorField, emailField, imageUrlField);

        // Spacer pushes the buttons to the bottom half of the green panel, matching the design
        javafx.scene.layout.Region spacer = new javafx.scene.layout.Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        Button clearButton = new Button("Clear");
        clearButton.getStyleClass().add("action-button");
        clearButton.setOnAction(e -> clearForm());

        Button addButton = new Button("Add");
        addButton.getStyleClass().add("action-button");
        addButton.setOnAction(e -> addStudent());

        Button deleteButton = new Button("Delete");
        deleteButton.getStyleClass().add("action-button");
        deleteButton.setOnAction(e -> deleteSelectedStudent());

        Button editButton = new Button("Edit");
        editButton.getStyleClass().add("action-button");
        editButton.setOnAction(e -> editSelectedStudent());

        for (Button b : new Button[]{clearButton, addButton, deleteButton, editButton}) {
            b.setMaxWidth(Double.MAX_VALUE);
            b.setPrefHeight(60);
        }

        VBox buttonsBox = new VBox(15, clearButton, addButton, deleteButton, editButton);

        sidebar.getChildren().addAll(fieldsBox, spacer, buttonsBox);
        return sidebar;
    }

    // ---------------------------------------------------------------
    // Bottom status bar
    // ---------------------------------------------------------------
    private HBox buildStatusBar() {
        HBox statusBar = new HBox();
        statusBar.setId("status-bar");
        statusBar.setAlignment(Pos.CENTER_LEFT);
        statusBar.setPadding(new Insets(4, 10, 4, 10));

        statusLabel = new Label("Ready");
        statusBar.getChildren().add(statusLabel);
        return statusBar;
    }

    // ---------------------------------------------------------------
    // Button actions
    // ---------------------------------------------------------------
    private void clearForm() {
        firstNameField.clear();
        lastNameField.clear();
        departmentField.clear();
        majorField.clear();
        emailField.clear();
        imageUrlField.clear();
        tableView.getSelectionModel().clearSelection();
        statusLabel.setText("Form cleared");
    }

    private void addStudent() {
        if (firstNameField.getText().isBlank() || lastNameField.getText().isBlank()) {
            statusLabel.setText("First Name and Last Name are required");
            return;
        }
        Student student = new Student(
                nextId++,
                firstNameField.getText(),
                lastNameField.getText(),
                departmentField.getText(),
                majorField.getText(),
                emailField.getText(),
                imageUrlField.getText()
        );
        students.add(student);
        statusLabel.setText("Added student: " + student.getFirstName() + " " + student.getLastName());
        clearForm();
    }

    private void deleteSelectedStudent() {
        Student selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText("Select a row to delete");
            return;
        }
        students.remove(selected);
        statusLabel.setText("Deleted student ID " + selected.getId());
        clearForm();
    }

    private void editSelectedStudent() {
        Student selected = tableView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            statusLabel.setText("Select a row to edit");
            return;
        }
        selected.setFirstName(firstNameField.getText());
        selected.setLastName(lastNameField.getText());
        selected.setDepartment(departmentField.getText());
        selected.setMajor(majorField.getText());
        selected.setEmail(emailField.getText());
        selected.setImageUrl(imageUrlField.getText());
        tableView.refresh();
        statusLabel.setText("Updated student ID " + selected.getId());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
