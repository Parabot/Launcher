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

    public static boolean loadLocal;
    public static boolean noVerify;
    public static boolean verbose;
    public static boolean debug;
    public static boolean server;
    public static String serverName;

    @FXML // fx:id="clearCacheButton"
    public JFXButton clearCacheButton;

    @FXML // fx:id="loadLocalToggleButton"
    public JFXToggleButton loadLocalToggleButton;

    @FXML // fx:id="versionLabel"
    public Label versionLabel;

    @FXML // fx:id="startButton"
    public JFXButton startButton;

    @FXML // fx:id="noVerifyToggleButton"
    public JFXToggleButton noVerifyToggleButton;

    @FXML // fx:id="verboseToggleButton"
    public JFXToggleButton verboseToggleButton;

    @FXML // fx:id="debugToggleButton"
    public JFXToggleButton debugToggleButton;

    @FXML // fx:id="serverTextField"
    public JFXTextField serverTextField;

    @FXML // fx:id="javaVersionLabel"
    public Label javaVersionLabel;

    @FXML // fx:id="serverToggleButton"
    public JFXToggleButton serverToggleButton;

    @FXML // fx:id="statusLabel"
    public Label statusLabel;

    @FXML
    void clearCache() {
        //todo Clear Parabot Cache
    }

    @FXML
    void startClient() {
        //todo Start Parabot Client
    }

    @FXML
    void getLoadLocalSelected() {
        loadLocal = loadLocalToggleButton.isSelected();
    }

    @FXML
    void getNoVerifySelected() {
        noVerify = noVerifyToggleButton.isSelected();
    }

    @FXML
    void getVerboseSelected() {
        verbose = verboseToggleButton.isSelected();
    }

    @FXML
    void getDebugSelected() {
        debug = debugToggleButton.isSelected();
    }

    @FXML
    void getServerSelected() {
        server = serverToggleButton.isSelected();
        handleServerTextField();
    }

    @FXML
    void setServerName() {
        serverName = serverTextField.getText();
    }

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

    private void handleServerTextField() {
        if (!server && serverTextField.isVisible()) {
            serverTextField.setVisible(false);
        } else {
            serverTextField.setVisible(true);
        }
    }
}

