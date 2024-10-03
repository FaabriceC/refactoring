package com.zelda.zelda.modele.acteur;


import com.zelda.zelda.modele.Consommable.Bracelet;
import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.Consommable.PotionForce;
import com.zelda.zelda.modele.Consommable.PotionSoin;
import com.zelda.zelda.modele.Inventaire;
import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.armes.*;

import com.zelda.zelda.modele.dynamique.BlockDynamique;
import com.zelda.zelda.vue.ProjectileVue;
import javafx.animation.PauseTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class Link extends Personnage {

    private IntegerProperty pv;
    long actionTime = 0L;
    private boolean linkAttaque;
    private int derniereDirection;
    private Inventaire inventaire;

    private boolean linkRamasse;

    private BooleanProperty braceletUtilise = new SimpleBooleanProperty(false);
    private boolean linkRamassePotionSoin;

    private boolean linkRamassePotionForce;
    private boolean linkRamasseBracelet;

    private boolean linkARamasseEpee;
    private boolean linkARamasseArc;
    private boolean linkARamasseBommerang;
    private boolean linkARamasseBouclier;
    private boolean linkARamasseArme;

    private boolean linkARamassePotionSoin;
    private boolean linkARamassePotionForce;

    private boolean linkARamasseBracelet;
    private Arme armeEquipe;

    private String armeChoisi;

    private int pointAttaque;

    private Projectile fleche;
    private int tempAvantDisparitionDeLaFleche;

    private Projectile boomerang;

    private int tempAvantRetourBoomerang;

    private boolean invisible;

    private boolean tp;

    public Link(String nom, int x, int y, Terrain t) {
        super(x, y, nom, t);
        this.pv= new SimpleIntegerProperty(5) {
        };
        this.pv.setValue(5);
        this.linkAttaque = false;
        this.inventaire= new Inventaire();
        this.linkRamasse = false;


        this.linkARamasseEpee = false;
        this.linkARamasseArc = false;
        this.linkARamasseBommerang = false;
        this.linkARamasseBouclier=false;

        this.linkARamassePotionSoin = false;
        this.linkARamassePotionForce = false;

        this.armeEquipe = null;

        this.armeChoisi = null;

        this.pointAttaque = 1;

        this.fleche = new Projectile("arrows.png");

        this.tempAvantDisparitionDeLaFleche = 0;

        this.boomerang = new Projectile("boomerang.png");

        this.tempAvantRetourBoomerang = 0;


    }


    public boolean peutSeDeplacer  (int tuileX, int tuileY){
        int margeX = margeErreur(0,0)[0];
        int margeY = margeErreur(margeX,0)[1];


        return !terrain.collision(tuileX+margeX,tuileY+margeY);


    }





    public void  seDeplace() {
        tp =false;
        int deplacementX = 0;
        int deplacementY = 0;


        int vitesse =2;

        if (pousseLeBloc())
            vitesse = 1;


        if (this.direction.getValue() == 2) {
            deplacementX += vitesse;

        }  if (this.direction.getValue() == 4) {
            deplacementX -= vitesse;

        } if (this.direction.getValue() == 3) {
            deplacementY += vitesse;

        } if (this.direction.getValue() == 1) {
            deplacementY -= vitesse;

        }

        int newX = this.getX() + deplacementX;
        int newY = this.getY() + deplacementY;

        if (getTp(newX,newY)==102) {
            this.setX(4158);
            this.setY(1556);
            tp=true;
        }

        if (getTp(newX,newY)==30) {
            this.setX(3136);
            this.setY(1816);
            tp=true;
        }

        if ( peutSeDeplacer(newX, newY)&& terrain.dansTerrain(newX,newY)&&!tp) {


            this.setX(newX);
            this.setY(newY);
        }


    }
   /* public void consommerPotion(){
        for(int i=0; i<inventaire.getInventaireConsommable().size();i++){
            if (inventaire.getInventaireConsommable().get(i) instanceof PotionSoin && this.equipe)

        }

    }

    */



    @Override
    public int[] margeErreur(int margeX, int margeY) {
        int[]marge=new int[2];
        switch (direction.getValue()) {
            case 4:
                margeX = 10;
                margeY= 26;
                break;
            case 2:
                margeX = 26;
                margeY=26;
                break;
            case 1:
                margeX = 16;
                margeY = 26;
                break;
            case 3:
                margeX = 16;
                margeY = 26;
                break;
        }
        marge[0]=margeX;
        marge[1]=margeY;
        return marge;
    }







    public void attaque(Monstre monstre){
        if(this.armeEquipe != null) {
            if (this.armeEquipe.getNomPng().equals("epee.png")){
                this.attaqueAvecEpee(monstre);
            } else if (this.armeEquipe.getNomPng().equals("arc.png")) {
                this.attaqueAvecArc();
            } else if (this.armeEquipe.getNomPng().equals("boomerang.png")){
                this.attaqueAvecBoomerang();
            }
        } else {
            this.attaqueSansArme(monstre);
        }

    }




    public void attaqueSansArme(Monstre monstre){
        long currentTime = System.currentTimeMillis();
        if( currentTime - actionTime >= 500 && this.linkAttaque && derniereDirection == 1 && this.getY()-monstre.getY() < 32 && this.getY()-monstre.getY() >= -1  && Math.abs(this.getX()-monstre.getX()) < 16 ){
            monstreSubitDegat(monstre);
            if(monstre.peutSeDeplacer(monstre.getX(),monstre.getY()-32)){
                monstre.setY(monstre.getY()-32);
            }
            actionTime = currentTime;

        }
        if(currentTime - actionTime >= 500 && this.linkAttaque && derniereDirection == 2 && monstre.getX()-this.getX() < 32 && monstre.getX()-this.getX() >= -1  && Math.abs(this.getY()-monstre.getY()) < 16 ){
            monstreSubitDegat(monstre);
            if(monstre.peutSeDeplacer(monstre.getX()+32,monstre.getY())){
                monstre.setX(monstre.getX()+32);
            }
            actionTime = currentTime;

        }
        if(currentTime - actionTime >= 500 && this.linkAttaque && derniereDirection == 3 && monstre.getY()-this.getY() < 32 && monstre.getY()-this.getY() >= -1  && Math.abs(this.getX()-monstre.getX()) < 16 ){
            monstreSubitDegat(monstre);
            if(monstre.peutSeDeplacer(monstre.getX(),monstre.getY()+32)){
                monstre.setY(monstre.getY()+32);
            }
            actionTime = currentTime;

        }
        if(currentTime - actionTime >= 500 && this.linkAttaque && derniereDirection == 4 && this.getX()-monstre.getX() < 32 && this.getX()-monstre.getX() >= -1 && Math.abs(this.getY()-monstre.getY()) < 16 ){
            monstreSubitDegat(monstre);
            if(monstre.peutSeDeplacer(monstre.getX()-32,monstre.getY())){
                monstre.setX(monstre.getX()-32);
            }
            actionTime = currentTime;

        }



    }


    public void attaqueAvecEpee(Monstre monstre){
        long currentTime = System.currentTimeMillis();
        if( currentTime - actionTime >= 500 && this.linkAttaque && derniereDirection == 1 && this.getY()-monstre.getY() < 32 && this.getY()-monstre.getY() >= -1  && Math.abs(this.getX()-monstre.getX()) < 16 ){
            monstreSubitDegat(monstre);
            if(monstre.peutSeDeplacer(monstre.getX(),monstre.getY()-32)){
                monstre.setY(monstre.getY()-32);
            }
            actionTime = currentTime;

        }
        if(currentTime - actionTime >= 500 && this.linkAttaque && derniereDirection == 2 && monstre.getX()-this.getX() < 32 && monstre.getX()-this.getX() >= -1  && Math.abs(this.getY()-monstre.getY()) < 16 ){
            monstreSubitDegat(monstre);
            if(monstre.peutSeDeplacer(monstre.getX()+32,monstre.getY())){
                monstre.setX(monstre.getX()+32);
            }
            actionTime = currentTime;

        }
        if(currentTime - actionTime >= 500 && this.linkAttaque && derniereDirection == 3 && monstre.getY()-this.getY() < 32 && monstre.getY()-this.getY() >= -1  && Math.abs(this.getX()-monstre.getX()) < 16 ){
            monstreSubitDegat(monstre);
            if(monstre.peutSeDeplacer(monstre.getX(),monstre.getY()+32)){
                monstre.setY(monstre.getY()+32);
            }
            actionTime = currentTime;

        }
        if(currentTime - actionTime >= 500 && this.linkAttaque && derniereDirection == 4 && this.getX()-monstre.getX() < 32 && this.getX()-monstre.getX() >= -1 && Math.abs(this.getY()-monstre.getY()) < 16 ){
            monstreSubitDegat(monstre);
            if(monstre.peutSeDeplacer(monstre.getX()-32,monstre.getY())){
                monstre.setX(monstre.getX()-32);
            }
            actionTime = currentTime;

        }
    }



    public void attaqueAvecArc() {
        long currentTime = System.currentTimeMillis();


        if (this.linkAttaque && derniereDirection == 1) {
            fleche.setxProjectile(this.getX());
            fleche.setyProjectile(this.getY());
            fleche.setDire(1);
            tempAvantDisparitionDeLaFleche = 0;
        }

        if (this.linkAttaque && derniereDirection == 2) {
            fleche.setxProjectile(this.getX());
            fleche.setyProjectile(this.getY());
            fleche.setDire(2);
            tempAvantDisparitionDeLaFleche = 0;
        }

        if (this.linkAttaque && derniereDirection == 3) {
            fleche.setxProjectile(this.getX());
            fleche.setyProjectile(this.getY());
            fleche.setDire(3);
            tempAvantDisparitionDeLaFleche = 0;
        }

        if (this.linkAttaque && derniereDirection == 4) {
            fleche.setxProjectile(this.getX());
            fleche.setyProjectile(this.getY());
            fleche.setDire(4);
            tempAvantDisparitionDeLaFleche = 0;
        }
    }


    public void flecheSeDeplace(Projectile fleche ,Monstre monstre){
        if (fleche.getDire() == 1){
            if (Math.abs(fleche.getyProjectile() - monstre.getY()) < 2  && Math.abs(this.getX()-monstre.getX()) < 32 ){
                monstreSubitDegat(monstre);
            }
            fleche.setyProjectile(fleche.getyProjectile()-1);
            tempAvantDisparitionDeLaFleche = tempAvantDisparitionDeLaFleche+1;
            diparitionFleche();
        } else if(fleche.getDire() == 2) {
            if (Math.abs(fleche.getxProjectile() - monstre.getX()) < 2 && Math.abs(this.getY() - monstre.getY()) < 32) {
                monstreSubitDegat(monstre);
            }
            fleche.setxProjectile(fleche.getxProjectile() + 1);
            tempAvantDisparitionDeLaFleche = tempAvantDisparitionDeLaFleche+1;
            diparitionFleche();
        } else if(fleche.getDire() == 3) {
            if (Math.abs(fleche.getyProjectile() - monstre.getY()) < 2 && Math.abs(this.getX() - monstre.getX()) < 32) {
                monstreSubitDegat(monstre);
            }
            fleche.setyProjectile(fleche.getyProjectile() + 1);
            tempAvantDisparitionDeLaFleche = tempAvantDisparitionDeLaFleche+1;
            diparitionFleche();
        } else if(fleche.getDire() == 4) {
            if (Math.abs(fleche.getxProjectile() - monstre.getX()) < 2 && Math.abs(this.getX() - monstre.getX()) < 32) {
                monstreSubitDegat(monstre);
            }
            fleche.setxProjectile(fleche.getxProjectile() - 1);
            tempAvantDisparitionDeLaFleche = tempAvantDisparitionDeLaFleche+1;
            diparitionFleche();
        }
    }


    public void attaqueAvecBoomerang() {
        long currentTime = System.currentTimeMillis();


        if (this.linkAttaque && derniereDirection == 1) {
            boomerang.setxProjectile(this.getX());
            boomerang.setyProjectile(this.getY());
            boomerang.setDire(1);
            tempAvantRetourBoomerang = 0;
        }

        if (this.linkAttaque && derniereDirection == 2) {
            boomerang.setxProjectile(this.getX());
            boomerang.setyProjectile(this.getY());
            boomerang.setDire(2);
            tempAvantRetourBoomerang = 0;
        }

        if (this.linkAttaque && derniereDirection == 3) {
            boomerang.setxProjectile(this.getX());
            boomerang.setyProjectile(this.getY());
            boomerang.setDire(3);
            tempAvantRetourBoomerang = 0;
        }

        if (this.linkAttaque && derniereDirection == 4) {
            boomerang.setxProjectile(this.getX());
            boomerang.setyProjectile(this.getY());
            boomerang.setDire(4);
            tempAvantRetourBoomerang = 0;
        }
    }

    public void boomerangSeDeplace(Projectile boomerang ,Monstre monstre){
        if (boomerang.getDire() == 1){
            if (Math.abs(boomerang.getyProjectile() - monstre.getY()) < 2  && Math.abs(this.getX()-monstre.getX()) < 32 ){
                monstreSubitDegat(monstre);
            }
            if(tempAvantRetourBoomerang <96){
                boomerang.setyProjectile(boomerang.getyProjectile()-1);
            }else {
                boomerang.setyProjectile(boomerang.getyProjectile() + 1);
            }
            tempAvantRetourBoomerang = tempAvantRetourBoomerang+1;
        } else if(boomerang.getDire() == 2) {
            if (Math.abs(boomerang.getxProjectile() - monstre.getX()) < 2 && Math.abs(this.getY() - monstre.getY()) < 32) {
                monstreSubitDegat(monstre);
            }
            if(tempAvantRetourBoomerang <96){
                boomerang.setxProjectile(boomerang.getxProjectile()+1);
            }else {
                boomerang.setxProjectile(boomerang.getxProjectile() - 1);
            }
            tempAvantRetourBoomerang = tempAvantRetourBoomerang+1;
        } else if(boomerang.getDire() == 3) {
            if (Math.abs(boomerang.getyProjectile() - monstre.getY()) < 2 && Math.abs(this.getX() - monstre.getX()) < 32) {
                monstreSubitDegat(monstre);
            }
            if(tempAvantRetourBoomerang <96){
                boomerang.setyProjectile(boomerang.getyProjectile()+1);
            }else {
                boomerang.setyProjectile(boomerang.getyProjectile() - 1);
            }
            tempAvantRetourBoomerang = tempAvantRetourBoomerang+1;
        } else if(boomerang.getDire() == 4) {
            if (Math.abs(boomerang.getxProjectile() - monstre.getX()) < 2 && Math.abs(this.getX() - monstre.getX()) < 32) {
                monstreSubitDegat(monstre);
            }
            if(tempAvantRetourBoomerang <96){
                boomerang.setxProjectile(boomerang.getxProjectile()-1);
            }else {
                boomerang.setxProjectile(boomerang.getxProjectile() + 1);
            }
            tempAvantRetourBoomerang = tempAvantRetourBoomerang+1;
        }
    }
    public boolean linkMeurt(){
        if(this.getPv()==0){
            return true;
        }
        return false;
    }



//    public void ramasserEpee(Epee epee){
//        if (this.linkRamasse && Math.abs(this.getX() - epee.getX()) < 8  && Math.abs(this.getY() - epee.getY())  < 8 ){
//            inventaire.ajouterArme(epee);
//            linkARamasseEpee = true;
//
//        }
//    }
//    public void ramasserArc(Arc arc){
//        if (this.linkRamasse && Math.abs(this.getX() - arc.getX()) < 8  && Math.abs(this.getY() - arc.getY())  < 8){
//            inventaire.ajouterArme(arc);
//            linkARamasseArc = true;
//
//        }
//    }
//    public void ramasserBoomerang(Boomerang boomerang){
//        if (this.linkRamasse && Math.abs(this.getX() - boomerang.getX()) < 8  && Math.abs(this.getY() - boomerang.getY())  < 8){
//            inventaire.ajouterArme(boomerang);
//            linkARamasseBommerang = true;
//
//        }
//    }
//    public void ramasserBouclier(Bouclier bouclier){
//        if (this.linkRamasse && Math.abs(this.getX() - bouclier.getX()) < 8  && Math.abs(this.getY() - bouclier.getY())  < 8){
//            inventaire.ajouterArme(bouclier);
//            linkARamasseBouclier = true;
//
//        }
//    }
//
//
//    public void ramasserPotionSoin(PotionSoin potionDeSoin){
//        if (this.linkRamassePotionSoin && Math.abs(this.getX() - potionDeSoin.getX()) < 8  && Math.abs(this.getY() - potionDeSoin.getY()) < 8){
//            inventaire.ajouterConsommable(potionDeSoin);
//            this.linkARamassePotionSoin = true;
//        }
//    }
//    public void ramasserPotionForce(PotionForce potionForce){
//        if (this.linkRamassePotionForce && Math.abs(this.getX() - potionForce.getX()) < 8  && Math.abs(this.getY() - potionForce.getY()) < 8){
//            inventaire.ajouterConsommable(potionForce);
//            this.linkARamassePotionForce = true;
//        }
//    }


    private boolean ramasserConsommable(Consommable consommable, boolean linkRamasse, boolean linkARamasse) {
        boolean updatedLinkARamasse = linkARamasse;
        if (linkRamasse && Math.abs(getX() - consommable.getX()) < 8 && Math.abs(getY() - consommable.getY()) < 8) {
            inventaire.ajouterConsommable(consommable);
            updatedLinkARamasse = true;
        }
        return updatedLinkARamasse;
    }
    private boolean ramasserArme(Arme arme, boolean linkRamasse, boolean linkARamasse) {
        boolean updatedLinkARamasse = linkARamasse;
        if (linkRamasse && Math.abs(getX() - arme.getX()) < 8 && Math.abs(getY() - arme.getY()) < 8) {
            inventaire.ajouterArme(arme);
            updatedLinkARamasse = true;
        }
        return updatedLinkARamasse;
    }

    public void ramasserBracelet(Bracelet bracelet) {
        linkARamasseBracelet = ramasserConsommable(bracelet, linkRamasseBracelet, linkARamasseBracelet);
    }
    public void ramasserPotionForce(PotionForce potionForce) {
        linkARamassePotionForce = ramasserConsommable(potionForce, linkRamassePotionForce, linkARamassePotionForce);
    }
    public void ramasserPotionSoin(PotionSoin potionSoin) {
        linkARamassePotionSoin = ramasserConsommable(potionSoin, linkRamassePotionSoin, linkARamassePotionSoin);
    }
    public void ramasserBoomerang(Boomerang boomerang){
        linkARamasseBommerang =  ramasserArme(boomerang, linkRamasse, linkARamasseBommerang);
    }
    public void ramasserArc(Arc arc){
        linkARamasseArc =  ramasserArme(arc, linkRamasse, linkARamasseArc);
    }
    public void ramasserEpee(Epee epee){
        linkARamasseEpee =  ramasserArme(epee, linkRamasse, linkARamasseEpee);
    }

    public boolean isLinkRamasseBracelet() {
        return linkRamasseBracelet;
    }








    public void setPv(int pv) {
        this.pv.set(pv);
    }

    public int getPv() {
        return pv.get();
    }

    public IntegerProperty pvProperty() {
        return pv;
    }

    @Override
    public String toString() {
        return "Link" + super.toString();
    }
    public void setLinkAttaqueTrue(){
        this.linkAttaque=true;
    }
    public void setLinkAttaqueFalse(){
        this.linkAttaque=false;
    }

    public void setDerniereDirection(int i){
        this.derniereDirection = i;
    }

    public boolean getLinkABouger() {
        return isStatutPas();
    }

    public boolean pousseLeBloc (){
        for (BlockDynamique blocDynamique : terrain.getBlocsDynamiques()) {
            if (direction.getValue() == 1) {
                if (!terrain.collisionPourBloc(blocDynamique.getX()+31, blocDynamique.getY()-1, blocDynamique)&&!terrain.collisionPourBloc(blocDynamique.getX(), blocDynamique.getY()-1, blocDynamique))
                    if (xProperty().getValue() > blocDynamique.xProperty().getValue() - 18
                            && xProperty().getValue() < blocDynamique.xProperty().getValue() + 16
                            && yProperty().getValue() > blocDynamique.yProperty().getValue()
                            && yProperty().getValue() < blocDynamique.yProperty().getValue() + 10) {


                        blocDynamique.bouge(direction.intValue());
                        return true;
                    }
            }
            if (direction.getValue() == 2) {
                if (!terrain.collisionPourBloc(blocDynamique.getX()+32, blocDynamique.getY()+31, blocDynamique)&& !terrain.collisionPourBloc(blocDynamique.getX()+32, blocDynamique.getY(), blocDynamique))
                    if (xProperty().getValue() > blocDynamique.xProperty().getValue() - 30
                            && xProperty().getValue() < blocDynamique.xProperty().getValue()
                            && yProperty().getValue() > blocDynamique.yProperty().getValue() - 28
                            && yProperty().getValue() < blocDynamique.yProperty().getValue() + 6) {


                        blocDynamique.bouge(direction.intValue());
                        return true;
                    }
            }

            if (direction.getValue() == 3) {
                if (!terrain.collisionPourBloc(blocDynamique.getX()+31, blocDynamique.getY()+32, blocDynamique)&&!terrain.collisionPourBloc(blocDynamique.getX(), blocDynamique.getY()+32, blocDynamique))
                    if (xProperty().getValue() > blocDynamique.xProperty().getValue() - 18
                            && xProperty().getValue() < blocDynamique.xProperty().getValue() + 16
                            && yProperty().getValue() > blocDynamique.yProperty().getValue() - 30
                            && yProperty().getValue() < blocDynamique.yProperty().getValue()) {


                        blocDynamique.bouge(direction.intValue());
                        return true;
                    }
            }

            if (direction.getValue() == 4) {
                if (!terrain.collisionPourBloc(blocDynamique.getX()-1, blocDynamique.getY()+31, blocDynamique) && !terrain.collisionPourBloc(blocDynamique.getX()-1, blocDynamique.getY(), blocDynamique))
                    if (xProperty().getValue() > blocDynamique.xProperty().getValue()
                            && xProperty().getValue() < blocDynamique.xProperty().getValue() + 32
                            && yProperty().getValue() > blocDynamique.yProperty().getValue() - 28
                            && yProperty().getValue() < blocDynamique.yProperty().getValue() + 6) {



                        blocDynamique.bouge(direction.intValue());
                        return true;
                    }
            }


        }
        return false;
    }


    public void linkUtilisePotionSoin() {
        if (this.pv.getValue() <= 3) {
            this.setPv(this.getPv() + 2);
        } else if (this.pv.getValue() == 4) {
            this.setPv(this.getPv() + 1);
        }
    }

    public void linkUtilisePotionForce(){
        this.setPointAttaque(this.pointAttaque = this.pointAttaque+2);
        PauseTransition pause = new PauseTransition(Duration.seconds(60));
        pause.setOnFinished(event -> {
            this.setPointAttaque(this.pointAttaque = this.pointAttaque-2);
        });
        pause.play();

    }
    public int getPtsAttaque(){
        return this.pointAttaque;
    }

    public void setPointAttaque(int pointAttaque){
        this.pointAttaque=pointAttaque;
    }


    public void setRamasserArmeTrue(){
        this.linkRamasse=true;

    }

    public void setRamasserArmeFalse(){
        this.linkRamasse=false;

    }

    public void setLinkRamassePotion(boolean linkRamassePotion) {
        this.linkRamassePotionSoin = linkRamassePotion;
        this.linkRamassePotionForce = linkRamassePotion;
        this.linkRamasseBracelet = linkRamassePotion;
    }

    public boolean getRamasseArme(){
        return this.linkRamasse;
    }

    public boolean getRamassePotion(){
        return this.linkRamassePotionSoin;
    }

    public Inventaire getInventaire() {
        return inventaire;
    }


    public boolean isLinkARamasseEpee() {
        return linkARamasseEpee;
    }

    public boolean isLinkARamasseArc() {
        return linkARamasseArc;
    }

    public boolean isLinkARamasseArme() {
        return linkARamasseArme;
    }

    public boolean isLinkARamasseBommerang() {
        return linkARamasseBommerang;
    }

    public boolean isLinkARamasseBouclier() {
        return linkARamasseBouclier;
    }

    public boolean isLinkARamassePotionSoin() {
        return linkARamassePotionSoin;
    }
    public boolean isLinkARamassePotionForce() {
        return linkARamassePotionForce;
    }

    public boolean isLinkARamasseBracelet() {
        return linkARamasseBracelet;
    }


    public void equiperArme() {
        for(int i = 0;i< inventaire.getInventaireArme().size();i++){
            if(inventaire.getInventaireArme().get(i).getNomPng().equals(armeChoisi)){
                this.armeEquipe = inventaire.getInventaireArme().get(i);

            }
        }
    }

    public void setArmeChoisi(String armeChoisi) {
        this.armeChoisi = armeChoisi;
    }

    public Projectile getFleche() {
        return fleche;
    }


    public Arme getArmeEquipe() {
        return armeEquipe;
    }


    public BooleanProperty braceletUtiliseProperty() {
        return braceletUtilise;
    }

    public void resetBracelet() {
        braceletUtilise.set(false);
    }
    public void linkUtiliseBracelet() {
        braceletUtilise.set(true);
        this.invisible=true;
    }

    public boolean isInvisible() {
        return invisible;
    }

    public void setInvisible(boolean invisible) {
        this.invisible = invisible;
    }

    public int getTp(int x, int y){

        if (xProperty().getValue()>3135 && xProperty().getValue()<3169 &&yProperty().intValue()>1697&&yProperty().intValue()<1729){
            return 102;
        }

        if (xProperty().getValue()>4159 && xProperty().getValue()<4193 &&yProperty().intValue()>1439&&yProperty().intValue()<1473){
            return 30;
        }

        return -1;
    }



    public void monstreSubitDegat(Monstre monstre){
        if(this.armeEquipe == null){
            monstre.setPv(monstre.getPv() - (this.pointAttaque));
            monstre.setMonsSubitDegat(true);
        }else{
            monstre.setPv(monstre.getPv() - (this.pointAttaque + this.armeEquipe.getDegats()));
            monstre.setMonsSubitDegat(true);
        }
    }

    public void diparitionFleche(){
        if (tempAvantDisparitionDeLaFleche == 128){
            this.fleche.setxProjectileNull();
            this.fleche.setyProjectileNull();

        }
    }

    public void diparitionBoomerang(){
        if (tempAvantRetourBoomerang == 192){
            this.boomerang.setxProjectileNull();
            this.boomerang.setyProjectileNull();

        }
    }

    public Projectile getBoomerang() {
        return boomerang;
    }

    public void agit() {
        this.seDeplace();
        this.equiperArme();
    }

}