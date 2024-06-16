package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.deplacement.Point2D;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public  class Bouclier extends Arme {
  private int degats;




  public Bouclier (){
    this.degats=2;
    this.x = new SimpleIntegerProperty(1500);
    this.y = new SimpleIntegerProperty(500);
    this.nomPng = "bouclier.png";
  }



  public void seDéfendre(){

  }


  public void attaquer(){
  }




}