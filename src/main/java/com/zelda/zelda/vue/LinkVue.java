package com.zelda.zelda.vue;

import com.zelda.zelda.HelloApplication;
import com.zelda.zelda.modele.Link;
import javafx.beans.value.ChangeListener;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class LinkVue extends PersonnageVue {
    private Image imageCoeur = new Image(String.valueOf(HelloApplication.class.getResource("coeur.png")));

    private ImageView imageViewCoeur1;
    private ImageView imageViewCoeur2;
    private ImageView imageViewCoeur3;
    private ImageView imageViewCoeur4;
    private ImageView imageViewCoeur5;


    public LinkVue(Link link, String nomImage) {
        super(link, nomImage);

        // TODO 4 new Image qu'on stocke en attribut
        listImageFab = new Image[][] {

                {
                        new Image(String.valueOf(HelloApplication.class.getResource("Link1.png"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("Link1A.gif"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("link1b.png")))
                },
                {
                        new Image(String.valueOf(HelloApplication.class.getResource("Link2.gif"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("Link2A.gif"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("link2b.png")))
                },
                {
                        new Image(String.valueOf(HelloApplication.class.getResource("Link3.gif"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("Link3A.gif"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("link3b.png")))
                },
                {
                        new Image(String.valueOf(HelloApplication.class.getResource("Link4.gif"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("Link4A.gif"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("link4b.png")))
                }
        };


        imageViewCoeur1 = new ImageView(imageCoeur);
        imageViewCoeur2 = new ImageView(imageCoeur);
        imageViewCoeur3 = new ImageView(imageCoeur);
        imageViewCoeur4 = new ImageView(imageCoeur);
        imageViewCoeur5 = new ImageView(imageCoeur);

        imageViewCoeur1.setX(0);
        imageViewCoeur1.setY(0);

        imageViewCoeur2.setX(25);
        imageViewCoeur2.setY(0);

        imageViewCoeur3.setX(50);
        imageViewCoeur3.setY(0);

        imageViewCoeur4.setX(75);
        imageViewCoeur4.setY(0);

        imageViewCoeur5.setX(100);
        imageViewCoeur5.setY(0);

        ChangeListener<Number> pvListener = (obs, oldPv, newPv) -> {
            int newValue = newPv.intValue();
            if (newValue < 5) {
                imageViewCoeur5.setVisible(false);
            }
            if (newValue < 4) {
                imageViewCoeur4.setVisible(false);
            }
            if (newValue < 3) {
                imageViewCoeur3.setVisible(false);

            }
            if (newValue <2){
                imageViewCoeur2.setVisible(false);
            }
            if (newValue <1){
                imageViewCoeur1.setVisible(false);
                //link.meurt
            }


        };

        link.pvProperty().addListener(pvListener);


    }







    public ImageView getImageView() {
        return super.getImageView();
    }

    public ImageView getImageViewCoeur1() {
        return imageViewCoeur1;
    }

    public ImageView getImageViewCoeur2() {
        return imageViewCoeur2;
    }

    public ImageView getImageViewCoeur3() {
        return imageViewCoeur3;
    }

    public ImageView getImageViewCoeur4() {
        return imageViewCoeur4;
    }

    public ImageView getImageViewCoeur5() {
        return imageViewCoeur5;
    }

}






