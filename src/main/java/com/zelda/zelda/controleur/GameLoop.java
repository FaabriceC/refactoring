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

        link.seDeplace();
        link.equiperArme();
        for (int i =0;i<this.env.getPersonnageListe().size();i++){ // TODO dans le modèle
            if (this.env.getPersonnageListe().get(i) instanceof Monstre){
                Monstre m = (Monstre) this.env.getPersonnageListe().get(i);

                link.attaque(m);
                if (link.getArmeEquipe() instanceof Arc){
                    link.flecheSeDeplace(this.link.getFleche(),m);
                } else if(link.getArmeEquipe() instanceof Boomerang){
                    link.boomerangSeDeplace(this.link.getBoomerang(),m);
                }


            }

        }
        if(link.linkMeurt()){
            this.stop();
        }


        this.env.compteMonstre();
        if (this.env.getNbMonstre() == 0 ){
            link.getFleche().setxProjectileNull();
            link.getFleche().setyProjectileNull();
        }
        for (int i=0;i<this.env.getArmes().size();i++){
            if (this.env.getArmes().get(i) instanceof Epee){
                Epee epee = (Epee) this.env.getArmes().get(i);
                link.ramasserEpee(epee);
                if(link.isLinkARamasseEpee()){
                    this.env.getArmes().remove(epee);
                }
            }
        }
        for (int i=0;i<this.env.getArmes().size();i++){
            if (this.env.getArmes().get(i) instanceof Arc){
                Arc arc = (Arc) this.env.getArmes().get(i);
                link.ramasserArc(arc);
                if(link.isLinkARamasseArc()){
                    this.env.getArmes().remove(arc);
                }
            }
        }
        for (int i=0;i<this.env.getArmes().size();i++){
            if (this.env.getArmes().get(i) instanceof Boomerang){
                Boomerang boomerang = (Boomerang) this.env.getArmes().get(i);
                link.ramasserBoomerang(boomerang);
                if(link.isLinkARamasseBommerang()){
                    this.env.getArmes().remove(boomerang);
                }
            }
        }
        for (int i=0;i<this.env.getConsommables().size();i++){
            if (this.env.getConsommables().get(i) instanceof PotionSoin){
                PotionSoin potionSoin = (PotionSoin) this.env.getConsommables().get(i);
                link.ramasserPotionSoin(potionSoin);
                if(link.isLinkARamassePotionSoin()){
                    this.env.getConsommables().remove(potionSoin);
                }
            }
        }
        for (int i=0;i<this.env.getConsommables().size();i++){
            if (this.env.getConsommables().get(i) instanceof PotionForce){
                PotionForce potionForce = (PotionForce) this.env.getConsommables().get(i);
                link.ramasserPotionForce(potionForce);
                if(link.isLinkARamassePotionForce()){
                    this.env.getConsommables().remove(potionForce);
                }
            }
        }

        for (int i=0;i<this.env.getConsommables().size();i++){
            if (this.env.getConsommables().get(i) instanceof Bracelet){
                Bracelet bracelet = (Bracelet) this.env.getConsommables().get(i);
                link.ramasserBracelet(bracelet);
                if(link.isLinkARamasseBracelet()){
                    this.env.getConsommables().remove(bracelet);
                }
            }
        }
    }
    private  void rafraichirMonstre (){
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