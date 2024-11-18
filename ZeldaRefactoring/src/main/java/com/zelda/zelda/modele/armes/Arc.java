package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;
import javafx.beans.property.SimpleIntegerProperty;

public  class Arc extends com.zelda.zelda.modele.armes.Arme {

    private Fleche fleche;
    //private int tempAvantDisparitionDeLaFleche;


    public Arc (){
        super();
        this.degats=2;
        this.x = new SimpleIntegerProperty(1500);
        this.y = new SimpleIntegerProperty(550);
        this.nomPng="arc.png";

    }

    public void attaqueAvecArme(Monstre monstre){
        if (Link.getInstance().isLinkAttaque() && cooldown(currentTime,1000)) {

            Fleche fleche = new Fleche("arrows.png");
            fleche.apparait(Link.getInstance().getDerniereDirection(),Link.getInstance());

        }

    }

    public void setAZRE(){
        this.x.setValue(220);
    }

    public void setAZRE2(){
        this.y.setValue(220);
    }

}