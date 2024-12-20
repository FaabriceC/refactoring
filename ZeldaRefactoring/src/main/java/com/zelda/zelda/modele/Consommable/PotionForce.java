package com.zelda.zelda.modele.Consommable;

import com.zelda.zelda.modele.acteur.Link;
import javafx.animation.PauseTransition;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

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