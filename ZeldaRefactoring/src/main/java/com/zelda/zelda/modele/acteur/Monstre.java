package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.deplacement.BFS;
import com.zelda.zelda.modele.deplacement.StrategieDeplacement;
import javafx.beans.property.IntegerProperty;

import java.util.List;

public abstract class Monstre extends Personnage {
    long actionTime = 0L;
    protected StrategieDeplacement strategieDeplacement;
    private boolean dircAlea;
    private boolean subitDegat;

    public Monstre(int pv,int x, int y, String nom) {
        super( pv,x, y, nom);
        this.dircAlea = false;
        this.strategieDeplacement = new BFS(this,Terrain.getInstance());
        this.subitDegat = false;
    }

    public void seDeplaceEtAttaque(){
        if (Math.abs(Link.getInstance().getX() - this.getX()) < 128 && Math.abs(Link.getInstance().getY() - this.getY()) < 128) {
            effectueDeplacement();

        }
        attaqueSiPossible(Link.getInstance());
    }
    public void setStrategieDeplacement(StrategieDeplacement strategieDeplacement){
        this.strategieDeplacement = strategieDeplacement;
    }

    public StrategieDeplacement getStrategieDeplacement(){
        return this.strategieDeplacement;
    }

    public double distanceAvecLink() {
        int dx = this.getX() - Link.getInstance().getX();
        int dy = this.getY() - Link.getInstance().getY();
        return Math.sqrt(dx * dx + dy * dy);
    }


    public abstract boolean condition(Link link);

    public boolean peutSeDeplacer  (int tuileX, int tuileY){
        int margeX = margeErreur(0,0)[0];
        int margeY = margeErreur(0,0)[1];

        return  !Terrain.getInstance().collision(tuileX+margeX,tuileY+margeY);
    }

    public void attaqueSiPossible(Personnage personnage) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - actionTime >= 2500) && (peutAttaquer(Link.getInstance(), 1) || peutAttaquer(Link.getInstance(), 2) || peutAttaquer(Link.getInstance(), 3) || peutAttaquer(Link.getInstance(), 4))) {
            Link.getInstance().setPv(Link.getInstance().getPv() - 1);
            actionTime = currentTime;
        }
    }

    public void effectueDeplacement(){
        if (!Link.getInstance().isInvisible().getValue()) {
            strategieDeplacement.seDeplace();
        }
    }

    public boolean peutAttaquer(Link link, int direction) {
        return (this.direction.getValue() == direction && this.getY() - link.getY() < 16 && link.getY() - this.getY() >= 0 && Math.abs(this.getX() - link.getX()) < 8 ||
                this.direction.getValue() == direction && link.getX() - this.getX() < 16 && link.getX() - this.getX() >= 0 && Math.abs(this.getY() - link.getY()) < 8);

    }



    public IntegerProperty pvProperty() {
        return pv;
    }

    public boolean vivant() {
        return this.pv.getValue() > 0;
    }

    public boolean isDircAlea() {
        return dircAlea;
    }

    public void setDirectionValue(int directionValue) {
        this.direction.setValue(directionValue);
    }


    public boolean isSubitDegat() {
        return subitDegat;
    }



    public int[] margeErreur(int margeX, int margeY) {
        int[] marge = new int[2];
        switch (direction.getValue()) {
            case 4:
                margeX = valeur();
                margeY = valeur();
                break;
            case 2:
                margeX = valeur();
                margeY = valeur();
                break;
            case 1:
                margeX = valeur();
                margeY = valeur();
                break;
            case 3:
                margeX = valeur();
                margeY = valeur();
                break;
        }
        marge[0] = margeX;
        marge[1] = margeY;
        return new int[]{margeX, margeY};
    }

    public void setSubitDegat(boolean subitDegat) {
        this.subitDegat = subitDegat;
    }

    public abstract int valeur();

}