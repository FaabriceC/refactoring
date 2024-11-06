package com.zelda.zelda.controleur;

import com.zelda.zelda.modele.*;
import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.Consommable.PotionForce;
import com.zelda.zelda.modele.Consommable.PotionSoin;
import com.zelda.zelda.modele.acteur.*;
import com.zelda.zelda.modele.armes.Arme;
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
    private LinkVue linkVue;

    @FXML
    private Pane backgroundPaneConso;
    private GameLoop gameLoop;
    private ControleurKey controleurKey;

    private Inventaire inv;
    private InventaireVue inventaireVue;

    @FXML
    private ToolBar consommable;

    private ProjectileVue proVue;

    public void initialize(URL location, ResourceBundle resources) {

        initTerrain();

        initDecorations();

        initLink();

        initInventaire();
        initListObs();
        Environnement.getInstance().initArmes();
        Environnement.getInstance().initConsommable();
        Environnement.getInstance().initMonstre();

        controleurKey = new ControleurKey(inventaireVue);
        controleurKey.initKeyHandler(panneauJeu, Link.getInstance());

        this.gameLoop = new GameLoop();
        this.gameLoop.start();

        initPane();

    }

    public void initTerrain() {
        try {
            terrainVue = new TerrainVue(panneauJeu, Terrain.getInstance());
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
        BlockDynamique blocktest = new BlockDynamique(600, 300, "arbre1.png", Terrain.getInstance());
        Terrain.getInstance().ajouterBlocDynamique(blocktest);
        BlockDynamiqueVue blocktestVue = new BlockDynamiqueVue(blocktest, "arbre1.png");
        panneauJeu.getChildren().add(blocktestVue.getImageView());
    }

    public void initLink() {
        this.linkVue = new LinkVue(Link.getInstance(), panneauJeu, "Link3.gif", backgroundPaneConso, consommable, backgroundPane, itemToolBar);
        this.panneauJeu.getChildren().add(this.linkVue.getImageView());


        for (ImageView coeur : linkVue.getListImageViewsCoeur()) {
            this.panneauJeu.getChildren().add(coeur);
        }

        this.proVue = new ProjectileVue(Link.getInstance().getFleche());
        this.panneauJeu.getChildren().add(this.proVue.getImageView());

//        ProjectileVue  proVueBoomerang = new ProjectileVue(link.getBoomerang());
//        this.panneauJeu.getChildren().add(proVueBoomerang.getImageView());
    }


    public void initListObs(){
        ListChangeListener<Personnage> personnageListChangeListener = new ListObs(panneauJeu);
        Environnement.getInstance().getPersonnageListe().addListener(personnageListChangeListener);

        ListChangeListener<Arme> armeListChangeListener = new ListObsArmes(panneauJeu, itemToolBar);
        Environnement.getInstance().getArmes().addListener(armeListChangeListener);

        ListChangeListener<Consommable> consommableListChangeListener = new ListObsConsommables(panneauJeu, consommable, Link.getInstance());
        Environnement.getInstance().getConsommables().addListener(consommableListChangeListener);


    }


    public void initInventaire() {
        inv = Link.getInstance().getInventaire();
        this.epee = new Epee();
        inv.ajouterArme(epee);
        this.potionSoin = new PotionSoin();
        this.potionForce = new PotionForce();

        inv.ajouterConsommable(potionSoin);
        inv.ajouterConsommable(potionForce);

        this.inventaireVue = new InventaireVue(inv, itemToolBar, consommable, backgroundPaneConso, Link.getInstance());
    }


}
