package com.zelda.zelda.vue;

import com.zelda.zelda.HelloApplication;
import com.zelda.zelda.modele.Monstre;
import com.zelda.zelda.modele.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MonstreVue extends PersonnageVue {


    public MonstreVue(Monstre monstre, String nomImage) {
        super(monstre, nomImage);

        // Override the default images with specific images for the Monstre
        listImageFab = new Image[][]{
                {
                        new Image(String.valueOf(HelloApplication.class.getResource("slime1.png")))
                },
                {
                        new Image(String.valueOf(HelloApplication.class.getResource("slime2.png")))
                },
                {
                        new Image(String.valueOf(HelloApplication.class.getResource("slime3.png")))
                } ,
                {
                        new Image(String.valueOf(HelloApplication.class.getResource("slime4.png")))
                }
        };


    }




    @Override
    public ImageView getImageView() {
        return super.getImageView();
    }




}
