package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.acteur.Monstre;
import javafx.beans.property.SimpleIntegerProperty;
import com.zelda.zelda.modele.acteur.Link;

public  class Epee extends com.zelda.zelda.modele.armes.Arme {

    public Epee(){
        super();
        this.x = new SimpleIntegerProperty(1500);
        this.y = new SimpleIntegerProperty(400);
        this.degats=100;
        this.nom = "epee.png";
    }


    public void executerAttaque(Monstre monstre){
        long currentTime = System.currentTimeMillis();
        if(peutAttaquerDansDirection(Link.getInstance().getDerniereDirection(),monstre) && cooldown(currentTime,500)){
            this.infligerDegatsAuMonstre(monstre);
            if(monstre.peutReculerSelonDirection(Link.getInstance().getDerniereDirection())){
                this.reculerMonstreDansDirection(Link.getInstance().getDerniereDirection(),monstre);
            }


        }

    }





}