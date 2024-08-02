package com.model;

import java.util.Set;

importcom.model.Diagnosis;

public class Patient {

    private int id;
    private String firstName;
    private String lastName;
    Private String type;
    private Set<Diagnosis> diagnosis;

    public Patient(String firstName, String lastName, String type) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.type = type;
    }
   
    public Patient() {
    }

    public void getId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getType() {
        return this.type;
    } 

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setDiagnosis(List<Diagnosis> diagnosis) {
        this.diagnosis = diagnosis
    }
    
    public String displayDiagnosis() {
        StringBuilder stringBuilder = new StringBuilder();
        for(Diagnosis diagnois : diagnosis) {
            stringBuilder.append(diagnois.getName() + ", ");    
        }
        return stringBuilder.toString();
    }
    
    public String getName() {
        return firstName + " " + lastName;
    }
}