    package com.zelda.zelda.controleur;

    import com.zelda.zelda.HelloApplication;
    //import com.zelda.zelda.modele.Decoration;
    import com.zelda.zelda.modele.Decoration;
    import com.zelda.zelda.modele.Link;
    //import com.zelda.zelda.vue.DecorationVue;
    import com.zelda.zelda.modele.Monstre;
    import com.zelda.zelda.vue.DecorationVue;
    import com.zelda.zelda.vue.LinkVue;
    import com.zelda.zelda.vue.MonstreVue;
    import com.zelda.zelda.vue.TerrainVue;
    import javafx.animation.Timeline;
    import javafx.application.Platform;
    import javafx.fxml.FXML;
    import javafx.fxml.Initializable;
    import javafx.scene.image.Image;
    import javafx.scene.image.ImageView;
    import javafx.scene.input.KeyCode;
    import javafx.scene.layout.Pane;
    import javafx.scene.layout.TilePane;
    import com.zelda.zelda.modele.Terrain;
    import javafx.scene.input.KeyEvent;

    import java.net.URL;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Map;
    import java.util.ResourceBundle;


    public class
    Controleur implements Initializable {
        private Terrain t;

      /*  private Timeline gameLoop;*/
        private TerrainVue terrainVue;


       // private DecorationVue decorationVue;

        @FXML
        private TilePane tile;
        @FXML
        private Pane panneauJeu;

        @FXML
        private Pane panneauDeco;
        private Link link;
        private LinkVue linkVue;
        private Monstre monstre;
        private MonstreVue monstreVue;

        private Decoration decoration;
        private DecorationVue decoVue;


        private GameLoop gameLoop;

        private ControleurKey controleurKey;



        public void initialize(URL location, ResourceBundle resources) { //l'initialisation principale
            initTerrain();
            initDeco();
            initLink();
           // initMonstre();

            controleurKey = new ControleurKey();
            controleurKey.initKeyHandler(panneauJeu, link);


            this.gameLoop = new GameLoop(link,monstre);
            this.gameLoop.start();



        }

        public void initDeco(){
         DecorationVue.initDecorations(panneauJeu,t);

        }


        public void initTerrain(){
            this.t = new Terrain();
            this.terrainVue = new TerrainVue(t);
            this.terrainVue.initTerrain(tile);
        }

        public void initLink(){   // Start la gameLoop et ajoute link à une certaine position
            this.link=new Link("link",256,224,this.t);
            this.linkVue=new LinkVue(link,"Link3.png");
            this.panneauJeu.getChildren().add(this.linkVue.getImageView());

        }

        public void initMonstre(){
            this.monstre = new Monstre(10,250,250,"SlimeTropBien",this.t);
            this.monstreVue = new MonstreVue(monstre, "slime3.png");
            this.panneauJeu.getChildren().add(this.monstreVue.getImageView());
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









    }
