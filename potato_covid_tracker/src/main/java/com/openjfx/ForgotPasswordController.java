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

public class ForgotPasswordController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML
    private Button backButton;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Label passwordWarningLabel;

    @FXML
    private PasswordField rePasswordPasswordField;

    @FXML
    private Button resetPasswordButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label usernameWarningLabel;
    
    @FXML
    private Label firstnameWarningLabel;

    @FXML
    private Label lastnameWarningLabel;

    public void initialize(URL url ,ResourceBundle  resourceBundle){

    }

    private boolean checkFirstname() {
        String firstname = firstnameTextField.getText();
        if (firstname.equals("")) {
            firstnameWarningLabel.setText("firstname is blank");
            return false;
        } else if (firstname.contains(" ")) {
            firstnameWarningLabel.setText("firstname contains \" \"");
            return false;
        } else {
            firstnameWarningLabel.setText("");
            return true;
        }
    }

    @FXML
    private boolean checkLastname() {
        String lastname = lastnameTextField.getText();
        if (lastname.equals("")) {
            lastnameWarningLabel.setText("lastname is blank");
            return false;
        } else if (lastname.contains(" ")) {
            lastnameWarningLabel.setText("lastname contains \" \"");
            return false;
        } else {
            lastnameWarningLabel.setText("");
            return true;
        }
    }

    private boolean checkUsername() {
        String username = usernameTextField.getText();
        if (username.equals("")) {
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

    private boolean checkPassword() {
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

    private boolean verifyResetPassword() throws IOException{
        String username = usernameTextField.getText();
        String firstname = firstnameTextField.getText();
        String lastname = lastnameTextField.getText();
        int userID = UserData.getUserID(username);
        if(userID == -1 ){
            usernameWarningLabel.setText("don't have this user in system");
            return false;
        } else if (!(firstname.equals(UserData.getFirstname(userID)) && lastname.equals(UserData.getLastname(userID)))){
            usernameWarningLabel.setText("some thing wrong");
            return false;
        } else {
            return true;
        }
    }

    public void backButton(ActionEvent event) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));	
		root = loader.load();
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void resetPasswordButton(ActionEvent event) throws IOException {
        boolean condition1 = checkUsername();
        boolean condition2 = checkFirstname();
        boolean condition3 = checkLastname();
        boolean condition4 = checkPassword();
        if(condition1 && condition2 && condition3 && condition4){
            boolean condition5 = verifyResetPassword();
            if(condition5){
                UserData.resetPassword(usernameTextField.getText(), passwordPasswordField.getText());
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/login.fxml"));	
                root = loader.load();
                stage = (Stage)((Node)event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

}
