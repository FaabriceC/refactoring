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


    private boolean invisible;

    private boolean tp;
    private static Link uniqueInstance = null;



    private Link(String nom, int x, int y, Terrain t) {
        super(x, y, nom, t);
        this.pv= new SimpleIntegerProperty(5);
        this.pv.setValue(5);
        this.linkAttaque = false;
        this.inventaire= new Inventaire();
        this.armeEquipe = null;
        this.armeChoisi = null;
        this.pointAttaque = 1;

    }

    public static Link getInstance() {

        if (uniqueInstance == null) {
            uniqueInstance = new Link("Link", 800, 400, Terrain.getInstance());
        }

        return uniqueInstance;
    }


    public boolean peutSeDeplacer  (int tuileX, int tuileY){
        int margeX = margeErreur(0,0)[0];
        int margeY = margeErreur(margeX,0)[1];

        return !Terrain.getInstance().collision(tuileX+margeX,tuileY+margeY);

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

        if ( peutSeDeplacer(newX, newY)&& Terrain.getInstance().dansTerrain(newX,newY)&&!tp) {
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
            this.armeEquipe.attaqueAvecArme(monstre);
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
        for (BlockDynamique blocDynamique : Terrain.getInstance().getBlocsDynamiques()) {
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
        if (!Terrain.getInstance().collisionPourBloc(blocDynamique.getX() + valeur1, blocDynamique.getY() - valeur2, blocDynamique) &&!Terrain.getInstance().collisionPourBloc(blocDynamique.getX(), blocDynamique.getY() - valeur3, blocDynamique)) {
            if (xProperty().getValue() > blocDynamique.xProperty().getValue() - valeur4 &&xProperty().getValue() < blocDynamique.xProperty().getValue() + valeur5 &&yProperty().getValue() > blocDynamique.yProperty().getValue() + valeur6 &&yProperty().getValue() < blocDynamique.yProperty().getValue() + valeur7) {
                blocDynamique.bouge(direction.intValue());
                return true;
            }
        }
        return false;
    }

/*
    public void linkUtilisePotionSoin() {
        if (this.pv.getValue() <= 3) {
            this.setPv(this.getPv() + 2);
        } else if (this.pv.getValue() == 4) {
            this.setPv(this.getPv() + 1);
        }
    }
 */

    /*

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





    public boolean isLinkAttaque() {
        return linkAttaque;
    }

    public int getPointAttaque() {
        return pointAttaque;
    }

    public int getDerniereDirection() {
        return derniereDirection;
    }

    public void agit() {
        this.seDeplace();
        this.equiperArme();
        this.attaqueMonstre();

    }


}

