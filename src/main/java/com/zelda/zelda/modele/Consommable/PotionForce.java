package com.zelda.zelda.modele.Consommable;

import com.zelda.zelda.modele.Consommable.Consommable;
import javafx.beans.property.SimpleIntegerProperty;

public class PotionForce extends Consommable {
    private int force;

    public PotionForce(){
        this.nom = "potionForce.png";
        this.force   = 2;

        this.x = new SimpleIntegerProperty(300);
        this.y = new SimpleIntegerProperty(150);
    }



    public int getPvSoin() {
        return force;
    }
}