package com.zelda.zelda.controleur;

import com.zelda.zelda.modele.armes.Arme;
import com.zelda.zelda.modele.armes.Projectile;
import com.zelda.zelda.vue.ArmeVue;
import com.zelda.zelda.vue.ProjectileVue;
import javafx.collections.ListChangeListener;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;

public class ListObsProjectile implements ListChangeListener<Projectile> {

    private Pane panneauJeu;


    public ListObsProjectile(Pane panneauJeu) {
        this.panneauJeu = panneauJeu;
    }

    public void onChanged(ListChangeListener.Change<? extends Projectile> a) {
        while (a.next()) {
            for (Projectile projectile : a.getAddedSubList()) {
                ProjectileVue prv = new ProjectileVue(projectile);
                this.panneauJeu.getChildren().add(prv.getImageView());

            }
            for (Projectile projectile : a.getRemoved()){
                System.out.println(a.getRemoved());
                ProjectileVue prv2 = new ProjectileVue(projectile);
                panneauJeu.getChildren().remove(panneauJeu.lookup("#" + projectile.getId()));
            }

        }

    }
}
