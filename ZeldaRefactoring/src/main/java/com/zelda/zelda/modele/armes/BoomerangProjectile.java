package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;

public class BoomerangProjectile extends Projectile{

    //private int tempsAventDisparitionDuBoomerang;
    private int tempsAvantRetourDuBoomerang;

    public boolean boomerangEnMain;

    private static BoomerangProjectile uniqueInstance=null;

    public BoomerangProjectile(String nom) {
        super(nom);
        this.degat = 2;
        //this.tempsAventDisparitionDuBoomerang = 0;
        this.tempsAvantRetourDuBoomerang = 0;
        this.boomerangEnMain = true;
    }


    public void apparitionBoomerang(int direction,Link link){
        if (boomerangEnMain){
            BoomerangProjectile.getInstance().boomerangEnMain = false;
            BoomerangProjectile.getInstance().setxProjectile(link.getX());
            BoomerangProjectile.getInstance().setyProjectile(link.getY());
            BoomerangProjectile.getInstance().setDire(direction);
            //BoomerangProjectile.getInstance().tempsAventDisparitionDuBoomerang = 0;
            //BoomerangProjectile.getInstance().boomerangEnMain = false;
            BoomerangProjectile.getInstance().tempsAvantRetourDuBoomerang = 0;
            Environnement.getInstance().getProjectiles().add(BoomerangProjectile.getInstance());
        }
    }



    public void boomerangAgit(){
        for (int i = 0; i < Environnement.getInstance().getPersonnageListe().size(); i++) {
            if (Environnement.getInstance().getPersonnageListe().get(i) instanceof Monstre) {
                Monstre m = (Monstre) Environnement.getInstance().getPersonnageListe().get(i);
                BoomerangProjectile.getInstance().boomerangAttaque(BoomerangProjectile.getInstance().getDire(),m);
            }
        }
        BoomerangProjectile.getInstance().ProjectileSeDeplaceSelonDirection(BoomerangProjectile.getInstance().getDire());
        BoomerangProjectile.getInstance().diparitionBoomerang();

    }



    public void boomerangAttaque(int directionBoomerang, Monstre monstre){
        if (monstreTouchable(directionBoomerang,monstre,BoomerangProjectile.getInstance())) {
            BoomerangProjectile.getInstance().faitDesDegatAuMonstre(monstre,BoomerangProjectile.getInstance());
            if (!monstre.vivant()) {
                Environnement.getInstance().getPersonnageListe().remove(monstre);
            }
        }
    }


    public void ProjectileSeDeplaceSelonDirection(int directionProjectile){
        if (    (directionProjectile == 1 && tempsAvantRetourDuBoomerang <128)) {
            BoomerangProjectile.getInstance().setyProjectile(BoomerangProjectile.getInstance().getyProjectile()-2);
        } else if (     (directionProjectile == 2 && tempsAvantRetourDuBoomerang <128 )) {
            BoomerangProjectile.getInstance().setxProjectile(BoomerangProjectile.getInstance().getxProjectile() + 2);
        } else if ((directionProjectile == 3 && tempsAvantRetourDuBoomerang <128)) {
            BoomerangProjectile.getInstance().setyProjectile(BoomerangProjectile.getInstance().getyProjectile() + 2);
        } else if ((directionProjectile == 4 && tempsAvantRetourDuBoomerang <128)) {
            BoomerangProjectile.getInstance().setxProjectile(BoomerangProjectile.getInstance().getxProjectile() - 2);
        }

        if (tempsAvantRetourDuBoomerang >= 128){
            if (Link.getInstance().getX() < BoomerangProjectile.getInstance().getxProjectile()){
                BoomerangProjectile.getInstance().setxProjectile(BoomerangProjectile.getInstance().getxProjectile() - 2);
            }
            if(Link.getInstance().getX() > BoomerangProjectile.getInstance().getxProjectile()){
                BoomerangProjectile.getInstance().setxProjectile(BoomerangProjectile.getInstance().getxProjectile() + 2);
            }
            if (Link.getInstance().getY() < BoomerangProjectile.getInstance().getyProjectile()){
                BoomerangProjectile.getInstance().setyProjectile(BoomerangProjectile.getInstance().getyProjectile() - 2);
            }

            if (Link.getInstance().getY() > BoomerangProjectile.getInstance().getyProjectile()){
                BoomerangProjectile.getInstance().setyProjectile(BoomerangProjectile.getInstance().getyProjectile() + 2);
            }


        }


        /*
        if(directionProjectile != 0){
            tempsAventDisparitionDuBoomerang = tempsAventDisparitionDuBoomerang+2;
        }

         */

        tempsAvantRetourDuBoomerang = tempsAvantRetourDuBoomerang +2;
    }



    /*
    public void ProjectileSeDeplaceSelonDirection(int directionProjectile){
        if (    (directionProjectile == 1 && tempsAventDisparitionDuBoomerang <=128)     ||  (directionProjectile == 3 && tempsAventDisparitionDuBoomerang >128) ) {
            BoomerangProjectile.getInstance().setyProjectile(BoomerangProjectile.getInstance().getyProjectile()-2);
        } else if (     (directionProjectile == 2 && tempsAventDisparitionDuBoomerang <=128 )   || (directionProjectile == 4 && tempsAventDisparitionDuBoomerang >128 )) {
            BoomerangProjectile.getInstance().setxProjectile(BoomerangProjectile.getInstance().getxProjectile() + 2);
        } else if ((directionProjectile == 3 && tempsAventDisparitionDuBoomerang <=128)    ||    (directionProjectile == 1 && tempsAventDisparitionDuBoomerang >128)  ) {
            BoomerangProjectile.getInstance().setyProjectile(BoomerangProjectile.getInstance().getyProjectile() + 2);
        } else if ((directionProjectile == 4 && tempsAventDisparitionDuBoomerang <=128)  || (directionProjectile == 2 && tempsAventDisparitionDuBoomerang >128)) {
            BoomerangProjectile.getInstance().setxProjectile(BoomerangProjectile.getInstance().getxProjectile() - 2);
        }
        if(directionProjectile != 0){
            tempsAventDisparitionDuBoomerang = tempsAventDisparitionDuBoomerang+2;
        }
    }

     */

/*
    public boolean monstreTouchable(int direcrionFleche,Monstre monstre) {
        if (direcrionFleche == 1 || direcrionFleche == 3){
            return Math.abs(this.getyProjectile() - monstre.getY()) < 2  && Math.abs(this.getxProjectile()-monstre.getX()) < 32;
        } else {
            return Math.abs(this.getxProjectile() - monstre.getX()) < 2 && Math.abs(this.getyProjectile() - monstre.getY()) < 32;
        }
    }

 */

    public void diparitionBoomerang(){
        if (!boomerangEnMain && BoomerangProjectile.getInstance().getxProjectile() == Link.getInstance().getX() && BoomerangProjectile.getInstance().getyProjectile() == Link.getInstance().getY()){
            BoomerangProjectile.getInstance().setxProjectileNull();
            BoomerangProjectile.getInstance().setyProjectileNull();
            BoomerangProjectile.getInstance().setDire(0);
            //tempsAventDisparitionDuBoomerang = 0;
            BoomerangProjectile.getInstance().boomerangEnMain = true;
            Environnement.getInstance().getProjectiles().add(BoomerangProjectile.getInstance());

        }
    }

    public static BoomerangProjectile getInstance() {
        if(uniqueInstance==null) {
            uniqueInstance= new BoomerangProjectile("boomerang.png");
        }
        return uniqueInstance;
    }

}
