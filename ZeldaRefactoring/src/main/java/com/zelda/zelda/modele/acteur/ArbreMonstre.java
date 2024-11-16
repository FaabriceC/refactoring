package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;

public class ArbreMonstre extends Monstre {

    public ArbreMonstre(int x, int y) {
        super(200, x, y, "arbreMonstre.png", Terrain.getInstance());
    }

    public boolean condition(Link link) {
        return true;
    }

    public int valeur() {
        return 32;
    }

}
