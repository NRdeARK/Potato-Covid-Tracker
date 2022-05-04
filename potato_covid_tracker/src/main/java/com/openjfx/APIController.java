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

public class APIController {

    @FXML
    private Label cured;

    @FXML
    private Label dead;

    @FXML
    private Label infect;

    private final String countryAPI = "https://covid19.ddc.moph.go.th/api/Cases/";

    private final String cityAPI = "https://covid19.ddc.moph.go.th/api/Cases/"; // timeline-cases-by-provinces

    public void api() throws MalformedURLException {
        APIConnector apiConnecter = new APIConnector(countryAPI);
        JSONArray jsonArray = apiConnecter.getJSONArray("timeline-cases-all");
        JSONObject jsonData = (JSONObject) (apiConnecter.getJSONArray("timeline-cases-all").get(jsonArray.size() - 1));

        System.out.println(jsonData.toString());

    }

    public String[] getCountryDailyData() {
        // String [] dailyData;
        try {
            APIConnector apiConnecter = new APIConnector(countryAPI);
            JSONArray jsonArray = apiConnecter.getJSONArray("timeline-cases-all");
            JSONObject jsonData = (JSONObject) (apiConnecter.getJSONArray("timeline-cases-all")
                    .get(jsonArray.size() - 1));
            // {
            // 0"txn_date":"2022-05-04",
            // 1"new_case":9288,
            // 2"total_case":4290824,
            // 3"new_case_excludeabroad":9268,
            // 4"total_case_excludeabroad":4265927,
            // 5"new_death":82,
            // 6"total_death":28860,
            // 7"new_recovered":19119,
            // 8"total_recovered":4153310,
            // 9"update_date":"2022-05-04 07:35:09"
            // }
            String[] dailyData = {
                    jsonData.get("new_case").toString(),
                    jsonData.get("total_case").toString(),
                    jsonData.get("new_death").toString(),
                    jsonData.get("total_death").toString(),
                    jsonData.get("new_recovered").toString(),
                    jsonData.get("total_recovered").toString()
            };
            return dailyData;
        } catch (Exception e) {
            String[] dailyData = {};

            e.printStackTrace();

            return dailyData;
        }

    }

    public String[][] getCountryWeeklyData() {
        String[][] weeklyData = new String[7][6];
        try {
            APIConnector apiConnecter = new APIConnector(countryAPI);
            JSONArray jsonArray = apiConnecter.getJSONArray("timeline-cases-all");
            for (int i = 0; i < 7; i++) {
                JSONObject jsonData = (JSONObject) (apiConnecter.getJSONArray("timeline-cases-all")
                        .get(jsonArray.size() - 1 - i));
                weeklyData[i][0] = jsonData.get("new_case").toString();
                weeklyData[i][1] = jsonData.get("total_case").toString();
                weeklyData[i][2] = jsonData.get("new_death").toString();
                weeklyData[i][3] = jsonData.get("total_death").toString();
                weeklyData[i][4] = jsonData.get("new_recovered").toString();
                weeklyData[i][5] = jsonData.get("total_recovered").toString();
            }
            return weeklyData;
        } catch (Exception e) {
            e.printStackTrace();
            return weeklyData;
        }

    }

    public String[][] getCountryMonthlyData() {
        String[][] monthlyData = new String[30][6];
        try {
            APIConnector apiConnecter = new APIConnector(countryAPI);
            JSONArray jsonArray = apiConnecter.getJSONArray("timeline-cases-all");
            for (int i = 0; i < 30; i++) {
                JSONObject jsonData = (JSONObject) (apiConnecter.getJSONArray("timeline-cases-all")
                        .get(jsonArray.size() - 1 - i));
                monthlyData[i][0] = jsonData.get("new_case").toString();
                monthlyData[i][1] = jsonData.get("total_case").toString();
                monthlyData[i][2] = jsonData.get("new_death").toString();
                monthlyData[i][3] = jsonData.get("total_death").toString();
                monthlyData[i][4] = jsonData.get("new_recovered").toString();
                monthlyData[i][5] = jsonData.get("total_recovered").toString();
            }
            return monthlyData;
        } catch (Exception e) {
            e.printStackTrace();
            return monthlyData;
        }
    }
}

