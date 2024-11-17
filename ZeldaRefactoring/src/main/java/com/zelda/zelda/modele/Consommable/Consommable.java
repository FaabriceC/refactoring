package com.zelda.zelda.modele.Consommable;

import com.zelda.zelda.modele.Inventaire;
import com.zelda.zelda.modele.Item;
import javafx.beans.property.IntegerProperty;

public class Consommable extends Item {


    @Override
    public void ajouterInventaire(Inventaire inventaire) {
        inventaire.getInventaireConsommable().add(this);
    }


}
