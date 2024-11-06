package com.zelda.zelda.controleur;

import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.acteur.Boss;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;


public class GameLoop {

    private Link link;

    private final long DELAI_MOUVEMENT = 5_000_000;
    private final long DELAI_SLIME = 20_000_000;
    private final long DELAI_BOSS = 10_000_000;
    private Timeline gameLoop;
    private Environnement env;
    private IntegerProperty temps;

    public GameLoop(Link link, Environnement environnement) {
        this.link = link;
        this.env = environnement;
        temps = new SimpleIntegerProperty(0);
        initAnimation();
    }

    private void initAnimation() {
        gameLoop = new Timeline();
        gameLoop.setCycleCount(Timeline.INDEFINITE);


        KeyFrame kf = new KeyFrame(Duration.seconds(0.033), (event) -> {
            this.link.seDeplace();
            link.equiperArme();
            rafraichirLink();


            if (temps.getValue() % 2 == 0) {
                this.env.deplacementMonstre();
            }

            temps.setValue(temps.getValue()+1);

            if (this.link.linkMeurt()) {
                gameLoop.stop();
            }
        });

        gameLoop.getKeyFrames().add(kf);
        gameLoop.play();
    }

    public void start() {
        gameLoop.play();
    }

    private void rafraichirLink() {

        //link.agit();

        for (int i = 0; i < this.env.getPersonnageListe().size(); i++) { // TODO dans le modèle
            if (this.env.getPersonnageListe().get(i) instanceof Monstre m) {
                link.attaque(m);
//                if (link.getArmeEquipe() instanceof Arc){
//                    link.flecheSeDeplace(this.link.getFleche(),m);
//                } else if(link.getArmeEquipe() instanceof Boomerang){
//                    link.boomerangSeDeplace(this.link.getBoomerang(),m);
//                }
//
//
            }


        }


    }


}