package lk.ijse.serenitymentalhealththerapycenter.controller;

import jakarta.persistence.Table;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.serenitymentalhealththerapycenter.bo.BOFactory;
import lk.ijse.serenitymentalhealththerapycenter.bo.PatientBO;
import lk.ijse.serenitymentalhealththerapycenter.config.FactoryConfiguration;
import lk.ijse.serenitymentalhealththerapycenter.dto.PatientDTO;
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
    @FXML private TableView<PatientDTO> patient_table;
    @FXML private TableColumn<PatientDTO, String> col_p_address;
    @FXML private TableColumn<PatientDTO, String> col_p_contact;
    @FXML private TableColumn<PatientDTO, String> col_p_gender;
    @FXML private TableColumn<PatientDTO, String> col_p_id;
    @FXML private TableColumn<PatientDTO, String> col_p_name;
    @FXML private TableColumn<PatientDTO, String> col_p_reg_date;


    private final String PATIENT_NAME_REGEX    = "^[A-Za-z\\s]{3,}$";
    private final String PATIENT_CONTACT_REGEX = "^[0-9]{10}$";
    private final String PATIENT_ADDRESS_REGEX = "^.{4,}$";

    private final Alerts alert = new Alerts("Patient Management.");
    private final PatientBO patientBO = (PatientBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PATIENT);

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
    private void loadPatients(){
        try{
            List<PatientDTO> patients = patientBO.getAllPatients();
            ObservableList<PatientDTO> obList = FXCollections.observableArrayList();
            obList.addAll(patients);
            patient_table.setItems(obList);
        }catch (Exception e){
            e.printStackTrace();
            alert.getErrorAlert("Something Went Wrong!").show();
        }

    }

    @FXML
    private void handelSavePatient(){

        String name     = p_name_field.getText();
        String contact  = p_contact_field.getText();
        String address  = p_address_field.getText();
        String gender   = p_gender_box.getValue();
        String date     = String.valueOf(LocalDate.now());

        // data validation
        if(!name.matches(PATIENT_NAME_REGEX)){
            alert.getErrorAlert("Invalid Name!").show();
        } else if(!contact.matches(PATIENT_CONTACT_REGEX)){
            alert.getErrorAlert("Invalid Contact Number!").show();
        }else if(!address.matches(PATIENT_ADDRESS_REGEX)){
            alert.getErrorAlert("Invalid Address!").show();
        }else if(gender == null){
            alert.getErrorAlert("Select a Gender!").show();
        }
        else{
            // check if is there duplicate values
            int duplicateVal = checkDataIsDuplicate(name, contact);
            if(duplicateVal == 0){
                alert.getErrorAlert("Patient name is already exist!").show();
                return;
            }
            else if(duplicateVal == 1){
                alert.getErrorAlert("Patient contact is already exist!").show();
                return;
            }

            // save patient data
            PatientDTO patient = new PatientDTO("0", name, gender, contact, address, date);
            boolean isSaved = patientBO.savePatient(patient);
            if(isSaved){
                alert.getSuccessAlert("Patient Saved Successfully!").show();
                clearFields();
            }else {
                alert.getErrorAlert("Something Went Wrong!").show();
            }
        }
    }

    @FXML
    public void handelUpdatePatient(){

    }

    @FXML
    public void handelDeletePatient(){

    }

    // check if is there duplicate values return 0=> name , 1=> contact -1=> no duplicate
    private int checkDataIsDuplicate(String name, String contact){
        return patientBO.checkDuplicateData(name, contact);
    }

    // load table data to the fields
    @FXML
    public void getPatientTableData(){
        TableView.TableViewSelectionModel<PatientDTO> selectedCustomer = patient_table.getSelectionModel();
        PatientDTO patient = selectedCustomer.getSelectedItem();

        if(patient != null){
            p_id_field.setText(patient.getId());
            p_name_field.setText(patient.getName());
            p_contact_field.setText(patient.getContact());
            p_address_field.setText(patient.getAddress());
            p_gender_box.setValue(patient.getGender());
        }
    }

    void getNextPID(){
        String nextId = patientBO.getNextID();
        p_id_field.setText(nextId);
    }

    @FXML
    private void clearFields() {
        p_name_field.setText("");
        p_contact_field.setText("");
        p_address_field.setText("");
        p_gender_box.setPromptText("Select a gender");
        loadPatients();
        getNextPID();
    }

}
