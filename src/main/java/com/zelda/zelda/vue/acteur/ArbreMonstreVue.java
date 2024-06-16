package com.zelda.zelda.vue.acteur;

import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.acteur.Personnage;
import javafx.scene.image.Image;

public class ArbreMonstreVue extends MonstreVue {

        public ArbreMonstreVue(Personnage personnage, String nomImage) {
            super(personnage, nomImage);

            listImageFab = new Image[][]{
                    {
                            new Image(String.valueOf(Lanceur.class.getResource("arbreMonstre.png")))
                    },
                    {
                            new Image(String.valueOf(Lanceur.class.getResource("arbreMonstre.png")))
                    },
                    {
                            new Image(String.valueOf(Lanceur.class.getResource("arbreMonstre.png")))
                    } ,
                    {
                            new Image(String.valueOf(Lanceur.class.getResource("arbreMonstre.png")))
                    }
            };
        }
    }



