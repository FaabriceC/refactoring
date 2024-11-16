package com.zelda.zelda.modele;

import javafx.beans.property.IntegerProperty;

public abstract class Item {

    protected IntegerProperty x;
    protected IntegerProperty y;
    public static int compteur =1;
    private String id;

    protected String nomPng;

    public Item() {
        this.id = "W" + compteur;
        compteur++;
    }

    public abstract void ajouterInventaire(Inventaire inventaire);


    public int getX() {
        return x.getValue();
    }

    public int getY() {
        return y.getValue();
    }

    public String getId() {
        return id;
    }

    public String getNomPng() {
        return nomPng;
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }
}
