package com.zelda.zelda.modele.Consommable;

import com.zelda.zelda.modele.Consommable.Consommable;
import javafx.beans.property.SimpleIntegerProperty;

public class PotionSoin extends Consommable {
    private int pvSoin;

    public PotionSoin(){
        this.nom = "potionSoin.png";
        this.pvSoin = 2;

        this.x = new SimpleIntegerProperty(520);
        this.y = new SimpleIntegerProperty(450);
    }



    public int getPvSoin() {
        return pvSoin;
    }
}
