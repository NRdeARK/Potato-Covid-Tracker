package com.openjfx;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

import org.kordamp.ikonli.javafx.FontIcon;
import org.kordamp.ikonli.javafx.StackedFontIcon;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
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
    private Button EditProfileButton;

    @FXML
    private Label countdownLabel;

    @FXML
    private ImageView profileImageView;

    @FXML
    private Circle profileImage;

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
    private StackPane rootpane;

    @FXML
    private AnchorPane rootAnchorPane;

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

                            case "AboutUs": {
                                try {
                                    LogManager.changeScene("profile", "aboutUs");
                                    root = FXMLLoader.load(getClass().getResource("fxml/aboutUs.fxml"));
                                    stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                                    String css = this.getClass().getResource("styles/profile.css").toExternalForm();
                                    scene = new Scene(root);
                                    scene.getStylesheets().add(css);
                                    stage.setScene(scene);
                                    stage.show();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                                break;
                            }

                            case "Exit": {
                                try {
                                    LogManager.changeScene("profile", "logout");
                                    JFXButton yesButton = new JFXButton("YES");
                                    yesButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev1) -> {
                                        try {
                                            LogManager.changeScene("logout", "launch");
                                            root = FXMLLoader.load(getClass().getResource("fxml/launch.fxml"));
                                            stage = (Stage) ((Node) ev1.getSource()).getScene().getWindow();
                                            scene = new Scene(root);
                                            stage.setScene(scene);
                                            stage.show();
                                        } catch (IOException e1) {
                                            e1.printStackTrace();
                                        }

                                        stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                                        scene = new Scene(root);
                                        stage.setScene(scene);
                                        stage.show();

                                    });

                                    JFXButton noButton = new JFXButton("NO");
                                    noButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent ev2) -> {
                                        try {
                                            root = FXMLLoader.load(getClass()
                                                    .getResource("fxml/" + LogManager.getSceneFromLastLog() + ".fxml"));
                                            stage = (Stage) ((Node) ev2.getSource()).getScene().getWindow();

                                            LogManager.changeScene("logout", LogManager.getSceneFromLastLog());
                                            scene = new Scene(root);
                                            stage.setScene(scene);
                                            stage.show();

                                        } catch (IOException e) {
                                            // TODO: handle exception
                                        }

                                    });

                                    showMaterialDialog(rootpane, rootAnchorPane, Arrays.asList(yesButton, noButton),
                                            "Logout Confirmation", "Are you sure you want to log out?");
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
                            case "Country": {
                                try {
                                    LogManager.changeScene("profile", "country");
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/country.fxml"));
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
                            case "City": {
                                try {
                                    LogManager.changeScene("profile", "city");
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/city.fxml"));
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
            if(day<0 || month <0)
            {return month*-1 + " months " + day*-1 + " days pass from schedule";}
            else
            {return month + " months " + day + " days until your next vaccine";}
        } catch (Exception e) {
            return "null";
        }
    }

    @FXML
    public void displayProfile(int userID) {
        try {
            File imageFile = new File(UserData.getProfilePicture(userID));
            Image image = new Image(imageFile.toURI().toString());
            profileImage.setFill(new ImagePattern(image));
            //profileImageView.setImage(image);
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

    public static void showMaterialDialog(StackPane root, Node nodeToBeBlurred, List<JFXButton> controls, String header,
            String body) {
        BoxBlur blur = new BoxBlur(3, 3, 3);
        if (controls.isEmpty()) {
            controls.add(new JFXButton("Okay"));
        }
        JFXDialogLayout dialogLayout = new JFXDialogLayout();
        JFXDialog dialog = new JFXDialog(root, dialogLayout, JFXDialog.DialogTransition.TOP);

        controls.forEach(controlButton -> {
            controlButton.getStyleClass().add("profile-dialog-button");
            controlButton.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent mouseEvent) -> {
                dialog.close();
            });
        });

        dialogLayout.setHeading(new Label(header));
        dialogLayout.setBody(new Label(body));
        dialogLayout.setActions(controls);
        dialog.show();
        dialog.setOnDialogClosed((JFXDialogEvent event1) -> {
            nodeToBeBlurred.setEffect(null);
        });
        nodeToBeBlurred.setEffect(blur);
    }

    // public void profileButton(ActionEvent event) throws IOException {
    // // don't use
    // }

    // public void globalButton(ActionEvent event) throws IOException {
    // LogManager.changeScene("profile", "global");
    // FXMLLoader loader = new
    // FXMLLoader(getClass().getResource("fxml/global.fxml"));
    // root = loader.load();
    // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // scene = new Scene(root);
    // stage.setScene(scene);
    // stage.show();
    // }

    // public void countryButton(ActionEvent event) throws IOException {
    // LogManager.changeScene("profile", "country");
    // FXMLLoader loader = new
    // FXMLLoader(getClass().getResource("fxml/country.fxml"));
    // root = loader.load();
    // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // scene = new Scene(root);
    // stage.setScene(scene);
    // stage.show();
    // }

    // public void cityButton(ActionEvent event) throws IOException {
    // LogManager.changeScene("profile", "city");
    // FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/city.fxml"));
    // root = loader.load();
    // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // scene = new Scene(root);
    // stage.setScene(scene);
    // stage.show();
    // }

    // public void aboutUsButton(ActionEvent event) throws IOException {
    // LogManager.changeScene("profile", "aboutUs");
    // root = FXMLLoader.load(getClass().getResource("fxml/aboutUs.fxml"));
    // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // String css =
    // this.getClass().getResource("styles/profile.css").toExternalForm();
    // scene = new Scene(root);
    // scene.getStylesheets().add(css);
    // stage.setScene(scene);
    // stage.show();
    // }

    // public void logoutButton(ActionEvent event) throws IOException {
    // LogManager.changeScene("profile", "logout");
    // root =
    // FXMLLoader.load(getClass().getResource("fxml/logoutConfirmation.fxml"));
    // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // scene = new Scene(root);
    // stage.setScene(scene);
    // stage.show();
    // }

}
