package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;
import com.zelda.zelda.modele.acteur.Personnage;
import javafx.beans.property.IntegerProperty;

public abstract class Arme {

    protected int degats;
    protected IntegerProperty x;
    protected IntegerProperty y;

    public static int compteur =1;
    private String id;

    protected String nomPng;

    long actionTime = 0L;

    public Arme(){
        this.id = "W" + compteur;
        compteur++;

    }

    public void attaqueAvecArme(Personnage personnage){
        long currentTime = System.currentTimeMillis();
        if(attaquePossibleSelonDirection(Link.getInstance().getDerniereDirection(),personnage,currentTime)){
            this.faitDesDegatAuMonstre(personnage);
            if(personnage.peutReculerSelonDirection(Link.getInstance().getDerniereDirection())){
                this.faitReculerMonstreSelonDirection(Link.getInstance().getDerniereDirection(),personnage);
            }
            System.out.println("Dégats de l'arme : " + Link.getInstance().getArmeEquipe().getDegats());


            actionTime = currentTime;

        }

    }



    public boolean attaquePossibleSelonDirection(int direction, Personnage personnage,long currentTime){
        if (direction == 1 ){
            return /*currentTime - actionTime >= 500 && */Link.getInstance().getY()-personnage.getY() < 32 && Link.getInstance().getY()-personnage.getY() >= -1  && Math.abs(Link.getInstance().getX()-personnage.getX()) < 16;
        } else if (direction == 2) {
            return /* currentTime - actionTime >= 500 &&*/  personnage.getX()-Link.getInstance().getX() < 32 && personnage.getX()-Link.getInstance().getX() >= -1  && Math.abs(Link.getInstance().getY()-personnage.getY()) < 16;
        } else if (direction == 3){
            return personnage.getY()-Link.getInstance().getY() < 32 && personnage.getY()-Link.getInstance().getY() >= -1  && Math.abs(Link.getInstance().getX()-personnage.getX()) < 16;
        } else {
            return Link.getInstance().getX()-personnage.getX() < 32 && Link.getInstance().getX()-personnage.getX() >= -1 && Math.abs(Link.getInstance().getY()-personnage.getY()) < 16;
        }
    }


    public void faitReculerMonstreSelonDirection(int direction,Personnage personnage){
        if(direction == 1){
            personnage.setY(personnage.getY()+32);
        } else if (direction == 2){
            personnage.setX(personnage.getX()-32);
        } else if (direction == 3){
            personnage.setY(personnage.getY()-32);
        } else {
            personnage.setX(personnage.getX()+32);
        }
    }


    public void faitDesDegatAuMonstre(Personnage personnage){
        personnage.setPv(personnage.getPv() - (Link.getInstance().getPointAttaque() + Link.getInstance().getArmeEquipe().getDegats()));
        System.out.println("Monstre pv : " + personnage.getPv());
    }

/*
    public boolean monstrePeutReculerSelonDirection(int direction,Monstre monstre){
        if(direction == 1){
            return monstre.peutSeDeplacer(monstre.getX(),monstre.getY()-32);
        } else if (direction == 2){
            return monstre.peutSeDeplacer(monstre.getX()+32,monstre.getY());
        } else if (direction == 3){
            return monstre.peutSeDeplacer(monstre.getX(),monstre.getY()+32);
        } else {
            return monstre.peutSeDeplacer(monstre.getX()-32,monstre.getY());
        }
    }


 */



    public String getId() {return id;}
    public String getNomPng() {return nomPng;}
    public int getX(){return x.getValue();}
    public int getY(){return y.getValue();}
    public IntegerProperty xProperty(){return x;}
    public IntegerProperty yProperty(){return y;}
    public int getDegats() {return degats;}


}