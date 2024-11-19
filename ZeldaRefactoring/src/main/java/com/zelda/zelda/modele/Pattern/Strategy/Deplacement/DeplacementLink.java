package com.zelda.zelda.modele.Pattern.Strategy.Deplacement;

import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.acteur.Link;

public class DeplacementLink implements StrategieDeplacement {


    public void seDeplace() {
        Link.getInstance().setTp(false);
        int vitesse = Link.getInstance().pousseLeBloc() ? 1 : 5;

        int deplacementX = (Link.getInstance().getDirection() == 2 ? vitesse : 0) -
                (Link.getInstance().getDirection() == 4 ? vitesse : 0);
        int deplacementY = (Link.getInstance().getDirection() == 3 ? vitesse : 0) -
                (Link.getInstance().getDirection() == 1 ? vitesse : 0);

        int newX = Link.getInstance().getX() + deplacementX;
        int newY = Link.getInstance().getY() + deplacementY;

        switch (Link.getInstance().getTp()) {
            case 102 -> teleporterVers(4158, 1556);
            case 30 -> teleporterVers(3136, 1816);
        }

        if (!Link.getInstance().isTp() && Link.getInstance().peutSeDeplacer(newX, newY) && Terrain.getInstance().dansTerrain(newX, newY)) {
            Link.getInstance().setX(newX);
            Link.getInstance().setY(newY);
        }
    }

    public void teleporterVers(int x, int y) {
        Link.getInstance().setX(x);
        Link.getInstance().setY(y);
        Link.getInstance().setTp(true);
    }

}