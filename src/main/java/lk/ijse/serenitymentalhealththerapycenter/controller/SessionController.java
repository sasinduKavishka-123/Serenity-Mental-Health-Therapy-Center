package lk.ijse.serenitymentalhealththerapycenter.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lk.ijse.serenitymentalhealththerapycenter.bo.BOFactory;
import lk.ijse.serenitymentalhealththerapycenter.bo.ProgramBO;
import lk.ijse.serenitymentalhealththerapycenter.bo.SessionBO;
import lk.ijse.serenitymentalhealththerapycenter.bo.TherapistBo;
import lk.ijse.serenitymentalhealththerapycenter.dto.*;
import lk.ijse.serenitymentalhealththerapycenter.util.Alerts;

import java.util.List;

public class SessionController {

    // session table ------------------------------
    @FXML private TableView<SessionTM> session_table;
    @FXML private TableColumn<SessionTM, String> col_id;
    @FXML private TableColumn<SessionTM, String> col_program;
    @FXML private TableColumn<SessionTM, String> col_therapist;
    @FXML private TableColumn<SessionTM, String> col_day;
    @FXML private TableColumn<SessionTM, String> col_time;

    // buttons ---------------------------
    @FXML private Button s_btn_delete;
    @FXML private Button s_btn_save;
    @FXML private Button s_btn_update;

    // therapist input fields -----------------
    @FXML private ComboBox<String> s_t_combo_box;
    @FXML private TextField s_t_contact_field;
    @FXML private TextField s_t_email_field;

    // program input fields -----------------
    @FXML private TextField s_id_field;
    @FXML private ComboBox<String> s_program_combo_box;
    @FXML private TextField s_duration_field;
    @FXML private TextField s_fee_field;
    @FXML private ComboBox<String> s_day_combo_box;
    @FXML private ComboBox<String> s_time_combo_box;
    @FXML private TextField s_search_field;

    // radio buttons --------------------
    @FXML private RadioButton s_id_radio;
    @FXML private RadioButton s_p_radio;
    @FXML private RadioButton s_t_radio;

    // variables ------------------------------------------
    String[] timeArray = {"8:00 AM", "10:00 AM", "12:00 AM", "14:00 PM", "16:00 PM", "18:00 PM"};
    String[] days      = {"Monday", "TuesDay", "Wednesday", "Thursday", "Friday", "Saturday"};
    List<TherapistDTO> therapistList ;

    private final Alerts alert = new Alerts("Session Management.");
    TherapistDTO selectedTherapist ;
    ProgramDTO selectedProgram;

    // BOs ----------------------------------------------
    private final SessionBO sessionBO = (SessionBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.SESSION);
    private final TherapistBo therapistBo = (TherapistBo) BOFactory.getInstance().getBO(BOFactory.BOTypes.THERAPIST);
    private final ProgramBO programBO = (ProgramBO) BOFactory.getInstance().getBO(BOFactory.BOTypes.PROGRAM);

    public void initialize(){

        // initialize table columns
        col_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        col_program.setCellValueFactory(new PropertyValueFactory<>("program"));
        col_therapist.setCellValueFactory(new PropertyValueFactory<>("therapist"));
        col_day.setCellValueFactory(new PropertyValueFactory<>("day"));
        col_time.setCellValueFactory(new PropertyValueFactory<>("time"));

        // initialize day and time boxes
        ObservableList<String> obDays = FXCollections.observableArrayList(days);
        s_day_combo_box.setItems(obDays);
        ObservableList<String> obTime = FXCollections.observableArrayList(timeArray);
        s_time_combo_box.setItems(obTime);

        // initialize programs combobox
        ObservableList<String> obProgramList = FXCollections.observableArrayList();
        List<ProgramDTO> programs = programBO.getAllPrograms();
        for(ProgramDTO p : programs){
            obProgramList.add(p.getId() + " - " + p.getName());
        }
        s_program_combo_box.setItems(obProgramList);


        getNextID();
    }

    // initialize combo box to fill program data when program is selected
    @FXML
    void fillProgramData(){
        String text = s_program_combo_box.getValue();
        if(text == null){ return; }
        int num = text.indexOf("-");
        if(num > -1){
            int end = num - 1;
            selectedProgram = programBO.searchProgram(text.substring(0, end)).getFirst();
        }
        s_duration_field.setText(selectedProgram.getDuration());
        s_fee_field.setText(selectedProgram.getFee()+"");

        int programID = Integer.parseInt(selectedProgram.getId().substring(3));
        therapistList = programBO.getProgramWithTherapists(programID);

        initializeTherapistCombobox();
        s_t_combo_box.setDisable(false);
    }

    // initialize Therapist combobox
    void initializeTherapistCombobox(){
        ObservableList<String> obTherapistList = FXCollections.observableArrayList();
        for (TherapistDTO t : therapistList){
            obTherapistList.add(t.getId() + " - " + t.getName());
        }
        s_t_combo_box.setItems(obTherapistList);
    }

    // initialize combo box to fill therapist data when therapist is selected
    @FXML
    void fillTherapistData(){
        String text = s_t_combo_box.getValue();
        if(text == null){ return;}
        int num = text.indexOf("-");
        if(num < 0){
            return;
        }
        else{
            int end = num -1;
            selectedTherapist = therapistBo.searchTherapists(text.substring(0, end)).getFirst();
        }
        s_t_contact_field.setText(selectedTherapist.getContact());
        s_t_email_field.setText(selectedTherapist.getEmail());
    }

    // Get next patient id ---------------------------
    void getNextID(){
        String nextId = sessionBO.getNextID();
        s_id_field.setText(nextId);
    }

    @FXML
    void clearFields() {
        s_t_combo_box.setValue(null);
        s_program_combo_box.setValue(null);
        s_duration_field.setText("");
        s_fee_field.setText("");
        s_t_contact_field.setText("");
        s_t_email_field.setText("");
        selectedProgram = null;
        selectedTherapist = null;
        s_day_combo_box.setValue(null);
        s_time_combo_box.setValue(null);
        getNextID();
        s_btn_save.setDisable(false);
        s_btn_delete.setDisable(true);
        s_btn_update.setDisable(true);
        s_t_combo_box.setDisable(true);
    }

    @FXML
    void handelSaveSession() {
        String day = s_day_combo_box.getValue();
        String time = s_time_combo_box.getValue();

        if(selectedProgram == null){
            alert.getInfoAlert("Select a Program").show();
        }
        else if (selectedTherapist == null) {
            alert.getInfoAlert("Select a Therapist").show();
        }
        else if (day==null) {
            alert.getInfoAlert("Select a Day").show();
        }
        else if (time==null) {
            alert.getInfoAlert("Select a Time").show();
        }
        else{
            SessionDTO sessionDTO = new SessionDTO();
            sessionDTO.setDay(day);
            sessionDTO.setTime(time);
            sessionDTO.setProgram(selectedProgram);
            sessionDTO.setTherapist(selectedTherapist);

            boolean isSaved = sessionBO.saveSession(sessionDTO);
            if(isSaved){
                alert.getSuccessAlert("Session Saved Successfully!").show();
                clearFields();
            }else{
                alert.getErrorAlert("Something Went Wrong!").show();
            }
        }
    }

    @FXML
    void handelUpdateSession() {

    }

    @FXML
    void handelDeleteSession() {

    }

    @FXML
    void handelSearchSession() {

    }

}
