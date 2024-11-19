package com.zelda.zelda.modele.acteur;

public class ArbreMonstre extends Monstre {

    public ArbreMonstre(int x, int y) {

        super(20, x, y, "arbreMonstre.png");
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

    @Override
    public int[] getMarges() {
        return new int[]{32, 32};
    }
}
