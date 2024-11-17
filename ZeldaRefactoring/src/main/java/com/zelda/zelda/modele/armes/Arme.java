package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.Inventaire;
import com.zelda.zelda.modele.Item;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;
import com.zelda.zelda.modele.acteur.Personnage;

public abstract class Arme extends Item {

    protected int degats;
    long actionTime = 0L;

    public void attaque(Monstre monstre){
        long currentTime = System.currentTimeMillis();
        if(attaquePossibleSelonDirection(Link.getInstance().getDerniereDirection(),monstre,currentTime)){
            this.faitDesDegatAuMonstre(monstre);
            if(monstre.peutReculerSelonDirection(Link.getInstance().getDerniereDirection())){
                this.faitReculerMonstreSelonDirection(Link.getInstance().getDerniereDirection(),monstre);
            }

            actionTime = currentTime;

        }

    }

    public boolean attaquePossibleSelonDirection(int direction, Personnage personnage,long currentTime){
        if (direction == 1 ){
            return /*currentTime - actionTime >= 500 && */Link.getInstance().getY()-personnage.getY() < 32 && Link.getInstance().getY()-personnage.getY() >= -1  && Math.abs(Link.getInstance().getX()-personnage.getX()) < 16;
        } else if (direction == 2) {
            return /* currentTime - actionTime >= 500 &&*/  personnage.getX()-Link.getInstance().getX() < 32 && personnage.getX()-Link.getInstance().getX() >= -1  && Math.abs(Link.getInstance().getY()-personnage.getY()) < 16;
        } else if (direction == 3){
            return personnage.getY()-Link.getInstance().getY() < 32 && personnage.getY()-Link.getInstance().getY() >= -1  && Math.abs(Link.getInstance().getX()-personnage.getX()) < 16;
        } else {
            return Link.getInstance().getX()-personnage.getX() < 32 && Link.getInstance().getX()-personnage.getX() >= -1 && Math.abs(Link.getInstance().getY()-personnage.getY()) < 16;
        }
    }


    public void faitReculerMonstreSelonDirection(int direction, Personnage personnage) {
        switch (direction) {
            case 1 -> personnage.setY(personnage.getY() - 32);
            case 2 -> personnage.setX(personnage.getX() + 32);
            case 3 -> personnage.setY(personnage.getY() + 32);
            case 4 -> personnage.setX(personnage.getX() - 32);
        }
    }


    public void faitDesDegatAuMonstre(Monstre monstre){
        monstre.setPv(monstre.getPv() - (Link.getInstance().getPointAttaque() + Link.getInstance().getArmeEquipe().getDegats()));
        monstre.setSubitDegat(true);
    }

    public void ajouterInventaire(Inventaire inventaire) {
        inventaire.getInventaireArme().add(this);
    }

    public int getDegats() {
        return degats;
    }


}