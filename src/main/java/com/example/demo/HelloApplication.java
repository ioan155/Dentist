package com.example.demo;

import com.example.demo.repository.AppointmentRepository;
import com.example.demo.service.AppointmentController;
import com.example.demo.service.PatientController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    PatientController controller;
    AppointmentController controller1;
    AppointmentRepository repository;
    @Override
    public void start(Stage stage) throws IOException {
        repository = new AppointmentRepository();
        controller = new PatientController();
        controller1 = new AppointmentController(controller, repository);
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        fxmlLoader.<HelloController>getController().setPatientController(controller);
        fxmlLoader.<HelloController>getController().setController(controller1);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}