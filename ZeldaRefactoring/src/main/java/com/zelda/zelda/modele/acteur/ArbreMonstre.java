package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;

public class ArbreMonstre extends Monstre {

    public ArbreMonstre(int x, int y, Terrain t) {
        super(10, x, y, "arbreMonstre.png", t);
    }

    @Override
    public void seDeplace(Link link) {
        super.seDeplace(link);
    }

    public void attaque(Link link) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - actionTime >= 2500) && (peutAttaquer(link, 1) || peutAttaquer(link, 2) || peutAttaquer(link, 3) || peutAttaquer(link, 4))) {
            link.setPv(link.getPv() - 1);
            actionTime = currentTime;
        }
    }

    public boolean peutAttaquer(Link link, int direction) {
        if (this.direction.getValue() == direction && this.getY() - link.getY() < 16 && link.getY() - this.getY() >= 0 && Math.abs(this.getX() - link.getX()) < 8 ||
                this.direction.getValue() == direction && link.getX() - this.getX() < 16 && link.getX() - this.getX() >= 0 && Math.abs(this.getY() - link.getY()) < 8) {
            return true;
        }
        return false;
    }

    /**
    @Override
    public void attaque(Link link) {
        long currentTime = System.currentTimeMillis();
        int linkX = link.getX();
        int linkY = link.getY();
        int deltaX = Math.abs(this.getX() - linkX);
        int deltaY = Math.abs(this.getY() - linkY);

        if (currentTime - actionTime >= 2500) {
            if (this.direction.getValue() == 1 && deltaY < 16 && linkY <= this.getY() && deltaX < 8) {
                link.setPv(link.getPv() - 1);
                actionTime = currentTime;
            } else if (this.direction.getValue() == 2 && deltaX < 16 && linkX >= this.getX() && deltaY < 8) {
                link.setPv(link.getPv() - 1);
                actionTime = currentTime;
            } else if (this.direction.getValue() == 3 && deltaY < 16 && linkY >= this.getY() && deltaX < 8) {
                link.setPv(link.getPv() - 1);
                actionTime = currentTime;
            } else if (this.direction.getValue() == 4 && deltaX < 16 && linkX <= this.getX() && deltaY < 8) {
                link.setPv(link.getPv() - 1);
                actionTime = currentTime;
            }
        }
    }
     */

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
