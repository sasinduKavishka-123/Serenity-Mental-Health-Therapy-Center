module lk.ijse.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires javafx.graphics;

    // for hibernate
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    // for lombok
    requires static lombok;

    opens lk.ijse.serenitymentalhealththerapycenter.controller to javafx.fxml;

    exports lk.ijse.serenitymentalhealththerapycenter;
}