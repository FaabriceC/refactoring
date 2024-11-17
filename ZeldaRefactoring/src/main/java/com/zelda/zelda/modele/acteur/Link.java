package com.zelda.zelda.modele.acteur;


import com.zelda.zelda.modele.Consommable.Bracelet;
import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.Inventaire;
import com.zelda.zelda.modele.Item;
import com.zelda.zelda.modele.Terrain;
import com.zelda.zelda.modele.armes.*;

import com.zelda.zelda.modele.deplacement.DeplacementLink;
import com.zelda.zelda.modele.deplacement.StrategieDeplacement;
import com.zelda.zelda.modele.dynamique.BlockDynamique;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.ObservableList;

import java.util.*;

public class Link extends Personnage {

    private IntegerProperty pv;
    long actionTime = 0L;
    private boolean linkAttaque;
    private int derniereDirection;
    private Inventaire inventaire;
    private Arme armeEquipe;
    private String armeChoisi;
    private int pointAttaque;
    private boolean invisible;
    private boolean tp;
    private static Link uniqueInstance = null;

    private Bracelet bracelet;

    private StrategieDeplacement strategieDeplacement;

    private Link(String nom, int x, int y) {
        super(5, x, y, nom);
        this.pv= new SimpleIntegerProperty(5);
        this.pv.setValue(5);
        this.linkAttaque = false;
        this.inventaire= new Inventaire();
        this.armeEquipe = null;
        this.armeChoisi = null;
        this.pointAttaque = 1;
        this.inventaire = new Inventaire();
        this.strategieDeplacement = new DeplacementLink();


    }

    public Bracelet getBracelet() {
        return bracelet;
    }



    public static Link getInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Link("Link", 800, 400);
        }
        return uniqueInstance;
    }


    public boolean peutSeDeplacer(int tuileX, int tuileY){
        int margeX = margeErreur(0,0)[0];
        int margeY = margeErreur(margeX,0)[1];

        return !Terrain.getInstance().collision(tuileX+margeX,tuileY+margeY);
    }

    public void setTp(boolean tp) {
        this.tp = tp;
    }

    public boolean isTp() {
        return tp;
    }

    public void seDeplace() {
    strategieDeplacement.seDeplace();
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


    public void attaqueSiPossible(Personnage personnage) {
        if (this.armeEquipe != null) {
            this.armeEquipe.attaque((Monstre) personnage);
        } else {
            this.attaqueSansArme((Monstre) personnage);
        }

    }

    public void attaqueSansArme(Monstre personnage){
        long currentTime = System.currentTimeMillis();
        if(currentTime - actionTime >= 500 && derniereDirection == 1 && this.getY()-personnage.getY() < 32 && this.getY()-personnage.getY() >= -1  && Math.abs(this.getX()-personnage.getX()) < 16 ){
            monstreSubitDegat(personnage);
            if(personnage.peutSeDeplacer(personnage.getX(),personnage.getY()-32)){
                personnage.setY(personnage.getY()-32);
            }
            actionTime = currentTime;

        }
        if(currentTime - actionTime >= 500 && derniereDirection == 2 && personnage.getX()-this.getX() < 32 && personnage.getX()-this.getX() >= -1  && Math.abs(this.getY()-personnage.getY()) < 16 ){
            monstreSubitDegat(personnage);
            if(personnage.peutSeDeplacer(personnage.getX()+32,personnage.getY())){
                personnage.setX(personnage.getX()+32);
            }
            actionTime = currentTime;

        }
        if(currentTime - actionTime >= 500 && derniereDirection == 3 && personnage.getY()-this.getY() < 32 && personnage.getY()-this.getY() >= -1  && Math.abs(this.getX()-personnage.getX()) < 16 ){
            monstreSubitDegat(personnage);
            if(personnage.peutSeDeplacer(personnage.getX(),personnage.getY()+32)){
                personnage.setY(personnage.getY()+32);
            }
            actionTime = currentTime;

        }
        if(currentTime - actionTime >= 500 && derniereDirection == 4 && this.getX()-personnage.getX() < 32 && this.getX()-personnage.getX() >= -1 && Math.abs(this.getY()-personnage.getY()) < 16 ){
            monstreSubitDegat(personnage);
            if(personnage.peutSeDeplacer(personnage.getX()-32,personnage.getY())){
                personnage.setX(personnage.getX()-32);
            }
            actionTime = currentTime;

        }

    }

    public boolean estMort(){
        return this.getPv() == 0;
    }

    public void ramasser(ObservableList<Item> getItems) {
        Iterator<Item> iterator = getItems.iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (Math.abs(getX() - item.getX()) < 8 && Math.abs(getY() - item.getY()) < 8) {
                this.inventaire.ajouterItem(item);
                if (item  instanceof  Bracelet){
                    this.bracelet = new Bracelet();
                }
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

    public String toString() {
        return "Link" + super.toString();
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

    public int getPtsAttaque(){
        return this.pointAttaque;
    }

    public void setPointAttaque(int pointAttaque){
        this.pointAttaque=pointAttaque;
    }


    public void equiperArme() {
        for(int i = 0;i< this.inventaire.getInventaireArme().size();i++){
            if(this.getInventaire().getInventaireArme().get(i).getNom().equals(armeChoisi)){
                this.armeEquipe = this.getInventaire().getInventaireArme().get(i);
            }
        }

    }

    public void attaqueMonstre() {
        for (int i = 0; i < Environnement.getInstance().getPersonnageListe().size(); i++) {
            if (Environnement.getInstance().getPersonnageListe().get(i) instanceof Monstre) {
                Monstre m = (Monstre) Environnement.getInstance().getPersonnageListe().get(i);
                Link.getInstance().attaqueSiPossible(m);
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




    public void utiliseBracelet() {
        bracelet.activer();
    }

    public void resetBracelet() {
        bracelet.desactiver();
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
            monstre.setSubitDegat(true);
        }else{
            monstre.setPv(monstre.getPv() - (this.pointAttaque + this.armeEquipe.getDegats()));
            monstre.setSubitDegat(true);
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

    public Inventaire getInventaire() {
        return inventaire;
    }
}