package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.deplacement.Point2D;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public  class Epee extends Arme {

    private int degat;


    public Epee(){
        this.x = new SimpleIntegerProperty(1500);
        this.y = new SimpleIntegerProperty(400);
        this.degats=2;
        this.nomPng = "epee.png";
    }



    public void attaquer(){
    }




}