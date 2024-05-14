package com.zelda.zelda.modele;


import com.zelda.zelda.HelloApplication;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Link extends Personnage {
    private String nomImageActuelle;

    public Link(String nom, int x, int y, Terrain t) {
        super(x, y, nom, t,"Link1.png");
        this.nomImageActuelle = "Link1.png";

    }

    public void setNomImageActuelle(String nomImageActuelle) {
        this.nomImageActuelle = nomImageActuelle;
    }

    public String getNomImageActuelle() {
        return this.nomImageActuelle;
    }

    @Override
    public String toString() {
        return "Link" + super.toString();
    }

    public ImageView getImageView() {
        return super.getImageView();
    }






}