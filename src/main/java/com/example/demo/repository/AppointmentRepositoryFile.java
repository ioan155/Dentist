package com.example.demo.repository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import com.example.demo.model.Appointment;

public class AppointmentRepositoryFile extends AbstractRepository <Appointment, Integer>{

    private String filename;
    private PatientRepositoryFile patientRepository;

    public AppointmentRepositoryFile(String filename, PatientRepositoryFile patientRepository) {
        this.filename = filename;
        this.patientRepository = patientRepository;
        readFromFile();
    }

    //

    private void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] el = line.split(";");
                if (el.length != 3) {System.err.println("Not a valid number of attributes" + line);
                    continue;
                }
                try {
                    int appointmentID = Integer.parseInt(el[0]);
                    Appointment c = new Appointment(appointmentID, patientRepository.findById(Integer.parseInt(el[1])), el[2], el[3]);
                    super.add(c);
                } catch (NumberFormatException n) {
                    System.err.println("The ID is not a valid number" + el[0]);
                }
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error reading" + ex);
        }
    }
    public void writeToFile() {
        try (PrintWriter pw = new PrintWriter(filename)) {
            for (Appointment el : findAll()) {
                String line = el.getID() + ";"+el.getPatient().getID()+";"+ el.getDate()+ ";"+el.getDiagnostic();
                pw.println(line);
            }
        } catch (IOException ex) {
            throw new RuntimeException("Error writing" + ex);
        }
    }

    @Override
    public void add(Appointment obj) {
        try {
            super.add(obj);
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
