package com.zelda.zelda.modele.armes;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.deplacement.Point2D;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public  class Bouclier extends com.zelda.zelda.modele.armes.Arme {
  private int degats;




  public Bouclier (Link link){
    super(link);
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