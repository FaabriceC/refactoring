package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.deplacement.Point2D;
import javafx.beans.property.SimpleIntegerProperty;

public  class Arc extends Arme {




    public Arc (){

        this.degats=2;
        this.x = new SimpleIntegerProperty(1500);
        this.y = new SimpleIntegerProperty(550);
        this.nomPng="arc.png";
    }

    public void attaquer(){
    }

    public void setAZRE(){
        this.x.setValue(220);
    }

    public void setAZRE2(){
        this.y.setValue(220);
    }



}