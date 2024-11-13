package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.deplacement.Point2D;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import com.zelda.zelda.modele.acteur.Link;

public  class Epee extends com.zelda.zelda.modele.armes.Arme {

    private int degat;


    public Epee(Link link){
        super(link);
        this.x = new SimpleIntegerProperty(1500);
        this.y = new SimpleIntegerProperty(400);
        this.degats=100;
        this.nomPng = "epee.png";
    }







}