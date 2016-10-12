package org.parabot.launcher;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.parabot.api.misc.JavaUtil;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML // fx:id="clearCacheButton"
    private JFXButton clearCacheButton;

    @FXML // fx:id="loadLocalToggleButton"
    private JFXToggleButton loadLocalToggleButton;

    @FXML // fx:id="versionLabel"
    private Label versionLabel;

    @FXML // fx:id="startButton"
    private JFXButton startButton;

    @FXML // fx:id="noVerifyToggleButton"
    private JFXToggleButton noVerifyToggleButton;

    @FXML // fx:id="verboseToggleButton"
    private JFXToggleButton verboseToggleButton;

    @FXML // fx:id="debugToggleButton"
    private JFXToggleButton debugToggleButton;

    @FXML // fx:id="serverTextField"
    private JFXTextField serverTextField;

    @FXML // fx:id="javaVersionLabel"
    private Label javaVersionLabel;

    @FXML // fx:id="serverToggleButton"
    private JFXToggleButton serverToggleButton;

    @FXML // fx:id="statusLabel"
    private Label statusLabel;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //Set Java Version.
        javaVersionLabel.setText("Java Version : " + String.valueOf(JavaUtil.JAVA_VERSION));

        //Show or hide ServerTextField
        handleServerTextField();

        //Set Parabot Version
        //todo set Parabot Version

        //Set Status TextField
        //todo set Status TextField
    }

    @FXML
    private void clearCache() {
        //todo Clear Parabot Cache
    }

    @FXML
    private void startClient() {
        //todo Start Parabot Client
    }

    @FXML
    private void getLoadLocalSelected() {

    }

    @FXML
    private void getNoVerifySelected() {

    }

    @FXML
    private void getVerboseSelected() {

    }

    @FXML
    private void getDebugSelected() {

    }

    @FXML
    private void getServerSelected() {
        handleServerTextField();
    }

    @FXML
    private void setServerName() {

    }

    private void handleServerTextField() {
        if (false && serverTextField.isVisible()) {
            serverTextField.setVisible(false);
        } else {
            serverTextField.setVisible(true);
        }
    }
}

