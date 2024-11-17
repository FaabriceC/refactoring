package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;


public abstract class Personnage { // Crée un personnage
    private IntegerProperty x = new SimpleIntegerProperty();
    protected IntegerProperty pv;
    private IntegerProperty y = new SimpleIntegerProperty();
    private final String nom;
    protected IntegerProperty direction;
    protected IntegerProperty indicePas;
    private boolean statutPas;
    public static int compteur = 0;
    private String id;
    protected int pointAttaque;


    public Personnage(int pv,int x, int y, String nom) {
        this.x.set(x);
        this.y.set(y);
        this.pv = new SimpleIntegerProperty(pv);
        this.nom = nom;
        this.direction = new SimpleIntegerProperty(0);
        this.indicePas = new SimpleIntegerProperty(0);
        this.id = "C" + compteur;
        compteur++;

    }

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public IntegerProperty directionProperty() {
        return direction;
    }

    public IntegerProperty indicePasProperty() {
        return indicePas;
    }

    public abstract boolean peutSeDeplacer  (int tuileX, int tuileY);

    public int getX() {
        return x.getValue();
    }


    public int getY() {
        return y.getValue();
    }

    public abstract void attaqueSiPossible(Personnage personnage);

    public void setX(int x) {
        this.x.set(x);
    }

    public void setPv(int pv) {
        this.pv.set(pv);
    }

    public int getPv() {
        return pv.get();
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

    public int getPointAttaque() {
        return pointAttaque;
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public String getNom() {
        return nom;
    }

    public boolean isStatutPas() {
        return statutPas;
    }

    public void setStatutPas(boolean statutPas) {
        this.statutPas = statutPas;
    }

    public String getId(){
        return id;
    }

    public IntegerProperty pvProperty() {
        return pv;
    }

    public void setPointAttaque(int pointAttaque) {
        this.pointAttaque = pointAttaque;
    }

    public boolean estMort() {
        return this.pv.getValue() <= 0;
    }

}