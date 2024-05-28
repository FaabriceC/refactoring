package com.zelda.zelda.modele;




public class Link extends Personnage {


    public Link(String nom, int x, int y, Terrain t) {
        super(x, y, nom, t);

    }


    public void  seDeplace(Link link) {
        int deplacementX = 0;
        int deplacementY = 0;


        if (this.direction.getValue() == 2) {
            deplacementX += 2;
        } else if (this.direction.getValue() == 4) {
            deplacementX -= 2;
        } else if (this.direction.getValue() == 3) {
            deplacementY += 2;
        } else if (this.direction.getValue() == 1) {
            deplacementY -= 2;
        }

        int newX = this.getX() + deplacementX;
        int newY = this.getY() + deplacementY;

        if (terrain.dansTerrain(newX, newY) && !terrain.collisionAvecTuile(newX, newY,link)) {


            this.setX(newX);
            this.setY(newY);
        }
    }




    @Override
    public String toString() {
        return "Link" + super.toString();
    }








}