package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;

public class Boss extends Monstre {

    private boolean anim =false;

    public Boss(int x, int y, Terrain t){
        super(100,x,y,"slime3.gif",t);
    }

    public void seDeplace(Link link) {
        anim=false;
        if (Math.abs(link.getX() - this.getX()) < 128 && Math.abs(link.getY() - this.getY()) < 128) {
            if(!link.isInvisible())
                this.indicePasProperty().setValue(1);
            bfs.seDeplace(link);
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

}