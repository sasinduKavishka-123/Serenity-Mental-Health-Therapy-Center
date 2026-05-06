package lk.ijse.serenitymentalhealththerapycenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML private TextField p_id_field;
    @FXML private TextField p_name_field;
    @FXML private TextField p_contact_field;
    @FXML private TextArea p_address_field;
    @FXML private ComboBox<String> p_gender_box;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "Other");
        p_gender_box.setItems(genders);
    }

}
