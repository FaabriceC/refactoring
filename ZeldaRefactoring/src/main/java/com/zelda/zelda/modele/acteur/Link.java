package com.zelda.zelda.modele.acteur;


import com.zelda.zelda.modele.Consommable.Consommable;
import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.Inventaire;
import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.armes.*;

import com.zelda.zelda.modele.dynamique.BlockDynamique;
import javafx.animation.PauseTransition;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;
import javafx.util.Duration;

import java.util.*;

public class Link extends Personnage {

    private IntegerProperty pv;
    long actionTime = 0L;
    private boolean linkAttaque;
    private int derniereDirection;
    private Inventaire inventaire;
    private BooleanProperty braceletUtilise = new SimpleBooleanProperty(false);

    private Arme armeEquipe;

    private String armeChoisi;

    private int pointAttaque;

    private Projectile fleche;
    private int tempAvantDisparitionDeLaFleche;

    private Projectile boomerang;

    private int tempAvantRetourBoomerang;

    private boolean invisible;

    private boolean tp;
    private static Link uniqueInstance = null;



    public Link(String nom, int x, int y, Terrain t) {
        super(x, y, nom, t);
        this.pv= new SimpleIntegerProperty(5);
        this.pv.setValue(5);
        this.linkAttaque = false;
        this.inventaire= new Inventaire();
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

    public void seDeplace() {
        tp =false;
        int deplacementX = 0;
        int deplacementY = 0;

        int vitesse = 5;

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


    public int[] margeErreur(int margeX, int margeY) {
        int[]marge=new int[2];
        switch (direction.getValue()) {
            case 4:
                margeX = 10;
                margeY= 26;
                break;
            case 2:
                margeX = 26;
                margeY= 26;
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
        return this.getPv() == 0;
    }

    public void ramasserConsommable(ObservableList<Consommable> getConsommables) {
        Iterator<Consommable> iterator = getConsommables.iterator();
        while (iterator.hasNext()) {
            Consommable consommable = iterator.next();
            if (Math.abs(getX() - consommable.getX()) < 8 && Math.abs(getY() - consommable.getY()) < 8) {
                inventaire.ajouterConsommable(consommable);
                iterator.remove();
            }
        }
    }
    //TODO FAIRE UNE SUPERCLASSE ITEM QUI POSSEDE EN ABSTRACTR UNE METHODE RAMASSE
    public void ramasserArme(ObservableList<Arme> getArmes) {
        Iterator<Arme> iterator = getArmes.iterator();
        while (iterator.hasNext()) {
            Arme arme = iterator.next();
            if (Math.abs(getX() - arme.getX()) < 8 && Math.abs(getY() - arme.getY()) < 8) {
                inventaire.ajouterArme(arme);
                iterator.remove();
            }
        }
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

    public boolean pousseLeBloc() {
        int[][] valeurs = {
                {},
                {31, 1, 1, 18, 16, 0, 10},
                {32, -31, -32, 30, 0, -28, 6},
                {31, -32, -32, 18, 16, -30, 0},
                {-1, -31, 1, 0, 32, -28, 6}
        };
        int dir = direction.getValue();
        for (BlockDynamique blocDynamique : terrain.getBlocsDynamiques()) {
            if (dir >= 1 && dir <= 4) {
                if (raccourci(blocDynamique, valeurs[dir])) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean raccourci(BlockDynamique blocDynamique, int[] valeurs) {
        int valeur1 = valeurs[0], valeur2 = valeurs[1], valeur3 = valeurs[2], valeur4 = valeurs[3];
        int valeur5 = valeurs[4], valeur6 = valeurs[5], valeur7 = valeurs[6];
        if (!terrain.collisionPourBloc(blocDynamique.getX() + valeur1, blocDynamique.getY() - valeur2, blocDynamique) &&!terrain.collisionPourBloc(blocDynamique.getX(), blocDynamique.getY() - valeur3, blocDynamique)) {
            if (xProperty().getValue() > blocDynamique.xProperty().getValue() - valeur4 &&xProperty().getValue() < blocDynamique.xProperty().getValue() + valeur5 &&yProperty().getValue() > blocDynamique.yProperty().getValue() + valeur6 &&yProperty().getValue() < blocDynamique.yProperty().getValue() + valeur7) {
                blocDynamique.bouge(direction.intValue());
                return true;
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
 // TODO UN LINK EN SINGLETON changer le nom et déplacer dans leurs classes respectives
    public void linkUtilisePotionForce(){
        this.setPointAttaque(this.pointAttaque = this.pointAttaque+2);
        PauseTransition pause = new PauseTransition(Duration.seconds(60));
        pause.setOnFinished(event -> {
            this.setPointAttaque(this.pointAttaque = this.pointAttaque-2);
        });
        pause.play();

    }
    */

    public int getPtsAttaque(){
        return this.pointAttaque;
    }

    public void setPointAttaque(int pointAttaque){
        this.pointAttaque=pointAttaque;
    }


    public Inventaire getInventaire() {
        return inventaire;
    }



    public void equiperArme() {
        for(int i = 0;i< inventaire.getInventaireArme().size();i++){
            if(inventaire.getInventaireArme().get(i).getNomPng().equals(armeChoisi)){
                this.armeEquipe = inventaire.getInventaireArme().get(i);

            }
        }
    }
    public void  attaqueMonstre() {
        for (int i = 0; i < Environnement.getInstance().getPersonnageListe().size(); i++) {
            if (Environnement.getInstance().getPersonnageListe().get(i) instanceof Monstre) {
                Monstre m = (Monstre) Environnement.getInstance().getPersonnageListe().get(i);
                Link.getInstance().attaque(m);
                if (!m.vivant()) {
                    Environnement.getInstance().getPersonnageListe().remove(m);
                }
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
        this.attaqueMonstre();

    }

}