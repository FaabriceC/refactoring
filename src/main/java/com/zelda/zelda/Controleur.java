package com.zelda.zelda;

import com.zelda.zelda.modele.Link;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.StackPane;
import com.zelda.zelda.modele.Terrain;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;


public class Controleur implements Initializable {
    private Terrain t;

    @FXML
    private TilePane tile;
    @FXML
    private Pane panneauJeu;
    private Link link=new Link("link",256,224,t);


    private GameLoop gameLoop;

    public void initialize(URL location, ResourceBundle resources) {
        initTerrain();
        initLink();



    }



    public  void initTerrain(){
        this.t = new Terrain();
        Image terre = new Image(String.valueOf(HelloApplication.class.getResource("Hebres1x32.png")));
        Image pont = new Image(String.valueOf(HelloApplication.class.getResource("pont1x32.png")));
        Image herbe = new Image(String.valueOf(HelloApplication.class.getResource("Hebres2x32.png")));
        Image pierre = new Image(String.valueOf(HelloApplication.class.getResource("pierre.png")));
        Image eau = new Image(String.valueOf(HelloApplication.class.getResource("water32x32.gif")));
        Image cheminV = new Image(String.valueOf(HelloApplication.class.getResource("passageV.png")));
        Image cheminDroiteGaucheBas = new Image(String.valueOf(HelloApplication.class.getResource("cheminTripleDroiteGaucheBas.png")));
        Image cheminH = new Image(String.valueOf(HelloApplication.class.getResource("Grass1.png")));
        Image cheminTourant1 = new Image(String.valueOf(HelloApplication.class.getResource("Grass2x32.png")));
        Image cheminTourant2 = new Image(String.valueOf(HelloApplication.class.getResource("hautDroite.png")));
        Image cheminTourant3 = new Image(String.valueOf(HelloApplication.class.getResource("gaucheHaut.png")));
        Image cheminDroiteHautBas = new Image(String.valueOf(HelloApplication.class.getResource("Grass4x32.png")));
        Image cheminGaucheDroiteHaut = new Image(String.valueOf(HelloApplication.class.getResource("cheminTripleGaucheDroiteHaut.png")));
        Image spawn = new Image(String.valueOf(HelloApplication.class.getResource("spawn.png")));
        Image sable = new Image(String.valueOf(HelloApplication.class.getResource("sable.png")));
        Image grandeHerbe1 = new Image(String.valueOf(HelloApplication.class.getResource("Hebres2x32.png")));
        Image grandeHerbe2 = new Image(String.valueOf(HelloApplication.class.getResource("Hebres3x32.png")));




        for (int i = 0; i < this.t.getTerrain().length; i++) {
            for (int j = 0; j < this.t.getTerrain()[i].length; j++) {
                int nbTile = this.t.getTerrain()[i][j];

                switch (nbTile) {
                    case 1:
                        this.tile.getChildren().add(new ImageView(terre));
                        break;
                    case 2:
                        this.tile.getChildren().add(new ImageView(pont));
                        break;
                    case 3:
                        this.tile.getChildren().add(new ImageView(eau));
                        break;
                    case 4:
                        this.tile.getChildren().add(new ImageView(herbe));
                        break;
                    case 5:
                        this.tile.getChildren().add(new ImageView(pierre));
                        break;
                    case 6:
                        this.tile.getChildren().add(new ImageView(cheminV));
                        break;
                    case 7:
                        this.tile.getChildren().add(new ImageView(cheminDroiteGaucheBas));
                        break;
                    case 8:
                        this.tile.getChildren().add(new ImageView(cheminH));
                        break;
                    case 9:
                        this.tile.getChildren().add(new ImageView(cheminTourant1));
                        break;
                    case 10:
                        this.tile.getChildren().add(new ImageView(cheminTourant2));
                        break;
                    case 11:
                        this.tile.getChildren().add(new ImageView(cheminTourant3));
                        break;
                    case 12:
                        this.tile.getChildren().add(new ImageView(cheminDroiteHautBas));
                        break;
                    case 13:
                        this.tile.getChildren().add(new ImageView(cheminGaucheDroiteHaut));
                        break;
                    case 14:
                        this.tile.getChildren().add(new ImageView(spawn));
                        break;
                    case 15:
                        this.tile.getChildren().add(new ImageView(sable));
                        break;
                    case 16:
                        this.tile.getChildren().add(new ImageView(grandeHerbe1));
                        break;
                    case 17:
                        this.tile.getChildren().add(new ImageView(grandeHerbe2));
                        break;
                }
            }
        }
    }


    public void initLink(){
        this.gameLoop = new GameLoop(this);
        this.gameLoop.start();
        this.panneauJeu.setOnKeyPressed(this::gererKeyPressed);
        this.panneauJeu.setOnKeyReleased(this::gererKeyReleased);
        this.panneauJeu.setFocusTraversable(true);
        this.panneauJeu.getChildren().add(this.link.getImageView());
    }

    public void initPersos(){

        ImageView imageView = new ImageView(this.link.getImage());
        imageView.setFitWidth(32);
        imageView.setFitHeight(32);
        imageView.setX(256);
        imageView.setY(224);



        this.panneauJeu.getChildren().add(imageView);


    }

    public Link getLink() {
        return this.link;
    }

    public Pane getPanneauJeu() {
        return this.panneauJeu;
    }

    public Terrain getTerrain() {
        return this.t;
    }



    // Gestionnaire d'événements pour la pression des touches
    private void gererKeyPressed(KeyEvent event) {
        this.gameLoop.gererKeyPressed(event);
    }

    // Gestionnaire d'événements pour le relâchement des touches
    private void gererKeyReleased(KeyEvent event) {
        this.gameLoop.gererKeyReleased(event);
    }
}
