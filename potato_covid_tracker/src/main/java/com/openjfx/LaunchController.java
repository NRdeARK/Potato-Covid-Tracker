package com.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LaunchController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;



    @FXML
    private Button loginAsGuestButton;

    @FXML
    private Button loginAsPotatoButton;

    @FXML
    private ImageView logoImageView;

    @FXML
    private ImageView backgroundImageView;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image logoImage = new Image(getClass().getResourceAsStream("images/launch/logo.png"));
        logoImageView.setImage(logoImage);
        Image backgroundImage = new Image(getClass().getResourceAsStream("images/launch/background.png"));
        backgroundImageView.setImage(backgroundImage);
    }

    public void loginAsGuestButton(ActionEvent event) throws IOException {
        LogManager.writeLog(0, "login as guest successful");
        root = FXMLLoader.load(getClass().getResource("fxml/welcome.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("login as guest");
    }

    public void loginAsPotatoButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        System.out.println("login as potato");
    }
}
