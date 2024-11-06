package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;

public class ArbreMonstre extends Monstre {

    public ArbreMonstre(int x, int y, Terrain t) {
        super(10, x, y, "arbreMonstre.png", t);
    }

    @Override
    public void seDeplace(Link link) {
        super.seDeplace(link);
    }

    @Override
    public boolean condition(Link link) {
        return true;
    }

    @Override
    public int valeur() {
        return 32;
    }

}

