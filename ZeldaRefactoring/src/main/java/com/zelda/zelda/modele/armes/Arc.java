package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;
import javafx.beans.property.SimpleIntegerProperty;

public  class Arc extends com.zelda.zelda.modele.armes.Arme {

    //private int tempAvantDisparitionDeLaFleche;

    public Arc (){
        super();
        this.degats=2;
        this.x = new SimpleIntegerProperty(1500);
        this.y = new SimpleIntegerProperty(550);
        this.nomPng="arc.png";



    }

    public void attaqueAvecArme(Monstre monstre){
        long currentTime = System.currentTimeMillis();
        //Fleche fleche = new Fleche("arrows.png");
        //Environnement.getInstance().getProjectiles().add(fleche);
        /*
        if (Link.getInstance().isLinkAttaque()) {
            fleche.apparitionFleche(Link.getInstance().getDerniereDirection(),Link.getInstance());
        }
        fleche.flecheSeDeplace(monstre);
        */

        if (Link.getInstance().isLinkAttaque()) {
            Fleche.getInstance().apparitionFleche(Link.getInstance().getDerniereDirection(),Link.getInstance());
        }




    }

    public void setAZRE(){
        this.x.setValue(220);
    }

    public void setAZRE2(){
        this.y.setValue(220);
    }










/*
    public void apparitionFleche(int direction){
        fleche.setxProjectile(this.getX());
        fleche.setyProjectile(this.getY());
        fleche.setDire(direction);
        tempAvantDisparitionDeLaFleche = 0;
    }



    public void flecheSeDeplace(Monstre monstre){
        this.flecheDepl(fleche.getDire(),monstre);
        this.flecheSeDeplaceSelonDirection(fleche.getDire());
        this.diparitionFleche();

    }



    public void flecheDepl(int direcrionFleche, Monstre monstre){
        if (monstreTouchable(direcrionFleche,monstre)) {
            monstreSubitDegat(monstre);
        }
    }

    public void flecheSeDeplaceSelonDirection(int direcrionFleche){
        if (direcrionFleche == 1) {
            fleche.setyProjectile(fleche.getyProjectile()-1);
        } else if (direcrionFleche == 2) {
            fleche.setxProjectile(fleche.getxProjectile() + 1);
        } else if (direcrionFleche == 3) {
            fleche.setyProjectile(fleche.getyProjectile() + 1);
        } else if (direcrionFleche == 4) {
            fleche.setxProjectile(fleche.getxProjectile() - 1);
        }
        tempAvantDisparitionDeLaFleche = tempAvantDisparitionDeLaFleche+1;
    }



    public boolean monstreTouchable(int direcrionFleche,Monstre monstre) {
        if (direcrionFleche == 1 || direcrionFleche == 3){
            return Math.abs(fleche.getyProjectile() - monstre.getY()) < 2  && Math.abs(this.getX()-monstre.getX()) < 32;
        } else {
            return Math.abs(fleche.getxProjectile() - monstre.getX()) < 2 && Math.abs(this.getY() - monstre.getY()) < 32;
        }
    }

    public void diparitionFleche(){
        if (tempAvantDisparitionDeLaFleche == 128){
            this.fleche.setxProjectileNull();
            this.fleche.setyProjectileNull();

        }
    }

 */





}