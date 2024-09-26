package com.zelda.zelda.modele.Consommable;

import javafx.beans.property.IntegerProperty;

public class Consommable  {
    protected String nom;

    protected IntegerProperty x;
    protected IntegerProperty y;

    public static int compteur =0;
    private String id;





    public Consommable(){
        this.nom = "potion.png";
        this.id = "P" + compteur;
        compteur++;



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

    public String getNom(){
        return this.nom;
    }






}
