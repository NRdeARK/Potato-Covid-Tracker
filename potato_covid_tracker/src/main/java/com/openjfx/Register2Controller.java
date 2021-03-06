package com.openjfx;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.time.LocalDate;
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
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Node;

public class Register2Controller implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String filePath = "profile/justPotato.jpg";
    private String absolutePath = "";

    @FXML
    private Button createNewAccountButton;

    @FXML
    private Button backButton;

    @FXML
    private Button browseButton;

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

    @FXML
    private Label fileNameLabel;

    @FXML
    private Label fileNameWarningLabel;

    @FXML
    private Circle profileImage;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        File imageFile = new File("profile/justPotato.jpg");
        Image image = new Image(imageFile.toURI().toString());
        // profileImageView.setImage(profileImage);
        profileImage.setFill(new ImagePattern(image));
        fileNameLabel.setText("justPotato.jpg");
    }

    @FXML
    public boolean checkFirstname() {
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
    public boolean checkLastname() {
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
    public boolean checkGender() {
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
    public boolean checkVaccinatedDate() {
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
    public boolean checkVaccineDose() {
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
    public boolean checkProfileFile() throws IOException {
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
    public void browseProfileImage(ActionEvent event) {
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
            Image image = new Image(imageFile.toURI().toString());
            profileImage.setFill(new ImagePattern(image));
        }
    }

    @FXML
    public void BackButton(ActionEvent event) throws IOException {
        UserData.deleteNewUser1();
        LogManager.writeLog(0, "delete unfinished account", "register2");
        LogManager.changeScene("register2", "register1");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/register1.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void createNewAccountButton(ActionEvent event) throws IOException {
        boolean condition1 = checkFirstname();
        boolean condition2 = checkLastname();
        boolean condition3 = checkGender();
        boolean condition4 = checkVaccineDose();
        boolean condition5 = checkVaccinatedDate();
        boolean condition6 = checkProfileFile();
        if (condition1 && condition2 && condition3 && condition4 && condition5 && condition6) {
            UserData.createNewUser2(LogManager.getUserIDFromLastLog(), firstnameTextField.getText(),
                    lastnameTextField.getText(), genderTextField.getText(), vaccineDoseTextField.getText(),
                    lastVaccinatedDateTextField.getText(), fileNameLabel.getText());
            if (!fileNameLabel.getText().equals("justPotato.jpg")) {
                File src = new File(absolutePath);
                File dest = new File("profile/" + fileNameLabel.getText());
                Files.copy(src.toPath(), dest.toPath());
            }
            LogManager.writeLog(LogManager.getUserIDFromLastLog(), "create account successful", "register2");
            LogManager.changeScene("register2", "profile");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

}
