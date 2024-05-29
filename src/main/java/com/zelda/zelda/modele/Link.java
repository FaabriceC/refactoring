package com.zelda.zelda.modele;


import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Link extends Personnage {

    private IntegerProperty pv;
    private boolean linkAttaque;

    public Link(String nom, int x, int y, Terrain t) {
        super(x, y, nom, t);

        this.pv= new SimpleIntegerProperty(5) {
        };
        this.pv.setValue(5);
        this.linkAttaque = false;

    }


    public void  seDeplace(Link link) {
        int deplacementX = 0;
        int deplacementY = 0;


        if (this.direction.getValue() == 2) {
            deplacementX += 2;
        }  if (this.direction.getValue() == 4) {
            deplacementX -= 2;
        } if (this.direction.getValue() == 3) {
            deplacementY += 2;
        } if (this.direction.getValue() == 1) {
            deplacementY -= 2;
        }

        int newX = this.getX() + deplacementX;
        int newY = this.getY() + deplacementY;

        if (terrain.dansTerrain(newX, newY) && !terrain.collisionAvecTuile(newX, newY,link)) {


            this.setX(newX);
            this.setY(newY);
        }
    }

    /*
    public void attaque(Monstre monstre){
        if(this.linkAttaque == true  && Math.abs(this.getX() - monstre.getX()) < 16 && Math.abs(this.getY() - monstre.getY()) < 16){
            monstre.setPv(monstre.getPv()-5);
        }
    }

     */

    public void attaqu2(Monstre monstre){
        if(this.linkAttaque == true  && Math.abs(this.getX() - monstre.getX()) < 16 && Math.abs(this.getY() - monstre.getY()) < 16){
            monstre.setPv(monstre.getPv()-5);
        }
    }



    public void setPv(int pv) {
        this.pv.set(pv);
    }

    public int getPv() {
        return pv.get();
    }

    public IntegerProperty pvProperty() {
        return pv;
    }

    @Override
    public String toString() {
        return "Link" + super.toString();
    }










}