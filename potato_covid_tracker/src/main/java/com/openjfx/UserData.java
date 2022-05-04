package com.openjfx;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//import javafx.scene.shape.Path;

public class UserData {

    public static int verifyLogin(String username, String password) throws IOException {
        String line = null;
        File f1 = new File("userLogin.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            String readUsername = line.split(" ")[1];
            String readPassword = line.split(" ")[2];
            if (readUsername.equals(username) && readPassword.equals(password)) {
                fr.close();
                br.close();
                return Integer.parseInt(line.split(" ")[0]);
            }
        }
        fr.close();
        br.close();
        return -1;
    }

    // return user information
    public static String getUserInfo(int userID) throws IOException {
        String line = null;
        File f1 = new File("userInfo.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            int readID = Integer.parseInt(line.split(" ")[0]);
            if (userID == readID) {
                fr.close();
                br.close();
                return line;
            }
        }
        fr.close();
        br.close();
        return "ERROR(getUserInfo)";
    }

    public static String getUsername(int userID) throws IOException{
        String userInfo = getUserInfo(userID);
        String username = userInfo.split(" ")[1];
        return username;
    }
}