package com.zelda.zelda.modele.deplacement;

import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.acteur.Link;

public class DeplacementLink implements StrategieDeplacement {


    public void seDeplace() {
        Link.getInstance().setTp(false);
        int deplacementX = 0;
        int deplacementY = 0;

        int vitesse = 5;

        if (Link.getInstance().pousseLeBloc())
            vitesse = 1;

        if (Link.getInstance().directionProperty().getValue() == 2) {
            deplacementX += vitesse;

        }
        if (Link.getInstance().directionProperty().getValue() == 4) {
            deplacementX -= vitesse;

        }
        if (Link.getInstance().directionProperty().getValue() == 3) {
            deplacementY += vitesse;

        }
        if (Link.getInstance().directionProperty().getValue() == 1) {
            deplacementY -= vitesse;

        }

        int newX = Link.getInstance().getX() + deplacementX;
        int newY = Link.getInstance().getY() + deplacementY;

        if (Link.getInstance().getTp(newX, newY) == 102) {
            Link.getInstance().setX(4158);
            Link.getInstance().setY(1556);
            Link.getInstance().setTp(true);
        }

        if ( Link.getInstance().getTp(newX, newY) == 30) {
            Link.getInstance().setX(3136);
            Link.getInstance().setY(1816);
            Link.getInstance().setTp(true);        }

        if (Link.getInstance().peutSeDeplacer(newX, newY) && Terrain.getInstance().dansTerrain(newX, newY) && ! Link.getInstance().isTp()) {
            Link.getInstance().setX(newX);
            Link.getInstance().setY(newY);
        }


    }
}