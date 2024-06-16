package com.zelda.zelda.modele;

import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.acteur.Monstre;
import com.zelda.zelda.modele.acteur.Personnage;
import com.zelda.zelda.modele.armes.Arme;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Environnement {

    private ObservableList<Personnage> personnages;
    private IntegerProperty nbTours;

    private ObservableList<Arme> armes;

    private ObservableList<Consommable> consommables;

    private int nbMonstre;

    public Environnement(){
        this.nbTours = new SimpleIntegerProperty(0);
        this.personnages= FXCollections.observableArrayList();
        this.armes = FXCollections.observableArrayList();
        this.consommables = FXCollections.observableArrayList();
        this.compteMonstre();

    }
    public final int getNbTours(){
        return this.nbTours.getValue();
    }

    public final void setNbTours(int n){
        this.nbTours.setValue(n);
    }

    public final IntegerProperty nbTours(){
        return nbTours;
    }
    public ObservableList<Personnage> getPersonnageListe() {
        return personnages;
    }

    public ObservableList<Consommable> getConsommables(){ return  consommables;}



    public void ajouter(Personnage personnage){
        this.personnages.add(personnage);
    }

    public Personnage getPersonnage(String id) {
        for(Personnage p:this.personnages){
            if(p.getId().equals(id)){
                return p;
            }
        }
        return null;
    }

    public ObservableList<Arme> getArmes() {
        return armes;
    }

    public void ajouterListeArme(Arme arme){
        this.armes.add(arme);
    }

    public void ajouterListeConsommable (Consommable consommable){ this.consommables.add(consommable);
    }

    public void compteMonstre(){
        this.nbMonstre = 0;
        for (int i =0;i<this.getPersonnageListe().size();i++){
            if (this.getPersonnageListe().get(i) instanceof Monstre){
                this.nbMonstre = nbMonstre+1;
            }
        }
    }

    public int getNbMonstre() {
        return nbMonstre;
    }
}