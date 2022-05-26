package com.openjfx;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

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

    public static String getFirstname(int userID) throws IOException{
        String userInfo = getUserInfo(userID);
        String username = userInfo.split(" ")[2];
        return username;
    }

    public static String getLastname(int userID) throws IOException{
        String userInfo = getUserInfo(userID);
        String username = userInfo.split(" ")[3];
        return username;
    }

    public static String getGender(int userID) throws IOException{
        String userInfo = getUserInfo(userID);
        String username = userInfo.split(" ")[4];
        return username;
    }

    public static String getVaccineDose(int userID) throws IOException{
        String userInfo = getUserInfo(userID);
        String username = userInfo.split(" ")[5];
        return username;
    }

    public static String getLastVaccinatedDate(int userID) throws IOException{
        String userInfo = getUserInfo(userID);
        String username = userInfo.split(" ")[6];
        return username;
    }

    public static String getProfilePicture(int userID) throws IOException{
        String userInfo = getUserInfo(userID);
        //"profile/normalPotato.png"
        String username = "profile/" + userInfo.split(" ")[7];
        return username;
    }

    public static int createNewUser1(String username , String password) throws IOException{
        List<String> lines = new ArrayList<String>();
        String line = null;
        File f1 = new File("userLogin.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        fr.close();
        br.close();

        FileWriter fw = new FileWriter(f1);
        BufferedWriter out = new BufferedWriter(fw);
        int userID = lines.size()-1;

        out.write(userID + " " + username + " " + password);
        out.newLine();
        for (String s : lines) {
            out.write(s);
            out.newLine();
        }
        out.flush();
        out.close();
        return userID;
    }

    public static int createNewUser2(int userID, String firstname ,String lastname ,String gender ,String dose ,String vaccinatedDate, String profileName) throws IOException{
        List<String> lines = new ArrayList<String>();
        String line = null;
        File f1 = new File("userInfo.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        fr.close();
        br.close();

        FileWriter fw = new FileWriter(f1);
        BufferedWriter out = new BufferedWriter(fw);
        out.write(userID + " " + getNewestUsername() + " " + firstname + " " + lastname + " " + gender + " " + dose + " " + vaccinatedDate + " " + profileName);
        out.newLine();
        for (String s : lines) {
            out.write(s);
            out.newLine();
        }
        out.flush();
        out.close();
        return userID;
    }
    
    public static int getUserID(String username) throws IOException{
        String line = null;
        File f1 = new File("userLogin.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            String readUsername = line.split(" ")[1];
            if (username.equals(readUsername)) {
                fr.close();
                br.close();
                return Integer.parseInt(line.split(" ")[0]);
            }
        }
        fr.close();
        br.close();
        return -1;
    }
    
    public static void deleteNewUser1() throws IOException{
        List<String> lines = new ArrayList<String>();
        String line = null;
        File f1 = new File("userLogin.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        fr.close();
        br.close();

        FileWriter fw = new FileWriter(f1);
        BufferedWriter out = new BufferedWriter(fw);
        int i = 0;
        for (String s : lines) {
            if(i!=0){
                out.write(s);
                out.newLine();
            }
            i++;
        }
        out.flush();
        out.close();
    }
    
    public static int getNumberLine(String fileName) throws IOException{
        List<String> lines = new ArrayList<String>();
        String line = null;
        File f1 = new File(fileName);
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        fr.close();
        br.close();
        return lines.size();

    }

    public static boolean isDuplicateFile(String fileName) throws IOException {
        String line = null;
        File f1 = new File("userInfo.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            String readFileName = line.split(" ")[7];
            if (fileName.equals(readFileName)) {
                fr.close();
                br.close();
                return true;
            }
        }
        fr.close();
        br.close();
        return false;
    }

    private static String getNewestUsername() throws IOException{
        String line = null;
        File f1 = new File("userLogin.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        line = br.readLine();
        String readUsername = line.split(" ")[1];
        fr.close();
        br.close();
        return readUsername;
    }

    public static void resetPassword(String username, String newPassword)throws IOException{
        List<String> lines = new ArrayList<String>();
        String line = null;
        File f1 = new File("userLogin.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        fr.close();
        br.close();

        FileWriter fw = new FileWriter(f1);
        BufferedWriter out = new BufferedWriter(fw);
        for (String s : lines) {
            if (s.split(" ")[1].equals(username)) {
                out.write(s.split(" ")[0] + " " + s.split(" ")[1] + " " + newPassword);
                out.newLine();
            } else {
                out.write(s);
                out.newLine();
            }
        }
        out.flush();
        out.close();
    }

    public static void editProfile(int userID, String firstname ,String lastname ,String gender ,String dose ,String vaccinatedDate, String profileFileName)throws IOException{
        String username  = getUsername(userID);
        List<String> lines = new ArrayList<String>();
        String line = null;
        File f1 = new File("userData.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        fr.close();
        br.close();

        FileWriter fw = new FileWriter(f1);
        BufferedWriter out = new BufferedWriter(fw);
        for (String s : lines) {
            if (s.split(" ")[1].equals(username)) {
                out.write(userID + " " + username + " " + firstname + " " + lastname + " " + gender + " " + dose  + " " + vaccinatedDate + " " + profileFileName );
                out.newLine();
            } else {
                out.write(s);
                out.newLine();
            }
        }
        out.flush();
        out.close();
    }
}