package com.openjfx;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.File; // Import the File class
import java.io.FileNotFoundException; // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files



public class LoginController {

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;

    File myObj = new File("filename.txt");

    @FXML
    public void login(ActionEvent event) throws IOException {
        String username = usernameTextField.getText();
        String password = passwordPasswordField.getText();
        System.out.println(checkLogin(username, password));
    }

    public int checkLogin(String username, String password) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream("user/userLogin.txt")));
        String line;
        while ((line = reader.readLine()) != null) {
            String readUsername = line.split(" ")[1];
            String readPassword = line.split(" ")[2];
            int readID = Integer.parseInt(line.split(" ")[0]);
            if(readUsername.equals(username) && readPassword.equals(password)) {
                reader.close();
                return readID;
            }

        }
        reader.close();    
        return -1;
    }
}