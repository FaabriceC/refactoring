package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public abstract class Projectile {

    private String nom;
    private IntegerProperty xProjectile;
    private IntegerProperty yProjectile;
    public static int compteur =1;
    private String id;
    private int dire;
    protected int degat;


    public Projectile(String nom){
        this.nom = nom;
        this.xProjectile = new SimpleIntegerProperty();
        this.yProjectile = new SimpleIntegerProperty();
        this.id = "F" + compteur;
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


    public int getDegat() {
        return degat;
    }

    public boolean peutToucherMonstre(int direcrionProjectile,Monstre monstre, Projectile projectile) {
        if (direcrionProjectile == 1 || direcrionProjectile == 3){
            return Math.abs(projectile.getyProjectile() - monstre.getY()) < 2  && Math.abs(projectile.getxProjectile()-monstre.getX()) < 32;
        } else {
            return Math.abs(projectile.getxProjectile() - monstre.getX()) < 2 && Math.abs(projectile.getyProjectile() - monstre.getY()) < 32;
        }
    }


    public void faitDesDegatAuMonstre(Monstre monstre,Projectile projectile){
        monstre.setPv(monstre.getPv() - (Link.getInstance().getPointAttaque() + projectile.getDegat()));
        System.out.println(monstre.getPv());
    }

    public abstract void seDeplaceSelonDirection(int directionProjectile);
}
