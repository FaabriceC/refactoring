package com.zelda.zelda.vue;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

public class PageAccueil extends StackPane {

    @FXML
    private Button boutonCommencer, boutonCredits, boutonAideTouches;

    public PageAccueil() {
        Image backgroundImage = new Image(getClass().getResource("/com/zelda/zelda/bg/bg2.jpeg").toExternalForm());
        ImageView background = new ImageView(backgroundImage);

        GaussianBlur blur = new GaussianBlur(10);
        background.setEffect(blur);

        background.setFitWidth(1280);
        background.setFitHeight(960);
        background.setPreserveRatio(false);

        boutonCommencer = new Button("Commencer la Partie");
        boutonCommencer.setStyle("-fx-font-size: 20px; -fx-background-color: #4CAF50; -fx-text-fill: white;");
        boutonCommencer.setOnAction(this::lancerJeu);

        boutonCredits = new Button("Crédit");
        boutonCredits.setStyle("-fx-font-size: 20px; -fx-background-color: #FF6347; -fx-text-fill: white;");
        boutonCredits.setOnAction(this::afficherCredits);

        boutonAideTouches = new Button("Aide aux touches");
        boutonAideTouches.setStyle("-fx-font-size: 20px; -fx-background-color: #FFD700; -fx-text-fill: white;");
        boutonAideTouches.setOnAction(this::afficherAideTouches);

        Text titre = new Text("Bienvenue sur le Jeu 2D Zelda");
        titre.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-fill: #333;");

        Text sousTitre = new Text("BUT2 Informatique - 2024 - 2025");
        sousTitre.setStyle("-fx-font-size: 20px; -fx-fill: #555;");

        Text info = new Text("IUT de Montreuil\nDéveloppement Java - Projet Zelda");
        info.setStyle("-fx-font-size: 18px; -fx-fill: #777;");
        info.setTextAlignment(TextAlignment.CENTER);

        StackPane.setAlignment(titre, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setMargin(titre, new javafx.geometry.Insets(20, 0, 10, 0));

        StackPane.setAlignment(sousTitre, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setMargin(sousTitre, new javafx.geometry.Insets(60, 0, 10, 0));

        StackPane.setAlignment(info, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setMargin(info, new javafx.geometry.Insets(100, 0, 10, 0));

        StackPane.setAlignment(boutonCredits, javafx.geometry.Pos.CENTER_LEFT);
        StackPane.setMargin(boutonCredits, new javafx.geometry.Insets(250, 0, 20, 50));

        StackPane.setAlignment(boutonAideTouches, javafx.geometry.Pos.CENTER_RIGHT);
        StackPane.setMargin(boutonAideTouches, new javafx.geometry.Insets(250, 50, 20, 0));

        StackPane.setAlignment(boutonCommencer, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setMargin(boutonCommencer, new javafx.geometry.Insets(20, 0, 40, 0));

        getChildren().addAll(background, titre, sousTitre, info, boutonCommencer, boutonCredits, boutonAideTouches);
    }

    private void lancerJeu(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/zelda/zelda/InterfacePrincipale.fxml"));
            BorderPane panePrincipal = fxmlLoader.load();

            Scene scenePrincipal = new Scene(panePrincipal, 1280, 960);

            Stage stage = (Stage) boutonCommencer.getScene().getWindow();
            stage.setScene(scenePrincipal);
            stage.setTitle("Jeu 2D Zelda");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void afficherCredits(ActionEvent event) {
        System.out.println("Crédits : Tony UY, Fabrice CANNAN, Yassine BENOUDA");
    }

    private void afficherAideTouches(ActionEvent event) {
        System.out.println("Utilisez les touches : Z (haut), Q (gauche), S (bas), D (droite) pour vous déplacer.");
    }
}