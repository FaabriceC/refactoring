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
        this.bfs = new BFS(this, Terrain.getInstance());
        this.dircAlea = false;
        this.monsSubitDegat = false;
    }

    public void seDeplace(){
        if (Math.abs(Link.getInstance().getX() - this.getX()) < 128 && Math.abs(Link.getInstance().getY() - this.getY()) < 128) {
            if (condition(Link.getInstance())) {
                bfs.seDeplace(Link.getInstance());
            }

        }
        attaque();
    }

    public abstract boolean condition(Link link);

    public boolean peutSeDeplacer  (int tuileX, int tuileY){
        int margeX = margeErreur(0,0)[0];
        int margeY = margeErreur(0,0)[1];

        return  !Terrain.getInstance().collision(tuileX+margeX,tuileY+margeY);
    }

    public void choisiDirectionAleatoire() {
        if ((int) (Math.random() * 10) == 1) {
            this.direction.setValue((int) ((Math.random() * 4) + 1));
        }
    }

    public abstract void attaque(Link link);

    public boolean peutAttaquer(Link link, int direction) {
        return (this.direction.getValue() == direction && this.getY() - link.getY() < 16 && link.getY() - this.getY() >= 0 && Math.abs(this.getX() - link.getX()) < 8 ||
                this.direction.getValue() == direction && link.getX() - this.getX() < 16 && link.getX() - this.getX() >= 0 && Math.abs(this.getY() - link.getY()) < 8);

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

    public boolean peutReculerSelonDirection(int direction){
        if(direction == 1){
            return this.peutSeDeplacer(this.getX(),this.getY()-32);
        } else if (direction == 2){
            return this.peutSeDeplacer(this.getX()+32,this.getY());
        } else if (direction == 3){
            return this.peutSeDeplacer(this.getX(),this.getY()+32);
        } else {
            return this.peutSeDeplacer(this.getX()-32,this.getY());
        }
    }


}
