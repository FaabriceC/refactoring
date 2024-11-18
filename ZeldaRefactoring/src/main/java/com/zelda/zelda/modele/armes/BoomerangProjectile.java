package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;

public class BoomerangProjectile extends Projectile{

    //private int tempsAventDisparitionDuBoomerang;
    private int tempsAvantRetourDuBoomerang;

    public boolean boomerangEnMain;

    //private static BoomerangProjectile uniqueInstance=null;

    public BoomerangProjectile(String nom) {
        super(nom);
        this.degat = 2;
        //this.tempsAventDisparitionDuBoomerang = 0;
        this.tempsAvantRetourDuBoomerang = 0;
        this.boomerangEnMain = true;
    }


    public void apparitionBoomerang(int direction,Link link){
        if (boomerangEnMain){
            this.boomerangEnMain = false;
            this.setxProjectile(link.getX());
            this.setyProjectile(link.getY());
            this.setDire(direction);
            //BoomerangProjectile.getInstance().tempsAventDisparitionDuBoomerang = 0;
            //BoomerangProjectile.getInstance().boomerangEnMain = false;
            this.tempsAvantRetourDuBoomerang = 0;
            Environnement.getInstance().getProjectiles().add(this);
        }
    }



    public static void boomerangAgits(){
        for (int j = 0;j<Environnement.getInstance().getProjectiles().size();j++){
            if(Environnement.getInstance().getProjectiles().get(j) instanceof BoomerangProjectile){
                BoomerangProjectile b =(BoomerangProjectile) Environnement.getInstance().getProjectiles().get(j);
                b.boomerangAgit();
            }
        }
    }




    public void boomerangAgit(){
        for (int i = 0; i < Environnement.getInstance().getPersonnageListe().size(); i++) {
            if (Environnement.getInstance().getPersonnageListe().get(i) instanceof Monstre) {
                Monstre m = (Monstre) Environnement.getInstance().getPersonnageListe().get(i);
                this.faitDesDegatAuMonstre(this.getDire(),m);
            }
        }
        this.SeDeplaceSelonDirection(this.getDire());
        this.disparait();

    }



    public void faitDesDegatAuMonstre(int directionBoomerang, Monstre monstre){
        if (peutToucherMonstre(directionBoomerang,monstre,this)) {
            this.faitDesDegatAuMonstre(monstre,this);
            System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb");
            if (!monstre.vivant()) {
                Environnement.getInstance().getPersonnageListe().remove(monstre);
            }
        }
    }


    public void SeDeplaceSelonDirection(int directionProjectile){
        if (    (directionProjectile == 1 && tempsAvantRetourDuBoomerang <128)) {
            this.setyProjectile(this.getyProjectile() - 6);
        } else if (     (directionProjectile == 2 && tempsAvantRetourDuBoomerang <128 )) {
            this.setxProjectile(this.getxProjectile() + 6);
        } else if ((directionProjectile == 3 && tempsAvantRetourDuBoomerang <128)) {
            this.setyProjectile(this.getyProjectile() + 6);
        } else if ((directionProjectile == 4 && tempsAvantRetourDuBoomerang <128)) {
            this.setxProjectile(this.getxProjectile() - 6);
        }

        if (tempsAvantRetourDuBoomerang >= 128){
            if (Link.getInstance().getX() < this.getxProjectile()){
                this.setxProjectile(this.getxProjectile() - 6);
            }
            if(Link.getInstance().getX() > this.getxProjectile()){
                this.setxProjectile(this.getxProjectile() + 6);
            }
            if (Link.getInstance().getY() < this.getyProjectile()){
                this.setyProjectile(this.getyProjectile() - 6);
            }

            if (Link.getInstance().getY() > this.getyProjectile()){
                this.setyProjectile(this.getyProjectile() + 6);
            }


        }


        /*
        if(directionProjectile != 0){
            tempsAventDisparitionDuBoomerang = tempsAventDisparitionDuBoomerang+2;
        }

         */

        if(directionProjectile != 0){
            tempsAvantRetourDuBoomerang = tempsAvantRetourDuBoomerang+4;
        }

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

    public void disparait(){
        if (!boomerangEnMain && Math.abs( this.getxProjectile() - Link.getInstance().getX()) <16 && Math.abs( this.getyProjectile() - Link.getInstance().getY()) <16 && tempsAvantRetourDuBoomerang>=128){
            this.setxProjectileNull();
            this.setyProjectileNull();
            this.setDire(0);
            //tempsAventDisparitionDuBoomerang = 0;
            this.boomerangEnMain = true;
            Environnement.getInstance().getProjectiles().remove(this);

        }
    }

    /*
    public static BoomerangProjectile getInstance() {
        if(uniqueInstance==null) {
            uniqueInstance= new BoomerangProjectile("boomerang.png");
        }
        return uniqueInstance;
    }

     */

    public boolean isBoomerangEnMain() {
        return boomerangEnMain;
    }
}
