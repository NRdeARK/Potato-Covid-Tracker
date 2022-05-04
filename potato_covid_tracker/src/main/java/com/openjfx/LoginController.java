package com.openjfx;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Label cautionLabel;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;

    @FXML
    public void login(ActionEvent event) throws IOException {
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
		    ProfileController profileController = loader.getController();
		    profileController.displayUsername();
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

    }

    @FXML
    public void back(ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("fxml/launch.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}