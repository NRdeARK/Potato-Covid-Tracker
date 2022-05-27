package com.openjfx;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.events.JFXDialogEvent;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import javafx.scene.image.ImageView;
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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.Node;

public class CountryController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private APIController api = new APIController();
    private final String countryAPI = "https://covid19.ddc.moph.go.th/api/Cases/";
    private String[][] monthlyData = api.getCountryMonthlyData();
    private String[] modeList = {
            "all",
            "infected",
            "cured",
            "death"
    };
    private XYChart.Series<Number, Number> seriesDeath = new XYChart.Series<>();
    private XYChart.Series<Number, Number> seriesInfect = new XYChart.Series<>();
    private XYChart.Series<Number, Number> seriesCure = new XYChart.Series<>();

    @FXML
    private Button updateButton;

    @FXML
    private Label dailyCure;

    @FXML
    private Label dailyDeath;

    @FXML
    private Label dailyInfect;

    @FXML
    private Label modeLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label dateUpdateLabel;

    @FXML
    private AreaChart<Number, Number> countryChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private ComboBox<String> modeComboBox;

    @FXML
    private ImageView BgImage;

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
    private Label totalCured;

    @FXML
    private Label totalDeath;

    @FXML
    private Label totalInfected;

    private boolean mainMenuActive;
    private boolean subMenuActive;

    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // displayUsername();
        modeLabel.setText("Country");
        displayDailyData();

        countryChart.setTitle("Covid-19 30 days");
        countryChart.getData().add(seriesInfect);
        countryChart.getData().add(seriesCure);
        countryChart.getData().add(seriesDeath);
        modeComboBox.getItems().addAll(modeList);

        displayChart("all");

        seriesInfect.setName("Infected");
        seriesDeath.setName("Death");
        seriesCure.setName("Cure");

        modeComboBox.setOnAction(event -> {
            try {

                chooseGraph(event);
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
                                    LogManager.changeScene("country", "profile");
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

                            case "AboutUs": {
                                try {
                                    LogManager.changeScene("country", "aboutUs");
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
                                    LogManager.changeScene("country", "logout");
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
                                    LogManager.changeScene("country", "country");
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
                                    LogManager.changeScene("country", "city");
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
    void chooseGraph(ActionEvent event) throws IOException {
        String modeName = modeComboBox.getValue();
        displayChart(modeName);
    }

    @FXML
    public void displayUsername() {
        try {
            String username = UserData.getUsername(LogManager.getUserIDFromLastLog());
            usernameLabel.setText("username: " + username);
            System.out.println("display!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void displayDailyData() {
        int inf = Integer.parseInt(monthlyData[0][0]);
        int totalInf = Integer.parseInt(monthlyData[0][1]);
        int dead = Integer.parseInt(monthlyData[0][2]);
        int totalDead = Integer.parseInt(monthlyData[0][3]);
        int cure = Integer.parseInt(monthlyData[0][4]);
        int totalCure = Integer.parseInt(monthlyData[0][5]);
        String updateDate = monthlyData[0][6].split(" ")[0].replace("\"", "");
        dailyInfect.setText("+" + String.valueOf(inf));
        dailyDeath.setText("+" + String.valueOf(dead));
        dailyCure.setText("+" + String.valueOf(cure));
        totalInfected.setText(String.valueOf(totalInf));
        totalCured.setText(String.valueOf(totalDead));
        totalDeath.setText(String.valueOf(totalCure));
        dateUpdateLabel.setText("Update date: " + updateDate);
    }

    public void displayChart() {
        System.out.println("chart loading.....");

        // xAxis = new NumberAxis(1, 30, 1);
        // yAxis = new NumberAxis(0,10,100);

        yAxis.setLabel("Population");
        xAxis.setLabel("Day");

        // countryChart = new StackedAreaChart<Number,Number>(xAxis,yAxis);

        System.out.println("call api");
        // String [][] month = api.getCountryMonthlyData();
        System.out.println("finish call api");
        seriesInfect.getData().clear();
        seriesDeath.getData().clear();
        seriesCure.getData().clear();

        for (int i = 0; i < 30; i++) {
            seriesDeath.getData()
                    .add(new XYChart.Data<Number, Number>(i + 1, Integer.parseInt(monthlyData[29 - i][3])));
            // System.out.println( i+1 + " " +Integer.parseInt(month[i][3]));
            seriesInfect.getData()
                    .add(new XYChart.Data<Number, Number>(i + 1, Integer.parseInt(monthlyData[29 - i][1])));
            seriesCure.getData().add(new XYChart.Data<Number, Number>(i + 1, Integer.parseInt(monthlyData[29 - i][5])));
            // seriesDeath.setName("death");
            // seriesDeath.getData().add(new XYChart.Data<Number, Number>(i,i));
        }

        // System.out.println(seriesDeath.getData().toString());
        // countryChart.getData().add(seriesInfect);
        // countryChart.getData().add(seriesCure);
        // countryChart.getData().add(seriesDeath);
    }

    public void displayChart(String mode) {
        System.out.println("chart loading.....");

        // xAxis = new NumberAxis(1, 30, 1);
        // yAxis = new NumberAxis(0,10,100);

        yAxis.setLabel("pop");
        xAxis.setLabel("day");

        // countryChart = new StackedAreaChart<Number,Number>(xAxis,yAxis);

        System.out.println("call api");
        // String [][] month = api.getCountryMonthlyData();
        System.out.println("finish call api");
        seriesInfect.getData().clear();
        seriesDeath.getData().clear();
        seriesCure.getData().clear();
        for (int i = 0; i < 30; i++) {
            if (mode.equals("all")) {
                seriesDeath.getData()
                        .add(new XYChart.Data<Number, Number>(i + 1, Integer.parseInt(monthlyData[29 - i][3])));
                seriesInfect.getData()
                        .add(new XYChart.Data<Number, Number>(i + 1, Integer.parseInt(monthlyData[29 - i][1])));
                seriesCure.getData()
                        .add(new XYChart.Data<Number, Number>(i + 1, Integer.parseInt(monthlyData[29 - i][5])));
            }
            if (mode.equals("infected")) {
                seriesInfect.getData()
                        .add(new XYChart.Data<Number, Number>(i + 1, Integer.parseInt(monthlyData[29 - i][1])));

            }
            if (mode.equals("cured")) {
                seriesCure.getData()
                        .add(new XYChart.Data<Number, Number>(i + 1, Integer.parseInt(monthlyData[29 - i][5])));
            }
            if (mode.equals("death")) {
                seriesDeath.getData()
                        .add(new XYChart.Data<Number, Number>(i + 1, Integer.parseInt(monthlyData[29 - i][3])));

            }

            // seriesDeath.setName("death");
            // seriesDeath.getData().add(new XYChart.Data<Number, Number>(i,i));
        }

        // System.out.println(seriesDeath.getData().toString());

        // if (mode.equals("all")) {
        // countryChart.getData().add(seriesInfect);
        // countryChart.getData().add(seriesCure);
        // countryChart.getData().add(seriesDeath);
        // }
        // if (mode.equals("infected")) {
        // countryChart.getData().add(seriesInfect);

        // }
        // if (mode.equals("cured")) {
        // countryChart.getData().add(seriesCure);

        // }
        // if (mode.equals("death")) {
        // countryChart.getData().add(seriesDeath);
        // }
    }

    public void updateButton(ActionEvent event) throws IOException {
        System.out.println("start update country data");
        List<String> lines = new ArrayList<String>();
        APIConnector apiConnecter = new APIConnector(countryAPI);
        JSONArray jsonArray = apiConnecter.getJSONArray("timeline-cases-all");
        for (int i = 0; i < 30; i++) {
            System.out.println("data : " + (i + 1) + "/30");
            JSONObject jsonData = (JSONObject) (apiConnecter.getJSONArray("timeline-cases-all")
                    .get(jsonArray.size() - 1 - i));
            lines.add(jsonData.toString());
        }

        File f1 = new File("countryData.txt");
        FileWriter fw = new FileWriter(f1);
        BufferedWriter out = new BufferedWriter(fw);
        for (String s : lines) {
            out.write(s);
            out.newLine();
        }
        out.flush();
        out.close();
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
            controlButton.getStyleClass().add("country-dialog-button");
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
    // LogManager.changeScene("country", "profile");
    // FXMLLoader loader = new
    // FXMLLoader(getClass().getResource("fxml/profile.fxml"));
    // root = loader.load();
    // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // scene = new Scene(root);
    // stage.setScene(scene);
    // stage.show();
    // }

    // public void globalButton(ActionEvent event) throws IOException {
    // LogManager.changeScene("country", "global");
    // FXMLLoader loader = new
    // FXMLLoader(getClass().getResource("fxml/global.fxml"));
    // root = loader.load();
    // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // scene = new Scene(root);
    // stage.setScene(scene);
    // stage.show();
    // }

    // public void countryButton(ActionEvent event) throws IOException {
    // LogManager.changeScene("country", "country");
    // FXMLLoader loader = new
    // FXMLLoader(getClass().getResource("fxml/country.fxml"));
    // root = loader.load();
    // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // scene = new Scene(root);
    // stage.setScene(scene);
    // stage.show();
    // }

    // public void cityButton(ActionEvent event) throws IOException {
    // LogManager.changeScene("country", "city");
    // FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/city.fxml"));
    // root = loader.load();
    // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // scene = new Scene(root);
    // stage.setScene(scene);
    // stage.show();
    // }

    // public void logoutButton(ActionEvent event) throws IOException {
    // LogManager.changeScene("country", "logoutConfirmation");
    // root =
    // FXMLLoader.load(getClass().getResource("fxml/logoutConfirmation.fxml"));
    // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // scene = new Scene(root);
    // stage.setScene(scene);
    // stage.show();
    // }

    // public void aboutUsButton(ActionEvent event) throws IOException {
    // LogManager.changeScene("country", "aboutUs");
    // root = FXMLLoader.load(getClass().getResource("fxml/aboutUs.fxml"));
    // stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    // scene = new Scene(root);
    // stage.setScene(scene);
    // stage.show();
    // }

}
