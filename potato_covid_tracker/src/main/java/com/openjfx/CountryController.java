package com.openjfx;

import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
    
    String[][] monthlyData = api.getCountryMonthlyData();

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
    private AreaChart<Number, Number> countryChart;

    @FXML
    private NumberAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    public void initialize(URL url ,ResourceBundle  resourceBundle){
        displayUsername();
        modeLabel.setText("Country");
        displayDailyData();
        displayChart();
        
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

    public void profileButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void logoutButton(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("fxml/launch.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void displayDailyData() {
        int inf = Integer.parseInt(monthlyData[0][0]);
        int totalInf = Integer.parseInt(monthlyData[0][1]);
        int dead = Integer.parseInt(monthlyData[0][2]);
        int totalDead = Integer.parseInt(monthlyData[0][3]);
        int cure = Integer.parseInt(monthlyData[0][4]);
        int totalCure = Integer.parseInt(monthlyData[0][5]);

        dailyInfect.setText("inf : "+ String.valueOf(totalInf-inf)+" + "+String.valueOf(inf));
        dailyDeath.setText("death : "+String.valueOf(totalDead-dead)+" + "+String.valueOf(dead));
        dailyCure.setText("cure : "+String.valueOf(totalCure-cure)+" + "+String.valueOf(cure));
    }

    public void displayChart(){
        System.out.println("chart loading.....");
        
        // xAxis = new NumberAxis(1, 30, 1);
        // yAxis = new NumberAxis(0,10,100);

        yAxis.setLabel("pop");
        xAxis.setLabel("day");

        // countryChart = new StackedAreaChart<Number,Number>(xAxis,yAxis);
        
        countryChart.setTitle("Covid-19 30 days");
        System.out.println("call api");
        // String [][] month = api.getCountryMonthlyData();
        System.out.println("finish call api");
        XYChart.Series<Number,Number> seriesDeath = new XYChart.Series<Number,Number>();
        XYChart.Series<Number,Number> seriesInfect = new XYChart.Series<>();
        XYChart.Series<Number,Number> seriesCure = new XYChart.Series<>();

        for (int i = 0; i < 30; i++) {
            seriesDeath.getData().add(new XYChart.Data<Number, Number>(i + 1, Integer.parseInt(monthlyData[29-i][3])));
            // System.out.println( i+1 + " " +Integer.parseInt(month[i][3]));
            seriesInfect.getData().add(new XYChart.Data<Number, Number>(i + 1, Integer.parseInt(monthlyData[29-i][1])));
            seriesCure.getData().add(new XYChart.Data<Number, Number>(i + 1,Integer.parseInt(monthlyData[29-i][5])));
            // seriesDeath.setName("death");
            // seriesDeath.getData().add(new XYChart.Data<Number, Number>(i,i));
        }

        // System.out.println(seriesDeath.getData().toString()); 
        countryChart.getData().add(seriesInfect);
        countryChart.getData().add(seriesCure);
        countryChart.getData().add(seriesDeath);
    }
}

