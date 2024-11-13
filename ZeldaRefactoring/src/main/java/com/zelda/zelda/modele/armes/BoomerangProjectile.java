package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;

public class BoomerangProjectile extends Projectile{

    private int tempsAventDisparitionDuBoomerang;

    public BoomerangProjectile(String nom) {
        super(nom);
        this.tempsAventDisparitionDuBoomerang = 0;
    }


    public void apparitionBoomerang(int direction,Link link){
        if (tempsAventDisparitionDuBoomerang == 0){
            this.setxProjectile(link.getX());
            this.setyProjectile(link.getY());
            this.setDire(direction);
        }
    }



    public void boomerangSeDeplace(Monstre monstre){
        this.boomerangDepl(this.getDire(),monstre);
        this.boomerangSeDeplaceSelonDirection(this.getDire());
        this.diparitionBoomerang();

    }



    public void boomerangDepl(int directionBoomerang, Monstre monstre){
        if (monstreTouchable(directionBoomerang,monstre)) {
            this.getArme().faitDesDegatAuMonstre(monstre);
        }
    }

    public void boomerangSeDeplaceSelonDirection(int directionBoomerang){
        if (    (directionBoomerang == 1 && tempsAventDisparitionDuBoomerang <=128)     ||  (directionBoomerang == 3 && tempsAventDisparitionDuBoomerang >128) ) {
            this.setyProjectile(this.getyProjectile()-1);
        } else if (     (directionBoomerang == 2 && tempsAventDisparitionDuBoomerang <=128 )   || (directionBoomerang == 4 && tempsAventDisparitionDuBoomerang >128 )) {
            this.setxProjectile(this.getxProjectile() + 1);
        } else if ((directionBoomerang == 3 && tempsAventDisparitionDuBoomerang <=128)    ||    (directionBoomerang == 1 && tempsAventDisparitionDuBoomerang >128)  ) {
            this.setyProjectile(this.getyProjectile() + 1);
        } else if ((directionBoomerang == 4 && tempsAventDisparitionDuBoomerang <=128)  || (directionBoomerang == 2 && tempsAventDisparitionDuBoomerang >128)) {
            this.setxProjectile(this.getxProjectile() - 1);
        }
        if(directionBoomerang != 0){
            tempsAventDisparitionDuBoomerang = tempsAventDisparitionDuBoomerang+1;
        }
    }



    public boolean monstreTouchable(int direcrionFleche,Monstre monstre) {
        if (direcrionFleche == 1 || direcrionFleche == 3){
            return Math.abs(this.getyProjectile() - monstre.getY()) < 2  && Math.abs(this.getxProjectile()-monstre.getX()) < 32;
        } else {
            return Math.abs(this.getxProjectile() - monstre.getX()) < 2 && Math.abs(this.getyProjectile() - monstre.getY()) < 32;
        }
    }

    public void diparitionBoomerang(){
        if (tempsAventDisparitionDuBoomerang == 256){
            this.setxProjectileNull();
            this.setyProjectileNull();
            this.setDire(0);
            tempsAventDisparitionDuBoomerang = 0;

        }
    }

}
