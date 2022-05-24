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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label cautionLabel;

    @FXML
    private Button loginButton;

    @FXML
    private ImageView backgroundImageView;

    @FXML
    private ImageView logoImageView;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    private Label backLabel;

    public void initialize(URL url ,ResourceBundle  resourceBundle){
        Image logoImage = new Image(getClass().getResourceAsStream("images/login/logo.png"));
        logoImageView.setImage(logoImage);
        Image backgroundImage = new Image(getClass().getResourceAsStream("images/login/background.png"));
        backgroundImageView.setImage(backgroundImage);
    }

    @FXML
    public void loginButton(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        int userID = UserData.verifyLogin(username, password);
        if(userID == -1){
            System.out.println("username or password are invalid");
            cautionLabel.setText("username or password are invalid");
            LogManager.writeLog(0,"login unsuccessful");         
        }
        else{
            System.out.println("login successful");
            LogManager.writeLog(userID,"login as user successful");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));	
		    root = loader.load();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void registerButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/register1.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void forgotPasswordLabel() throws IOException{
        root = FXMLLoader.load(getClass().getResource("fxml/forgotPassword.fxml"));
        scene = new Scene(root);
        stage = (Stage) backLabel.getParent().getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void backButton() throws IOException{
        root = FXMLLoader.load(getClass().getResource("fxml/launch.fxml"));
        scene = new Scene(root);
        stage = (Stage) backLabel.getParent().getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}