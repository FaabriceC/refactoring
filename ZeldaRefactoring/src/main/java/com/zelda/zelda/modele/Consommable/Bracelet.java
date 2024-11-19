package com.zelda.zelda.modele.Consommable;

import com.zelda.zelda.modele.acteur.Link;
import javafx.beans.property.SimpleIntegerProperty;

public class Bracelet extends Consommable {

    public Bracelet(){
        this.nom = "bracelet.png";
        this.x = new SimpleIntegerProperty(850);
        this.y = new SimpleIntegerProperty(450);

    }

    @Override
    public void utilise() {
        Link.getInstance().setInvisible(true);
    }


}