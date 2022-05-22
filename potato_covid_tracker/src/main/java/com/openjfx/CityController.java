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
import javafx.stage.Stage;
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
    private final String cityAPI = "https://covid19.ddc.moph.go.th/api/Cases/";

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
    private Button updateButton;

    @FXML
    private ComboBox<String> cityComboBox;

    @FXML
    private Label deathLabel;

    @FXML
    private Label infectLabel;

    @FXML
    private Label modeLabel;

    @FXML
    private Label usernameLabel;

    @FXML
    private Label dateLabel;

    


    @FXML
    public void initialize(URL url ,ResourceBundle  resourceBundle){
        displayUsername();
        displayCityData(1);
        cityComboBox.getItems().addAll(cityList);
        cityComboBox.setOnAction(event -> {
			try {
				chooseCity(event);
			} catch (IOException e) {
				e.printStackTrace();
			}
		});
        
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

    @FXML
    public void displayCityData(int CityID){
        try {
            APIController api = new APIController();
            String [] cityData = api.getCityDailyData(CityID);
            modeLabel.setText("city : " + cityData[5]);
            infectLabel.setText("infected: " + (Integer.parseInt(cityData[1]) - Integer.parseInt(cityData[0]))+ " + " + cityData[0]);
            deathLabel.setText("death: " + (Integer.parseInt(cityData[3]) - Integer.parseInt(cityData[2]))+ " + " + cityData[2]);
             dateLabel.setText("date update: " + cityData[4]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void updateButton(ActionEvent event) throws IOException {
        System.out.println("start update city data");
        List<String> lines = new ArrayList<String>();
        APIConnector apiConnecter = new APIConnector(cityAPI);
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

    public void chooseCity(ActionEvent event) throws IOException{
        String cityName = cityComboBox.getValue();
        APIController api = new APIController();
        int userID = api.getCityIDfromName(cityName);
        displayCityData(userID);
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
        root = FXMLLoader.load(getClass().getResource("fxml/launch.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }



}

