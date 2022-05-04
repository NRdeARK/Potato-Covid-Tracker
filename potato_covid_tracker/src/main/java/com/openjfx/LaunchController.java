package com.openjfx;



import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LaunchController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button loginAsGuestButton;

    @FXML
    private Button loginAsPotatoButton;

    public void loginAsGuest(ActionEvent event) throws IOException {
        LogManager.writeLog(0, "login as guest successful");
<<<<<<< Updated upstream

        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/welcome.fxml"));
        root = (Parent) loader.load();
        WelcomeController welcomeController = loader.getController();
        welcomeController.displayUsername();
        Scene newScene = new Scene(root);
        Stage newStage = new Stage();
        newStage.setScene(newScene);
        newStage.show();
=======
        root = FXMLLoader.load(getClass().getResource("fxml/welcome.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
>>>>>>> Stashed changes
        // stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        // scene = new Scene(root);
        // stage.setScene(scene);
        // stage.show();
    }

    public void loginAsPotato(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("POTATO");
    }
}

