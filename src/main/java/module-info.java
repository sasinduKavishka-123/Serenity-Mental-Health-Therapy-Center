module lk.ijse.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;

    opens lk.ijse.serenitymentalhealththerapycenter to javafx.fxml;
    exports lk.ijse.serenitymentalhealththerapycenter;
}