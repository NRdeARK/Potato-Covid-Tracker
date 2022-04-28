package com.openjfx;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class UserData {

    public int verifyLogin(String username, String password) throws IOException {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(getClass().getResourceAsStream("user/userLogin.txt")));
        String line;
        while ((line = reader.readLine()) != null) {
            String readUsername = line.split(" ")[1];
            String readPassword = line.split(" ")[2];
            if(readUsername.equals(username) && readPassword.equals(password)) {
                reader.close();
                return Integer.parseInt(line.split(" ")[0]);
                //return id
            }
        }
        reader.close();    
        return -1;
    }
    //return user information
    public String getUserInfo(int userID) throws IOException{
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(getClass().getResourceAsStream("user/userInfo.txt")));
        String line;
        while ((line = reader.readLine()) != null) {
            int readID = Integer.parseInt(line.split(" ")[0]);
            if(userID == readID) {
                reader.close();
                return line;
            }
        }
        reader.close();    
        return "ERROR";
    }
}
