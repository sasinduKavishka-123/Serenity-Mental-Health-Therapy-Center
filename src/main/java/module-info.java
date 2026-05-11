module lk.ijse.serenitymentalhealththerapycenter {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;
    requires mysql.connector.j;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires javafx.graphics;

    // for hibernate
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;

    // for lombok
    requires static lombok;
    requires javafx.base;


    opens lk.ijse.serenitymentalhealththerapycenter.controller to javafx.fxml;
    opens lk.ijse.serenitymentalhealththerapycenter.entity to javafx.base, org.hibernate.orm.core;
    opens lk.ijse.serenitymentalhealththerapycenter.dto to javafx.base;

    exports lk.ijse.serenitymentalhealththerapycenter;
}