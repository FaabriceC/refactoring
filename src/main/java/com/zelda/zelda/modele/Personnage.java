package com.zelda.zelda.modele;



import com.zelda.zelda.HelloApplication;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Personnage {
    private Image image;
    private IntegerProperty x = new SimpleIntegerProperty();
    private IntegerProperty y = new SimpleIntegerProperty();
    protected Terrain terrain;
    private String nom;
    private ImageView imageView;

    public Personnage(int x, int y, String nom, Terrain terrain,String nomImage) {
        this.x.set(x);
        this.y.set(y);
        this.nom = nom;
        this.terrain = terrain;
        this.image = new Image(String.valueOf(HelloApplication.class.getResource(nomImage)));
        this.imageView=new ImageView(this.image);
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void setImageView (ImageView imageView) {
        this.imageView = imageView;
    }



    public Image setImage(Image image) {
        return image;
    }

    public Image getImage() {
        return image;
    }

    public int getX() {
        return x.getValue();
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public int getY() {
        return y.getValue();
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public String getNom() {
        return nom;
    }








}



