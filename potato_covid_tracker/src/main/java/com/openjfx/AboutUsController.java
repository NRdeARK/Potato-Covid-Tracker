package com.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class AboutUsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button backButton;

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    @FXML
    public void backButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/" + LogManager.getSceneFromLastLog() + ".fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        LogManager.changeScene("aboutUs", LogManager.getSceneFromLastLog());
    }

}
