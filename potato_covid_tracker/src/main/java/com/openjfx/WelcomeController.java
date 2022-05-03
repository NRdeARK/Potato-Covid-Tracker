package com.openjfx;

import javafx.scene.control.Label;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class WelcomeController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label usernameLabel;

    @FXML
    public void displayUsername() throws IOException {
        String username = UserData.getUsername(LogManager.getUserIDFromLastLog());
		usernameLabel.setText("username: " + username);
        System.out.println("display!");
	}

    @FXML
    public void ContinueButton(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


}

