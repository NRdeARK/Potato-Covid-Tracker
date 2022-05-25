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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class WelcomeController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private ImageView backgroundImageView;

    @FXML
    private Button noButton;

    @FXML
    private Label tutorialLabel;

    @FXML
    private Button yesButton;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image backgroundImage = new Image(getClass().getResourceAsStream("images/welcome/background.png"));
        backgroundImageView.setImage(backgroundImage);
    }

    public void startButton(ActionEvent event) throws IOException {
        LogManager.changeScene("welcome", "profile");
        root = FXMLLoader.load(getClass().getResource("fxml/profile.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void openTutorial() throws IOException {
        LogManager.changeScene("welcome","tutorial");
        root = FXMLLoader.load(getClass().getResource("fxml/tutorial.fxml"));
        scene = new Scene(root);
        stage = (Stage) tutorialLabel.getParent().getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
