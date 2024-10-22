package com.zelda.zelda;

import com.zelda.zelda.controleur.Controleur;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

public class Lanceur extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zelda/zelda/InterfacePrincipale.fxml"));
        BorderPane panePrincipal = fxmlLoader.load();

        Controleur controleur = fxmlLoader.getController();
        System.out.println(controleur);
        
        Scene scene = new Scene(panePrincipal, 1280, 960);

        stage.setTitle("Jeu 2D Zelda");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
