package com.openjfx;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;

public class EditProfile2Controller implements Initializable{
    private Stage stage;
    private Scene scene;
    private Parent root;
    String filePath = "profile/justPotato.jpg";
    String absolutePath = "";
    
    @FXML
    private Button SaveProfileButton;

    @FXML
    private Button backButton;

    @FXML
    private Button browseButton;

    @FXML
    private Label fileNameLabel;

    @FXML
    private Label fileNameWarningLabel;

    @FXML
    private TextField firstnameTextField;

    @FXML
    private Label firstnameWarningLabel;

    @FXML
    private TextField genderTextField;

    @FXML
    private Label genderWarningLabel;

    @FXML
    private TextField lastVaccinatedDateTextField;

    @FXML
    private Label lastVaccinatedDateWarningLabel;

    @FXML
    private Label lastnameLabel;

    @FXML
    private TextField lastnameTextField;

    @FXML
    private Label lastnameWarningLabel;

    @FXML
    private Label passwordWarningLabel;

    @FXML
    private ImageView profileImageView;

    @FXML
    private TextField vaccineDoseTextField;

    @FXML
    private Label vaccineDoseWarningLabel;

    @FXML
    private Label warningLabel;

    public void initialize(URL url ,ResourceBundle  resourceBundle){
    
        
    }

    
    @FXML
    boolean checkFirstname() {
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
    boolean checkLastname() {
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

    @FXML
    boolean checkGender() {
        String gender = genderTextField.getText();
        if (gender.equals("")) {
            genderWarningLabel.setText("gender is blank");
            return false;
        } else if (gender.contains(" ")) {
            genderWarningLabel.setText("gender contains \" \"");
            return false;
        } else {
            genderWarningLabel.setText("");
            return true;
        }
    }

    @FXML
    boolean checkVaccinatedDate() {
        try {
            String vaccinatedDate = lastVaccinatedDateTextField.getText();
            LocalDate.parse(vaccinatedDate);
            lastVaccinatedDateWarningLabel.setText("");
            return true;
        } catch (Exception e) {

        }
        lastVaccinatedDateWarningLabel.setText("format is wrong");
        return false;

    }

    @FXML
    boolean checkVaccineDose() {
        try {
            int dose = Integer.parseInt(vaccineDoseTextField.getText());
            if (dose < 0) {
                vaccineDoseWarningLabel.setText("dose is less than one");
                return false;
            } else {
                vaccineDoseWarningLabel.setText("");
                return true;
            }

        } catch (Exception e) {
            vaccineDoseWarningLabel.setText("not in Integer format");
            return false;
        }
    }

    @FXML
    boolean checkProfileFile() throws IOException {
        if (UserData.isDuplicateFile(fileNameLabel.getText())
                && !fileNameLabel.getText().equals("justPotato.jpg")) {
            fileNameWarningLabel.setText("file name is duplicated");
            return false;
        } else {
            fileNameWarningLabel.setText("");
            return true;
        }
    }

    @FXML
    void browseProfileImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("jpg Files", "*.jpg"),
                new FileChooser.ExtensionFilter("png Files", "*.png"),
                new FileChooser.ExtensionFilter("jpeg Files", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile.exists() && selectedFile != null) {
            absolutePath = selectedFile.getAbsolutePath();
            filePath = "profile/" + selectedFile.getName();
            fileNameLabel.setText(selectedFile.getName());
            File imageFile = new File(absolutePath);
            Image profileImage = new Image(imageFile.toURI().toString());
            profileImageView.setImage(profileImage);
        }
    }

    @FXML
    void BackButton(ActionEvent event) {

    }

    @FXML
    void saveProfileButton(ActionEvent event) {

    }
}
