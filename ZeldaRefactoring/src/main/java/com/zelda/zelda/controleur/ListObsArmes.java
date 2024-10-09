package com.zelda.zelda.controleur;

import com.zelda.zelda.modele.acteur.Personnage;
import com.zelda.zelda.modele.armes.Arme;
import com.zelda.zelda.vue.ArmeVue;
import com.zelda.zelda.vue.acteur.MonstreVue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;

public class ListObsArmes implements ListChangeListener<Arme> {
    private Pane panneauJeu;
    private ToolBar toolBar;


    public ListObsArmes(Pane panneauJeu,ToolBar itemToolBar) {
        this.panneauJeu = panneauJeu;
        this.toolBar=itemToolBar;
    }

    public void onChanged(Change<? extends Arme> a) {
        while (a.next()) {
            for (Arme arme : a.getAddedSubList()) {
                ArmeVue av = new ArmeVue(arme,arme.getNomPng());
                this.panneauJeu.getChildren().add(av.getImageView());

            }
            for (Arme arme : a.getRemoved()){
                System.out.println(a.getRemoved());
                ArmeVue av2 = new ArmeVue(arme, arme.getNomPng(),"inutile");
                panneauJeu.getChildren().remove(panneauJeu.lookup("#" + arme.getId()));
                this.toolBar.getItems().add(av2.getImageView());
            }

        }

    }



}