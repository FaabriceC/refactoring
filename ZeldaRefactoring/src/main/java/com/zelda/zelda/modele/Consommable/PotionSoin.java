package com.zelda.zelda.modele.Consommable;

import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.acteur.Link;
import javafx.beans.property.SimpleIntegerProperty;

public class PotionSoin extends Consommable {
    private int pvSoin;

    public PotionSoin(){
        this.nom = "potionSoin.png";
        this.pvSoin = 2;

        this.x = new SimpleIntegerProperty(520);
        this.y = new SimpleIntegerProperty(450);
    }

    public void utilise() {
        if (Link.getInstance().getPv() <= 3) {
            Link.getInstance().setPv(Link.getInstance().getPv() + 2);
        } else if (Link.getInstance().getPv() == 4) {
            Link.getInstance().setPv(Link.getInstance().getPv() + 1);
        }
    }

}
