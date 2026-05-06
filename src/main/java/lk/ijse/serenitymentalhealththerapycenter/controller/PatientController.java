package lk.ijse.serenitymentalhealththerapycenter.controller;

import jakarta.persistence.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealththerapycenter.entity.Patient;
import lk.ijse.serenitymentalhealththerapycenter.model.PatientModel;
import lk.ijse.serenitymentalhealththerapycenter.util.Alerts;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

public class PatientController implements Initializable {

    @FXML private TextField p_id_field;
    @FXML private TextField p_name_field;
    @FXML private TextField p_contact_field;
    @FXML private TextArea p_address_field;
    @FXML private ComboBox<String> p_gender_box;
    // table ---------------------------------
    @FXML private TableView<Patient> patient_table;
    @FXML private TableColumn<Patient, String> col_p_address;
    @FXML private TableColumn<Patient, String> col_p_contact;
    @FXML private TableColumn<Patient, String> col_p_gender;
    @FXML private TableColumn<Patient, String> col_p_id;
    @FXML private TableColumn<Patient, String> col_p_name;
    @FXML private TableColumn<Patient, String> col_p_reg_date;


    private final String PATIENT_NAME_REGEX    = "^[A-Za-z\\s]{3,}$";
    private final String PATIENT_CONTACT_REGEX = "^[0-9]{10}$";
    private final String PATIENT_ADDRESS_REGEX = "^.{4,}$";

    private final Alerts alert = new Alerts("Patient Management.");
    private final PatientModel patientModel = new PatientModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // initialize combo box
        ObservableList<String> genders = FXCollections.observableArrayList("Male", "Female", "Other");
        p_gender_box.setItems(genders);

        // initialize patient table
        col_p_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_p_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_p_contact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        col_p_gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
        col_p_reg_date.setCellValueFactory(new PropertyValueFactory<>("registeredDay"));
        col_p_address.setCellValueFactory(new PropertyValueFactory<>("address"));

        loadPatients();
    }

    @FXML
    private void handelSavePatient(){
//        int id = Integer.parseInt(p_id_field.getText());
        String name     = p_name_field.getText();
        String contact  = p_contact_field.getText();
        String address  = p_address_field.getText();
        String gender   = p_gender_box.getValue();
        String date     = String.valueOf(LocalDate.now());

        if(!name.matches(PATIENT_NAME_REGEX)){
            alert.getErrorAlert("Invalid Name!").show();
        } else if(!contact.matches(PATIENT_CONTACT_REGEX)){
            alert.getErrorAlert("Invalid Contact Number!").show();
        }else if(!address.matches(PATIENT_ADDRESS_REGEX)){
            alert.getErrorAlert("Invalid Address!").show();
        }else if(gender == null){
            alert.getErrorAlert("Select a Gender!").show();
        }else{

            Patient patient = new Patient(0, name, gender, contact, address, date);

            boolean isSaved = patientModel.savePatient(patient);

            if(isSaved){
                alert.getSuccessAlert("Patient Saved Successfully!").show();
                clearFields();
            }else {
                alert.getErrorAlert("Something Went Wrong!").show();
            }
        }

    }

    @FXML
    private void loadPatients(){
        try{
            List<Patient> patients = patientModel.getAllCustomers();
            ObservableList<Patient> obList = FXCollections.observableArrayList();
            for(Patient p : patients){
                obList.add(p);
            }
            patient_table.setItems(obList);
        }catch (Exception e){
            e.printStackTrace();
            alert.getErrorAlert("Something Went Wrong!").show();
        }


    }

    @FXML
    public void getPatientTableData(){
        TableView.TableViewSelectionModel<Patient> selectedCustomer = patient_table.getSelectionModel();
        Patient patient = selectedCustomer.getSelectedItem();

        if(patient != null){
            p_id_field.setText("" + patient.getId());
            p_name_field.setText(patient.getName());
            p_contact_field.setText(patient.getContact());
            p_address_field.setText(patient.getAddress());
            p_gender_box.setValue(patient.getGender());
        }

    }

    private void clearFields(){
        p_name_field.setText("");
        p_contact_field.setText("");
        p_address_field.setText("");
        p_gender_box.setValue("");
        loadPatients();
    }

}
