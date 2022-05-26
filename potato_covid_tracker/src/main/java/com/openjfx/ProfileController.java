package com.openjfx;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;

import javafx.scene.shape.Circle;

import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.javafx.StackedFontIcon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import javafx.util.Duration;

import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class ProfileController implements Initializable {

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
    private Button AboutUsButton;

    @FXML
    private Button EditProfileButton;

    @FXML
    private Label countdownLabel;

    @FXML
    private ImageView profileImageView;

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

    @FXML
    private Label doseLabel;

    @FXML
    private Label genderLabel;

    @FXML
    private Label greetingLabel;

    @FXML
    private Label firstnameLabel;

    @FXML
    private Label lastnameLabel;

    @FXML
    private ImageView profileImage;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label lastVaccinatedDateLabel;

    @FXML
    private JFXDrawer MainMenuDrawer;

    @FXML
    private JFXDrawer SubMenuDrawer;

    @FXML
    private JFXHamburger Hamberger;

    @FXML
    private AnchorPane mainAnchor;

    private boolean mainMenuActive;
    private boolean subMenuActive;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            displayProfile(LogManager.getUserIDFromLastLog());

            HamburgerBackArrowBasicTransition burgerTask = new HamburgerBackArrowBasicTransition(Hamberger);
            burgerTask.setRate(-1);

            TranslateTransition tt = new TranslateTransition();
            tt.setDuration(Duration.millis(500));
            tt.setNode(Hamberger);

            mainMenuActive = false;
            subMenuActive = false;

            VBox mainMenuVbox = FXMLLoader.load(getClass().getResource("fxml/menubar.fxml"));
            MainMenuDrawer.setSidePane(mainMenuVbox);
            VBox subMenuVbox = FXMLLoader.load(getClass().getResource("fxml/menuslide.fxml"));

            Hamberger.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                burgerTask.setRate(burgerTask.getRate() * -1);

                if (mainMenuActive) {
                    if (subMenuActive) {
                        SubMenuDrawer.close();
                    }

                    tt.setToX(0);
                    burgerTask.play();
                    tt.play();
                    mainMenuActive = false;

                } else {
                    tt.setToX(80);
                    burgerTask.play();
                    tt.play();
                    mainMenuActive = true;

                }

                if (MainMenuDrawer.isOpened()) {
                    MainMenuDrawer.close();
                } else {
                    MainMenuDrawer.open();
                }

            });

            for (Node node : mainMenuVbox.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (ev) -> {
                        switch (node.getAccessibleText()) {
                            case "Profile": {
                                try {
                                    LogManager.changeScene("profile", "profile");
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
                                    root = loader.load();
                                    stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                                    scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                            case "Menu": {

                                SubMenuDrawer.setSidePane(subMenuVbox);

                                if (SubMenuDrawer.isOpened()) {
                                    SubMenuDrawer.close();
                                    subMenuActive = false;
                                } else {
                                    SubMenuDrawer.open();
                                    subMenuActive = true;
                                }
                                break;
                            }

                            case "Notification": {

                                break;
                            }

                            case "Help": {
                                break;
                            }

                            case "Exit": {
                                try {
                                    LogManager.changeScene("profile", "logout");
                                    root = FXMLLoader.load(getClass().getResource("fxml/logoutConfirmation.fxml"));
                                    stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                                    scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();

                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            }
                        }
                    });
                }
            }

            for (Node node : subMenuVbox.getChildren()) {
                if (node.getAccessibleText() != null) {
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (ev) -> {
                        switch (node.getAccessibleText()) {

                        }
                    });
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getCountdownVaccinated(int userID) throws IOException {
        try {
            LocalDate date1 = LocalDate.now();
            LocalDate date2 = LocalDate.parse(UserData.getLastVaccinatedDate(userID));
            int delay = 0;
            int dose = Integer.parseInt(UserData.getVaccineDose(userID));
            if (dose == 0) {
                delay = 0;
            } else if (dose == 1) {
                delay = 90;
            } else {
                delay = 180;
            }
            date2 = date2.plusDays(delay);
            Period intervalPeriod = Period.between(date1, date2);
            int day = intervalPeriod.getDays();
            int month = intervalPeriod.getMonths();
            return month + " months " + day + " days until your next vaccine";
        } catch (Exception e) {
            return "null";
        }
    }

    @FXML
    public void displayProfile(int userID) {
        try {
            File imageFile = new File(UserData.getProfilePicture(userID));
            Image image = new Image(imageFile.toURI().toString());
            profileImageView.setImage(image);
            usernameLabel.setText(UserData.getUsername(userID));
            firstnameLabel.setText(UserData.getFirstname(userID));
            lastnameLabel.setText(UserData.getLastname(userID));
            genderLabel.setText(UserData.getGender(userID));
            doseLabel.setText(UserData.getVaccineDose(userID));
            lastVaccinatedDateLabel.setText(UserData.getLastVaccinatedDate(userID));
            countdownLabel.setText(getCountdownVaccinated(userID));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editProfileButton(ActionEvent event) throws IOException {
        LogManager.changeScene("profile", "editProfile1");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/editProfile1.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void profileButton(ActionEvent event) throws IOException {
        //don't use
    }

    public void globalButton(ActionEvent event) throws IOException {
        LogManager.changeScene("profile", "global");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/global.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void countryButton(ActionEvent event) throws IOException {
        LogManager.changeScene("profile", "country");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/country.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cityButton(ActionEvent event) throws IOException {
        LogManager.changeScene("profile", "city");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/city.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void aboutUsButton(ActionEvent event) throws IOException {
        LogManager.changeScene("profile", "aboutUs");
        root = FXMLLoader.load(getClass().getResource("fxml/aboutUs.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String css = this.getClass().getResource("styles/profile.css").toExternalForm();
        scene = new Scene(root);
        scene.getStylesheets().add(css);
        stage.setScene(scene);
        stage.show();
    }

    public void logoutButton(ActionEvent event) throws IOException {
        LogManager.changeScene("profile", "logout");
        root = FXMLLoader.load(getClass().getResource("fxml/logoutConfirmation.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
