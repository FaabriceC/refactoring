package com.zelda.zelda.controleur;

import com.zelda.zelda.modele.acteur.ArbreMonstre;
import com.zelda.zelda.modele.acteur.Boss;
import com.zelda.zelda.modele.acteur.Personnage;
import com.zelda.zelda.modele.acteur.Slime; // Import the Slime class
import com.zelda.zelda.vue.acteur.ArbreMonstreVue;
import com.zelda.zelda.vue.acteur.BossVue;
import com.zelda.zelda.vue.acteur.MonstreVue;
import com.zelda.zelda.vue.acteur.SlimeVue;
import javafx.collections.ListChangeListener;
import javafx.scene.layout.Pane;

public class ListObs implements ListChangeListener<Personnage> {
    private Pane panneauJeu;

    public ListObs(Pane panneauJeu) {
        this.panneauJeu = panneauJeu;
    }

    @Override
    public void onChanged(Change<? extends Personnage> c) {
        while (c.next()) {
            for (int i = 0; i < c.getAddedSize(); i++) {
                Personnage personnage = c.getAddedSubList().get(i);
                MonstreVue mv;
                if (personnage instanceof Slime) {
                    mv = new SlimeVue(personnage, personnage.getNom());
                }
                else if(personnage instanceof Boss){
                    mv = new BossVue( personnage,personnage.getNom());
                }
                else if(personnage instanceof ArbreMonstre){
                    mv = new ArbreMonstreVue( personnage,personnage.getNom());
                }

                else {
                    mv = new MonstreVue(personnage, personnage.getNom());
                }
                this.panneauJeu.getChildren().add(mv.getImageView());
            }

            for (Personnage m : c.getRemoved()) {
                System.out.println(c.getRemoved().size());
                panneauJeu.getChildren().remove(panneauJeu.lookup("#" + m.getId()));
            }
        }
    }
}
