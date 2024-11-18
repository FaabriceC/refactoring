package com.zelda.zelda;

import com.zelda.zelda.vue.PageAccueil;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Lanceur extends Application {


    @Override
    public void start(Stage stage) throws IOException {

        PageAccueil pageAccueil = new PageAccueil();
        Scene sceneAccueil = new Scene(pageAccueil, 1280, 960);
        stage.setResizable(false);

        stage.setTitle("Zelda - Page d'Accueil");
        stage.setScene(sceneAccueil);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}