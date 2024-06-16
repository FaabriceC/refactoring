package com.zelda.zelda.vue;

import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.armes.Arme;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ArmeVue {
    private Arme arme;
    private Image image;
    private ImageView imageView;

    public ArmeVue(Arme arme, String nomArme){
        this.arme = arme;
        this.image = new Image(String.valueOf(Lanceur.class.getResource(nomArme)));
        this.imageView = new ImageView(this.image);
        this.imageView.setId(arme.getId());
        this.imageView.translateXProperty().bind(arme.xProperty());
        this.imageView.translateYProperty().bind(arme.yProperty());

    }



    public ArmeVue(Arme arme, String nomArme,String inutile){
        this.arme = arme;
        this.image = new Image(String.valueOf(Lanceur.class.getResource(nomArme)));
        this.imageView = new ImageView(this.image);
        this.imageView.setId(arme.getId());

    }
    public ImageView getImageView() {
        return this.imageView;
    }

}