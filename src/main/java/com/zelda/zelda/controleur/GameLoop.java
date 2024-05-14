package com.zelda.zelda.controleur;


import com.zelda.zelda.HelloApplication;
import com.zelda.zelda.controleur.Controleur;
import javafx.scene.image.Image;

import com.zelda.zelda.modele.Link;
import com.zelda.zelda.modele.Terrain;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.util.HashMap;
import java.util.Map;

public class GameLoop extends AnimationTimer {
    private Controleur controleur;
    private Map<KeyCode, Boolean> keys = new HashMap<>();
    private long dernierMouvement = 0;
    private final long DELAI_MOUVEMENT = 25_000_000;//100 000 000

    public GameLoop(Controleur controleur) {
        this.controleur = controleur;
    }

    @Override
    public void handle(long now) {
        if (now - this.dernierMouvement >= this.DELAI_MOUVEMENT) {
            rafraichir();
            this.dernierMouvement = now;
        }
    }

    // Méthode pour rafraîchir la position du personnage
    private void rafraichir() {
        Link link = this.controleur.getLink(); // Obtenir la référence de link depuis le contrôleur
        Terrain terrain = this.controleur.getTerrain(); // Obtenir la référence de terrain depuis le contrôleur
        int deplacementX = 0;
        int deplacementY = 0;
        String direction = "rien";

// Optimiser le if elseif juste en bas

        if (isKeyPressed(KeyCode.RIGHT)) {
            deplacementX += 2;
            link.setNomImageActuelle("Link4.png"); // Image pour déplacement vers la droite
            direction = "RIGHT";
        } else if (isKeyPressed(KeyCode.LEFT)) {
            deplacementX -= 2;
            link.setNomImageActuelle("Link2.png"); // Image pour déplacement vers la gauche
            direction = "LEFT";
        } else if (isKeyPressed(KeyCode.DOWN)) {
            deplacementY += 2;
            link.setNomImageActuelle("Link1.png");
            direction = "DOWN";
        } else if (isKeyPressed(KeyCode.UP)) {
            deplacementY -= 2;
            link.setNomImageActuelle("Link3.png"); // Image pour déplacement vers le haut
            direction = "UP";
        }




        int newX = link.getX() + deplacementX;
        int newY = link.getY() + deplacementY;

        if (terrain.dansTerrain(newX, newY) && !terrain.collisionAvecTuile(newX,newY,direction)) {


                link.setX(newX);
                link.setY(newY);
                link.getImageView().setX(newX);
                link.getImageView().setY(newY);
        }
        link.getImageView().setImage(new Image(String.valueOf(HelloApplication.class.getResource(link.getNomImageActuelle()))));
    }

    // Méthode pour vérifier si une touche est actuellement enfoncée
    private boolean isKeyPressed(KeyCode key) {
        return this.keys.getOrDefault(key, false);
    }

    // Méthode pour ajouter ou retirer une touche de la map en fonction de son état
    public void setKeyPressed(KeyCode key, boolean pressed) {
        this.keys.put(key, pressed);
    }

    // Gestionnaire d'événements pour la pression des touches
    public void gererKeyPressed(KeyEvent event) {
        setKeyPressed(event.getCode(), true);
    }

    // Gestionnaire d'événements pour le relâchement des touches
    public void gererKeyReleased(KeyEvent event) {
        setKeyPressed(event.getCode(), false);
    }
}