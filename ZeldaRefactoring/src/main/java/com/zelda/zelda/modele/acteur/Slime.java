package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;

public class Slime extends Monstre {

    public Slime(int x, int y, Terrain t) {
        super(7, x, y, "slime3.gif", t);
    }

    public void seDeplace(Link link) { //TODO RENOMMER LA METHODE seDeplaceEtAttaque
        if (Math.abs(link.getX() - this.getX()) < 128 && Math.abs(link.getY() - this.getY()) < 128) {
            if (!link.isInvisible()) {
                bfs.seDeplace(link);
            }
            attaque(Link.getInstance());
        }

    }

    @Override
    public boolean condition(Link link) {
        return !link.isInvisible();
    }

    @Override
    public int valeur() {
        return 32;
    }

}