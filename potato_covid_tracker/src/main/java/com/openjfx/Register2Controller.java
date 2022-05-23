package com.openjfx;

import java.io.File;
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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Register2Controller implements Initializable{
    
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button CreateNewAccountButton;

    @FXML
    private Button backButton;

    @FXML
    private Button browseButton;

    @FXML
    private TextField firstnameTextfield;

    @FXML
    private TextField genderTextField;

    @FXML
    private TextField lastVaccinatedDate;

    @FXML
    private Label lastnameLabel;

    @FXML
    private TextField lastnameTextfield;

    @FXML
    private Label passwordWarningLabel;

    @FXML
    private ImageView profileImageView;

    @FXML
    private TextField vaccineDose;

    @FXML
    private Label warningLabel;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File imageFile = new File("profile/justPotato.jpg");
        Image profileImage = new Image(imageFile.toURI().toString());
        profileImageView.setImage(profileImage);
    }

    @FXML
    void BackButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/register1.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void CheckFirstname(ActionEvent event) {
        String firstname = firstnameTextfield.getText();
        firstname.
    }

    @FXML
    void CheckLastname(ActionEvent event) {

    }

    @FXML
    void CreateNewAccountButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void browseProfileImage(ActionEvent event) {

    }

    @FXML
    void checkGender(ActionEvent event) {

    }

    @FXML
    void checkVaccinatedDate(ActionEvent event) {

    }

    @FXML
    void checkVaccineDose(ActionEvent event) {

    }
}
