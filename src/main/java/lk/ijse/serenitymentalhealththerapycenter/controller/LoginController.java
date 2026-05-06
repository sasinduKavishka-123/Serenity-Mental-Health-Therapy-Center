package lk.ijse.serenitymentalhealththerapycenter.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import lk.ijse.serenitymentalhealththerapycenter.Launcher;

import java.io.IOException;

public class LoginController {

    @FXML private TextField nameField;
    @FXML private PasswordField passwordField;

    @FXML
    private void login() {
        String userName = nameField.getText();
        String password = passwordField.getText();

        try {
            Launcher.stage.close();
            Launcher.setRoot("dashboard");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
