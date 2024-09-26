package com.zelda.zelda.vue.acteur;

import com.zelda.zelda.Lanceur;
import com.zelda.zelda.modele.acteur.Personnage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PersonnageVue {
    private Personnage personnage;
    private Image image;

    protected Image[][] listImageFab ;

    private int  res ;


    private ImageView imageView;

    public PersonnageVue(Personnage personnage, String nomImage) {
        this.personnage = personnage;
        this.image = new Image(String.valueOf(Lanceur.class.getResource(nomImage)));
        this.imageView = new ImageView(this.image);
        this.imageView.translateXProperty().bind(personnage.xProperty());
        this.imageView.translateYProperty().bind(personnage.yProperty());
        this.imageView.setId(personnage.getId());



        // TODO créer un listener de IntegerProperty direction dans Perso




        // Ajouter un écouteur sur la direction du personnage
        personnage.directionProperty().addListener((observableDir, oldValueDir, newValueDir) -> {
            updateImageBasedOnDirection(newValueDir.intValue(), res);
        });

            // Ajouter un écouteur sur l'indice de pas du personnage
        personnage.indicePasProperty().addListener((observablePas, oldValuePas, newValuePas) -> {
                res = observablePas.getValue().intValue();

                System.out.println(res);
                // Mettre à jour l'image en fonction de la direction et du nouvel indice de pas
                updateImageBasedOnDirection(personnage.directionProperty().getValue(), res);
        });



    }

    private void updateImageBasedOnDirection(int direction, int indicePas) {
        try {
        // Mettre à jour l'image en fonction de la direction et de l'indice de pas
        switch (direction) {
            case 1:
                imageView.setImage(listImageFab[0][indicePas]);
                break;
            case 2:
                imageView.setImage(listImageFab[1][indicePas]);
                break;
            case 3:
                imageView.setImage(listImageFab[2][indicePas]);
                break;
            case 4:
                imageView.setImage(listImageFab[3][indicePas]);
                break;
            default:
                // Gérer le cas où la direction n'est pas valide
                break;
        }
        } catch (Exception e) {
            System.err.println("Error updating image based on direction and step index: " + e.getMessage());
            e.printStackTrace();
        }
    }






    public ImageView getImageView() {
        return this.imageView;
    }

    public int getHauteurLength(){
        return listImageFab[0].length;
    }
}
