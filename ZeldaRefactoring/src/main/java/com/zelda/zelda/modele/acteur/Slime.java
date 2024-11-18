package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;

public class Slime extends Monstre {

    public Slime(int x, int y) {
        super(7, x, y, "slime3.gif");
    }



    @Override
    public boolean condition(Link link) {
        return !link.isInvisible().getValue();
    }


    public boolean peutAttaquer(Link link, int direction) {
        if (this.direction.getValue() == direction && this.getY() - link.getY() < 16 && link.getY() - this.getY() >= 0 && Math.abs(this.getX() - link.getX()) < 8 ||
                this.direction.getValue() == direction && link.getX() - this.getX() < 16 && link.getX() - this.getX() >= 0 && Math.abs(this.getY() - link.getY()) < 8) {
            return true;
        }
        return false;
    }


    @Override
    public int valeur() {
        return 32;
    }


    @Override
    public void attaqueSiPossible(Personnage personnage) {
        super.attaqueSiPossible(personnage);

    }

}
