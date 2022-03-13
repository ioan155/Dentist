package com.example.demo.repository;

import com.example.demo.model.Appointment;

public class AppointmentRepository extends AbstractRepository<Appointment, Integer> {

    public AppointmentRepository(){};

    public AppointmentRepository reportByDiagnostic(String diag){
        AppointmentRepository app = new AppointmentRepository();
        for(Appointment elem:this.findAll()){
            if(elem.getDiagnostic() == diag)
                app.add(elem);
        }
        return app;
    }

    public AppointmentRepository reportByYear(String year) {
        AppointmentRepository app = new AppointmentRepository();
        for (Appointment elem : this.findAll()) {
            if (elem.getDate().contains(year))
                app.add(elem);
        }
        return app;
    }
}
