package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.Inventaire;
import com.zelda.zelda.modele.Item;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;
import javafx.beans.property.IntegerProperty;

public abstract class Arme extends Item {

    protected int degats;
    long actionTime = 0L;

    public void attaque(Monstre monstre){
        long currentTime = System.currentTimeMillis();
        if(currentTime - actionTime >= 500 && Link.getInstance().getDerniereDirection() == 1 && Link.getInstance().getY()-monstre.getY() < 32 && Link.getInstance().getY()-monstre.getY() >= -1  && Math.abs(Link.getInstance().getX()-monstre.getX()) < 16 ){
            faitDesDegatAuMonstre(monstre);
            if(monstre.peutSeDeplacer(monstre.getX(),monstre.getY()-32)){
                monstre.setY(monstre.getY()-32);
            }
            actionTime = currentTime;

        }
        if(currentTime - actionTime >= 500 && Link.getInstance().getDerniereDirection() == 2 && monstre.getX()-Link.getInstance().getX() < 32 && monstre.getX()-Link.getInstance().getX() >= -1  && Math.abs(Link.getInstance().getY()-monstre.getY()) < 16 ){
            faitDesDegatAuMonstre(monstre);
            if(monstre.peutSeDeplacer(monstre.getX()+32,monstre.getY())){
                monstre.setX(monstre.getX()+32);
            }
            actionTime = currentTime;

        }
        if(currentTime - actionTime >= 500 && Link.getInstance().getDerniereDirection() == 3 && monstre.getY()-Link.getInstance().getY() < 32 && monstre.getY()-Link.getInstance().getY() >= -1  && Math.abs(Link.getInstance().getX()-monstre.getX()) < 16 ){
            faitDesDegatAuMonstre(monstre);
            if(monstre.peutSeDeplacer(monstre.getX(),monstre.getY()+32)){
                monstre.setY(monstre.getY()+32);
            }
            actionTime = currentTime;

        }
        if(currentTime - actionTime >= 500 && Link.getInstance().getDerniereDirection() == 4 && Link.getInstance().getX()-monstre.getX() < 32 && Link.getInstance().getX()-monstre.getX() >= -1 && Math.abs(Link.getInstance().getY()-monstre.getY()) < 16 ){
            faitDesDegatAuMonstre(monstre);
            if(monstre.peutSeDeplacer(monstre.getX()-32,monstre.getY())){
                monstre.setX(monstre.getX()-32);
            }
            actionTime = currentTime;

        }

    }

    public void faitDesDegatAuMonstre(Monstre monstre) {
        monstre.setPv(monstre.getPv() - (Link.getInstance().getPointAttaque() + Link.getInstance().getArmeEquipe().getDegats()));
        monstre.setSubitDegats(true);
    }

    public void ajouterInventaire(Inventaire inventaire) {
        inventaire.getInventaireArme().add(this);
    }

    public int getDegats() {return degats;}


}