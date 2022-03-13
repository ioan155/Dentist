package com.example.demo.model;
import com.example.demo.model.Patient;

import java.io.Serializable;

public class Appointment implements Identifiable<Integer>, Serializable {
    int id;
    Patient patient;
    String date, diagnostic;

    public Appointment(int id, Patient patient, String date, String diagnostic) {
        this.id = id;
        this.patient = patient;
        this.date = date;
        this.diagnostic = diagnostic;
    }

    public Appointment() {
        this.id = 0;
        this.patient = patient;
        this.date = "";
        this.diagnostic = "";
    }

    @Override
    public String toString() {
        String s = "Apointment with id: " + this.id + " of patient with id " + patient.getId() + " and diagnostic " + this.diagnostic + " is on " + this.date + "\n";
        return s;
    }

    @Override
    public Integer getID() {
        return this.id;
    }

    @Override
    public void setID(Integer id) {

    }

    void setId(int id){this.id = id;}

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(String diagnostic) {
        this.diagnostic = diagnostic;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
