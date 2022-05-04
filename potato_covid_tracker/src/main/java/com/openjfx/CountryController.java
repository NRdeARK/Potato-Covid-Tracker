package com.openjfx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

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

public class CountryController implements Initializable {

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
    private Label dailyCure;

    @FXML
    private Label dailyDeath;

    @FXML
    private Label dailyInfect;

    @FXML
    private Label modeLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    public void initialize(URL url ,ResourceBundle  resourceBundle){
        modeLabel.setText("Country");
        APIController api = new APIController();
        int inf = Integer.parseInt(api.getCountryDailyData()[0]);
        int totalInf = Integer.parseInt(api.getCountryDailyData()[1]);
        int dead = Integer.parseInt(api.getCountryDailyData()[2]);
        int totalDead = Integer.parseInt(api.getCountryDailyData()[3]);
        int cure = Integer.parseInt(api.getCountryDailyData()[4]);
        int totalCure = Integer.parseInt(api.getCountryDailyData()[5]);

        dailyInfect.setText("inf : "+ String.valueOf(totalInf-inf)+" + "+String.valueOf(inf));
        dailyDeath.setText("death : "+String.valueOf(totalDead-dead)+" + "+String.valueOf(dead));
        dailyCure.setText("cure : "+String.valueOf(totalCure-cure)+" + "+String.valueOf(cure));

        String [][] week = api.getCountryWeeklyData();

        String [][] month = api.getCountryWeeklyData();


    }

    @FXML
    public void displayUsername() {
        try {
            String username = UserData.getUsername(LogManager.getUserIDFromLastLog());
            usernameLabel.setText("username: " + username);
            System.out.println("display!");
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

    public void globalButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/global.fxml"));
        root = loader.load();
        GlobalController globalController = loader.getController();
        globalController.displayUsername();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void countryButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/country.fxml"));
        root = loader.load();
        CountryController countryController = loader.getController();
        countryController.displayUsername();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cityButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/city.fxml"));
        root = loader.load();
        CityController cityController = loader.getController();
        cityController.displayUsername();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void profileButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
        root = loader.load();
        ProfileController profileController = loader.getController();
        profileController.displayUsername();
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

