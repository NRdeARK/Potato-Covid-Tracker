package com.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Register1Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button loginButton;

    @FXML
    private Button nextButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Label passwordWarningLabel;

    @FXML
    private PasswordField rePasswordPasswordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label usernameWarningLabel;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public boolean usernameWarning() throws IOException {
        String username = usernameTextField.getText();
        int userID = UserData.getUserID(username);
        if (userID != -1) {
            usernameWarningLabel.setText("username has been taken");
            return false;
        } else if (username.equals("")) {
            usernameWarningLabel.setText("username is blank");
            return false;
        } else if (username.contains(" ")) {
            usernameWarningLabel.setText("username contains \" \"");
            return false;
        } else {
            usernameWarningLabel.setText("");
            return true;
        }
    }

    @FXML
    public boolean passwordWarning() {
        String password = passwordPasswordField.getText();
        String rePassword = rePasswordPasswordField.getText();
        if (!password.equals(rePassword)) {
            passwordWarningLabel.setText("password don't match");
            return false;
        } else if (password.equals("") || rePassword.equals("")) {
            passwordWarningLabel.setText("password is blank");
            return false;
        } else if (password.contains(" ")) {
            passwordWarningLabel.setText("password contains \" \"");
            return false;
        } else {
            passwordWarningLabel.setText("");
            return true;
        }
    }

    @FXML
    public void loginButton(ActionEvent event) throws IOException {
        LogManager.changeScene(0, "register1", "login");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void nextButton(ActionEvent event) throws IOException {
        boolean check1 = passwordWarning();
        boolean check2 = usernameWarning();
        if (check1 && check2) {
            String username = usernameTextField.getText();
            String password = passwordPasswordField.getText();
            int userID = UserData.createNewUser1(username, password);
            LogManager.changeScene(userID, "register1", "register2");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/register2.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
