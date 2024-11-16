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
        return !link.isInvisible();
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