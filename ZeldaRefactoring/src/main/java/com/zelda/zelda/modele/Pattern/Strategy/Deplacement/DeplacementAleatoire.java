package com.zelda.zelda.modele.Pattern.Strategy.Deplacement;

import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.acteur.Monstre;

import java.util.Random;

public class DeplacementAleatoire implements StrategieDeplacement {
    private final Monstre monstre;
    long actionTime = 1000L;

    public DeplacementAleatoire(Monstre monstre) {
        this.monstre = monstre;
    }

    public void seDeplace() {

        Random random = new Random();
        int vitesse = 4;
        int directionAleatoire = random.nextInt(4);
        int deplacementX = 0;
        int deplacementY = 0;

        switch (directionAleatoire) {
            case 0 -> deplacementY -= vitesse;
            case 1 -> deplacementX += vitesse;
            case 2 -> deplacementY += vitesse;
            case 3-> deplacementX -= vitesse;
        }

        int newX = monstre.getX() + deplacementX;
        int newY = monstre.getY() + deplacementY;

        if (monstre.peutSeDeplacer(newX, newY) && Terrain.getInstance().dansTerrain(newX, newY)) {
            monstre.setX(newX);
            monstre.setY(newY);
        }

        actionTime = System.currentTimeMillis();
    }
}
