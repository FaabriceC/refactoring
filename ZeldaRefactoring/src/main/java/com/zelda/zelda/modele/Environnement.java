package com.zelda.zelda.modele;

import com.zelda.zelda.modele.Consommable.Bracelet;
import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.Consommable.PotionForce;
import com.zelda.zelda.modele.Consommable.PotionSoin;
import com.zelda.zelda.modele.acteur.*;
import com.zelda.zelda.modele.armes.Arc;
import com.zelda.zelda.modele.armes.Arme;
import com.zelda.zelda.modele.armes.Boomerang;
import com.zelda.zelda.modele.armes.Epee;
import com.zelda.zelda.modele.dynamique.BlockDynamique;
import com.zelda.zelda.vue.TerrainVue;
import com.zelda.zelda.vue.dynamique.BlockDynamiqueVue;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.ArrayList;

public class Environnement {
    private static Environnement uniqueInstance=null;

    private ObservableList<Personnage> personnages;

    private ObservableList<Arme> armes;

    private ObservableList<Consommable> consommables;
    private ArrayList<Monstre> monstres;

    private ObservableList<Projectile> projectiles;

    private Link link;

    private Environnement() {
        this.personnages = FXCollections.observableArrayList();
        this.armes = FXCollections.observableArrayList();
        this.consommables = FXCollections.observableArrayList();
        this.items = FXCollections.observableArrayList();
        this.monstres = new ArrayList<>();

        this.projectiles = FXCollections.observableArrayList();
    }

    public static Environnement getInstance() {
        if(uniqueInstance==null) {
            uniqueInstance= new Environnement();
        }
        return uniqueInstance;
    }


    public ObservableList<Personnage> getPersonnageListe() {
        return personnages;
    }

    public ObservableList<Item> getConsommables() {
        return consommables;
    }

    public void ajouter(Personnage personnage) {
        this.personnages.add(personnage);
    }


    public ObservableList<Item> getArmes() {
        return armes;
    }

    public void ajouterListeArme(Arme arme) {
        this.armes.add(arme);
    }

    public void ajouterListeConsommable(Consommable consommable) {
        this.consommables.add(consommable);
    }

    public ObservableList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void initMonstre() {
        Monstre monstre1 = new Slime(708, 472);
        Monstre monstre2 = new Slime(750, 472);
        Monstre monstre3 = new ArbreMonstre(760, 440);
        Monstre boss = new Boss(4844,868);

        this.ajouter(monstre1);
        this.ajouter(monstre2);
        this.ajouter(monstre3);
        this.ajouter(boss);

        this.monstres.add(monstre1);
        this.monstres.add(monstre2);
        this.monstres.add(monstre3);
        this.monstres.add(boss);
    }

    public void initArmes(){

        Epee epee = new Epee();
        this.ajouterListeArme(epee);
        Boomerang boomerang = new Boomerang();
        this.ajouterListeArme(boomerang);

        Arc arc = new Arc();
        this.ajouterListeArme(arc);

        this.items.add(epee);
        this.items.add(boomerang);
        this.items.add(arc);
    }


    public void initConsommable(){
        Bracelet bracelet = new Bracelet();
        this.ajouterListeConsommable(bracelet);

        Consommable popoDeSoin = new PotionSoin();
        PotionForce popoDeForce = new PotionForce();

        this.ajouterListeConsommable(popoDeSoin);
        this.ajouterListeConsommable(popoDeForce);

        this.items.add(bracelet);
        this.items.add(popoDeForce);
        this.items.add(popoDeSoin);

    }

    public void actionMonstre() {
        for (Monstre monstre : this.monstres) {
            monstre.seDeplaceEtAttaque();
        }
    }

}