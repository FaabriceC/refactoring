package com.zelda.zelda.modele.armes;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Projectile {

    private String nom;
    private IntegerProperty xProjectile;
    private IntegerProperty yProjectile;

    public static int compteur =1;
    private String id;

    private int dire;

    public Projectile(String nom){
        this.nom = nom;
        this.xProjectile = new SimpleIntegerProperty();
        this.yProjectile = new SimpleIntegerProperty();

        this.id = "P" + compteur;
        compteur++;
    }


    public String getId() {
        return id;
    }

    public int getxProjectile() {
        return xProjectile.get();
    }

    public IntegerProperty xProjectileProperty() {
        return xProjectile;
    }


    public int getyProjectile() {
        return yProjectile.get();
    }

    public IntegerProperty yProjectileProperty() {
        return yProjectile;
    }


    public void setxProjectile(int xProjectile) {
        this.xProjectile.set(xProjectile);
    }

    public void setyProjectile(int yProjectile) {
        this.yProjectile.set(yProjectile);
    }

    public String getNom() {
        return nom;
    }

    public int getDire() {
        return dire;
    }

    public void setDire(int dire) {
        this.dire = dire;
    }

    public void setxProjectileNull() {
        this.xProjectile.setValue(null);
    }
    public void setyProjectileNull() {
        this.yProjectile.setValue(null);
    }
}
