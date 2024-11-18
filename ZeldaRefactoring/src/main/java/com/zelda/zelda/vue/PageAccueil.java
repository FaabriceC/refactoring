package com.zelda.zelda.vue;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class PageAccueil extends StackPane {

    @FXML
    private Button boutonCommencer, boutonCredits, boutonAideTouches, boutonQuitter;

    public PageAccueil() {
        Image backgroundImage = new Image(getClass().getResource("/com/zelda/zelda/bg/bg2.jpeg").toExternalForm());
        ImageView background = new ImageView(backgroundImage);

        GaussianBlur blur = new GaussianBlur(15);
        background.setEffect(blur);
        background.setFitWidth(1280);
        background.setFitHeight(960);
        background.setPreserveRatio(false);

        Text titre = new Text("Bienvenue dans le Monde de Zelda");
        titre.setStyle("-fx-font-size: 40px; -fx-font-weight: bold; -fx-fill: #ffffff;");
        titre.setEffect(new DropShadow(10, Color.BLACK));

        Text sousTitre = new Text("Une aventure vous attend !");
        sousTitre.setStyle("-fx-font-size: 25px; -fx-fill: #dddddd; -fx-font-style: italic;");
        sousTitre.setEffect(new DropShadow(5, Color.BLACK));

        boutonCommencer = creerBouton("Commencer la Partie", "#4CAF50", "Lancer l'aventure !");
        boutonCommencer.setOnAction(this::lancerJeu);

        boutonCredits = creerBouton("Crédits", "#FF6347", "Voir les développeurs");
        boutonCredits.setOnAction(this::afficherCredits);

        boutonAideTouches = creerBouton("Aide aux touches", "#FFD700", "Consulter les commandes");
        boutonAideTouches.setOnAction(this::afficherAideTouches);

        boutonQuitter = creerBouton("Quitter", "#DC143C", "Fermer le jeu");
        boutonQuitter.setOnAction(event -> Platform.exit());

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), this);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        StackPane.setAlignment(titre, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setMargin(titre, new javafx.geometry.Insets(20, 0, 10, 0));

        StackPane.setAlignment(sousTitre, javafx.geometry.Pos.TOP_CENTER);
        StackPane.setMargin(sousTitre, new javafx.geometry.Insets(70, 0, 10, 0));

        StackPane.setAlignment(boutonCommencer, javafx.geometry.Pos.CENTER);
        StackPane.setMargin(boutonCommencer, new javafx.geometry.Insets(50, 0, 10, 0));

        StackPane.setAlignment(boutonCredits, javafx.geometry.Pos.CENTER_LEFT);
        StackPane.setMargin(boutonCredits, new javafx.geometry.Insets(250, 0, 20, 50));

        StackPane.setAlignment(boutonAideTouches, javafx.geometry.Pos.CENTER_RIGHT);
        StackPane.setMargin(boutonAideTouches, new javafx.geometry.Insets(250, 50, 20, 0));

        StackPane.setAlignment(boutonQuitter, javafx.geometry.Pos.BOTTOM_CENTER);
        StackPane.setMargin(boutonQuitter, new javafx.geometry.Insets(20, 0, 40, 0));

        getChildren().addAll(background, titre, sousTitre, boutonCommencer, boutonCredits, boutonAideTouches, boutonQuitter);
    }

    private Button creerBouton(String texte, String couleurFond, String tooltipTexte) {
        Button bouton = new Button(texte);
        bouton.setStyle(String.format("-fx-font-size: 20px; -fx-background-color: %s; -fx-text-fill: white; -fx-padding: 10 20;", couleurFond));
        bouton.setEffect(new DropShadow(5, Color.BLACK));
        bouton.setOnMouseEntered(event -> bouton.setStyle(bouton.getStyle() + "-fx-scale-x: 1.1; -fx-scale-y: 1.1;"));
        bouton.setOnMouseExited(event -> bouton.setStyle(String.format("-fx-font-size: 20px; -fx-background-color: %s; -fx-text-fill: white; -fx-padding: 10 20;", couleurFond)));
        bouton.setTooltip(new javafx.scene.control.Tooltip(tooltipTexte));
        return bouton;
    }

    public void lancerJeu(ActionEvent event) {
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