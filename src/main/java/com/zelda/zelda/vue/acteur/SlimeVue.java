package com.zelda.zelda.vue.acteur;

import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.acteur.Personnage;
import javafx.scene.image.Image;

public class SlimeVue extends MonstreVue{
    public SlimeVue(Personnage personnage, String nomImage) {
        super(personnage, nomImage);

        listImageFab = new Image[][]{
                {
                        new Image(String.valueOf(Lanceur.class.getResource("slime1.gif")))
                },
                {
                        new Image(String.valueOf(Lanceur.class.getResource("slime2.gif")))
                },
                {
                        new Image(String.valueOf(Lanceur.class.getResource("slime3.gif")))
                } ,
                {
                        new Image(String.valueOf(Lanceur.class.getResource("slime4.gif")))
                }
        };
    }
}
