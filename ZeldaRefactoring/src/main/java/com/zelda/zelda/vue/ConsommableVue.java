package com.zelda.zelda.vue;

import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.Consommable.Consommable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;



public class ConsommableVue {

    private Consommable consommable;
    private Image image;
    private ImageView imageView;

    public ConsommableVue(Consommable consommable, String nom) {
        this.consommable = consommable;
        this.image = new Image(String.valueOf(Lanceur.class.getResource(nom)));
        this.imageView = new ImageView(this.image);



        this.imageView.setId(consommable.getId());
        this.imageView.translateXProperty().bind(consommable.xProperty());
        this.imageView.translateYProperty().bind(consommable.yProperty());
    }


    public ConsommableVue(Consommable consommable,String nom, String inutile ){
        this.consommable = consommable;
        this.image = new Image(String.valueOf(Lanceur.class.getResource(nom)));
        this.imageView = new ImageView(this.image);
        this.imageView.setId(consommable.getId());

    }


    public ImageView getImageView() {
        return this.imageView;
    }
}


