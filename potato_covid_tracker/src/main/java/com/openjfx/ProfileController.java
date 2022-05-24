package com.openjfx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            displayProfile(LogManager.getUserIDFromLastLog());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCountdownVaccinated(int userID) throws IOException {
        try {
            LocalDate date1 = LocalDate.now();
            LocalDate date2 = LocalDate.parse(UserData.getVaccineDate(userID));
            int delay = 0;
            int dose = Integer.parseInt(UserData.getVaccineDose(userID));
            if (dose == 0) {
                delay = 0;
            } else if (dose == 1) {
                delay = 90;
            } else {
                delay = 180;
            }
            date2 = date2.plusDays(delay);
            Period intervalPeriod = Period.between(date1, date2);
            int day = intervalPeriod.getDays();
            int month = intervalPeriod.getMonths();
            return month + " months " + day + " days until your next vaccine";
        } catch (Exception e) {
            return "null";
        }
    }

    @FXML
    public void displayProfile(int userID) {
        try {
            usernameLabel1.setText(UserData.getUsername(userID));

            File imageFile = new File(UserData.getProfilePicture(userID));
            Image image = new Image(imageFile.toURI().toString());
            profileImage.setImage(image);

            usernameLabel2.setText(UserData.getUsername(userID));

            nameLabel.setText(UserData.getFirstname(userID) + " " + UserData.getLastname(userID));

            genderLabel.setText(UserData.getGender(userID));

            doseLabel.setText(UserData.getVaccineDose(userID));

            vaccinatedDate.setText(UserData.getVaccineDate(userID));

            countdownLabel.setText(getCountdownVaccinated(userID));

            System.out.println("display!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void profileButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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

    public void logoutButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/launch.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
