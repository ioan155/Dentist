package com.example.demo.repository;
//import com.example.demo.model.Patient;
import java.util.ArrayList;

import com.example.demo.model.Patient;

public class PatientRepository extends AbstractRepository<Patient, Integer> {
    public PatientRepository(){};

    public PatientRepository overAge(int age){
        PatientRepository pat = new PatientRepository();
        for(Patient p:this.findAll()){
            if(p.getAge()>age)
                pat.add(p);
        }
        return pat;
    }

    public PatientRepository underAge(int age){
        PatientRepository pat = new PatientRepository();
        for(Patient p:this.findAll()){
            if(p.getAge()<age)
                pat.add(p);
        }
        return pat;
    }
}
