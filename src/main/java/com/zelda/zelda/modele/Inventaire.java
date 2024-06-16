package com.zelda.zelda.modele;

import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.armes.Arme;

import java.util.ArrayList;

public class Inventaire {

    private ArrayList<Arme> inventaireArme;
    private ArrayList<Consommable> inventaireConsommable;

    public Inventaire() {
        this.inventaireArme = new ArrayList<>();
        this.inventaireConsommable = new ArrayList<>();
    }

    public void ajouterArme(Arme arme) {

        this.inventaireArme.add(arme);

    }

    public void ajouterConsommable(Consommable consommable) {

            this.inventaireConsommable.add(consommable);

    }



    public ArrayList<Arme> getInventaireArme() {
        return inventaireArme;
    }




    public ArrayList<Consommable> getInventaireConsommable() {
        return inventaireConsommable;
    }

    public int getTotalItems() {
        return inventaireArme.size() + inventaireConsommable.size();
    }
}