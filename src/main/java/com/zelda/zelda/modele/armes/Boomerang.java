package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.acteur.Monstre;
import com.zelda.zelda.modele.deplacement.Point2D;
import javafx.beans.property.SimpleIntegerProperty;
import com.zelda.zelda.modele.acteur.Link;

public  class Boomerang extends com.zelda.zelda.modele.armes.Arme {

    private BoomerangProjectile boomerang;

    public Boomerang (Link link){
        super(link);
        this.degats=2;
        this.x = new SimpleIntegerProperty(1500);
        this.y = new SimpleIntegerProperty(500);
        this.nomPng="boomerang.png";
        this.boomerang = new BoomerangProjectile("boomerang.png",this);
    }



    public void attaqueAvecArme(Monstre monstre){
        long currentTime = System.currentTimeMillis();
        if (link.isLinkAttaque()) {
            boomerang.apparitionBoomerang(link.getDerniereDirection());
        }
        boomerang.boomerangSeDeplace(monstre);

    }

    public BoomerangProjectile getBoomerang() {
        return boomerang;
    }
}