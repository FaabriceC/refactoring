package com.zelda.zelda.vue.acteur;

import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.acteur.Link;
import javafx.animation.AnimationTimer;
import javafx.animation.PauseTransition;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.control.ToolBar;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class LinkVue extends PersonnageVue {

    private Link link;
    private Pane panneauJeu;

    private static final int COEUR_SPACING = 25;

    private Image imageCoeur = new Image(String.valueOf(Lanceur.class.getResource("coeur.png")));
    private ArrayList<ImageView> listImageViewsCoeur;

    private Pane backgroundPaneConso;
    private ToolBar consommable;
    private Pane backgroundPane;
    private ToolBar itemToolBar;

    public LinkVue(Link link, Pane panneauJeu, String nomImage, Pane backgroundPaneConso, ToolBar consommable, Pane backgroundPane, ToolBar itemToolBar) {
        super(link, nomImage);
        this.panneauJeu = panneauJeu;
        this.link = link;
        this.backgroundPaneConso = backgroundPaneConso;
        this.consommable = consommable;
        this.backgroundPane = backgroundPane;
        this.itemToolBar = itemToolBar;

        setupCameraFollow();

        listImageFab = new Image[][] {
                {
                        new Image(String.valueOf(Lanceur.class.getResource("Link1.png"))),
                        new Image(String.valueOf(Lanceur.class.getResource("Link1A.gif"))),
                        new Image(String.valueOf(Lanceur.class.getResource("link1b.png")))
                },
                {
                        new Image(String.valueOf(Lanceur.class.getResource("Link2.gif"))),
                        new Image(String.valueOf(Lanceur.class.getResource("Link2A.gif"))),
                        new Image(String.valueOf(Lanceur.class.getResource("link2b.png")))
                },
                {
                        new Image(String.valueOf(Lanceur.class.getResource("Link3.gif"))),
                        new Image(String.valueOf(Lanceur.class.getResource("Link3A.gif"))),
                        new Image(String.valueOf(Lanceur.class.getResource("link3b.png")))
                },
                {
                        new Image(String.valueOf(Lanceur.class.getResource("Link4.gif"))),
                        new Image(String.valueOf(Lanceur.class.getResource("Link4A.gif"))),
                        new Image(String.valueOf(Lanceur.class.getResource("link4b.png")))
                }
        };

        listImageViewsCoeur = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            ImageView imageViewCoeur = new ImageView(imageCoeur);
            imageViewCoeur.setX(i * COEUR_SPACING);
            imageViewCoeur.setY(0);
            listImageViewsCoeur.add(imageViewCoeur);
        }

        ChangeListener<Number> pvListener = (obs, oldPv, newPv) -> {
            int newValue = newPv.intValue();
            for (int i = 0; i < listImageViewsCoeur.size(); i++) {
                listImageViewsCoeur.get(i).setVisible(i < newValue);
            }
        };

        link.pvProperty().addListener(pvListener);

        link.getBracelet().utiliseProperty().addListener((obs, old, utilise) -> {
            if (utilise) {
                cacheLink();
                PauseTransition pause = new PauseTransition(Duration.seconds(10));
                pause.setOnFinished(event -> Link.getInstance().resetBracelet());
                pause.play();
            }
        });
    }

    public void cacheLink() {
        ImageView linkImageView = getImageView();
        linkImageView.setOpacity(0.5);
    }

    private void setupCameraFollow() {
        AnimationTimer cameraFollowTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                updateCamera();
            }
        };
        cameraFollowTimer.start();
    }

    private void updateCamera() {
        double centerX = panneauJeu.getWidth() / 2 - link.getX();
        double centerY = panneauJeu.getHeight() / 2 - link.getY();
        panneauJeu.setTranslateX(centerX);
        panneauJeu.setTranslateY(centerY);

        double heartOffsetX = -centerX; // Adjust to follow the camera horizontally
        double heartOffsetY = -centerY; // Adjust to follow the camera vertically

        for (ImageView imageViewCoeur : listImageViewsCoeur) {
            imageViewCoeur.setTranslateX(heartOffsetX);
            imageViewCoeur.setTranslateY(heartOffsetY);
        }

        backgroundPaneConso.setTranslateX(heartOffsetX);
        backgroundPaneConso.setTranslateY(heartOffsetY);
        consommable.setTranslateX(heartOffsetX);
        consommable.setTranslateY(heartOffsetY);
        backgroundPane.setTranslateX(heartOffsetX);
        backgroundPane.setTranslateY(heartOffsetY);
        itemToolBar.setTranslateX(heartOffsetX);
        itemToolBar.setTranslateY(heartOffsetY);
    }

    public ImageView getImageView() {
        return super.getImageView();
    }

    public ArrayList<ImageView> getListImageViewsCoeur() {
        return listImageViewsCoeur;
    }
}
