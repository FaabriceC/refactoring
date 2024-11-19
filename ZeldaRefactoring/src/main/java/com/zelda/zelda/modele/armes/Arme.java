package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.Inventaire;
import com.zelda.zelda.modele.Item;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;
import com.zelda.zelda.modele.acteur.Personnage;

public abstract class Arme extends Item {

    protected int degats;
    long actionTime = 0L;

    public abstract void executerAttaque(Monstre monstre);

    public boolean peutAttaquerDansDirection(int direction, Monstre monstre){
        switch(direction){
            case 1: return Link.getInstance().getY()-monstre.getY() < 32 && Link.getInstance().getY()-monstre.getY() >= -1  && Math.abs(Link.getInstance().getX()-monstre.getX()) < 16;
            case 2: return monstre.getX()-Link.getInstance().getX() < 32 && monstre.getX()-Link.getInstance().getX() >= -1  && Math.abs(Link.getInstance().getY()-monstre.getY()) < 16;
            case 3: return monstre.getY()-Link.getInstance().getY() < 32 && monstre.getY()-Link.getInstance().getY() >= -1  && Math.abs(Link.getInstance().getX()-monstre.getX()) < 16;
            default: return Link.getInstance().getX()-monstre.getX() < 32 && Link.getInstance().getX()-monstre.getX() >= -1 && Math.abs(Link.getInstance().getY()-monstre.getY()) < 16;
        }
    }


    public void reculerMonstreDansDirection(int direction, Personnage personnage) {
        switch (direction) {
            case 1 -> personnage.setY(personnage.getY() - 32);
            case 2 -> personnage.setX(personnage.getX() + 32);
            case 3 -> personnage.setY(personnage.getY() + 32);
            case 4 -> personnage.setX(personnage.getX() - 32);
        }
    }

    public void infligerDegatsAuMonstre(Monstre monstre){
        monstre.setPv(monstre.getPv() - (Link.getInstance().getPointAttaque() + Link.getInstance().getArmeEquipe().getDegats()));
        monstre.setSubitDegat(true);
    }

    public void ajouterInventaire(Inventaire inventaire) {
        inventaire.getInventaireArme().add(this);}


    public int getDegats(){
        return degats;
    }

    public boolean cooldown(long currentTime,int cooldown){
        if (currentTime - actionTime >= cooldown){
            actionTime = currentTime;
            return true;

        } else{
            return false;
        }
    }

}