package com.zelda.zelda.modele.Consommable;

import com.zelda.zelda.modele.Consommable.Consommable;
import javafx.beans.property.SimpleIntegerProperty;

public class PotionForce extends Consommable {
    private int force;

    public PotionForce(){
        this.nom = "potionForce.png";
        this.force = 2;
        this.x = new SimpleIntegerProperty(500);
        this.y = new SimpleIntegerProperty(450);
    }

    public void utilise() {
        Link.getInstance().setPointAttaque((Link.getInstance().getPointAttaque() + force));
        PauseTransition pause = new PauseTransition(Duration.seconds(60));
        pause.setOnFinished(event -> {
            Link.getInstance().setPointAttaque((Link.getInstance().getPointAttaque() - force));
        });
        pause.play();

    }

}