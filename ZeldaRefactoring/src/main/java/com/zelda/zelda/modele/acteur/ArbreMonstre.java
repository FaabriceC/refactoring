package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;

public class ArbreMonstre extends Monstre {

    public ArbreMonstre(int x, int y) {

        super(200, x, y, "arbreMonstre.png");
    }

    public boolean condition(Link link) {
        return true;
    }

    public int valeur() {
        return 32;
    }

    public void seDeplaceEtAttaque(){
        if (Math.abs(Link.getInstance().getX() - this.getX()) < 128 && Math.abs(Link.getInstance().getY() - this.getY()) < 128) {
            strategieDeplacement.seDeplace();
            attaqueSiPossible(Link.getInstance());
        }
    }

    @Override
    public void attaqueSiPossible(Personnage personnage) {
        super.attaqueSiPossible(personnage);

    }
}
