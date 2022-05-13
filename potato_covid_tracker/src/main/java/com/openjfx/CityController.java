package com.openjfx;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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
            infectLabel.setText((Integer.parseInt(cityData[1]) - Integer.parseInt(cityData[0]))+ " + " + cityData[0]);
            deathLabel.setText((Integer.parseInt(cityData[3]) - Integer.parseInt(cityData[2]))+ " + " + cityData[2]);
             dateLabel.setText(cityData[4]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void chooseCity(ActionEvent event) throws IOException{
        String cityName = cityComboBox.getValue();
        APIController api = new APIController();
        int userID = api.getCityIDfromName(cityName);
        displayCityData(userID);
    }

    public void globalButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/global.fxml"));
        root = loader.load();
        GlobalController globalController = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void countryButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/country.fxml"));
        root = loader.load();
        CountryController countryController = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void cityButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/city.fxml"));
        root = loader.load();
        CityController cityController = loader.getController();
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void profileButton(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/profile.fxml"));
        root = loader.load();
        ProfileController profileController = loader.getController();
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

