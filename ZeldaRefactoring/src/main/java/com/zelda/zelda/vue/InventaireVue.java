package com.zelda.zelda.vue;

import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.Inventaire;
import com.zelda.zelda.modele.Consommable.PotionForce;
import com.zelda.zelda.modele.Consommable.PotionSoin;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.armes.Epee;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

public class InventaireVue {
    private Inventaire inventaire;
    private ToolBar itemToolBar;
    private ToolBar consommable;

    private Pane itemToolBarPane;
    private Link link;
    private boolean isVisible = false;

    public InventaireVue(Inventaire inventaire, ToolBar itemToolBar, ToolBar consommable, Pane itemToolBarPane, Link link) {
        this.inventaire = inventaire;
        this.itemToolBar = itemToolBar;
        this.consommable = consommable;
        this.itemToolBarPane= itemToolBarPane;
        this.link=link;
        initInventaireVue();
    }

    private void initInventaireVue() {
        for (int i = 0; i < inventaire.getInventaireArme().size(); i++) {
            if (inventaire.getInventaireArme().get(i) instanceof Epee) {
                Image i1 = new Image(String.valueOf(Lanceur.class.getResource("epee.png")));
                ImageView imageViewEpee = new ImageView(i1);
                itemToolBar.getItems().add(imageViewEpee);
            }

            if (inventaire.getInventaireArme().get(i) instanceof Epee) {
                Image i4 = new Image(String.valueOf(Lanceur.class.getResource("arc.png")));
                ImageView imageViewArc = new ImageView(i4);
                itemToolBar.getItems().add(imageViewArc);
            }
        }

        for (int i = 0; i < inventaire.getInventaireConsommable().size(); i++) {
            if (inventaire.getInventaireConsommable().get(i) instanceof PotionSoin) {
                Image i2 = new Image(String.valueOf(Lanceur.class.getResource("potionSoin.png")));
                ImageView imageViewPotionSoin = new ImageView(i2);
                imageViewPotionSoin.setOnMouseClicked(event -> {
                       link.linkUtilisePotionSoin();
                            System.out.println(link.getPv());
                        consommable.getItems().remove(imageViewPotionSoin);
                }

                );

                consommable.getItems().add(imageViewPotionSoin);
            } else if (inventaire.getInventaireConsommable().get(i) instanceof PotionForce) {
                Image i3 = new Image(String.valueOf(Lanceur.class.getResource("potionForce.png")));
                ImageView imageViewPotionForce = new ImageView(i3);
                imageViewPotionForce.setOnMouseClicked(event -> {
                            link.linkUtilisePotionForce();
                    System.out.println(link.getPtsAttaque());
                            consommable.getItems().remove(imageViewPotionForce);
                        });
                consommable.getItems().add(imageViewPotionForce);
            }
        }
    }


    public void setVisible(){
        consommable.setVisible(!isVisible);
        itemToolBarPane.setVisible(!isVisible);
        isVisible = !isVisible;
    }
}
