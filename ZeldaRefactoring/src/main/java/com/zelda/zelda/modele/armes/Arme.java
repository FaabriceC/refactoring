package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;
import javafx.beans.property.IntegerProperty;

public abstract class Arme {

    protected int degats;
    protected IntegerProperty x;
    protected IntegerProperty y;

    public static int compteur =1;
    private String id;

    protected String nomPng;



    //JE REFACTOR
    protected Link link;
    
    long actionTime = 0L;

    public Arme(Link link){
        this.link = link;
        this.id = "W" + compteur;
        compteur++;

    }




    public void attaqueAvecArme(Monstre monstre){
        long currentTime = System.currentTimeMillis();
        if(attaquePossibleSelonDirection(link.getDerniereDirection(),monstre,currentTime)){
            System.out.println("LINK ATTAQUE");
            this.faitDesDegatAuMonstre(monstre);
            if(monstre.peutReculerSelonDirection(link.getDerniereDirection())){
                this.faitReculerMonstreSelonDirection(link.getDerniereDirection(),monstre);
            }

            //actionTime = currentTime;

        }

    }



    public boolean attaquePossibleSelonDirection(int direction, Monstre monstre,long currentTime){
        if (direction == 1 ){
            return /*currentTime - actionTime >= 500 &&*/ link.getY()-monstre.getY() < 32 && link.getY()-monstre.getY() >= -1  && Math.abs(link.getX()-monstre.getX()) < 16;
        } else if (direction == 2) {
            return/* currentTime - actionTime >= 500 &&*/  monstre.getX()-link.getX() < 32 && monstre.getX()-link.getX() >= -1  && Math.abs(link.getY()-monstre.getY()) < 16;
        } else if (direction == 3){
            return monstre.getY()-link.getY() < 32 && monstre.getY()-link.getY() >= -1  && Math.abs(link.getX()-monstre.getX()) < 16;
        } else {
            return link.getX()-monstre.getX() < 32 && link.getX()-monstre.getX() >= -1 && Math.abs(link.getY()-monstre.getY()) < 16;
        }
    }


    public void faitReculerMonstreSelonDirection(int direction,Monstre monstre){
        if(direction == 1){
            monstre.setY(monstre.getY()-32);
        } else if (direction == 2){
            monstre.setX(monstre.getX()+32);
        } else if (direction == 3){
            monstre.setY(monstre.getY()+32);
        } else {
            monstre.setX(monstre.getX()-32);
        }
    }


    public void faitDesDegatAuMonstre(Monstre monstre){
        monstre.setPv(monstre.getPv() - (link.getPointAttaque() + link.getArmeEquipe().getDegats()));
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

    public Link getLink() {
        return link;
    }
}