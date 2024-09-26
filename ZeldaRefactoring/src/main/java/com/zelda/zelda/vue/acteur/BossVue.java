package com.zelda.zelda.vue.acteur;

import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.acteur.Personnage;
import javafx.scene.image.Image;

public class BossVue extends MonstreVue{
    public BossVue(Personnage personnage, String nomImage) {
        super(personnage, nomImage);

        listImageFab = new Image[][]{
                {
                        new Image(String.valueOf(Lanceur.class.getResource("boss/StatiqueDroite/statique.png"))),
                        new Image(String.valueOf(Lanceur.class.getResource("boss/MarcheADroite/gif.gif")))
                },
                {
                        new Image(String.valueOf(Lanceur.class.getResource("boss/StatiqueDroite/statique.png"))),
                        new Image(String.valueOf(Lanceur.class.getResource("boss/MarcheADroite/gif.gif")))
                },
                {
                        new Image(String.valueOf(Lanceur.class.getResource("boss/StatiqueGauche/statique.png"))),
                        new Image(String.valueOf(Lanceur.class.getResource("boss/MarcheAGauche/gif.gif")))
                } ,
                {
                        new Image(String.valueOf(Lanceur.class.getResource("boss/StatiqueGauche/statique.png"))),
                        new Image(String.valueOf(Lanceur.class.getResource("boss/MarcheAGauche/gif.gif")))
                }
        };


    }
}

