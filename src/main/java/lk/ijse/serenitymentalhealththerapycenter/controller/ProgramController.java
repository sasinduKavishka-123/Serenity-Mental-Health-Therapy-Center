package lk.ijse.serenitymentalhealththerapycenter.controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.serenitymentalhealththerapycenter.bo.BOFactory;
import lk.ijse.serenitymentalhealththerapycenter.bo.ProgramBO;
import lk.ijse.serenitymentalhealththerapycenter.bo.TherapistBo;
import lk.ijse.serenitymentalhealththerapycenter.dto.ProgramDTO;
import lk.ijse.serenitymentalhealththerapycenter.dto.TherapistDTO;
import lk.ijse.serenitymentalhealththerapycenter.entity.Program;
import lk.ijse.serenitymentalhealththerapycenter.util.Alerts;

import java.math.BigDecimal;
import java.util.List;

public class ProgramController {

    // table data ------------------
    @FXML private TableView<ProgramDTO> program_table;
    @FXML private TableColumn<ProgramDTO, String> col_pr_id;
    @FXML private TableColumn<ProgramDTO, String> col_pr_name;
    @FXML private TableColumn<ProgramDTO, String> col_pr_duration;
    @FXML private TableColumn<ProgramDTO, String> col_pr_fee;

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

    // final variables --------------------------
    private final Alerts alert = new Alerts("Program Management.");
    private final ProgramBO programBO = (ProgramBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PROGRAM);
    private final TherapistBo therapistBo = (TherapistBo) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPIST);

    // variables --------------------------
    TherapistDTO selectedTherapist = null;
    ProgramDTO selectedProgram     = null;

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

        // make buttons and combo box disable true and false
        p_search_text.setVisible(false);
        pr_btn_update.setDisable(true);
        pr_btn_delete.setDisable(true);
        pr_t_name_combo_box.setDisable(true);

        // initialize program table
        col_pr_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_pr_name.setCellValueFactory(new PropertyValueFactory<>("name"));
        col_pr_duration.setCellValueFactory(new PropertyValueFactory<>("duration"));
        col_pr_fee.setCellValueFactory(new PropertyValueFactory<>("fee"));

        loadProgramTable();
        getNextProgramID();
    }

    // get next program ID ----------------------
    void getNextProgramID(){
        String nextID = programBO.getNextID();
        pr_id_field.setText(nextID);
    }

    // load programs table ----------------------------------
    void loadProgramTable(){
        try{
            List<ProgramDTO> programs = programBO.getAllPrograms();
            ObservableList<ProgramDTO> obList = FXCollections.observableArrayList();
            obList.addAll(programs);
            program_table.setItems(obList);
        }catch (Exception e){
            e.printStackTrace();
            alert.getErrorAlert("Something Went Wrong!").show();
        }
    }

    // initialize combo box to fill therapist data when therapist is selected
    @FXML
    void fillTherapistData(){
        String text = pr_t_name_combo_box.getValue();
        if(text != null){
            int end = text.indexOf("-") -1;
            selectedTherapist = therapistBo.searchTherapists(text.substring(0, end)).getFirst();
            pr_t_id_field.setText(selectedTherapist.getId());
            pr_t_contact_field.setText(selectedTherapist.getContact());
            pr_t_email_field.setText(selectedTherapist.getEmail());
        }
    }

    @FXML
    void clearFields() {
        pr_name_field.setText("");
        pr_duration_field.setText("");
        pr_fee_field.setText("");
        pr_search_field.setText("");
        pr_t_name_combo_box.setValue(null);
        pr_t_id_field.setText("");
        pr_t_contact_field.setText("");
        pr_t_email_field.setText("");
        p_search_text.setVisible(false);
        pr_btn_update.setDisable(true);
        pr_btn_delete.setDisable(true);
        pr_btn_save.setDisable(false);
        pr_t_name_combo_box.setDisable(true);
        loadProgramTable();
        getNextProgramID();
    }

    @FXML
    void handelSaveProgram() {
        String name     = pr_name_field.getText().trim();
        String duration = pr_duration_field.getText().trim();
        String fee      = pr_fee_field.getText().trim();

        if(name.isEmpty()){
            alert.getErrorAlert("Invalid Name").show();
        }
        else if(duration.isEmpty()){
            alert.getErrorAlert("Invalid duration").show();
        }
        else if (fee.isEmpty()) {
            alert.getErrorAlert("Invalid Fee").show();
        }
        else{
            BigDecimal pr_fee = BigDecimal.valueOf(Integer.parseInt(fee));
            ProgramDTO programDTO = new ProgramDTO();
            programDTO.setName(name);
            programDTO.setDuration(duration);
            programDTO.setFee(pr_fee);

            boolean isSaved = programBO.saveProgram(programDTO);

            if(isSaved){
                alert.getSuccessAlert("Program Saved Successfully!").show();
                clearFields();
            }else{
                alert.getErrorAlert("Something Went Wrong!").show();
            }
        }
    }

    @FXML
    void handelUpdateProgram() {
        String id       = pr_id_field.getText().trim();
        String name     = pr_name_field.getText().trim();
        String duration = pr_duration_field.getText().trim();
        String fee      = pr_fee_field.getText().trim();

        if(name.isEmpty()){
            alert.getErrorAlert("Invalid Name").show();
        }
        else if(duration.isEmpty()){
            alert.getErrorAlert("Invalid duration").show();
        }
        else if (fee.isEmpty()) {
            alert.getErrorAlert("Invalid Fee").show();
        }
        else{
            BigDecimal pr_fee = BigDecimal.valueOf(Double.parseDouble(fee));
            ProgramDTO programDTO = new ProgramDTO();
            programDTO.setId(id.substring(3));
            programDTO.setName(name);
            programDTO.setDuration(duration);
            programDTO.setFee(pr_fee);

            boolean isUpdated = programBO.updateProgram(programDTO);

            if(isUpdated){
                alert.getSuccessAlert("Program Updated Successfully!").show();
                clearFields();
            }else{
                alert.getErrorAlert("Something Went Wrong!").show();
            }
        }
    }

    @FXML
    void handelDeleteProgram() {
        String id = pr_id_field.getText().trim();
        int pr_id = Integer.parseInt(id.substring(3));
        boolean isDeleted = programBO.deleteProgram(pr_id);

        if(isDeleted){
            alert.getSuccessAlert("Program Deleted Successfully!").show();
            clearFields();
        }else{
            alert.getErrorAlert("Something Went Wrong!").show();
        }
    }

    @FXML
    void getPatientTableData() {
        TableView.TableViewSelectionModel<ProgramDTO> selectedPr = program_table.getSelectionModel();
        ProgramDTO program = selectedPr.getSelectedItem();

        if(program != null){
            selectedProgram = program;
            pr_id_field.setText(program.getId());
            pr_name_field.setText(program.getName());
            pr_duration_field.setText(program.getDuration());
            pr_fee_field.setText(program.getFee()+"");

            // make buttons disable false and true
            pr_btn_save.setDisable(true);
            pr_btn_update.setDisable(false);
            pr_btn_delete.setDisable(false);
            pr_t_name_combo_box.setDisable(false);
        }

    }

    @FXML
    void handelSearchProgram() {
        String text = pr_search_field.getText().trim();

        if(text.isEmpty()){
            alert.getInfoAlert("Enter ID or Name.").show();
            return;
        }

        List<ProgramDTO> programs = programBO.searchProgram(text);
        if(programs.isEmpty()){
            alert.getInfoAlert("Program Not Found!").show();
            return;
        }
        ObservableList<ProgramDTO> obList = FXCollections.observableArrayList();
        obList.addAll(programs);
        program_table.setItems(obList);
    }

    @FXML
    void handelLinkProgram() {
        boolean isLinked =  therapistBo.addProgram(selectedTherapist, selectedProgram);
        if(isLinked){
            alert.getSuccessAlert("Program Linked Successfully!").show();
            clearFields();
        }
        else{
            alert.getErrorAlert("Something Went Wrong!").show();
        }
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
