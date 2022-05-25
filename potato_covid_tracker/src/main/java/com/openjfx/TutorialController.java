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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.Node;

public class TutorialController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    String[] tutorialPath = {
        "images/tutorial/1pic.png",
        "images/tutorial/2pic.png",
        "images/tutorial/3pic.png"
    };
    int index = 0;

    @FXML
    private Button BackButton;

    @FXML
    private Button FinishButton;

    @FXML
    private Button NextButton;

    @FXML
    private ImageView tutorialImageView;
    

    public void initialize(URL url, ResourceBundle resourceBundle) {
        Image tutorialImage = new Image(getClass().getResourceAsStream(tutorialPath[index]));
        tutorialImageView.setImage(tutorialImage);

    }

    public void finishButton(ActionEvent event) throws IOException {
        LogManager.changeScene("tutorial", "profile");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void backButton(ActionEvent event) {
        index--;
        if(index == -1){
            index=0;
        }
        Image tutorialImage = new Image(getClass().getResourceAsStream(tutorialPath[index]));
        tutorialImageView.setImage(tutorialImage);

    }

    @FXML
    void nextButton(ActionEvent event) {
        index++;
        if(index == tutorialPath.length){
            index=tutorialPath.length-1;
        }
        Image tutorialImage = new Image(getClass().getResourceAsStream(tutorialPath[index]));
        tutorialImageView.setImage(tutorialImage);
    }
}