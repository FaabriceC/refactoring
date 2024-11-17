package com.zelda.zelda.controleur;

import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.acteur.Boss;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;


public class GameLoop {

    private Timeline gameLoop;
    private IntegerProperty temps;

    public GameLoop() {
        temps = new SimpleIntegerProperty(0);
        initAnimation();
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);

        KeyFrame kf = new KeyFrame(Duration.seconds(0.033), (event) -> {
            Link.getInstance().seDeplace();
            if (temps.getValue() % 2 == 0) {
                Environnement.getInstance().actionMonstre();
            }

            temps.setValue(temps.getValue()+1);

            if (Link.getInstance().estMort()) {
                gameLoop.stop();
            }
        });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }

    public void start() {
        gameLoop.play();
    }

}