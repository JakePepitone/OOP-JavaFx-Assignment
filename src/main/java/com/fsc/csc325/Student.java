package com.fsc.csc325;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Simple data model representing a student record displayed in the TableView.
 */
public class Student {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty department;
    private final SimpleStringProperty major;
    private final SimpleStringProperty email;
    private final SimpleStringProperty imageUrl;

    public Student(int id, String firstName, String lastName, String department, String major, String email, String imageUrl) {
        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.department = new SimpleStringProperty(department);
        this.major = new SimpleStringProperty(major);
        this.email = new SimpleStringProperty(email);
        this.imageUrl = new SimpleStringProperty(imageUrl);
    }

    public int getId() {
        return id.get();
    }

    public void setId(int value) {
        id.set(value);
    }

    public SimpleIntegerProperty idProperty() {
        return id;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String value) {
        firstName.set(value);
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String value) {
        lastName.set(value);
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(String value) {
        department.set(value);
    }

    public SimpleStringProperty departmentProperty() {
        return department;
    }

    public String getMajor() {
        return major.get();
    }

    public void setMajor(String value) {
        major.set(value);
    }

    public SimpleStringProperty majorProperty() {
        return major;
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String value) {
        email.set(value);
    }

    public SimpleStringProperty emailProperty() {
        return email;
    }

    public String getImageUrl() {
        return imageUrl.get();
    }

    public void setImageUrl(String value) {
        imageUrl.set(value);
    }

    public SimpleStringProperty imageUrlProperty() {
        return imageUrl;
    }
}
