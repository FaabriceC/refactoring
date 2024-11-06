package com.zelda.zelda.controleur;

import com.zelda.zelda.modele.*;
import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.Consommable.PotionForce;
import com.zelda.zelda.modele.Consommable.PotionSoin;
import com.zelda.zelda.modele.acteur.*;
import com.zelda.zelda.modele.armes.Arc;
import com.zelda.zelda.modele.armes.Arme;
import com.zelda.zelda.modele.armes.Boomerang;
import com.zelda.zelda.modele.armes.Epee;
import com.zelda.zelda.modele.dynamique.BlockDynamique;
import com.zelda.zelda.vue.InventaireVue;
import com.zelda.zelda.vue.ProjectileVue;
import com.zelda.zelda.vue.acteur.LinkVue;
import com.zelda.zelda.vue.TerrainVue;
import com.zelda.zelda.vue.dynamique.BlockDynamiqueVue;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
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
    private ProjectileVue proVue2;

    public void initialize(URL location, ResourceBundle resources) {

        initTerrain();

        initDecorations();
        initLink();


        this.env = new Environnement(link, terrain);


        initInventaire();
        initListObs();
        this.initArmes();
        this.env.initConsommable();
        this.env.initMonstre();

        //initProjectile();

        controleurKey = new ControleurKey(inventaireVue, this.env);
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
/*
        this.proVue = new ProjectileVue(((Arc)this.env.getArmes().get(2)).getFleche());
        this.panneauJeu.getChildren().add(this.proVue.getImageView());

//        ProjectileVue  proVueBoomerang = new ProjectileVue(link.getBoomerang());
//        this.panneauJeu.getChildren().add(proVueBoomerang.getImageView());

        this.proVue2 = new ProjectileVue(((Boomerang)env.getArmes().get(1)).getBoomerang());
        this.panneauJeu.getChildren().add(this.proVue2.getImageView());

 */
    }


    public void initListObs(){
        ListChangeListener<Personnage> personnageListChangeListener = new ListObs(panneauJeu);
        this.env.getPersonnageListe().addListener(personnageListChangeListener);

        ListChangeListener<Arme> armeListChangeListener = new ListObsArmes(panneauJeu, itemToolBar);
        this.env.getArmes().addListener(armeListChangeListener);

        ListChangeListener<Consommable> consommableListChangeListener = new ListObsConsommables(panneauJeu, consommable, link);
        this.env.getConsommables().addListener(consommableListChangeListener);


    }


    public void initInventaire() {
        inv = this.link.getInventaire();
        Epee epee = new Epee(link);
        inv.ajouterArme(epee);
        this.potionSoin = new PotionSoin();
        this.potionForce = new PotionForce();

        inv.ajouterConsommable(potionSoin);
        inv.ajouterConsommable(potionForce);

        this.inventaireVue = new InventaireVue(inv, itemToolBar, consommable, backgroundPaneConso, link);


    }

    public void initArmes(){

        Epee epee = new Epee(link);
        this.env.ajouterListeArme(epee);
        Boomerang boomerang = new Boomerang(link);
        this.env.ajouterListeArme(boomerang);

        Arc arc = new Arc(link);
        this.env.ajouterListeArme(arc);


        this.proVue = new ProjectileVue(arc.getFleche());
        this.panneauJeu.getChildren().add(this.proVue.getImageView());


        this.proVue2 = new ProjectileVue(boomerang.getBoomerang());
        this.panneauJeu.getChildren().add(this.proVue2.getImageView());

    }

/*
    public void initProjectile(){
        this.proVue = new ProjectileVue(((Arc)this.env.getArmes().get(2)).getFleche());
        this.panneauJeu.getChildren().add(this.proVue.getImageView());

//        ProjectileVue  proVueBoomerang = new ProjectileVue(link.getBoomerang());
//        this.panneauJeu.getChildren().add(proVueBoomerang.getImageView());

        this.proVue2 = new ProjectileVue(((Boomerang)env.getArmes().get(1)).getBoomerang());
        this.panneauJeu.getChildren().add(this.proVue2.getImageView());
    }


 */

}
