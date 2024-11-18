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
        for (int j = 0;j<Environnement.getInstance().getProjectiles().size();j++){
            if(Environnement.getInstance().getProjectiles().get(j) instanceof Fleche){
                Fleche f =(Fleche) Environnement.getInstance().getProjectiles().get(j);
                f.flecheAgit();
            }
        }
    }


    public void flecheAgit(){
        for (int i = 0; i < Environnement.getInstance().getPersonnageListe().size(); i++) {
            if (Environnement.getInstance().getPersonnageListe().get(i) instanceof Monstre) {
                Monstre m = (Monstre) Environnement.getInstance().getPersonnageListe().get(i);
                this.flecheAttaque(this.getDire(),m);
            }
        }
        this.ProjectileSeDeplaceSelonDirection(this.getDire());
        this.diparitionFleche();
    }

    public void apparitionFleche(int direction,Link link){
        this.setxProjectile(link.getX());
        this.setyProjectile(link.getY());
        this.setDire(direction);
        this.tempAvantDisparitionDeLaFleche = 0;
        Environnement.getInstance().getProjectiles().add(this);
    }


/*
    public void flecheSeDeplace(Monstre monstre){
        Fleche.getInstance().flecheAttaque(this.getDire(),monstre);
        Fleche.getInstance().flecheSeDeplaceSelonDirection(this.getDire());
        Fleche.getInstance().diparitionFleche();

    }

 */



    public void flecheAttaque(int direcrionFleche, Monstre monstre){
        if (monstreTouchable(direcrionFleche,monstre,this)) {
            this.faitDesDegatAuMonstre(monstre,this);
            System.out.println( "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
            if (!monstre.vivant()) {
                Environnement.getInstance().getPersonnageListe().remove(monstre);
            }
        }
    }

    public void ProjectileSeDeplaceSelonDirection(int directionProjectile){
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


/*
    public boolean monstreTouchable(int direcrionFleche,Monstre monstre) {
        if (direcrionFleche == 1 || direcrionFleche == 3){
            return Math.abs(Fleche.getInstance().getyProjectile() - monstre.getY()) < 2  && Math.abs(Fleche.getInstance().getxProjectile()-monstre.getX()) < 32;
        } else {
            return Math.abs(Fleche.getInstance().getxProjectile() - monstre.getX()) < 2 && Math.abs(Fleche.getInstance().getyProjectile() - monstre.getY()) < 32;
        }
    }
    */


    public void diparitionFleche(){
        if (tempAvantDisparitionDeLaFleche == 128){
            this.setxProjectileNull();
            this.setyProjectileNull();
            this.setDire(0);
            tempAvantDisparitionDeLaFleche = 0;

            Environnement.getInstance().getProjectiles().remove(this);


        }
    }









/*
    public void faitDesDegatAuMonstre(Monstre monstre){
        monstre.setPv(monstre.getPv() - (Link.getInstance().getPointAttaque() + Fleche.getInstance().getDegat()));
        System.out.println(monstre.getPv());
    }

 */
}
