package com.zelda.zelda.modele.Consommable;

import com.zelda.zelda.modele.Consommable.Consommable;
import javafx.beans.property.SimpleIntegerProperty;

public class PotionForce extends Consommable {
    private int force;

    public PotionForce(){
        this.nom = "potionForce.png";
        this.force   = 2;

        this.x = new SimpleIntegerProperty(500);
        this.y = new SimpleIntegerProperty(450);
    }

}