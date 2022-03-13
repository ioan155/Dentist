package com.example.demo.repository;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Map;
import com.example.demo.model.Patient;


public class PatientRepositorySerialization extends AbstractRepository<Patient, Integer>{

    public String filename;

    public PatientRepositorySerialization(String filename) {
        this.filename = filename;
        //readFromFile();

    }

    private void readFromFile ()
    {
        try(ObjectInputStream in= new ObjectInputStream (new FileInputStream(filename)))
        {
            elem = (Map<Integer, Patient>) in.readObject();
        }
        catch(Exception err)
        {
            throw new RuntimeException("Error reading from file: "+err);
        }
    }

    public void writeToFile()
    {
        try(ObjectOutputStream e=new ObjectOutputStream(new FileOutputStream(filename))){
            e.writeObject(elem);
        }
        catch(IOException r){
            throw new RuntimeException("message " + r);
        }
    }


    @Override
    public void add(Patient obj) {
        try {
            super.add(obj);
            writeToFile();
        } catch (RuntimeException e) {
            throw new RuntimeException("Object wasn't added" + e + " "+obj);
        }
    }

    @Override
    public void delete(Patient obj) {
        try {
            super.delete(obj);
            writeToFile();
        } catch (RuntimeException ex) {
            throw new RuntimeException("Object was not deleted" + ex +" "+obj);
        }
    }

    @Override
    public void update(Patient obj, Integer id) {
        try {
            super.update(obj, id);
            writeToFile();
        } catch (RuntimeException ex) {
            throw new RuntimeException("Object was not updated" + ex + " "+obj);
        }
    }

}
