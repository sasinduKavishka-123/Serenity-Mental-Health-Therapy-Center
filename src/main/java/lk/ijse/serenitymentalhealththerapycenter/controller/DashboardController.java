package lk.ijse.serenitymentalhealththerapycenter.controller;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import lk.ijse.serenitymentalhealththerapycenter.Launcher;

import java.io.IOException;

public class DashboardController {

    @FXML private Pane homePane;

    @FXML
    private void dashboardNav() throws IOException {
        Launcher.setRoot("dashboard");
    }

    @FXML
    private void patientNav() throws IOException {
        Parent patientFXML = Launcher.loadFXML("patient");
        homePane.getChildren().setAll(patientFXML);
    }

    @FXML
    private void programNav() throws IOException {
        Parent patientFXML = Launcher.loadFXML("program");
        homePane.getChildren().setAll(patientFXML);
    }

    @FXML
    private void therapistNav() throws IOException{
        Parent patientFXML = Launcher.loadFXML("therapist");
        homePane.getChildren().setAll(patientFXML);
    }
}
