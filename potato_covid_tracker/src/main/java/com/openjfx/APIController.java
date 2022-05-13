package com.openjfx;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

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

    public boolean updateContryData() throws IOException {
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
        return true;
    }

    public boolean updateCityData() throws IOException {
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
        return true;
    }

    public String[] getCountryDailyData() throws IOException {
        // String [] dailyData;
        String line = null;
        File f1 = new File("countryData.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        if ((line = br.readLine()) != null) {
            fr.close();
            br.close();
        }
        fr.close();
        br.close();
        String[] arr = line.split(",");
        // {
        // 0"total_case_excludeabroad":4328304,
        // 1"new_recovered":8807,
        // 2"txn_date":"2022-05-12",
        // 3"total_death":29311,
        // 4"new_death":59,
        // 5"total_case":4353237,
        // 6"total_recovered":4246499,
        // 7"new_case_excludeabroad":8016,
        // 8"update_date":"2022-05-12 07:37:18",
        // 9"new_case":8019
        // }
        String[] dailyData = {
                // case
                arr[9].split(":")[1].replace("}", ""),
                arr[5].split(":")[1],
                // death
                arr[4].split(":")[1],
                arr[3].split(":")[1],
                // cure
                arr[1].split(":")[1],
                arr[6].split(":")[1],
                // date
                arr[8].split(":")[1],
        };
        return dailyData;
    }

    public String[][] getCountryWeeklyData() throws IOException {

        String[][] weeklyData = new String[7][7];
        String line = null;
        File f1 = new File("countryData.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        for (int i = 0; i < 7; i++) {
            line = br.readLine();
            String[] arr = line.split(",");
            // case
            weeklyData[i][0] = arr[9].split(":")[1].replace("}", "");
            weeklyData[i][1] = arr[5].split(":")[1];
            // death
            weeklyData[i][2] = arr[4].split(":")[1];
            weeklyData[i][3] = arr[3].split(":")[1];
            // cure
            weeklyData[i][4] = arr[1].split(":")[1];
            weeklyData[i][5] = arr[6].split(":")[1];
            // date
            weeklyData[i][6] = arr[8].split(":")[1];
            System.out.println("case " + weeklyData[i][0] + " : " + weeklyData[i][1]);
            System.out.println("death " + weeklyData[i][2] + " : " + weeklyData[i][3]);
            System.out.println("cure " + weeklyData[i][4] + " : " + weeklyData[i][5]);
            System.out.println("date " + weeklyData[i][6]);

        }
        return weeklyData;
    }

    public String[][] getCountryMonthlyData() {
        String[][] monthlyData = new String[30][7];
        String line = null;
        try {

            File f1 = new File("countryData.txt");
            FileReader fr = new FileReader(f1);
            BufferedReader br = new BufferedReader(fr);
            for (int i = 0; i < 30; i++) {
                line = br.readLine();
                String[] arr = line.split(",");
                // case
                monthlyData[i][0] = arr[9].split(":")[1].replace("}", "");
                monthlyData[i][1] = arr[5].split(":")[1];
                // death
                monthlyData[i][2] = arr[4].split(":")[1];
                monthlyData[i][3] = arr[3].split(":")[1];
                // cure
                monthlyData[i][4] = arr[1].split(":")[1];
                monthlyData[i][5] = arr[6].split(":")[1];
                // date
                monthlyData[i][6] = arr[8].split(":")[1];
                System.out.println("case " + monthlyData[i][0] + " : " + monthlyData[i][1]);
                System.out.println("death " + monthlyData[i][2] + " : " + monthlyData[i][3]);
                System.out.println("cure " + monthlyData[i][4] + " : " + monthlyData[i][5]);
                System.out.println("date " + monthlyData[i][6]);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return monthlyData;
    }

    public String getCityNameFromID(int cityID) throws IOException {
        String cityName;
        String line = null;
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("txt/provinceOrder.txt")));
        for (int i = 0; i < cityID; i++) {
            line = reader.readLine();
        }
        reader.close();
        cityName = line.split(" ")[1];
        return cityName;
    }

    public int getCityIDfromName(String cityName) throws IOException {
        int cityID = 0;
        String line = null;
        String cityList = "";
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("txt/provinceOrder.txt")));
        while (!cityList.equals(cityName) && (line = reader.readLine()) != null) {
            cityList = line.split("\"")[1].split("\"")[0];
            cityID = Integer.parseInt(line.split(" \"")[0]);
        }
        reader.close();
        return cityID;
    }

    public String[] getCityDailyData(int cityID) throws IOException {
        String line = null;
        File f1 = new File("cityData.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        for (int i = 0; i < 79 - cityID; i++) {
            line = br.readLine();
        }
        String[] arr = line.split(",");
        // {
        // 0"total_case_excludeabroad":8963,
        // 1"txn_date":"2022-05-13",
        // 2"total_death":51,
        // 3"province":"????????",
        // 4"new_death":0,
        // 5"total_case":8969,
        // 6"new_case_excludeabroad":20,
        // 7"update_date":"2022-05-13 07:35:56",
        // 8"new_case":20
        // }
        System.out.println(line);
        String[] dailyData = {
                // case
                arr[8].split(":")[1].replace("}", ""),
                arr[5].split(":")[1],
                // death
                arr[4].split(":")[1],
                arr[2].split(":")[1],
                // date
                arr[1].split(":")[1],
                // province name
                getCityNameFromID(cityID)
        };
        System.out.println(getCityNameFromID(cityID));
        return dailyData;
    }
}
