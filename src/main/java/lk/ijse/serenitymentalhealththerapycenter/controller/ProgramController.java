package lk.ijse.serenitymentalhealththerapycenter.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import lk.ijse.serenitymentalhealththerapycenter.bo.BOFactory;
import lk.ijse.serenitymentalhealththerapycenter.bo.ProgramBO;
import lk.ijse.serenitymentalhealththerapycenter.bo.TherapistBo;
import lk.ijse.serenitymentalhealththerapycenter.dto.TherapistDTO;
import lk.ijse.serenitymentalhealththerapycenter.entity.Program;
import lk.ijse.serenitymentalhealththerapycenter.util.Alerts;

import java.util.List;

public class ProgramController {

    // table data ------------------
    @FXML private TableView<Program> program_table;
    @FXML private TableColumn<Program, String> col_pr_contact;
    @FXML private TableColumn<Program, String> col_pr_gender;
    @FXML private TableColumn<Program, String> col_pr_id;
    @FXML private TableColumn<Program, String> col_pr_name;

    // input fields Program------------------
    @FXML private TextField pr_id_field;
    @FXML private TextField pr_name_field;
    @FXML private TextField pr_duration_field;
    @FXML private TextField pr_fee_field;
    @FXML private TextField pr_search_field;
    @FXML private Label p_search_text;

    // input fields Therapist------------------
    @FXML private ComboBox<String> pr_t_name_combo_box;
    @FXML private TextField pr_t_id_field;
    @FXML private TextField pr_t_contact_field;
    @FXML private TextField pr_t_email_field;

    // Buttons------------------
    @FXML Button pr_btn_save;
    @FXML Button pr_btn_update;
    @FXML Button pr_btn_delete;

    private final Alerts alert = new Alerts("Program Management.");
    private final ProgramBO programBO = (ProgramBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PROGRAM);
    private final TherapistBo therapistBo = (TherapistBo) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPIST);

    public void initialize(){

        // initialize therapist combo box -------------
        ObservableList<String> observableList = FXCollections.observableArrayList();
        List<TherapistDTO> therapistDTOList = therapistBo.getAllTherapists();
        for(TherapistDTO dto : therapistDTOList){
            observableList.add(dto.getId() + " - " + dto.getName());
        }
        pr_t_name_combo_box.setItems(observableList);

        // initialize duration text field to input only numbers
        pr_duration_field.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if(newText.matches("\\d*")){
                return change;
            }
            else{
                return null;
            }
        }));

        // initialize fee text field to input only price with two decimals
        pr_fee_field.setTextFormatter(new TextFormatter<>(change -> {
            String newText = change.getControlNewText();
            if(newText.matches("\\d*(\\.\\d{0,2})?")){
                return change;
            }
            else{
                return null;
            }
        }));

        p_search_text.setVisible(false);
        pr_btn_update.setDisable(true);
        pr_btn_delete.setDisable(true);
    }

    // initialize combo box to fill therapist data when therapist is selected
    @FXML
    void fillTherapistData(){
        String text = pr_t_name_combo_box.getValue();
        int end = text.indexOf("-") -1;
        List<TherapistDTO> selectedTherapist = therapistBo.searchTherapists(text.substring(0, end));
        pr_t_id_field.setText(selectedTherapist.getFirst().getId());
        pr_t_contact_field.setText(selectedTherapist.getFirst().getContact());
        pr_t_email_field.setText(selectedTherapist.getFirst().getEmail());
    }

    @FXML
    void clearFields() {
        p_search_text.setVisible(false);
        pr_btn_update.setDisable(true);
        pr_btn_delete.setDisable(true);
    }

    @FXML
    void handelSaveProgram() {
        String name     = pr_name_field.getText().trim();
        String duration = pr_duration_field.getText().trim();
        String fee      = pr_fee_field.getText().trim();



    }

    @FXML
    void handelUpdateProgram() {

    }

    @FXML
    void handelDeleteProgram() {

    }

    @FXML
    void getPatientTableData() {

    }

    @FXML
    void handelSearchProgram() {

    }

    @FXML
    void handelLinkProgram() {

    }

    @FXML
    void handelUnlinkProgram() {

    }

    @FXML
    void makeNotVisible() {
        p_search_text.setVisible(false);
    }

    @FXML
    void makeVisible() {
        p_search_text.setVisible(true);
    }

}
