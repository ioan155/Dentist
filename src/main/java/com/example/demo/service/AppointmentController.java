package com.example.demo.service;

import com.example.demo.model.Appointment;
import com.example.demo.repository.AppointmentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class AppointmentController implements Controller <Appointment,Integer> {
    AppointmentRepository appointmentRepository;
    PatientController patientController;

    public AppointmentController(PatientController patientController, AppointmentRepository repository){
        this.patientController = patientController;
        this.appointmentRepository = repository;
//        for(Appointment a: repository.findAll()){
//            try{
//                this.patientController.searchObj(a.getPatient().getID());
//            } catch (Exception e){
//                this.patientController.addObj(a.getPatient());
//            }
//
//        }
    }

    @Override
    public void addObj(Appointment elem) {
        this.appointmentRepository.add(elem);
    }

    @Override
    public void deleteObj(Appointment elem){
        this.appointmentRepository.delete(elem);
    }

    @Override
    public void updateObj(Appointment elem, Integer id){
        this.appointmentRepository.update(elem,id);
    }

    @Override
    public Appointment searchObj(Integer id){
        return this.appointmentRepository.findById(id);
    }

    @Override
    public Iterable<Appointment> FindAll() {
        return this.appointmentRepository.findAll();
    }

    public List<String> getNamesByDiagnostic(String diag) {
        ArrayList<Appointment> object = new ArrayList<>() {};
        for( Appointment app : this.appointmentRepository.findAll())
            object.add(app);

        List<String> result = object
                .stream()
                .filter((p) -> p.getDiagnostic().equals(diag))
                .map(s -> s.getPatient().getName())
                .collect(Collectors.toList());
       // System.out.println(diag);


        return result;
    }

    public List<Appointment> getAppointmentsOfaPatientById(int id){

        ArrayList<Appointment> object = new ArrayList<>() {
        };
        for( Appointment a : this.appointmentRepository.findAll())
            if(a.getPatient().getId() == id)object.add(a);

        List<Appointment> result = object
                .stream()
                .collect(Collectors.toList());

        return result;

    }
}
