package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;

public class Fleche extends Projectile {

    private int tempAvantDisparitionDeLaFleche;

    public Fleche(String nom,Arc arc) {
        super(nom,arc);
        tempAvantDisparitionDeLaFleche = 0;
    }


    public void apparitionFleche(int direction){
        if (tempAvantDisparitionDeLaFleche == 0){
            this.setxProjectile(this.getArme().getLink().getX());
            this.setyProjectile(this.getArme().getLink().getY());
            this.setDire(direction);
        }
    }



    public void flecheSeDeplace(Monstre monstre){
        this.flecheDepl(this.getDire(),monstre);
        this.flecheSeDeplaceSelonDirection(this.getDire());
        this.diparitionFleche();

    }



    public void flecheDepl(int direcrionFleche, Monstre monstre){
        if (monstreTouchable(direcrionFleche,monstre)) {
            this.getArme().faitDesDegatAuMonstre(monstre);
        }
    }

    public void flecheSeDeplaceSelonDirection(int direcrionFleche){
        if (direcrionFleche == 1) {
            this.setyProjectile(this.getyProjectile()-1);
        } else if (direcrionFleche == 2) {
            this.setxProjectile(this.getxProjectile() + 1);
        } else if (direcrionFleche == 3) {
            this.setyProjectile(this.getyProjectile() + 1);
        } else if (direcrionFleche == 4) {
            this.setxProjectile(this.getxProjectile() - 1);
        }
        if(direcrionFleche != 0){
            tempAvantDisparitionDeLaFleche = tempAvantDisparitionDeLaFleche+1;
        }
    }



    public boolean monstreTouchable(int direcrionFleche,Monstre monstre) {
        if (direcrionFleche == 1 || direcrionFleche == 3){
            return Math.abs(this.getyProjectile() - monstre.getY()) < 2  && Math.abs(this.getxProjectile()-monstre.getX()) < 32;
        } else {
            return Math.abs(this.getxProjectile() - monstre.getX()) < 2 && Math.abs(this.getyProjectile() - monstre.getY()) < 32;
        }
    }

    public void diparitionFleche(){
        if (tempAvantDisparitionDeLaFleche == 128){
            this.setxProjectileNull();
            this.setyProjectileNull();
            this.setDire(0);
            tempAvantDisparitionDeLaFleche = 0;

        }
    }


}
