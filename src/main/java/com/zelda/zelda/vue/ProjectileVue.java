package com.zelda.zelda.vue;

import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.armes.Projectile;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProjectileVue {

    private Projectile projectile;
    private Image image;
    private ImageView imageView;


    public ProjectileVue(Projectile p) {
        this.projectile = p;
        this.image = new Image(String.valueOf(Lanceur.class.getResource(projectile.getNom())));
        this.imageView = new ImageView(this.image);
        this.imageView.setId(projectile.getId());
        this.imageView.translateXProperty().bind(projectile.xProjectileProperty());
        this.imageView.translateYProperty().bind(projectile.yProjectileProperty());
    }

    public ImageView getImageView() {
        return imageView;
    }
}
