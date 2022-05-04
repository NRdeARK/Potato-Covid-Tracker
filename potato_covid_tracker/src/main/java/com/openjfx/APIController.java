package com.openjfx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

public class APIController implements Initializable{

    @FXML
    private Label cured;

    @FXML
    private Label dead;

    @FXML
    private Label infect;

    private final String countryAPI = "https://covid19.ddc.moph.go.th/api/Cases/";

    private final String cityAPI = "https://covid19.ddc.moph.go.th/api/Cases/timeline-cases-by-provinces";

    public void api() throws MalformedURLException{
        APIConnector apiConnecter = new APIConnector(countryAPI);
        JSONArray jsonArray = apiConnecter.getJSONArray("timeline-cases-all");
        JSONObject jsonData = (JSONObject)(apiConnecter.getJSONArray("timeline-cases-all").get(jsonArray.size()-1));

        System.out.println(jsonData.toString());
        
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            api();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}
