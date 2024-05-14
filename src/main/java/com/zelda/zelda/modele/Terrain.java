package com.zelda.zelda.modele;

import java.lang.reflect.Array;
import java.util.ArrayList;

/* Défini les emplacements des différentes tiles du terrain, chaque nombres correspond a une tile spécifique
 * ex : le chiffre 5 correspond a la tile de l'eau
 */
public class Terrain {
    private int[][] terrain = {
            {5,5,3,3,3,3,17,6,1,17,3,3,3,3,3,3,3,5},
            {3,3,3,3,3,16,17,6,1,16,3,3,3,3,3,3,3,5},
            {3,3,3,3,3,17,17,6,1,17,16,3,3,3,3,3,3,5},
            {3,3,17,16,16,17,1,6,1,16,16,17,17,17,16,3,3,17},
            {16,16,16,16,16,1,1,6,1,1,1,1,1,1,1,1,17,17},
            {1,1,1,1,9,8,8,13,8,8,8,8,7,8,13,8,8,8},
            {16,16,16,1,6,17,17,17,1,17,17,16,6,1,1,1,1,1},
            {5,16,16,1,6,16,17,1,14,1,17,17,6,1,17,16,17,16},
            {5,1,1,8,12,17,17,17,1,16,17,17,6,1,1,16,17,17},
            {5,5,16,16,10,8,8,8,8,8,8,8,11,1,1,5,5,5}
    };




    //Fonction qui permet d'obtenir le terrain
    public int[][] getTerrain() {
        return this.terrain;
    }




    //Fonction permettant de verifier que les du point (x,y) se trouve dans les limites du terrain
    public boolean dansTerrain(int x, int y){
        return (0 <= x && x< 576-32 && 0<=y && y<320-32);
    }


    //Fonction qui permet de detecter les tuiles avec lesquelle les personnages entre en collision
    public boolean collisionAvecTuile(int x, int y,String direction) {
        // margeX défini la marge de detection des tile ou on ne peut pas marcher sur l'axe des x
        int margeX=0;
        // margeY défini la marge de detection des tile ou on ne peut pas marcher sur l'axe des y
        int margeY=0;


        switch (direction) {
            case "LEFT":
                margeX = 10;
                margeY= 26;
                break;
            case "RIGHT":
                margeX = 26;
                margeY=26;
                break;
            case "UP":
                margeX = 16;
                margeY = 26;
                break;
            case "DOWN":
                margeX = 16;
                margeY = 26;
                break;
        }


        int tuileX = (x+margeX) / 32;
        int tuileY = (y+margeY) / 32;
        if (estObstacleEau(tuileX, tuileY)||estObstaclePierre(tuileX, tuileY)){
            return true;
        }


        return false;
    }

    //Fonction qui permet de detecter les tile d'eau
    private boolean estObstacleEau(int tuileX, int tuileY) {
        int tuile = this.terrain[tuileY][tuileX];
        return tuile == 3;
    }

    //Fonction qui permet de detecter les tile de pierre
    private boolean estObstaclePierre(int tuileX, int tuileY) {
        int tuile = this.terrain[tuileY][tuileX];
        return tuile == 5;
    }




}