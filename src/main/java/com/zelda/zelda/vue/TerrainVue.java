package com.zelda.zelda.vue;
import com.zelda.zelda.HelloApplication;
import com.zelda.zelda.modele.Terrain;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;

public class TerrainVue {

    private Terrain t;

    public TerrainVue(Terrain t) {
        this.t = t;
    }

    //initialisation des textures de terrain
    public  void initTerrain(TilePane tile){
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
        Image arbre1 = new Image(String.valueOf(HelloApplication.class.getResource("arbre1.png")));




        for (int i = 0; i < this.t.getLongueur(); i++) {
            for (int j = 0; j < this.t.getHauteurLength(); j++) {
                int nbTile = this.t.getCase(i,j);

                switch (nbTile) {
                    case 1:
                        tile.getChildren().add(new ImageView(terre));
                        break;
                    case 2:
                        tile.getChildren().add(new ImageView(pont));
                        break;
                    case 3:
                        tile.getChildren().add(new ImageView(eau));
                        break;
                    case 4:
                        tile.getChildren().add(new ImageView(herbe));
                        break;
                    case 5:
                        tile.getChildren().add(new ImageView(pierre));
                        break;
                    case 6:
                       tile.getChildren().add(new ImageView(cheminV));
                        break;
                    case 7:
                        tile.getChildren().add(new ImageView(cheminDroiteGaucheBas));
                        break;
                    case 8:
                        tile.getChildren().add(new ImageView(cheminH));
                        break;
                    case 9:
                        tile.getChildren().add(new ImageView(cheminTourant1));
                        break;
                    case 10:
                        tile.getChildren().add(new ImageView(cheminTourant2));
                        break;
                    case 11:
                        tile.getChildren().add(new ImageView(cheminTourant3));
                        break;
                    case 12:
                        tile.getChildren().add(new ImageView(cheminDroiteHautBas));
                        break;
                    case 13:
                        tile.getChildren().add(new ImageView(cheminGaucheDroiteHaut));
                        break;
                    case 14:
                        tile.getChildren().add(new ImageView(spawn));
                        break;
                    case 15:
                       tile.getChildren().add(new ImageView(sable));
                        break;
                    case 16:
                       tile.getChildren().add(new ImageView(grandeHerbe1));
                        break;
                    case 17:
                        tile.getChildren().add(new ImageView(grandeHerbe2));
                        break;
                    case 18:
                        tile.getChildren().add(new ImageView(arbre1));
                        break;
                }
            }
        }
    }


}
