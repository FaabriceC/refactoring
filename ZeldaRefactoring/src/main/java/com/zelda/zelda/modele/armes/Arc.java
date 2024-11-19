package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;
import javafx.beans.property.SimpleIntegerProperty;

public  class Arc extends Arme {

    public Arc (){
        super();
        this.degats=2;
        this.x = new SimpleIntegerProperty(1500);
        this.y = new SimpleIntegerProperty(550);
        this.nom = "arc.png";
    }

    public void executerAttaque(Monstre monstre){
        long currentTime = System.currentTimeMillis();
        if (cooldown(currentTime,1000)) {
            Fleche fleche = new Fleche("arrows.png");
            fleche.apparait(Link.getInstance().getDerniereDirection(),Link.getInstance());

        }

    }

}