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
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import javafx.scene.Node;

public class EditProfile1Controller implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button backButton;

    @FXML
    private Button nextButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private Label passwordWarningLabel;

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void backButton(ActionEvent event) throws IOException {
        LogManager.changeScene("editProfile1", "profile");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public boolean checkPassword() {
        String password = passwordPasswordField.getText();
        if (password.equals("")) {
            passwordWarningLabel.setText("password is blank");
            return false;
        } else {
            passwordWarningLabel.setText("");
            return true;
        }
    }

    @FXML
    void nextButton(ActionEvent event) throws IOException {
        if (checkPassword()) {
            String username = UserData.getUsername(LogManager.getUserIDFromLastLog());
            String password = passwordPasswordField.getText();
            int userID = UserData.verifyLogin(username, password);
            if (userID == -1) {
                passwordWarningLabel.setText("password is incorrect");
            } else {
                passwordWarningLabel.setText("");
                LogManager.changeScene("editProfile1", "editProfile2");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/editProfile2.fxml"));
                root = loader.load();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }

    @FXML
    void passwordWarning(ActionEvent event) throws IOException {
        if (checkPassword()) {
            String username = UserData.getUsername(LogManager.getUserIDFromLastLog());
            String password = passwordPasswordField.getText();
            int userID = UserData.verifyLogin(username, password);
            if (userID == -1) {
                passwordWarningLabel.setText("password is incorrect");
            } else {
                passwordWarningLabel.setText("");
                LogManager.changeScene("editProfile1", "editProfile2");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/editProfile2.fxml"));
                root = loader.load();
                stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
