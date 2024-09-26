package com.zelda.zelda.vue.dynamique;



import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.dynamique.BlockDynamique;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;


public class BlockDynamiqueVue {

    private BlockDynamique blockDynamique;
    private Image image;
    protected ImageView imageView;

    public BlockDynamiqueVue(BlockDynamique blockDynamique, String nomImage) {
        this.blockDynamique = blockDynamique;
        this.image = new Image(String.valueOf(Lanceur.class.getResource(nomImage)));
        if (this.image == null) {
            throw new IllegalArgumentException("Image not found: " + nomImage);
        }
        this.imageView = new ImageView(this.image);

        this.imageView.translateXProperty().bind(blockDynamique.xProperty());
        this.imageView.translateYProperty().bind(blockDynamique.yProperty());
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void initImage (TilePane tilePane) {

    }
}