package com.openjfx;

import com.jfoenix.controls.JFXDrawer;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.javafx.StackedFontIcon;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class ProfileControllerTest implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    private Button MenuButton;

    @FXML
    private Button ProfileButton;

    @FXML
    private Circle ProfileCircle;

    @FXML
    private Label modeLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private ImageView profileView;

    @FXML
    private ImageView ProfileCircleImg;

    @FXML
    private AnchorPane InnerButtonAnchor;

    @FXML
    private AnchorPane MiddleAnchor;

    @FXML
    private StackedFontIcon Icon1;

    @FXML
    private StackedFontIcon Icon2;

    @FXML
    private FontIcon Icon01;

    @FXML
    private FontIcon Icon02;

    @FXML
    private FontIcon Icon03;

    @FXML
    private FontIcon Icon04;

    @FXML
    private AnchorPane ProfileAnchor;

    @FXML
    private JFXDrawer menuDrawer;

    private boolean menuActive;
    private boolean profileActive;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image profileImage = new Image(getClass().getResourceAsStream("images/profile/potato.jpg"));

        profileView.setImage(profileImage);
        ProfileCircleImg.setImage(profileImage);
        InnerButtonAnchor.setVisible(false);
        ProfileAnchor.setVisible(true);
        ProfileButton.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.color(0.0, 0.0, 0.0, 0.69), 8.45, 0, 0, 6));
        profileActive = true;
        menuActive = false;
    }

    @FXML
    public void displayUsername() {
        try {
            // String username = UserData.getUsername(LogManager.getUserIDFromLastLog());
            // usernameLabel.setText("username: " + username);
            // System.out.println("display!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void ProfileButtonHover(MouseEvent event) {
        ProfileAnchor.setVisible(true);
        ProfileButton.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.color(0.0, 0.0, 0.0, 0.69), 8.45, 0, 0, 6));

    }

    @FXML
    void ProfileButtonExit(MouseEvent event) {
        if (profileActive) {
            ProfileAnchor.setVisible(true);
        } else {
            InnerButtonAnchor.setVisible(false);
            ProfileButton.setEffect(null);
        }

    }

    @FXML
    void MenuButtonHover(MouseEvent event) {
        InnerButtonAnchor.setVisible(true);
        Icon01.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.color(0.0, 0.0, 0.0, 0.69), 8.45, 0, 0, 6));
        Icon02.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.color(0.0, 0.0, 0.0, 0.69), 8.45, 0, 0, 6));
        Icon03.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.color(0.0, 0.0, 0.0, 0.69), 8.45, 0, 0, 6));
        Icon04.setEffect(new DropShadow(BlurType.GAUSSIAN, Color.color(0.0, 0.0, 0.0, 0.69), 8.45, 0, 0, 6));
    }

    @FXML
    void MenuButtonExit(MouseEvent event) {
        if (menuActive) {
            InnerButtonAnchor.setVisible(true);
        } else {
            InnerButtonAnchor.setVisible(false);
            Icon01.setEffect(null);
            Icon02.setEffect(null);
            Icon03.setEffect(null);
            Icon04.setEffect(null);
        }

    }

    public void MenuButton(ActionEvent event) throws IOException {

        FXMLoader pageloader = new FXMLoader();

        try {
            System.out.println("MENU BUTTON PRESSED");
            VBox vbox = FXMLLoader.load(getClass().getResource("fxml/menuslide.fxml"));
            menuDrawer.setSidePane(vbox);
            menuDrawer.setBackground(Background.fill(Color.TRANSPARENT));
        
            for(Node node : vbox.getChildren()){
                if(node.getAccessibleText() != null){
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e)->{
                        switch (node.getAccessibleText()){
                            case "Material_One": {
                                System.out.println("GLOBAL PRESSED");
                                Pane view = pageloader.getPage("countryscene");
                                MiddleAnchor.getChildren().clear();
                                MiddleAnchor.getChildren().add(view);
                            }

                        }
                    });
                }
            }

            if (menuActive == false) {
                menuDrawer.open();
                menuActive = true;
            } else if (menuActive) {
                menuDrawer.close();
                menuActive = false;
            }

            
        } catch (Exception e) {
            e.printStackTrace();
        }
       
        

    }

    // public void globalButton(ActionEvent event) throws IOException {
    //     FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/global.fxml"));
    //     root = loader.load();
    //     GlobalController globalController = loader.getController();
    //     globalController.displayUsername();
    //     stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    //     scene = new Scene(root);
    //     stage.setScene(scene);
    //     stage.show();
    //     try {
    //         System.out.println("GLOBAL PRESSED");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    // public void countryButton(ActionEvent event) throws IOException {
    //     FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/country.fxml"));
    //     root = loader.load();
    //     CountryController countryController = loader.getController();
    //     // countryController.displayChart();
    //     stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    //     scene = new Scene(root);
    //     stage.setScene(scene);
    //     stage.show();
    // }

    // public void cityButton(ActionEvent event) throws IOException {
    //     FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/city.fxml"));
    //     root = loader.load();
    //     CityController cityController = loader.getController();
    //     cityController.displayUsername();
    //     stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    //     scene = new Scene(root);
    //     stage.setScene(scene);
    //     stage.show();
    // }

    public void profileButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
        root = loader.load();
        ProfileController profileController = loader.getController();
        profileController.displayUsername();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String css = this.getClass().getResource("styles/profile.css").toExternalForm();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    // public void logoutButton(ActionEvent event) throws IOException {
    //     root = FXMLLoader.load(getClass().getResource("fxml/launch.fxml"));
    //     stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    //     String css = this.getClass().getResource("styles/launch.css").toExternalForm();
    //     scene = new Scene(root);
    //     scene.getStylesheets().add(css);
    //     stage.setScene(scene);
    //     stage.show();
    // }

}
