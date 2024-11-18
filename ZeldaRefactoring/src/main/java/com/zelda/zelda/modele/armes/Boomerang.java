package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.acteur.Monstre;
import com.zelda.zelda.modele.deplacement.Point2D;
import javafx.beans.property.SimpleIntegerProperty;
import com.zelda.zelda.modele.acteur.Link;

public  class Boomerang extends com.zelda.zelda.modele.armes.Arme {


    private BoomerangProjectile boomerangProjectile;

    public Boomerang (){
        super();
        this.degats=2;
        this.x = new SimpleIntegerProperty(1500);
        this.y = new SimpleIntegerProperty(500);
        this.nomPng="boomerang.png";
    }



    public void attaqueAvecArme(Monstre monstre){
        long currentTime = System.currentTimeMillis();
        //BoomerangProjectile boomerang = new BoomerangProjectile("boomerang.png");
        //Environnement.getInstance().getProjectiles().add(boomerang);
        if (Link.getInstance().isLinkAttaque() && ( this.boomerangProjectile == null || this.boomerangProjectile.boomerangEnMain)  ) {
            this.boomerangProjectile = new BoomerangProjectile("boomerang.png");
            this.boomerangProjectile.apparitionBoomerang(Link.getInstance().getDerniereDirection(),Link.getInstance());
        }

    }

}