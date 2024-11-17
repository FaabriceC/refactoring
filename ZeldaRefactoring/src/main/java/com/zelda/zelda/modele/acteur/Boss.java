package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.deplacement.StrategieDeplacement;

public class Boss extends Monstre {

    private boolean anim =false;

    public Boss(int x, int y){
        super(100,x,y,"slime3.gif");
    }

    public void seDeplaceEtAttaque() {
        if (distanceAvecLink() < 128) {
            this.indicePasProperty().setValue(1);
            strategieDeplacement.seDeplace();
            anim=true;
        }

        if (!anim){
            this.indicePasProperty().setValue(0);
        }

    }

    @Override
    public boolean condition(Link link) {
        return true;
    }

    // Valeur pour la fonction margeErreur()
    @Override
    public int valeur() {
        return 24;
    }

    @Override
    public void attaqueSiPossible(Personnage personnage) {
            super.attaqueSiPossible(personnage);
    }
}