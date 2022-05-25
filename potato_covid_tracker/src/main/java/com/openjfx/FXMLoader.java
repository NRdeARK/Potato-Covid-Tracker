package com.openjfx;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class FXMLoader {

    private Pane view;

    public Pane getPage(String fileName) {

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/" + fileName + ".fxml"));

            view = loader.load();
        } catch (Exception e) {
            System.out.println("No page " + fileName + " please check Fxmloader.");
        }
        return view;
    }

}
