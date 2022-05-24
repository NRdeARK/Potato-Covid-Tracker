package com.openjfx;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.animation.Transition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

public class MainPageController implements Initializable {

    @FXML
    private AnchorPane Bg;

    @FXML
    private Circle BgCircle1;

    @FXML
    private Circle BgCircle2;

    @FXML
    private ImageView BgImage;

    @FXML
    private Pane InnerLeftPane;

    @FXML
    private AnchorPane LeftPane;

    @FXML
    private JFXHamburger Hamberger;

    @FXML
    private JFXDrawer MainMenuDrawer;

    @FXML
    private JFXDrawer SubMenuDrawer;

    @FXML
    private Pane middlePane;

    private boolean mainMenuActive;
    private boolean subMenuActive;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        try {

            Image backgroundImage = new Image(getClass().getResourceAsStream("images/mainpage/bg.png"));
            BgImage.setImage(backgroundImage);

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

                            }

                        }
                    });
                }
            }

        } catch (IOException e1) {
            e1.printStackTrace();
        }

    }

}
