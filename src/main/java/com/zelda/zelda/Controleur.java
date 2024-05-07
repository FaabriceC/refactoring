package com.zelda.zelda;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import com.zelda.zelda.modele.Terrain;

import java.net.URL;
import java.util.ResourceBundle;


public class Controleur implements Initializable {
    private Terrain t;

    @FXML
    private TilePane tile;


    public void initialize(URL location, ResourceBundle resources) {
        this.t = new Terrain();


        Image terre = new Image(String.valueOf(HelloApplication.class.getResource("Hebres1x32.png")));
        Image bleu = new Image(String.valueOf(HelloApplication.class.getResource("pont1x32.png")));
        Image pont2 = new Image(String.valueOf(HelloApplication.class.getResource("pont2x32.png")));
        Image eau = new Image(String.valueOf(HelloApplication.class.getResource("water32x32.gif")));



        for (int i = 0; i < t.getTerrain().length; i++) {
            for (int j = 0; j < t.getTerrain()[i].length; j++) {
                int nbTile = t.getTerrain()[i][j];

                switch (nbTile) {
                    case 1:
                        tile.getChildren().add(new ImageView(terre));
                        break;
                    case 2:
                        tile.getChildren().add(new ImageView(bleu));
                        break;
                    case 3:
                        tile.getChildren().add(new ImageView(eau));
                        break;
                    case 4:
                        tile.getChildren().add(new ImageView(pont2));
                        break;
                }
            }
        }
    }
}
