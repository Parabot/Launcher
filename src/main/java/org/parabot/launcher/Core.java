package org.parabot.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

/**
 * @author JKetelaar
 */
public class Core extends Application {

    @Override
    public void start(Stage stage) throws Exception{
        //todo set the right path for fxml file, file on webserver is preferred @JKetelaar.
        URL fxmlLocation = this.getClass().getResource("view.fxml");
        Parent root = FXMLLoader.load(fxmlLocation);
        stage.setTitle("Parabot");
        stage.setScene(new Scene(root));
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
