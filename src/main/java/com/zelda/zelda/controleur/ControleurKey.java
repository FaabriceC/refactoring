package com.zelda.zelda.controleur;

import com.zelda.zelda.modele.Link;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

public class ControleurKey {

    private boolean upPressed = false;
    private boolean rightPressed = false;
    private boolean downPressed = false;
    private boolean leftPressed = false;

    private boolean xPressed = false;
    private final List<KeyCode> keyOrder = new ArrayList<>();

    public ControleurKey() {
    }

    public void initKeyHandler(Pane panneauJeu, Link link) {
        panneauJeu.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                newScene.setOnKeyPressed(event -> {
                    KeyCode key = event.getCode();
                    if (!keyOrder.contains(key)) {
                        keyOrder.add(key);
                    }

                    switch (key) {
                        case UP:
                            upPressed = true;
                            break;
                        case RIGHT:
                            rightPressed = true;
                            break;
                        case DOWN:
                            downPressed = true;
                            break;
                        case LEFT:
                            leftPressed = true;
                            break;
                        case X:
                            xPressed = true;
                            break;
                    }

                    link.setStatutPas(true);
                    link.indicePasProperty().setValue(1);
                    updateLink(link);
                });

                newScene.setOnKeyReleased(event -> {
                    KeyCode key = event.getCode();
                    keyOrder.remove(key);

                    switch (key) {
                        case UP:
                            upPressed = false;
                            break;
                        case RIGHT:
                            rightPressed = false;
                            break;
                        case DOWN:
                            downPressed = false;
                            break;
                        case LEFT:
                            leftPressed = false;
                            break;
                        case X:
                            xPressed = false;
                            break;
                    }

                    updateLink(link);
                });
            }
        });
    }

    private void updateLink(Link link) {
        int direction = 0;
        boolean moving = false;

        for (int i = keyOrder.size() - 1; i >= 0; i--) {
            KeyCode key = keyOrder.get(i);
            switch (key) {
                case UP:
                    if (!downPressed) {
                        direction = 1;
                        moving = true;
                    }
                    break;
                case RIGHT:
                    if (!leftPressed) {
                        direction = 2;
                        moving = true;
                    }
                    break;
                case DOWN:
                    if (!upPressed) {
                        direction = 3;
                        moving = true;
                    }
                    break;
                case LEFT:
                    if (!rightPressed) {
                        direction = 4;
                        moving = true;
                    }
                    break;
            }
            if (moving) {
                break;
            }
        }

        if (!moving) {
            link.setStatutPas(false);
            link.indicePasProperty().setValue(0);
        } else {
            link.setStatutPas(true);
        }

        link.directionProperty().setValue(direction);

        if (xPressed) {
            link.setLinkAttaqueTrue();
        } else {
            link.setLinkAttaqueFalse();
        }
    }
}
