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

    public boolean checkUsername() throws IOException {
        String username = usernameTextField.getText();
        int userID = UserData.getUserID(username);
        return userID == -1;
    }

    public boolean checkPassword() {
        return passwordPasswordField.getText().equals(rePasswordPasswordField.getText());
    }

    @FXML
    public boolean usernameWarning() throws IOException {
        if (checkUsername()) {
            usernameWarningLabel.setText("");
        } else {
            usernameWarningLabel.setText("username has been taken");
        }

        return checkUsername();
    }

    @FXML
    public boolean passwordWarning() {
        if (checkPassword()) {
            passwordWarningLabel.setText("");
        } else {
            passwordWarningLabel.setText("password don't match");
        }
        return checkPassword();
    }

    @FXML
    void loginButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void nextButton(ActionEvent event) throws IOException {
        if ( passwordWarning() && usernameWarning()) {
            String username = usernameTextField.getText();
            String password = passwordPasswordField.getText();
            int userID = UserData.CreateNewUser1(username, password);
            LogManager.writeLog(userID, "register part 1/2");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/register2.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }
}
