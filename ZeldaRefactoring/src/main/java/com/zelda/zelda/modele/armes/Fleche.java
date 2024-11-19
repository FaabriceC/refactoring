package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;

public class Fleche extends Projectile {

    private int tempAvantDisparitionDeLaFleche;


    public Fleche(String nom) {
        super(nom);
        this.degat = 1;
        tempAvantDisparitionDeLaFleche = 0;
    }


    public static void flecheAgits(){
        for (int j = 0; j< Environnement.getInstance().getProjectiles().size(); j++){
            if(Environnement.getInstance().getProjectiles().get(j) instanceof Fleche){
                Fleche f =(Fleche) Environnement.getInstance().getProjectiles().get(j);
                f.agit();
            }
        }
    }

    public void agit(){
        for (int i = 0; i < Environnement.getInstance().getPersonnageListe().size(); i++) {
            if (Environnement.getInstance().getPersonnageListe().get(i) instanceof Monstre) {
                Monstre m = (Monstre) Environnement.getInstance().getPersonnageListe().get(i);
                this.infligerDegatsAuMonstre(this.getDire(),m);
            }
        }
        this.seDeplaceSelonDirection(this.getDire());
        this.disparait();
    }

    public void apparait(int direction,Link link){
        this.setxProjectile(link.getX());
        this.setyProjectile(link.getY());
        this.setDire(direction);
        this.tempAvantDisparitionDeLaFleche = 0;
        Environnement.getInstance().getProjectiles().add(this);
    }


    public void infligerDegatsAuMonstre(int direcrionFleche, Monstre monstre){
        if (peutToucherMonstre(direcrionFleche,monstre,this)) {
            this.faitDesDegatAuMonstre(monstre,this);
            if (monstre.estMort()) {
                Environnement.getInstance().getPersonnageListe().remove(monstre);
            }
        }
    }

    public void seDeplaceSelonDirection(int directionProjectile){
        if (directionProjectile == 1) {
            this.setyProjectile(this.getyProjectile()-2);
        } else if (directionProjectile == 2) {
            this.setxProjectile(this.getxProjectile() + 2);
        } else if (directionProjectile == 3) {
            this.setyProjectile(this.getyProjectile() + 2);
        } else if (directionProjectile == 4) {
            this.setxProjectile(this.getxProjectile() - 2);
        }
        if(directionProjectile != 0){
            tempAvantDisparitionDeLaFleche = tempAvantDisparitionDeLaFleche+2;
        }
    }

    public void disparait(){
        if (tempAvantDisparitionDeLaFleche == 128){
            this.setxProjectileNull();
            this.setyProjectileNull();
            this.setDire(0);
            tempAvantDisparitionDeLaFleche = 0;
            Environnement.getInstance().getProjectiles().remove(this);

        }
    }


}
