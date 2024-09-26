package com.zelda.zelda.controleur;

import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.Consommable.PotionForce;
import com.zelda.zelda.modele.Consommable.PotionSoin;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.vue.ConsommableVue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;

public class ListObsConsommables implements ListChangeListener<Consommable> {
    private Pane panneauJeu;
    private ToolBar consommablesToolBar;

    private Link link;

    public ListObsConsommables(Pane panneauJeu,ToolBar consommablesTool,Link link){
        this.panneauJeu = panneauJeu;
        this.consommablesToolBar= consommablesTool;
        this.link =link;
    }


    public void onChanged(Change<? extends Consommable> a) {

        while (a.next()) {
            for (int i = 0; i < a.getAddedSize(); i++) {
                ConsommableVue cV = new ConsommableVue(a.getAddedSubList().get(i), (a.getAddedSubList().get(i)).getNom());
                this.panneauJeu.getChildren().add(cV.getImageView());


            }
            for (Consommable consommable : a.getRemoved()) {
                ConsommableVue cV2 = new ConsommableVue(consommable, consommable.getNom(), " ");
                panneauJeu.getChildren().remove(panneauJeu.lookup("#" + consommable.getId()));
                if (consommable instanceof PotionForce) {
                    cV2.getImageView().setOnMouseClicked(event -> {
                        link.linkUtilisePotionForce();
                        consommablesToolBar.getItems().remove(cV2.getImageView());

                    });
                } else if (consommable instanceof PotionSoin) {
                    cV2.getImageView().setOnMouseClicked(event -> {
                        link.linkUtilisePotionSoin();
                        consommablesToolBar.getItems().remove(cV2.getImageView());

                    });
                } else {
                    cV2.getImageView().setOnMouseClicked(event -> {
                        link.linkUtiliseBracelet();
                        if(Math.random() < 0.10){
                            consommablesToolBar.getItems().remove(cV2.getImageView());
                        }


                    });
                }
                this.consommablesToolBar.getItems().add(cV2.getImageView());

            }
        }
    }








}
