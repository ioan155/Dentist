package com.example.demo.service;

import com.example.demo.model.Patient;
import com.example.demo.repository.PatientRepository;

import java.util.*;
import java.util.stream.Collectors;

public class PatientController implements Controller<Patient, Integer>{

    PatientRepository patientRepository;

    public PatientController(){
        patientRepository = new PatientRepository();
    }

    public PatientController(PatientRepository repository){
        this.patientRepository = repository;
    }

    @Override
    public void addObj(Patient elem) {

        this.patientRepository.add(elem);
    }

    @Override
    public void deleteObj(Patient elem) {

        this.patientRepository.delete(elem);
    }

    @Override
    public void updateObj(Patient elem, Integer id) {

        this.patientRepository.update(elem, id);
    }

    @Override
    public Patient searchObj(Integer id) {

        return this.patientRepository.findById(id);
    }

    @Override
    public Iterable<Patient> FindAll() {

        return this.patientRepository.findAll();
    }

    public List<String> getNamesOfThoseOlderThan(int age) {
        ArrayList<Patient> obj = new ArrayList<Patient>() {
        };
        for (Patient a : this.patientRepository.findAll())
            obj.add(a);

        List<String> result = obj
                .stream()
                .filter((p) -> p.getAge() > age)
                .map(s -> s.getName())
                .collect(Collectors.toList());

        return result;
    }

    public List<String> getNamesOfThoseYoungerThan(int age) {
        ArrayList<Patient> obj = new ArrayList<Patient>() {
        };
        for (Patient a : this.patientRepository.findAll())
            obj.add(a);

        List<String> result = obj
                .stream()
                .filter((p) -> p.getAge() < age)
                .map(s -> s.getName())
                .collect(Collectors.toList());

        return result;
    }

    public Optional<Patient> getOldestPatient() {
        ArrayList<Patient> obj = new ArrayList<Patient>() {
        };
        for (Patient a : this.patientRepository.findAll())
            obj.add(a);

        Optional<Patient> result = obj.stream()
                .max( Comparator.comparing(Patient::getAge));

        return result;
    }


}
