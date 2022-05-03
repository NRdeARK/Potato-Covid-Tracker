package com.openjfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private Label cautionLabel;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    public void login(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        UserData userData = new UserData();
        int userID = userData.verifyLogin(username, password);
        if(userID == -1){
            System.out.println("username or password are invalid");
            cautionLabel.setText("username or password are invalid");
            LogManager.writeLog(0,"login unsuccessful");         
        }
        else{
            String userInfo = userData.getUserInfo(userID);
            System.out.println("login successful");
            LogManager.writeLog(userID,"login successful");
        }

    }
}