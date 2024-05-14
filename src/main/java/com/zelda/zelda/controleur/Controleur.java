package com.zelda.zelda.controleur;

import com.zelda.zelda.HelloApplication;
//import com.zelda.zelda.modele.Decoration;
import com.zelda.zelda.modele.Link;
//import com.zelda.zelda.vue.DecorationVue;
import com.zelda.zelda.vue.TerrainVue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import com.zelda.zelda.modele.Terrain;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class
Controleur implements Initializable {
    private Terrain t;
    private TerrainVue terrainVue;
   // private DecorationVue decorationVue;

    @FXML
    private TilePane tile;
    @FXML
    private Pane panneauJeu;
    private Link link=new Link("link",256,224,t);




    private GameLoop gameLoop;


    public void initialize(URL location, ResourceBundle resources) { //l'initialisation principale
        initTerrain();
        //initDeco();
        initLink();



    }
    /*
    public void initDeco(){
        this.decorationVue = new DecorationVue(t);
        for (int i=0;i< decorationVue.getListDeco().size();i++){
            this.panneauJeu.getChildren().add(decorationVue.getListDeco().get(i).getImageView());
        }

    }

     */
    /*public void initDeco(){
        this.decorationVue.initDecorations(decorationVue.getListDeco());
    }
    */



    public void initTerrain(){
        this.t = new Terrain();
        this.terrainVue = new TerrainVue(t);
        this.terrainVue.initTerrain(tile);
    }





    public void initLink(){   // Start la gameLoop et ajoute link à une certaine position
        this.gameLoop = new GameLoop(this);
        this.gameLoop.start();
        this.panneauJeu.setOnKeyPressed(this::gererKeyPressed);
        this.panneauJeu.setOnKeyReleased(this::gererKeyReleased);
        this.panneauJeu.setFocusTraversable(true);
        this.panneauJeu.getChildren().add(this.link.getImageView());
    }

   /* public void initPersos(){  // Pour ajouter les PNJ dans le futur

        ImageView imageView = new ImageView(this.link.getImage());
        imageView.setFitWidth(32);
        imageView.setFitHeight(32);


        this.panneauJeu.getChildren().add(imageView);
    }*/

    public Link getLink() {
        return this.link;
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
