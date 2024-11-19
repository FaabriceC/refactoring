package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.acteur.Monstre;
import javafx.beans.property.SimpleIntegerProperty;
import com.zelda.zelda.modele.acteur.Link;

public  class Boomerang extends com.zelda.zelda.modele.armes.Arme {

    private BoomerangProjectile boomerangProjectile;

    public Boomerang (){
        super();
        this.degats=2;
        this.x = new SimpleIntegerProperty(1500);
        this.y = new SimpleIntegerProperty(500);
        this.nom = "boomerang.png";
    }


    public void executerAttaque(Monstre monstre){
        if ((this.boomerangProjectile == null || this.boomerangProjectile.boomerangEnMain) ) {
            this.boomerangProjectile = new BoomerangProjectile("boomerang.png");
            this.boomerangProjectile.apparait(Link.getInstance().getDerniereDirection(),Link.getInstance());
        }
    }

}