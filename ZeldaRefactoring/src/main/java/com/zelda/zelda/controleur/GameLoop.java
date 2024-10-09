package com.zelda.zelda.controleur;


import com.zelda.zelda.modele.Consommable.Bracelet;
import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.Consommable.PotionForce;
import com.zelda.zelda.modele.Consommable.PotionSoin;
import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.acteur.Boss;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;
import com.zelda.zelda.modele.armes.*;
import javafx.animation.AnimationTimer;


public class GameLoop extends AnimationTimer {
    // essayer de eviter ce systeme de delai et le faire via duration second

    private Link link;

    private long dernierMouvement = 0;
    private long dernierMouvementSlime = 0;
    private long dernierMouvementBoss = 0;
    private final long DELAI_MOUVEMENT = 5_000_000;
    private final long DELAI_SLIME = 20_000_000;
    private final long DELAI_BOSS = 10_000_000;
    private Environnement env;

    public GameLoop(Link link, Environnement environnement) {
        this.link = link;
        this.env = environnement;
    }

    @Override
    public void handle(long now) {
        if (now - this.dernierMouvement >= this.DELAI_MOUVEMENT) {
            rafraichirLink();
            this.dernierMouvement = now;
        }

        if (now - this.dernierMouvementSlime >= this.DELAI_SLIME) {
            rafraichirMonstre();
            this.dernierMouvementSlime = now;
        }


        if (now - this.dernierMouvementBoss >= this.DELAI_BOSS) {
            rafraichirBoss();
            this.dernierMouvementBoss = now;
        }


    }

    // Méthode pour rafraîchir la position du personnage
    private void rafraichirLink() {

        link.agit();

        for (int i = 0; i < this.env.getPersonnageListe().size(); i++) { // TODO dans le modèle
            if (this.env.getPersonnageListe().get(i) instanceof Monstre) {
                Monstre m = (Monstre) this.env.getPersonnageListe().get(i);
                link.attaque(m);
//                if (link.getArmeEquipe() instanceof Arc){
//                    link.flecheSeDeplace(this.link.getFleche(),m);
//                } else if(link.getArmeEquipe() instanceof Boomerang){
//                    link.boomerangSeDeplace(this.link.getBoomerang(),m);
//                }
//
//
            }

            if (this.env.getNbMonstre() == 0) {
                link.getFleche().setxProjectileNull();
                link.getFleche().setyProjectileNull();
            }

            env.ramasserArmes();
            env.ramasserConsommable();

        }
    }


    private void rafraichirMonstre(){
        for (int i =0;i<this.env.getPersonnageListe().size();i++){
            if (this.env.getPersonnageListe().get(i) instanceof Monstre){
                Monstre m = (Monstre) this.env.getPersonnageListe().get(i);
                m.seDeplace(link);
                m.attaque(link);
                if(!m.vivant()){
                    this.env.getPersonnageListe().remove(m);
                }
            }
        }

    }

    private  void rafraichirBoss (){
        for (int i =0;i<this.env.getPersonnageListe().size();i++){
            if (this.env.getPersonnageListe().get(i) instanceof Boss){
                Boss m = (Boss) this.env.getPersonnageListe().get(i);
                m.seDeplace(link);
                m.attaque(link);
                if(!m.vivant()){
                    this.env.getPersonnageListe().remove(m);
                }
            }
        }

    }

}