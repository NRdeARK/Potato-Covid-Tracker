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
import javafx.stage.Stage;
import javafx.scene.Node;

public class WelcomeController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button noButton;

    @FXML
    private Button yesButton;

    public void initialize(URL url ,ResourceBundle  resourceBundle){
    
    }

    public void yesButton(ActionEvent event) throws IOException {
        System.out.println("old guest user");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
        root = loader.load();
        ProfileController profileController = loader.getController();
        profileController.displayUsername();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }

    public void noButton(ActionEvent event) throws IOException {
        System.out.println("new guest user");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/tutorial.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
}
