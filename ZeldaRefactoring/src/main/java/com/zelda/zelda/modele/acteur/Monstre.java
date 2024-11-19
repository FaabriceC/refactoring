package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.deplacement.BFS;
import com.zelda.zelda.modele.Pattern.Strategy.Deplacement.StrategieDeplacement;

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

    public double distanceAvecLink() {
        int dx = this.getX() - Link.getInstance().getX();
        int dy = this.getY() - Link.getInstance().getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    public boolean peutSeDeplacer  (int tuileX, int tuileY){
        int margeX = margeErreur()[0];
        int margeY = margeErreur()[1];

        return  !Terrain.getInstance().collision(tuileX+margeX,tuileY+margeY);
    }

    public void attaqueSiPossible(Personnage personnage) {
        long currentTime = System.currentTimeMillis();
        if ((currentTime - actionTime >= 2500) && (peutAttaquer( 1) || peutAttaquer(2) || peutAttaquer(3) || peutAttaquer(4))) {
            Link.getInstance().setPv(Link.getInstance().getPv() - 1);
            actionTime = currentTime;
        }
    }

    public void effectueDeplacement(){
        if (!Link.getInstance().isInvisible().getValue()) {
            strategieDeplacement.seDeplace();
        }
    }

    public boolean peutAttaquer(int direction) {
        return (this.direction.getValue() == direction && this.getY() - Link.getInstance().getY() < 16 && Link.getInstance().getY() - this.getY() >= 0 && Math.abs(this.getX() - Link.getInstance().getX()) < 8 ||
                this.direction.getValue() == direction && Link.getInstance().getX() - this.getX() < 16 && Link.getInstance().getX() - this.getX() >= 0 && Math.abs(this.getY() - Link.getInstance().getY()) < 8);

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

    public void setSubitDegat(boolean subitDegat) {
        this.subitDegat = subitDegat;
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