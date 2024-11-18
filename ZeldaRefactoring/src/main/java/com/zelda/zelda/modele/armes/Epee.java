package com.zelda.zelda.modele.armes;

import javafx.beans.property.SimpleIntegerProperty;

public class Epee extends Arme {

    public Epee(){

        this.x = new SimpleIntegerProperty(1500);
        this.y = new SimpleIntegerProperty(400);
        this.degats=2;
        this.nom = "epee.png";
    }

}