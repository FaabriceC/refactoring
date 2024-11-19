package com.zelda.zelda.modele.acteur;

public class Slime extends Monstre {

    public Slime(int x, int y) {
        super(7, x, y, "slime3.gif");
    }


    public boolean peutAttaquer(Link link, int direction) {
        if (this.direction.getValue() == direction && this.getY() - link.getY() < 16 && link.getY() - this.getY() >= 0 && Math.abs(this.getX() - link.getX()) < 8 ||
                this.direction.getValue() == direction && link.getX() - this.getX() < 16 && link.getX() - this.getX() >= 0 && Math.abs(this.getY() - link.getY()) < 8) {
            return true;
        }
        return false;
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
