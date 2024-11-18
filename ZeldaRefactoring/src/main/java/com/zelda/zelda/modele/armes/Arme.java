package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.Environnement;
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
    
    private long actionTime = 0L;

    public Arme(){
        this.id = "W" + compteur;
        compteur++;

    }




    public abstract void attaqueAvecArme(Monstre monstre);



    public boolean attaquePossibleSelonDirection(int direction, Monstre monstre,long currentTime){
        if (direction == 1 ){
            return  Link.getInstance().getY()-monstre.getY() < 32 && Link.getInstance().getY()-monstre.getY() >= -1  && Math.abs(Link.getInstance().getX()-monstre.getX()) < 16;
        } else if (direction == 2) {
            return  monstre.getX()-Link.getInstance().getX() < 32 && monstre.getX()-Link.getInstance().getX() >= -1  && Math.abs(Link.getInstance().getY()-monstre.getY()) < 16;
        } else if (direction == 3){
            return   monstre.getY()-Link.getInstance().getY() < 32 && monstre.getY()-Link.getInstance().getY() >= -1  && Math.abs(Link.getInstance().getX()-monstre.getX()) < 16;
        } else {
            return Link.getInstance().getX()-monstre.getX() < 32 && Link.getInstance().getX()-monstre.getX() >= -1 && Math.abs(Link.getInstance().getY()-monstre.getY()) < 16;
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
        monstre.setPv(monstre.getPv() - (Link.getInstance().getPointAttaque() + Link.getInstance().getArmeEquipe().getDegats()));
        System.out.println(monstre.getPv());
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
/*
    public Link getLink() {
        return link;
    }

 */

    public long getActionTime() {
        return actionTime;
    }

    public boolean cooldown(long currentTime,int cooldown){
        if (currentTime - actionTime >= cooldown){
            actionTime = currentTime;
            return true;

        } else{
            return false;
        }
    }
}