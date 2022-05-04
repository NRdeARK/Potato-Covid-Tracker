package com.openjfx;

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

public class WelcomeController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label usernameLabel;

    public void initialize(URL url ,ResourceBundle  resourceBundle){
    }

    @FXML
<<<<<<< Updated upstream
    public void displayUsername(){
        try {
            String username = UserData.getUsername(LogManager.getUserIDFromLastLog());
		    usernameLabel.setText("username: " + username);
=======
    public void initialize(URL url ,ResourceBundle resourceBundle){
        //displayUsername();
        usernameLabel.setText("guest");
    }

    @FXML
    public void displayUsername() {
        try {
            String username = UserData.getUsername(LogManager.getUserIDFromLastLog());
            usernameLabel.setText("username: " + username);
>>>>>>> Stashed changes
            System.out.println("display!");
        } catch (Exception e) {
            e.printStackTrace();
        }
<<<<<<< Updated upstream
        
=======

>>>>>>> Stashed changes
	}



}

