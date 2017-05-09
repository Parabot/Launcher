package org.parabot.launcher;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import org.parabot.api.misc.JavaUtil;
import org.parabot.launcher.helpers.Launcher;
import org.parabot.launcher.helpers.SettingHelper;
import org.parabot.launcher.helpers.VersionHelper;
import org.parabot.launcher.io.CacheManager;
import org.parabot.launcher.io.Reader;
import org.parabot.launcher.models.Setting;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class Controller implements Initializable, ControllerImpl {

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

    private HashMap<String, String> defaultLabelValues;

    public Controller() {
        defaultLabelValues = new HashMap<>();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Set Status TextField
        setLabel(statusLabel, "Starting up");

        //Remove the ServerTextField on launch if needed.
        handleServerTextField();

        //Set serverTextField text color to white
        serverTextField.setStyle("-fx-text-fill: white;");

        fillComponents();

        setStatus("Ready");
    }

    @Override
    public void fillComponentsFromOtherThread() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                fillComponents();
            }
        });
    }

    @Override
    public void setReady(final int sleep) {
        new Thread() {
            @Override
            public void run() {
                try {
                    Thread.sleep(sleep);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                setStatus("Ready", true);
                startButton.setDisable(false);
            }
        }.start();
    }

    @Override
    public void disableStart(boolean otherThread) {
        if (otherThread) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    startButton.setDisable(true);
                }
            });
        } else {
            startButton.setDisable(true);
        }
    }

    private void fillComponents() {
        //Set Java Version.
        setLabel(javaVersionLabel, String.valueOf(JavaUtil.JAVA_VERSION));

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

        setLabel(versionLabel, String.valueOf(VersionHelper.getCurrentVersion()));
    }

    @FXML
    private void clearCache(ActionEvent event) {
        setReady(1500);
        setLabel(statusLabel, "Clearing cache");
        CacheManager.clearCache();
        setLabel(statusLabel, "Cache cleared");
    }

    @FXML
    private void startClient(ActionEvent event) {
        disableStart(false);
        setStatus("Starting client");

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

        new Launcher(this).start();
    }

    @FXML
    private void getServerSelected() {
        handleServerTextField();
    }

    private void handleServerTextField() {
        if (!serverToggleButton.isSelected() && serverTextField.isVisible()) {
            serverTextField.setVisible(false);
        } else {
            serverTextField.setVisible(true);
        }
    }

    private void setLabel(Label label, String append) {
        if (!defaultLabelValues.containsKey(label.getId())) {
            defaultLabelValues.put(label.getId(), label.getText());
        }

        label.setText(defaultLabelValues.get(label.getId()) + append);
    }

    @Override
    public void setStatus(String status) {
        setLabel(statusLabel, status);
    }

    @Override
    public void setStatus(final String status, boolean otherThread) {
        if (otherThread) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    setStatus(status);
                }
            });
        } else {
            setStatus(status);
        }
    }

}

