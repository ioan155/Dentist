package com.example.demo;

import com.example.demo.model.Appointment;
import com.example.demo.model.Patient;
import com.example.demo.service.AppointmentController;
import com.example.demo.service.PatientController;
import javafx.scene.control.ListView;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.collections.FXCollections;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HelloController {

    PatientController patientController;
    AppointmentController appointmentController;

    public void setPatientController(PatientController patientController){
        this.patientController = patientController;
    }

    public void setController(AppointmentController appointmentController){
        this.appointmentController = appointmentController;
    }

    @FXML
    private Label welcomeText;

    @FXML
    private TextField text_field_id_patient;

    @FXML
    private TextField text_field_name_patient;

    @FXML
    private TextField text_field_age_patient;

    @FXML
    private TextField text_field_id_appointment;

    @FXML
    private TextField text_field_patient_appointment;

    @FXML
    private TextField text_field_date_appointment;

    @FXML
    private TextField text_field_diagnostic_appointment;

    @FXML
    private TextField text_field_id_remove_patient;

    @FXML
    private TextField text_field_id_remove_appointment;

    @FXML
    private TextField text_field_report_appointment;

    @FXML
    private TextField text_field_older_than;

    @FXML
    private TextField text_field_younger_than;

    @FXML
    private TextField text_field_diagnostic;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }

    public void button_patient_on_action(ActionEvent actionEvent){
        String id_text = text_field_id_patient.getText();
        String name_text = text_field_name_patient.getText();
        String age_text = text_field_age_patient.getText();
        Patient patient = new Patient(Integer.parseInt(id_text), name_text, Integer.parseInt(age_text));
        patientController.addObj(patient);
        load_lists();
    }


    public void button_appointment_on_action(ActionEvent actionEvent){
        String id_text = text_field_id_appointment.getText();
        String patient_text = text_field_patient_appointment.getText();
        String date_text = text_field_date_appointment.getText();
        String diagnostic_text = text_field_diagnostic_appointment.getText();
        Appointment appointment = new Appointment(Integer.parseInt(id_text), patientController.searchObj(Integer.parseInt(patient_text)), date_text, diagnostic_text);
        appointmentController.addObj(appointment);
        load_lists();
    }

    @FXML
    private ListView list_view_patient;

    @FXML
    private ListView list_view_appointment;

    @FXML
    private ListView list_view_reports;

    public void load_lists(){
        List<Patient> list = new ArrayList<>();
        for(Patient patient: patientController.FindAll()){
            list.add(patient);
        }

        List<Appointment> list1 = new ArrayList<>();
        for(Appointment appointment: appointmentController.FindAll()){
            list1.add(appointment);
        }

        //List<String> list_names = new ArrayList<>();

        list_view_patient.setItems(FXCollections.observableList(list));
        list_view_appointment.setItems(FXCollections.observableList(list1));
        //list_view_reports.setItems(FXCollections.observableList(list_names));
    }

    public void button_remove_patient_on_action(ActionEvent actionEvent){
        String id_text = text_field_id_remove_patient.getText();
        patientController.deleteObj(patientController.searchObj(Integer.parseInt(id_text)));
        load_lists();
    }

    public void button_remove_appointment_on_action(ActionEvent actionEvent){
        String id_text = text_field_id_remove_appointment.getText();
        appointmentController.deleteObj(appointmentController.searchObj(Integer.parseInt(id_text)));
        load_lists();
    }

    public void button_update_patient_on_action(ActionEvent actionEvent){
        String id_text = text_field_id_patient.getText();
        String name_text = text_field_name_patient.getText();
        String age_text = text_field_age_patient.getText();
        Patient patient = new Patient(Integer.parseInt(id_text), name_text, Integer.parseInt(age_text));
        patientController.updateObj(patient,Integer.parseInt(id_text));
        load_lists();
    }

    public void button_update_appointment_on_action(ActionEvent actionEvent){
        String id_text = text_field_id_appointment.getText();
        String patient_text = text_field_patient_appointment.getText();
        String date_text = text_field_date_appointment.getText();
        String diagnostic_text = text_field_diagnostic_appointment.getText();
        Appointment appointment = new Appointment(Integer.parseInt(id_text), patientController.searchObj(Integer.parseInt(patient_text)), date_text, diagnostic_text);
        appointmentController.updateObj(appointment,Integer.parseInt(id_text));
        load_lists();
    }

    public void button_get_name_by_diagnostic(ActionEvent actionEvent){
        String diagnostic =  text_field_diagnostic.getText();
        System.out.println(diagnostic);
        List<String> list_names;
        list_names = appointmentController.getNamesByDiagnostic(diagnostic);
        list_view_reports.setItems(FXCollections.observableList(list_names));
        list_names.forEach(System.out::println);
    }

    public void button_appointment_of_patient(ActionEvent actionEvent){
         String id_text = text_field_report_appointment.getText();
         List<Appointment> app_list = new ArrayList<>();
         app_list = appointmentController.getAppointmentsOfaPatientById(Integer.parseInt(id_text));
         list_view_reports.setItems(FXCollections.observableList(app_list));
    }

    public void button_patients_older_than(ActionEvent actionEvent){
        String age = text_field_older_than.getText();
        List<String> p_list = new ArrayList<>();
        p_list = patientController.getNamesOfThoseOlderThan(Integer.parseInt(age));
        list_view_reports.setItems(FXCollections.observableList(p_list));
    }

    public void button_patients_younger_than(ActionEvent actionEvent){
        String age = text_field_younger_than.getText();
        List<String> p_list = new ArrayList<>();
        p_list = patientController.getNamesOfThoseYoungerThan(Integer.parseInt(age));
        list_view_reports.setItems(FXCollections.observableList(p_list));
    }

    public void button_oldest_patient(ActionEvent actionEvent){
        Optional<Patient> p_list;
        p_list = patientController.getOldestPatient();
        list_view_reports.setItems(FXCollections.observableList(List.of(p_list.get())));
    }

}