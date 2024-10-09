package com.zelda.zelda.controleur;

import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.*;
import com.zelda.zelda.modele.Consommable.Bracelet;
import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.Consommable.PotionForce;
import com.zelda.zelda.modele.Consommable.PotionSoin;
import com.zelda.zelda.modele.acteur.*;
import com.zelda.zelda.modele.armes.Arc;
import com.zelda.zelda.modele.armes.Arme;
import com.zelda.zelda.modele.armes.Boomerang;
import com.zelda.zelda.modele.armes.Epee;
import com.zelda.zelda.modele.dynamique.BlockDynamique;
import com.zelda.zelda.vue.ConsommableVue;
import com.zelda.zelda.vue.InventaireVue;
import com.zelda.zelda.vue.ProjectileVue;
import com.zelda.zelda.vue.acteur.LinkVue;
import com.zelda.zelda.vue.acteur.MonstreVue;
import com.zelda.zelda.vue.TerrainVue;
import com.zelda.zelda.vue.dynamique.BlockDynamiqueVue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controleur implements Initializable {
    private Terrain terrain;
    private TerrainVue terrainVue;

    @FXML
    private Pane backgroundPane;
    @FXML
    private ToolBar itemToolBar;
    private Epee epee;
    private PotionSoin potionSoin;
    private PotionForce potionForce;


    @FXML
    private Pane panneauJeu;


    private Link link;
    private LinkVue linkVue;


    @FXML
    private Pane backgroundPaneConso;

    private GameLoop gameLoop;

    private ControleurKey controleurKey;
    private Environnement env;
    private Inventaire inv;
    private InventaireVue inventaireVue;

    @FXML
    private ToolBar consommable;

    private ProjectileVue proVue;

    public void initialize(URL location, ResourceBundle resources) {

        initTerrain();

        initDecorations();
        initLink();
        this.env = new Environnement(link);
        initInventaire();
        initListObs();
        initArmes();
        initConsommable();
        initMonstre();

        controleurKey = new ControleurKey(inventaireVue);
        controleurKey.initKeyHandler(panneauJeu, link);

        this.gameLoop = new GameLoop(link, env);
        this.gameLoop.start();

        initPane();

    }

    public void initTerrain() {
        terrain = new Terrain("/com/zelda/zelda/MapZelda.json");
        try {
            terrainVue = new TerrainVue(panneauJeu, terrain);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        terrainVue.affichageVue();
    }

    public void initPane() {
        backgroundPaneConso.toFront();
        consommable.toFront();
        backgroundPane.toFront();
        itemToolBar.toFront();
    }

    public void initDecorations() {
        BlockDynamique blocktest = new BlockDynamique(600, 300, "arbre1.png", terrain);
        terrain.ajouterBlocDynamique(blocktest);
        BlockDynamiqueVue blocktestVue = new BlockDynamiqueVue(blocktest, "arbre1.png");
        panneauJeu.getChildren().add(blocktestVue.getImageView());
    }

    public void initLink() {
        this.link = new Link("link", 800, 400, this.terrain);
        this.linkVue = new LinkVue(link, panneauJeu, "Link3.gif", backgroundPaneConso, consommable, backgroundPane, itemToolBar);
        this.panneauJeu.getChildren().add(this.linkVue.getImageView());


        for (ImageView coeur : linkVue.getListImageViewsCoeur()) {
            this.panneauJeu.getChildren().add(coeur);
        }

        this.proVue = new ProjectileVue(link.getFleche());
        this.panneauJeu.getChildren().add(this.proVue.getImageView());

//        ProjectileVue  proVueBoomerang = new ProjectileVue(link.getBoomerang());
//        this.panneauJeu.getChildren().add(proVueBoomerang.getImageView());
    }


    public void initArmes(){

        Epee epee = new Epee();
        this.env.ajouterListeArme(epee);
        Boomerang boomerang = new Boomerang();
        this.env.ajouterListeArme(boomerang);

        Arc arc = new Arc();
        this.env.ajouterListeArme(arc);
    }
    public void initListObs(){
        ListChangeListener<Personnage> test2 = new ListObs(panneauJeu);
        this.env.getPersonnageListe().addListener(test2);

        ListChangeListener<Arme> test3 = new ListObsArmes(panneauJeu, itemToolBar);
        this.env.getArmes().addListener(test3);

        ListChangeListener<Consommable> test4 = new ListObsConsommables(panneauJeu, consommable, link);
        this.env.getConsommables().addListener(test4);

    }
    public void initConsommable(){
        Bracelet bracelet = new Bracelet();
        this.env.ajouterListeConsommable(bracelet);

        Consommable popoDeSoin = new PotionSoin();
        PotionForce popoDeForce = new PotionForce();

        this.env.ajouterListeConsommable(popoDeSoin);
        this.env.ajouterListeConsommable(popoDeForce);

    }

    public void initMonstre() {
        Monstre monstre1 = new Slime(708, 472, this.terrain);
        Monstre monstre2 = new Slime(750, 472, this.terrain);
        Monstre monstre3 = new ArbreMonstre(760, 440, this.terrain);
        Monstre boss = new Boss(4844,868,terrain);

        this.env.ajouter(monstre1);
        this.env.ajouter(monstre2);
        this.env.ajouter(monstre3);
        this.env.ajouter(boss);
    }

    public void initInventaire() {
        inv = this.link.getInventaire();
        this.epee = new Epee();
        inv.ajouterArme(epee);
        this.potionSoin = new PotionSoin();
        this.potionForce = new PotionForce();

        inv.ajouterConsommable(potionSoin);
        inv.ajouterConsommable(potionForce);

        this.inventaireVue = new InventaireVue(inv, itemToolBar, consommable, backgroundPaneConso, link);
    }


}
