package com.zelda.zelda.modele.Consommable;

import javafx.beans.property.SimpleIntegerProperty;

public class Bracelet extends Consommable {

    public Bracelet(){
        this.nom = "bracelet.png";
        this.x = new SimpleIntegerProperty(850);
        this.y = new SimpleIntegerProperty(450);

    }

}