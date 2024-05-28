package com.zelda.zelda.vue;

import com.zelda.zelda.HelloApplication;
import com.zelda.zelda.modele.Link;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;

public class LinkVue extends PersonnageVue {


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
                        new Image(String.valueOf(HelloApplication.class.getResource("Link2.png"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("Link2A.gif"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("link2b.png")))
                },
                {
                        new Image(String.valueOf(HelloApplication.class.getResource("Link3.png"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("Link3A.gif"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("link3b.png")))
                },
                {
                        new Image(String.valueOf(HelloApplication.class.getResource("Link4.png"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("Link4A.gif"))),
                        new Image(String.valueOf(HelloApplication.class.getResource("link4b.png")))
                }
        };


    }







    public ImageView getImageView() {
        return super.getImageView();
    }

}






