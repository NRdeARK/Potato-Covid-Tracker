package com.openjfx;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
import javafx.scene.Node;

public class CountryController implements Initializable {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private APIController api = new APIController();
    private final String countryAPI = "https://covid19.ddc.moph.go.th/api/Cases/";
    String[][] monthlyData = api.getCountryMonthlyData();
    String[] modeList = {
            "all",
            "infected",
            "cured",
            "death"
    };
    XYChart.Series<Number, Number> seriesDeath = new XYChart.Series<>();
    XYChart.Series<Number, Number> seriesInfect = new XYChart.Series<>();
    XYChart.Series<Number, Number> seriesCure = new XYChart.Series<>();

    @FXML
    private Button CityButton;

    @FXML
    private Button CountryButton;

    @FXML
    private Button GlobalButton;

    @FXML
    private Button LogoutButton;

    @FXML
    private Button ProfileButton;

    @FXML
    private Button AboutUsButton;

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
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayUsername();
        modeLabel.setText("Country");
        displayDailyData();
        
        countryChart.setTitle("Covid-19 30 days");
        countryChart.getData().add(seriesInfect);
        countryChart.getData().add(seriesCure);
        countryChart.getData().add(seriesDeath);
        modeComboBox.getItems().addAll(modeList);

        displayChart("all");

        modeComboBox.setOnAction(event -> {
            try {
                
                chooseGraph(event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
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
        String updateDate = monthlyData[0][6];
        dailyInfect.setText("Today infected : " + String.valueOf(totalInf - inf) + " + " + String.valueOf(inf));
        dailyDeath.setText("Today death : " + String.valueOf(totalDead - dead) + " + " + String.valueOf(dead));
        dailyCure.setText("Today cure : " + String.valueOf(totalCure - cure) + " + " + String.valueOf(cure));
        dateUpdateLabel.setText("update date: " + updateDate);
    }

    public void displayChart() {
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
        //     countryChart.getData().add(seriesInfect);
        //     countryChart.getData().add(seriesCure);
        //     countryChart.getData().add(seriesDeath);
        // }
        // if (mode.equals("infected")) {
        //     countryChart.getData().add(seriesInfect);

        // }
        // if (mode.equals("cured")) {
        //     countryChart.getData().add(seriesCure);

        // }
        // if (mode.equals("death")) {
        //     countryChart.getData().add(seriesDeath);
        // }
    }

    @FXML
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

    public void profileButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void globalButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/global.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void countryButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/country.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cityButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/city.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logoutButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/logoutConfirmation.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void aboutUsButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/aboutUs.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}
