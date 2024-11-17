package com.zelda.zelda.controleur;

import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.Consommable.PotionForce;
import com.zelda.zelda.modele.Consommable.PotionSoin;
import com.zelda.zelda.modele.Item;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.vue.ConsommableVue;
import com.zelda.zelda.vue.ItemVue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;

public class ListObsItem implements ListChangeListener<Item> {
    private Pane panneauJeu;
    private ToolBar toolBar;
    private ToolBar consommableToolBar;

    public ListObsItem(Pane panneauJeu, ToolBar itemToolBar, ToolBar consommableToolBar) {
        this.panneauJeu = panneauJeu;
        this.toolBar = itemToolBar;
        this.consommableToolBar = consommableToolBar;
    }

    @Override
    public void onChanged(Change<? extends Item> change) {
        while (change.next()) {
            for (Item item : change.getAddedSubList()) {
                if (item instanceof Consommable) {
                    Consommable consommable = (Consommable) item;
                    ConsommableVue cV = new ConsommableVue(consommable, consommable.getNom());
                    this.panneauJeu.getChildren().add(cV.getImageView());
                } else {
                    ItemVue itemVue = new ItemVue(item, item.getNom());
                    this.panneauJeu.getChildren().add(itemVue.getImageView());
                }
            }

            for (Item item : change.getRemoved()) {
                panneauJeu.getChildren().remove(panneauJeu.lookup("#" + item.getId()));

                if (item instanceof Consommable) {
                    Consommable consommable = (Consommable) item;
                    ConsommableVue cV2 = new ConsommableVue(consommable, consommable.getNom(), " ");
                    cV2.getImageView().setOnMouseClicked(event -> handleConsommableClick(consommable, cV2));
                    this.consommableToolBar.getItems().add(cV2.getImageView());
                } else {
                    ItemVue itemVue = new ItemVue(item, item.getNom(), "inutile");
                    this.toolBar.getItems().add(itemVue.getImageView());
                }
            }
        }
    }

    private void handleConsommableClick(Consommable consommable, ConsommableVue consommableVue) {
        if (consommable instanceof PotionForce) {
            ((PotionForce) consommable).utilise();
            consommableToolBar.getItems().remove(consommableVue.getImageView());
        } else if (consommable instanceof PotionSoin) {
            ((PotionSoin) consommable).utilise();
            consommableToolBar.getItems().remove(consommableVue.getImageView());
        } else {
            Link.getInstance().utiliseBracelet();
            if (Math.random() < 0.10) {
                consommableToolBar.getItems().remove(consommableVue.getImageView());
            }
        }
    }
}