package lk.ijse.serenitymentalhealththerapycenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import lk.ijse.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealththerapycenter.entity.Patient;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
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

    @FXML
    private void savePatient(){
        int id = Integer.parseInt(p_id_field.getText());
        String name = p_name_field.getText();
        String contact = p_contact_field.getText();
        String address = p_address_field.getText();
        String gender = p_gender_box.getValue();
        String date = String.valueOf(LocalDate.now());

        Patient p = new Patient(id, name, gender, contact, address, date);

        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        try{
            session.save(p);
        } catch (Exception e) {
            transaction.rollback();
            throw new RuntimeException(e);
        }finally {
            session.close();
        }

    }

}
