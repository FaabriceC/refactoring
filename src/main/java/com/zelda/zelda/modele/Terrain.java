package com.zelda.zelda.modele;

public class Terrain {
    private int[][] terrain = {
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1},
            {3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,3,3,3},
            {3,3,3,3,3,3,3,3,3,3,3,3,3,2,2,3,3,3},
            {1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1}
    };

    public Terrain() {
        //terrain =
    }

    public int longeurTerrain(){
        int cpt = 0;
        for (int i = 0;i<this.terrain.length;i++){
            cpt++;
        }
        return cpt;
    }

    public int largeurTerrain(){
        int cpt = 0;
        for (int j = 0;j<this.terrain[0].length;j++){
            cpt++;
        }
        return cpt;
    }

    public int[][] getTerrain() {
        return terrain;
    }

    /*
    public void remplirTerrain(){
        for(int i = 0;i<longeur;i++){
            for (int j = 0;j<largeur;j++){
                this.terrain[i][j] = 0;
            }
        }
    }

    public void afficheTerrain(){
        for(int i = 0;i<longeur;i++){
            for (int j = 0;j<largeur;j++){
                System.out.print(this.terrain[i][j]);
            }
            System.out.println();
        }
    }

     */
}