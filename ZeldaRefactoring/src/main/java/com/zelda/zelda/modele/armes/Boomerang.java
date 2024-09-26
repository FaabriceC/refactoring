package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.deplacement.Point2D;
import javafx.beans.property.SimpleIntegerProperty;

public  class Boomerang extends Arme {
    private int degats;



    public Boomerang (){
        this.degats=2;
        this.x = new SimpleIntegerProperty(1500);
        this.y = new SimpleIntegerProperty(500);
        this.nomPng="boomerang.png";
    }



    public void attaquer(){
    }


}