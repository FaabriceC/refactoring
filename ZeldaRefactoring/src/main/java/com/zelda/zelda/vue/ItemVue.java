package com.zelda.zelda.vue;

import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.Item;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ItemVue {
    private Item item;
    private Image image;
    private ImageView imageView;

    public ItemVue(Item item, String nomItem) {
        this.item = item;
        this.image = new Image(String.valueOf(Lanceur.class.getResource(nomItem)));
        this.imageView = new ImageView(this.image);
        this.imageView.setId(item.getId());
        this.imageView.translateXProperty().bind(item.xProperty());
        this.imageView.translateYProperty().bind(item.yProperty());
    }

    public ItemVue(Item item, String nomItem, String inutile) {
        this.item = item;
        this.image = new Image(String.valueOf(Lanceur.class.getResource(nomItem)));
        this.imageView = new ImageView(this.image);
        this.imageView.setId(item.getId());
    }

    public ImageView getImageView() {
        return this.imageView;
    }
}