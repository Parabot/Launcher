package org.parabot.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.parabot.launcher.data.Configuration;

/**
 * @author JKetelaar, Fryslan
 */
public class Core extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        //noinspection RedundantCast
        AnchorPane root = (AnchorPane) FXMLLoader.load(this.getClass().getResource("/storage/interface.fxml"));
        stage.setTitle(Configuration.BOT_TITLE);
        stage.setScene(new Scene(root));
        stage.show();
    }
}
