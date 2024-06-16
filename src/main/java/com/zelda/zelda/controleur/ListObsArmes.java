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
            for (int i = 0; i < a.getAddedSize(); i++) {
                ArmeVue av = new ArmeVue(a.getAddedSubList().get(i),(a.getAddedSubList().get(i)).getNomPng());
                this.panneauJeu.getChildren().add(av.getImageView());



            }
            for (Arme arme : a.getRemoved()){
                ArmeVue av2 = new ArmeVue(arme, arme.getNomPng(),"inutile");
                panneauJeu.getChildren().remove(panneauJeu.lookup("#" + arme.getId()));
                this.toolBar.getItems().add(av2.getImageView());
            }

        }

    }



}