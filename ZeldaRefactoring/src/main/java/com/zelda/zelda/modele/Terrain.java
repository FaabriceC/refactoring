package com.zelda.zelda.modele;

import com.zelda.zelda.modele.acteur.Personnage;
import com.zelda.zelda.modele.dynamique.BlockDynamique;
import com.zelda.zelda.util.JsonLoader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/* Défini les emplacements des différentes tiles du terrain, chaque nombres correspond a une tile spécifique
 * ex : le chiffre 5 correspond a la tile de l'eau
 */
public class Terrain {
    public int[][] solLayer;

    private ArrayList<BlockDynamique> blocsDynamiques = new ArrayList<>();
    private int[] tuileCollision = {9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,30,48,49,54,78,102};

    public Terrain(String jsonFilePath) {
        try (JsonLoader jsonLoader = new JsonLoader(jsonFilePath)) {
            Map<String, int[]> layers = jsonLoader.getLayers();


            solLayer = convertTo2DArray(layers.get("Sol"), 70, 180);
        } catch (IOException e) {
            System.err.println("(Terrain) Erreur de lecture du fichier JSON  : " + e.getMessage());
        }
    }

    public int[][] getSolLayer() {
        return solLayer;
    }




    public int[][] convertTo2DArray(int[] array, int rows, int cols) {

        if (array == null) {
            return new int[rows][cols]; // Return an empty array if null
        }
        int[][] result = new int[rows][cols];
        for (int i = 0; i < array.length; i++) {
            result[i / cols][i % cols] = array[i];
        }
        return result;
    }
    public boolean dansTerrain(int x, int y){
        return (0 <= x && x< solLayer[0].length*32-32 && 0<=y && y<solLayer.length*32-32);
    }


    public boolean collision(int x ,int y){
        return collisionAvecTuile(x,y) || collisionAvecBlockDynamique(x,y);

    }

    public boolean collisionPourBloc(int x ,int y , BlockDynamique blockDynamique){
        return collisionAvecTuile(x,y) ||  collisionPourBlockDynamique(x,y,blockDynamique);

    }

    private boolean collisionPourBlockDynamique(int x, int y,BlockDynamique blockDynamique) {
        for (BlockDynamique block : blocsDynamiques) {
            if (blockDynamique!= block)
                return block.enCollision(x, y);
        }
        return false;
    }

    private boolean collisionAvecBlockDynamique(int x, int y) {
        for (BlockDynamique block : blocsDynamiques) {
            return block.enCollision(x, y);
        }
        return false;
    }



    // Fonction qui permet de detecter les tuiles avec lesquelle les personnages entre en collision
    public boolean collisionAvecTuile(int x, int y) {
        int tuileX = (x) / 32;
        int tuileY = (y) / 32;

        return estObstacle(tuileX, tuileY);
    }


    public boolean collisionAvecTuileNonOpti(int x, int y, Personnage personnage) {
        // margeX défini la marge de detection des tile ou on ne peut pas marcher sur l'axe des x
        int margeX=0;
        // margeY défini la marge de detection des tile ou on ne peut pas marcher sur l'axe des y
        int margeY=0;


        margeX=personnage.margeErreur(margeX,margeY)[0];
        margeY=personnage.margeErreur(margeX,margeY)[1];


        int tuileX = (x+margeX) / 32;
        int tuileY = (y+margeY) / 32;
        if (estObstacle(tuileX,tuileY)){
            return true;
        }

        return false;
    }


    // Fonction qui permet de detecter les tile d'eau
    public boolean estObstacle(int tuileX, int tuileY) {
        int tuile = this.solLayer[tuileY][tuileX];
        for (int t : tuileCollision) {
            if (tuile == t+1) {
                return false;  // La tuile est trouvée dans tuileCollision, donc ce n'est pas un obstacle
            }
        }
        return true;  // La tuile n'est pas trouvée dans tuileCollision, donc c'est un obstacle
    }



    public void ajouterBlocDynamique(BlockDynamique bloc) {
        blocsDynamiques.add(bloc);
    }



    public ArrayList<BlockDynamique> getBlocsDynamiques() {
        return blocsDynamiques;
    }
}
