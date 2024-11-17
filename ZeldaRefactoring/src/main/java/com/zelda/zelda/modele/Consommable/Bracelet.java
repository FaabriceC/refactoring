package com.zelda.zelda.modele.Consommable;

import com.zelda.zelda.modele.acteur.Link;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Bracelet extends Consommable {

    private BooleanProperty utilise = new SimpleBooleanProperty(false);

    public Bracelet(){
        this.nom = "bracelet.png";
        this.x = new SimpleIntegerProperty(850);
        this.y = new SimpleIntegerProperty(450);

    }

    public BooleanProperty utiliseProperty() {
        return utilise;
    }

    public void activer() {
        utilise.set(true);
        Link.getInstance().setInvisible(true);
    }

    public void desactiver() {
        utilise.set(false);
        Link.getInstance().setInvisible(false);
    }

}