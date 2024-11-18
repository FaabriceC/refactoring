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
        long currentTime = System.currentTimeMillis();

        int directionAleatoire = random.nextInt(4) + 1;
        monstre.setDirectionValue(directionAleatoire);

        int deplacementX = 0;
        int deplacementY = 0;
        int vitesse = 4;


        switch (directionAleatoire) {
            case 1:
                deplacementY -= vitesse;
                break;
            case 2:
                deplacementX += vitesse;
                break;
            case 3:
                deplacementY += vitesse;
                break;
            case 4:
                deplacementX -= vitesse;
                break;
        }

        int newX = monstre.getX() + deplacementX;
        int newY = monstre.getY() + deplacementY;

        if (monstre.peutSeDeplacer(newX, newY) && Terrain.getInstance().dansTerrain(newX, newY)) {
            monstre.setX(newX);
            monstre.setY(newY);
        }
        actionTime = currentTime;

    }
}
