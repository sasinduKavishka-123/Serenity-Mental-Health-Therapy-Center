module lk.ijse.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires javafx.graphics;

    opens lk.ijse.serenitymentalhealththerapycenter.controller to javafx.fxml;

    exports lk.ijse.serenitymentalhealththerapycenter;
}