package com.zelda.zelda.controleur;

import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.vue.InventaireVue;
import com.zelda.zelda.vue.acteur.LinkVue;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControleurKey  {

    private final List<KeyCode> keyOrder = new ArrayList<>();
    private InventaireVue inventaireVue;
    private Environnement env;

    public ControleurKey(InventaireVue inventaireVue) {

        this.inventaireVue=inventaireVue;
    }

    public void initKeyHandler(Pane panneauJeu, Link link) {
        panneauJeu.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    KeyCode key = event.getCode();
                    if (!keyOrder.contains(key)) {
                        keyOrder.add(key);
                    }
                    updateLink(link);
                });

                newScene.setOnKeyReleased(event -> {
                    KeyCode key = event.getCode();
                    keyOrder.remove(key);
                    updateLink(link);
                });
            }
        });
    }

    private void updateLink(Link link) {
        boolean upPressed = keyOrder.contains(KeyCode.UP);
        boolean rightPressed = keyOrder.contains(KeyCode.RIGHT);
        boolean downPressed = keyOrder.contains(KeyCode.DOWN);
        boolean leftPressed = keyOrder.contains(KeyCode.LEFT);
        boolean xPressed = keyOrder.contains(KeyCode.X);
        boolean fPressed = keyOrder.contains(KeyCode.F);
        boolean iPressed = keyOrder.contains(KeyCode.I);
        boolean ePressed = keyOrder.contains(KeyCode.E);
        boolean uPressed = keyOrder.contains(KeyCode.U);
        boolean pPressed = keyOrder.contains(KeyCode.P);
        boolean kPressed = keyOrder.contains(KeyCode.K);
        boolean lPressed = keyOrder.contains(KeyCode.L);
        boolean mPressed = keyOrder.contains(KeyCode.M);

        int direction = 0;
        boolean moving = false;

        for (int i = keyOrder.size() - 1; i >= 0; i--) {
            KeyCode key = keyOrder.get(i);
            switch (key) {
                case UP:
                    if (!downPressed) {
                        direction = 1;
                        moving = true;
                        link.setDerniereDirection(1);
                    }
                    break;
                case RIGHT:
                    if (!leftPressed) {
                        direction = 2;
                        moving = true;
                        link.setDerniereDirection(2);
                    }
                    break;
                case DOWN:
                    if (!upPressed) {
                        direction = 3;
                        moving = true;
                        link.setDerniereDirection(3);
                    }
                    break;
                case LEFT:
                    if (!rightPressed) {
                        direction = 4;
                        moving = true;
                        link.setDerniereDirection(4);
                    }
                    break;
                case Z:
                    if (!downPressed) {
                        direction = 1;
                        moving = true;
                        link.setDerniereDirection(1);
                    }
                    break;
                case D:
                    if (!leftPressed) {
                        direction = 2;
                        moving = true;
                        link.setDerniereDirection(2);
                    }
                    break;
                case S:
                    if (!upPressed) {
                        direction = 3;
                        moving = true;
                        link.setDerniereDirection(3);
                    }
                    break;
                case Q:
                    if (!rightPressed) {
                        direction = 4;
                        moving = true;
                        link.setDerniereDirection(4);
                    }
                    break;
                case X:
                    xPressed = true;
                    break;

                case F:
                    fPressed = true;
                    break;
                case U:
                    uPressed = true;
                    break;
                case P:
                    pPressed = true;
                    break;
                case K:
                    kPressed = true;
                    break;
                case L:
                    lPressed =true ;
                case M:
                    mPressed = true;
                    break;
                default:
                    break;
            }
            if (moving) {
                break;
            }
        }

        link.setStatutPas(moving);
        link.indicePasProperty().setValue(moving ? 1 : 0);
        link.directionProperty().setValue(direction);

        if (xPressed) {
            Link.getInstance().attaqueMonstre();
        }
        if (fPressed){
            Link.getInstance().ramasser(Environnement.getInstance().getArmes());
        }


        if (pPressed) {
            Link.getInstance().equiperArme();
        }


        if (iPressed){
            inventaireVue.setVisible();

        }
        if (ePressed){
            Link.getInstance().ramasser(Environnement.getInstance().getConsommables());

        }
        if (uPressed){
            link.setArmeChoisi(Link.getInstance().getInventaire().getInventaireArme().get(0).getNom());
        } else if(pPressed && Link.getInstance().getInventaire().getInventaireArme().size()>1){
            link.setArmeChoisi(Link.getInstance().getInventaire().getInventaireArme().get(1).getNom());
        }else if(kPressed && Link.getInstance().getInventaire().getInventaireArme().size()>2){
            link.setArmeChoisi(Link.getInstance().getInventaire().getInventaireArme().get(2).getNom());
        }else if(lPressed && Link.getInstance().getInventaire().getInventaireArme().size()>3){
            link.setArmeChoisi(Link.getInstance().getInventaire().getInventaireArme().get(3).getNom());
        }else if(mPressed && Link.getInstance().getInventaire().getInventaireArme().size()>4){
            link.setArmeChoisi(Link.getInstance().getInventaire().getInventaireArme().get(4).getNom());
        }

    }
}