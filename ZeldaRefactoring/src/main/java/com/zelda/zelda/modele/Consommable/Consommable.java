package com.zelda.zelda.modele.Consommable;

import com.zelda.zelda.modele.Inventaire;
import com.zelda.zelda.modele.Item;
import javafx.beans.property.IntegerProperty;

public class Consommable extends Item {

    protected String nomPng;

    protected IntegerProperty x;
    protected IntegerProperty y;

    public static int compteur =0;
    private String id;

    public Consommable(){
        this.id = "P" + compteur;
        compteur++;
    }

    @Override
    public void ajouterInventaire(Inventaire inventaire) {
        inventaire.getInventaireConsommable().add(this);
    }

    public String getId(){
        return this.id;
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

    public String getNomPng(){
        return this.nomPng;
    }

}
