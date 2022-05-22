package com.openjfx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

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
import javafx.stage.Stage;
import javafx.scene.Node;

public class ProfileController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button CityButton;

    @FXML
    private Button CountryButton;

    @FXML
    private Button GlobalButton;

    @FXML
    private Button LogoutButton;

    @FXML
    private Button ProfileButton;

    @FXML
    private Label countdownLabel;

    @FXML
    private Label doseLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label greetingLabel;

    @FXML
    private Label nameLabel;

    @FXML
    private ImageView profileImage;

    @FXML
    private Label usernameLabel1;

    @FXML
    private Label usernameLabel2;

    @FXML
    private Label vaccinatedDate;

    @FXML
    public void initialize(URL url , ResourceBundle  resourceBundle){
        try {
            displayAll(LogManager.getUserIDFromLastLog());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void displayAll(int userID) {
        try {
            usernameLabel1.setText(UserData.getUsername(userID));
            usernameLabel2.setText(UserData.getUsername(userID));

            nameLabel.setText(UserData.getRealname(userID) + " " + UserData.getSurname(userID));

            genderLabel.setText(UserData.getGender(userID));

            doseLabel.setText(UserData.getVaccineDose(userID));

            vaccinatedDate.setText(UserData.getVaccineDate(userID));

            // File imageFile = new File("profile/normalPotato.png");
            File imageFile = new File(UserData.getProfilePicture(userID));
            Image image = new Image(imageFile.toURI().toString());
            profileImage.setImage(image);

            System.out.println("display!");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}


    public void globalButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/global.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void countryButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/country.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cityButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/city.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void profileButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logoutButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/launch.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}

