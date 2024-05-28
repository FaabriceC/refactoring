package com.zelda.zelda.vue;

import com.zelda.zelda.HelloApplication;
import com.zelda.zelda.modele.Decoration;
import com.zelda.zelda.modele.Personnage;
import com.zelda.zelda.modele.Terrain;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;

import java.util.ArrayList;

public class DecorationVue {

    private Decoration decoration;
    private Image image;
    private ImageView imageView;

    public DecorationVue(Decoration decoration, String nomImage) {
        this.decoration = decoration;
        this.image = new Image(String.valueOf(HelloApplication.class.getResource(nomImage)));
        if (this.image == null) {
            throw new IllegalArgumentException("Image not found: " + nomImage);
        }
        this.imageView = new ImageView(this.image);
        this.imageView.translateXProperty().bind(decoration.xProperty());
        this.imageView.translateYProperty().bind(decoration.yProperty());

    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public static void initDecorations(Pane pane, Terrain terrain) {
        Decoration arbre = new Decoration(256, 256, "ARBRE1", terrain);
        DecorationVue arbreVue = new DecorationVue(arbre, "arbre1.png");
        pane.getChildren().add(arbreVue.getImageView());


    }



}