package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;

public class Slime extends Monstre {

    public Slime(int x, int y, Terrain t) {
        super(5, x, y, "slime3.gif", t);
    }

    @Override
    public void seDeplace(Link link) {
        if (Math.abs(link.getX() - this.getX()) < 128 && Math.abs(link.getY() - this.getY()) < 128) {
            if (!link.isInvisible()) {
                bfs.seDeplace(link);
            }
        }
    }

    public void attaque(Link link) {
        long currentTime = System.currentTimeMillis();
        if (this.direction.getValue() == 1 && currentTime - actionTime >= 2500 && this.getY() - link.getY() < 16 && link.getY() - this.getY() >= 0 && Math.abs(this.getX() - link.getX()) < 8) {
            link.setPv(link.getPv() - 1);
            actionTime = currentTime;
        }
        if (this.direction.getValue() == 2 && currentTime - actionTime >= 2500 && link.getX() - this.getX() < 16 && link.getX() - this.getX() >= 0 && Math.abs(this.getY() - link.getY()) < 8) {
            link.setPv(link.getPv() - 1);
            actionTime = currentTime;
        }
        if (this.direction.getValue() == 3 && currentTime - actionTime >= 2500 && link.getY() - this.getY() < 16 && link.getY() - this.getY() >= 0 && Math.abs(this.getX() - link.getX()) < 8) {
            link.setPv(link.getPv() - 1);
            actionTime = currentTime;
        }
        if (this.direction.getValue() == 4 && currentTime - actionTime >= 2500 && this.getX() - link.getX() < 16 && this.getX() - link.getX() >= 0 && Math.abs(this.getY() - link.getY()) < 8) {
            link.setPv(link.getPv() - 1);
            actionTime = currentTime;
        }
    }

    @Override
    public int[] margeErreur(int margeX, int margeY) {
        int[] marge = new int[2];
        switch (direction.getValue()) {
            case 4:
                margeX = 32;
                margeY = 32;
                break;
            case 2:
                margeX = 32;
                margeY = 32;
                break;
            case 1:
                margeX = 32;
                margeY = 32;
                break;
            case 3:
                margeX = 32;
                margeY = 32;
                break;
        }
        marge[0] = margeX;
        marge[1] = margeY;
        return new int[]{margeX, margeY};
    }
}
