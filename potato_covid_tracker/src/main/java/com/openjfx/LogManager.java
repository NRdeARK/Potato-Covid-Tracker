package com.openjfx;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;

public class LogManager {

    public static void createLog() throws IOException {
        File logFile = new File("log.txt");
        if (logFile.createNewFile()) {
            System.out.println("File created: " + logFile.getName());
            writeLog(0, "log file created", "launch");
        } else {
            System.out.println("File already exists.");
        }
    }

    public static void writeLog(int userID, String log, String scene) throws IOException {
        List<String> lines = new ArrayList<String>();
        String line = null;
        File f1 = new File("log.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        fr.close();
        br.close();

        FileWriter fw = new FileWriter(f1);
        BufferedWriter out = new BufferedWriter(fw);
        LocalDateTime time = LocalDateTime.now();
        out.write("TimeStamp=" + time.toString() + "::UserID=" + userID + "::Log=" + log + "::Scene=" + scene);
        out.newLine();
        for (String s : lines) {
            out.write(s);
            out.newLine();
        }
        out.flush();
        out.close();
    }

    public static String readLastLog() throws IOException {
        String line = null;
        File f1 = new File("log.txt");
        FileReader fr = new FileReader(f1);
        BufferedReader br = new BufferedReader(fr);
        if ((line = br.readLine()) != null) {
            fr.close();
            br.close();
            return line;
        }
        fr.close();
        br.close();
        return "ERROR(readLastLog)";
    }

    public static int getUserIDFromLastLog() throws IOException {
        String line = readLastLog();
        String userID = line.split("::")[1].split("=")[1];
        return Integer.parseInt(userID);
    }

    public static String getSceneFromLastLog() throws IOException {
        String line = readLastLog();
        String scene = line.split("::")[3].split("=")[1];
        return scene;
    }

    public static void changeScene(int userID, String from, String to) throws IOException {
        writeLog(userID, "change Scene to #" + to + " form #" + from, from);
    }

    public static void changeScene(String from, String to) throws IOException {
        writeLog(getUserIDFromLastLog(), "change Scene to #" + to + " form #" + from, from);
    }
}
