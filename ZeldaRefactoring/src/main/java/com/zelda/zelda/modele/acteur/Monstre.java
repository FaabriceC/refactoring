package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.deplacement.BFS;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.List;

public abstract class Monstre extends Personnage {
    private IntegerProperty pv;
    private List<int[]> chemin;
    private int cheminIndex = 0;
    long actionTime = 0L;
    private int nbTours;

    protected BFS bfs;

    private boolean dircAlea;

    private boolean monsSubitDegat;

    public Monstre(int pv, int x, int y, String nom, Terrain t) {
        super(x, y, nom, t);
        this.pv = new SimpleIntegerProperty(pv);
        this.pv.setValue(pv);
        this.nbTours = 0;
        this.bfs = new BFS(this, terrain);
        this.dircAlea = false;
        this.monsSubitDegat = false;
    }

    public abstract void seDeplace(Link link);

    public boolean peutSeDeplacer  (int tuileX, int tuileY){
        int margeX = margeErreur(0,0)[0];
        int margeY = margeErreur(0,0)[1];

        return  !terrain.collision(tuileX+margeX,tuileY+margeY);
    }

    public void choisiDirectionAleatoire() {
        if ((int) (Math.random() * 10) == 1) {
            this.direction.setValue((int) ((Math.random() * 4) + 1));
        }
    }

    public abstract void attaque(Link link);


    public void setPv(int pv) {
        this.pv.set(pv);
    }

    public int getPv() {
        return pv.get();
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


    public boolean isMonsSubitDegat() {
        return monsSubitDegat;
    }

    public void setMonsSubitDegat(boolean monsSubitDegat) {
        this.monsSubitDegat = monsSubitDegat;
    }



}
