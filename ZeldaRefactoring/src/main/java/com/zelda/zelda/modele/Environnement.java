package com.zelda.zelda.modele;

import com.zelda.zelda.modele.Consommable.Bracelet;
import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.Consommable.PotionForce;
import com.zelda.zelda.modele.Consommable.PotionSoin;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;
import com.zelda.zelda.modele.acteur.Personnage;
import com.zelda.zelda.modele.armes.Arc;
import com.zelda.zelda.modele.armes.Arme;
import com.zelda.zelda.modele.armes.Boomerang;
import com.zelda.zelda.modele.armes.Epee;
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

    private Link link;

    public Environnement(Link link){
        this.link = link;
        this.nbTours = new SimpleIntegerProperty(0);
        this.personnages= FXCollections.observableArrayList();
        this.armes = FXCollections.observableArrayList();
        this.consommables = FXCollections.observableArrayList();
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

    public int getNbMonstre() {
        return nbMonstre;
    }

    public void ramasserEpee() {
        for (int i=0;i < this.getArmes().size();i++){
                Arme arme = this.getArmes().get(i);
                link.ramasserArme(arme);
                if(link.isLinkARamasseArme()){
                    this.getArmes().remove(arme);
                }

        }
    }

    public void ramasserArc(){
        for (int i=0;i<this.getArmes().size();i++){
            if (this.getArmes().get(i) instanceof Arc){
                Arc arc = (Arc) this.getArmes().get(i);
                link.ramasserArc(arc);
                if(link.isLinkARamasseArc()){
                    this.getArmes().remove(arc);
                }
            }
        }
    }


    public void ramasserArme() {
        for (int i=0;i<this.getArmes().size();i++){
                Arme arme = this.getArmes().get(i);
                this.getArmes().remove(arme);
            }
        }




    public void ramasserBoomerang() {
        for (int i=0;i<this.getArmes().size();i++){
            if (this.getArmes().get(i) instanceof Boomerang){
                Boomerang boomerang = (Boomerang) this.getArmes().get(i);
                link.ramasserBoomerang(boomerang);
                if(link.isLinkARamasseBommerang()){
                    this.getArmes().remove(boomerang);
                }
            }
        }
    }
    public void ramasserPotionSoin(){
        for (int i=0;i<this.getConsommables().size();i++){
            if (this.getConsommables().get(i) instanceof PotionSoin){
                PotionSoin potionSoin = (PotionSoin) this.getConsommables().get(i);
                link.ramasserPotionSoin(potionSoin);
                if(link.isLinkARamassePotionSoin()){
                    this.getConsommables().remove(potionSoin);
                }
            }
        }
    }
    public void ramasserPotionForce(){
        for (int i=0;i<this.getConsommables().size();i++){
            if (this.getConsommables().get(i) instanceof PotionForce){
                PotionForce potionForce = (PotionForce) this.getConsommables().get(i);
                link.ramasserPotionForce(potionForce);
                if(link.isLinkARamassePotionForce()){
                    this.getConsommables().remove(potionForce);
                }
            }
        }
    }

    public void ramasserBracelet(){
        for (int i=0;i<this.getConsommables().size();i++){
            if (this.getConsommables().get(i) instanceof Bracelet){
                Bracelet bracelet = (Bracelet) this.getConsommables().get(i);
                link.ramasserBracelet(bracelet);
                if(link.isLinkARamasseBracelet()){
                    this.getConsommables().remove(bracelet);
                }
            }
        }
    }
}