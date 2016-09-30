package org.parabot.launcher;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Controller {

    public static boolean useServer;
    public static boolean useLogin;

    public static String server;
    public static String username;
    public static String password;

    @FXML
    ComboBox serverComboBox;

    @FXML
    TextField usernameTextField;

    @FXML
    PasswordField passwordPassField;

    @FXML
    Label versionLabel,statusLabel,javaVersionLabel;



    public void clearCache(){

    }

    public void clearWorkingDir(){

    }

    public void startClient(){

    }

    public void setServer(){
        if(useServer) {
            server = serverComboBox.getSelectionModel().getSelectedItem().toString();
        }
    }

    public void setUsername(){
        if(useLogin) {
            username = usernameTextField.getText();
        }
    }

    public void setPassword(){
        if(useLogin) {
            password = passwordPassField.getText();
        }
    }


}
