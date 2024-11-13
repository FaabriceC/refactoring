package com.zelda.zelda.modele;

import com.zelda.zelda.modele.Consommable.Bracelet;
import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.Consommable.PotionForce;
import com.zelda.zelda.modele.Consommable.PotionSoin;
import com.zelda.zelda.modele.acteur.*;
import com.zelda.zelda.modele.armes.*;
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

    public ObservableList<Consommable> getConsommables() {
        return consommables;
    }

    public void ajouter(Personnage personnage) {
        this.personnages.add(personnage);
    }


    public ObservableList<Arme> getArmes() {
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
        Monstre monstre1 = new Slime(708, 472, Terrain.getInstance());
        Monstre monstre2 = new Slime(750, 472, Terrain.getInstance());
        Monstre monstre3 = new ArbreMonstre(760, 440);
        Monstre boss = new Boss(4844,868,Terrain.getInstance());

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
    }

    public void initConsommable(){
        Bracelet bracelet = new Bracelet();
        this.ajouterListeConsommable(bracelet);

        Consommable popoDeSoin = new PotionSoin();
        PotionForce popoDeForce = new PotionForce();

        this.ajouterListeConsommable(popoDeSoin);
        this.ajouterListeConsommable(popoDeForce);

    }
 /*   public void  attaqueMonstre() { //TODO A METTRE DANS LINK DE PREFERENCE + changer le nom en attaqueMonstre
        for (int i = 0; i < this.getPersonnageListe().size(); i++) {
            if (this.getPersonnageListe().get(i) instanceof Monstre) {
                Monstre m = (Monstre) this.getPersonnageListe().get(i);
                Link.getInstance().attaque(m);
                if (!m.vivant()) {
                    Environnement.getInstance().getPersonnageListe().remove(m);
                }
            }
        }
    }

  */

    public void actionMonstre() { // TODO Renommer en actionMonstre
        for (Monstre monstre : this.monstres) {
            monstre.seDeplace();
        }
    }

}