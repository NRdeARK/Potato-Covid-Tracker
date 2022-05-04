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

public class TutorialController implements Initializable{

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button finishButton;

    public void initialize(URL url ,ResourceBundle  resourceBundle){
    
    }

    public void finishButton(ActionEvent event) throws IOException {
        System.out.println("new guest user finish tutorial");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/main.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();    
    }
}