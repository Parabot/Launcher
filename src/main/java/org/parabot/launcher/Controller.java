package org.parabot.launcher;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.parabot.api.io.Directories;
import org.parabot.api.misc.JavaUtil;
import org.parabot.launcher.data.Configuration;
import org.parabot.launcher.helpers.SettingHelper;
import org.parabot.launcher.helpers.VersionHelper;
import org.parabot.launcher.io.Downloader;
import org.parabot.launcher.io.Reader;
import org.parabot.launcher.io.Writer;
import org.parabot.launcher.models.Setting;

import java.io.IOException;
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

        //Remove the ServerTextField on launch if needed.
        handleServerTextField();

        //Set serverTextField text color to white
        serverTextField.setStyle("-fx-text-fill: white;");

        //Set Java Version.
        javaVersionLabel.setText("Java Version : " + String.valueOf(JavaUtil.JAVA_VERSION));

        //Set Status TextField
        //todo set Status TextField
        statusLabel.setText("Starting up");

        Reader.parseConfiguration();
        for (Setting setting : SettingHelper.getSettings()) {
            switch (setting.getSetting().toLowerCase()) {
                case "noverify":
                    noVerifyToggleButton.setSelected(setting.isEnabled());
                    break;
                case "loadlocal":
                    loadLocalToggleButton.setSelected(setting.isEnabled());
                    break;
                case "verbose":
                    verboseToggleButton.setSelected(setting.isEnabled());
                    break;
                case "debug":
                    debugToggleButton.setSelected(setting.isEnabled());
                    break;
            }
        }
    }

    @FXML
    private void clearCache() {
        statusLabel.setText("Clearing Cache");
        Directories.clearCache();
        statusLabel.setText("Cache Cleared");
    }

    @FXML
    private void startClient(ActionEvent event) {
        statusLabel.setText("Starting Client");
        for (Setting setting : SettingHelper.getSettings()) {
            switch (setting.getSetting().toLowerCase()) {
                case "noverify":
                    setting.setEnabled(noVerifyToggleButton.isSelected());
                    break;
                case "loadlocal":
                    setting.setEnabled(loadLocalToggleButton.isSelected());
                    break;
                case "verbose":
                    setting.setEnabled(verboseToggleButton.isSelected());
                    break;
                case "debug":
                    setting.setEnabled(debugToggleButton.isSelected());
                    break;
            }
        }
        Downloader.downloadFile(Configuration.DOWNLOAD_BOT, Configuration.CLIENT_LOCATION);
        Writer.writeConfiguration();

        try {
            new Terminal().execute();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
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
        if (!serverToggleButton.isSelected() && serverTextField.isVisible()) {
            serverTextField.setVisible(false);
        } else {
            serverTextField.setVisible(true);
        }
    }
}

