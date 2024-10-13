package com.zelda.zelda.modele.armes;

import javafx.beans.property.IntegerProperty;

public abstract class Arme {

    protected int degats;
    protected IntegerProperty x;
    protected IntegerProperty y;

    public static int compteur = 1;
    private String id;

    protected String nomPng;

    public Arme(){
        this.id = "W" + compteur;
        compteur++;
    }

    public void attaquer(){

    }


    public String getId() {
        return id;
    }


    public String getNomPng() {
        return nomPng;
    }


    public int getX(){
        return x.getValue();
    }

    public int getY(){
        return y.getValue();
    }


    public IntegerProperty xProperty(){
        return x;
    }

    public IntegerProperty yProperty(){
        return y;
    }

    public int getDegats() {
        return degats;
    }

    @Override
    public String toString() {
        return "Arme{" +
                "degats=" + degats +
                ", x=" + x +
                ", y=" + y +
                ", id='" + id + '\'' +
                ", nomPng='" + nomPng + '\'' +
                '}';
    }
}