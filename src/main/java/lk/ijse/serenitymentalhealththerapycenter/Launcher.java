package lk.ijse.serenitymentalhealththerapycenter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Launcher extends Application {

    public static Stage stage;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage p_stage) throws Exception {
        stage = p_stage;
        stage.setTitle("Serenity Mental Health Therapy Center");
        stage.setResizable(false);
        stage.centerOnScreen();

        Scene scene = new Scene(loadFXML("login"), 700, 450);

        stage.setScene(scene);
        stage.show();

    }

    public static void setRoot(String fxml) throws IOException {
        Scene scene;
        if(fxml.equals("login")){
            scene = new Scene(loadFXML(fxml), 700, 450);

        }else {
            scene = new Scene(loadFXML(fxml), 1200, 700);
        }
        stage.setScene(scene);
        stage.show();
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Launcher.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
}
