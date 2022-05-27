package com.openjfx;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;

public class CityController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private String[] cityList = {
            "Krabi",
            "Bangkok",
            "Kanchanaburi",
            "Kalasin",
            "Kamphaeng Phet",
            "Khon Kaen",
            "Chanthaburi",
            "Chachoengsao",
            "Chonburi",
            "Chainat",
            "Chaiyaphum",
            "Chumphon",
            "Trang",
            "Trad",
            "Tak",
            "Nakhon Nayok",
            "Nakhon Pathom",
            "Nakhon Phanom",
            "Nakhon Ratchasima",
            "Nakhon Si Thammarat",
            "Nakhon Sawan",
            "Nonthaburi",
            "Narathiwat",
            "Nan",
            "Bueng Kan",
            "Buriram",
            "Pathum Thani",
            "Prachuap Khiri Khan",
            "Prachinburi",
            "Pattani",
            "Phra Nakhon Si Ayutthaya",
            "Phayao",
            "Phang Nga",
            "Phatthalung",
            "Phichit",
            "Phitsanulok",
            "Phuket",
            "Maha Sarakham",
            "Mukdahan",
            "Yala",
            "Yasothon",
            "Ranong",
            "Rayong",
            "Ratchaburi",
            "Roi Et",
            "Lopburi",
            "Lampang",
            "Lamphun",
            "Sisaket",
            "Sakon Nakhon",
            "Songkhla",
            "Satun",
            "Samut Prakan",
            "Samut Songkhram",
            "Samut Sakhon",
            "Saraburi",
            "Sa Kaeo",
            "Singburi",
            "Suphan Buri",
            "Surat Thani",
            "Surin",
            "Sukhothai",
            "Nong Khai",
            "Nong Bua Lamphu",
            "Amnat Charoen",
            "Udon Thani",
            "Uttaradit",
            "Uthai Thani",
            "Ubon Ratchathani",
            "Ang Thong",
            "Chiang Rai",
            "Chiang Mai",
            "Phetchaburi",
            "Phetchabun",
            "Loei",
            "Phrae",
            "Mae Hong Son",
    };

    @FXML
    private Button UpdateButton;

    @FXML
    private ComboBox<String> CityComboBox;

    @FXML
    private Label DeathLabel;

    @FXML
    private Label InfectLabel;

    @FXML
    private Label ModeLabel;

    @FXML
    private Label UsernameLabel;

    @FXML
    private Label DateLabel;

    @FXML
    private JFXHamburger Hamberger;

    @FXML
    private JFXDrawer MainMenuDrawer;

    @FXML
    private JFXDrawer SubMenuDrawer;

    @FXML
    private AnchorPane rootAnchorPane;

    @FXML
    private StackPane rootpane;

    @FXML
    private Label totalDeath;

    @FXML
    private Label totalInfected;

    private boolean mainMenuActive;
    private boolean subMenuActive;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // displayUsername();
        displayCityData(1);
        CityComboBox.getItems().addAll(cityList);
        CityComboBox.setOnAction(event -> {
            try {
                chooseCity(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        try {

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
                                    LogManager.changeScene("city", "profile");
                                    FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
                                    root = loader.load();
                                    stage = (Stage) ((Node) ev.getSource()).getScene().getWindow();
                                    scene = new Scene(root);
                                    stage.setScene(scene);
                                    stage.show();
                                    System.out.println("Profile Pressed");
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

                            case "AboutUs": {
                                try {
                                    LogManager.changeScene("city", "aboutUs");
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
                                    LogManager.changeScene("city", "logout");
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
                                    LogManager.changeScene("city", "country");
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
                                    LogManager.changeScene("city", "city");
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

    @FXML
    public void displayUsername() {
        try {
            String username = UserData.getUsername(LogManager.getUserIDFromLastLog());
            UsernameLabel.setText("username: " + username);
            System.out.println("display!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void displayCityData(int CityID) {
        try {
            APIController api = new APIController();
            String[] cityData = api.getCityDailyData(CityID);
            ModeLabel.setText(cityData[5]);
            InfectLabel.setText("+" + cityData[0]);
            DeathLabel.setText("+" + cityData[2]);
            DateLabel.setText("Update date: " + cityData[4]);
            totalInfected.setText(""+(Integer.parseInt(cityData[1])));
            totalDeath.setText(""+Integer.parseInt(cityData[3]));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateButton(ActionEvent event) throws IOException {
        System.out.println("start update city data");
        UpdateButton.setText();
        List<String> lines = new ArrayList<String>();
        APIConnector apiConnecter = new APIConnector("https://covid19.ddc.moph.go.th/api/Cases/");
        JSONArray jsonArray = apiConnecter.getJSONArray("today-cases-by-provinces");
        for (int i = 0; i < 78; i++) {
            System.out.println("data : " + (i + 1) + "/78");
            JSONObject jsonData = (JSONObject) (apiConnecter.getJSONArray("today-cases-by-provinces")
                    .get(jsonArray.size() - 1 - i));
            lines.add(jsonData.toString());
        }
        File f1 = new File("cityData.txt");
        FileWriter fw = new FileWriter(f1);
        BufferedWriter out = new BufferedWriter(fw);
        for (String s : lines) {
            out.write(s);
            out.newLine();
        }
        out.flush();
        out.close();
    }

    public void chooseCity(ActionEvent event) throws IOException {
        String cityName = CityComboBox.getValue();
        APIController api = new APIController();
        int cityID = api.getCityIDfromName(cityName);
        displayCityData(cityID);
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
            controlButton.getStyleClass().add("city-dialog-button");
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
    //     LogManager.changeScene("city", "profile");
    //     FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
    //     root = loader.load();
    //     stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    //     scene = new Scene(root);
    //     stage.setScene(scene);
    //     stage.show();
    // }
}
//     public void globalButton(ActionEvent event) throws IOException {
//         LogManager.changeScene("city", "global");
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/global.fxml"));
//         root = loader.load();
//         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         scene = new Scene(root);
//         stage.setScene(scene);
//         stage.show();
//     }

//     public void countryButton(ActionEvent event) throws IOException {
//         LogManager.changeScene("city", "country");
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/country.fxml"));
//         root = loader.load();
//         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         scene = new Scene(root);
//         stage.setScene(scene);
//         stage.show();
//     }

//     public void cityButton(ActionEvent event) throws IOException {
//         LogManager.changeScene("city", "city");
//         FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/city.fxml"));
//         root = loader.load();
//         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         scene = new Scene(root);
//         stage.setScene(scene);
//         stage.show();
//     }

//     public void logoutButton(ActionEvent event) throws IOException {
//         LogManager.changeScene("city", "logoutConfirmation");
//         root = FXMLLoader.load(getClass().getResource("fxml/logoutConfirmation.fxml"));
//         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         scene = new Scene(root);
//         stage.setScene(scene);
//         stage.show();
//     }

//     public void aboutUsButton(ActionEvent event) throws IOException {
//         LogManager.changeScene("city", "aboutUs");
//         root = FXMLLoader.load(getClass().getResource("fxml/aboutUs.fxml"));
//         stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//         scene = new Scene(root);
//         stage.setScene(scene);
//         stage.show();
//     }

// }
