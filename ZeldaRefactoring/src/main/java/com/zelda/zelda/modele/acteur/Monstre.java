package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.deplacement.BFS;
import com.zelda.zelda.modele.Pattern.Strategy.Deplacement.StrategieDeplacement;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

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
        if ((currentTime - actionTime >= 2500) && (peutAttaquer(this.direction.getValue()))) {
            Link.getInstance().setPv(Link.getInstance().getPv() - 1);
            System.out.println("Link prend un degat //////////////////////////////////////////////////");
            actionTime = currentTime;
        }
    }

    public void effectueDeplacement(){
        if (!Link.getInstance().isInvisible().getValue()) {
            strategieDeplacement.seDeplace();
        }
    }

    public boolean peutAttaquer(int direction) {
        return (direction == 1 && this.getY() - Link.getInstance().getY() < 32 && Link.getInstance().getY() - this.getY() >= -1 && Math.abs(this.getX() - Link.getInstance().getX()) < 16 ||
                direction == 3 && Link.getInstance().getY() - this.getY() < 48 && this.getY() - Link.getInstance().getY() >= -1 && Math.abs(this.getX() - Link.getInstance().getX()) < 16 ||
                direction == 2 && Link.getInstance().getX() - this.getX() < 32 && Link.getInstance().getX() - this.getX() >= -1 && Math.abs(this.getY() - Link.getInstance().getY()) < 16 ||
                direction == 4 && this.getX() - Link.getInstance().getX() < 32 && this.getX() - Link.getInstance().getX() >= -1 && Math.abs(this.getY() - Link.getInstance().getY()) < 16);

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