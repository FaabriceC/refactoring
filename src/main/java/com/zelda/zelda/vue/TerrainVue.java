package  com.zelda.zelda.vue;

import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.acteur.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TerrainVue {

    private static final int TILE_SIZE = 32; // Tile size is 32x32 pixels

    private Pane panneauJeu;

    private Terrain terrain;

    private Map<Integer, Image> tileImages;
    private Personnage personnage;


    public TerrainVue(Pane panneauJeu, Terrain terrain) throws IOException {

        this.panneauJeu = panneauJeu;

        this.terrain = terrain;

        loadTileImages();

    }



    private void loadTileImages() throws IOException {

        tileImages = new HashMap<>();

        // code tuiles de 0 Ã  n, remplacer n avec le nombre de tiles si on change on en a 419 actuellement

        for (int i = 0; i <= 191; i++) {

            URL imageUrl = Lanceur.class.getResource("/com/zelda/zelda/map/tile_" + i + ".png");

            tileImages.put(i+1, new Image(imageUrl.openStream()));

        }

    }



    public void affichageVue() {

        if (terrain.getSolLayer() != null) {

            renderLayer(terrain.getSolLayer());

        } else {

            System.err.println("Sol layer is null.");

        }

    }



    private void renderLayer(int[][] layer) {

        for (int i = 0; i < layer.length; i++) {

            for (int j = 0; j < layer[i].length; j++) {

                renderTile(layer[i][j], j, i);

            }

        }

    }



    private void renderTile(int tileCode, int x, int y) {



        Image tileImage = tileImages.get(tileCode);

        if (tileImage != null) {

            ImageView imageView = new ImageView(tileImage);

            imageView.setX(x * TILE_SIZE);

            imageView.setY(y * TILE_SIZE);

            panneauJeu.getChildren().add(imageView);

        }

    }


}