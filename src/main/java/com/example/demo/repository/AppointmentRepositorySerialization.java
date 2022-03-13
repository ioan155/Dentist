package com.example.demo.repository;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import com.example.demo.model.Patient;
import com.example.demo.model.Appointment;
import java.util.*;


public class AppointmentRepositorySerialization extends AbstractRepository<Appointment, Integer>{

    private String filename;
    public PatientRepositorySerialization patientRepository;

    private void readFromFile ()
    {
        try(ObjectInputStream in= new ObjectInputStream (new FileInputStream(filename)))
        {
            elem = (Map<Integer, Appointment>) in.readObject();
        }
        catch(IOException|ClassNotFoundException err)
        {
            throw new RuntimeException("Error reading from file: "+err);
        }
    }

    public AppointmentRepositorySerialization(String filename, PatientRepositorySerialization patientRepository)
    {
        this.filename = filename;
        this.patientRepository = patientRepository;
        //readFromFile();
    }


    public void writeToFile(){
        try(ObjectOutputStream e=new ObjectOutputStream(new FileOutputStream(filename)))
        {
            e.writeObject(elem);
        }
        catch(IOException r){
            throw new RuntimeException("message " + r);
        }
    }


    @Override
    public void add(Appointment obj) {
        try {
            super.add(obj);
            if(!this.patientRepository.elem.containsValue(obj.getPatient()))this.patientRepository.add(obj.getPatient());
            writeToFile();

        } catch (RuntimeException e) {
            throw new RuntimeException("Object wasn't added" + e + " "+obj);
        }
    }

    @Override
    public void delete(Appointment obj) {
        try {
            super.delete(obj);
            writeToFile();
        } catch (RuntimeException ex) {
            throw new RuntimeException("Object was not deleted" + ex +" "+obj);
        }
    }

    @Override
    public void update(Appointment obj, Integer id) {
        try {
            super.update(obj, id);
            writeToFile();
        } catch (RuntimeException ex) {
            throw new RuntimeException("Object was not updated" + ex + " "+obj);
        }
    }


}
