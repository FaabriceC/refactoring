package com.zelda.zelda.modele.armes;

import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;

public class Fleche extends Projectile {

    private int tempAvantDisparitionDeLaFleche;

    private static Fleche uniqueInstance=null;

    public Fleche(String nom) {
        super(nom);
        this.degat = 1;
        tempAvantDisparitionDeLaFleche = 0;
    }




    public void flecheAgit(){
        for (int i = 0; i < Environnement.getInstance().getPersonnageListe().size(); i++) {
            if (Environnement.getInstance().getPersonnageListe().get(i) instanceof Monstre) {
                Monstre m = (Monstre) Environnement.getInstance().getPersonnageListe().get(i);
                Fleche.getInstance().flecheAttaque(this.getDire(),m);
            }
        }

        Fleche.getInstance().ProjectileSeDeplaceSelonDirection(this.getDire());
        Fleche.getInstance().diparitionFleche();
    }

    public void apparitionFleche(int direction,Link link){
            Fleche.getInstance().setxProjectile(link.getX());
            Fleche.getInstance().setyProjectile(link.getY());
            Fleche.getInstance().setDire(direction);
            Fleche.getInstance().tempAvantDisparitionDeLaFleche = 0;
            Environnement.getInstance().getProjectiles().add(Fleche.getInstance());
    }


/*
    public void flecheSeDeplace(Monstre monstre){
        Fleche.getInstance().flecheAttaque(this.getDire(),monstre);
        Fleche.getInstance().flecheSeDeplaceSelonDirection(this.getDire());
        Fleche.getInstance().diparitionFleche();

    }

 */



    public void flecheAttaque(int direcrionFleche, Monstre monstre){
        if (monstreTouchable(direcrionFleche,monstre,Fleche.getInstance())) {
            Fleche.getInstance().faitDesDegatAuMonstre(monstre,Fleche.getInstance());
            if (!monstre.vivant()) {
                Environnement.getInstance().getPersonnageListe().remove(monstre);
            }
        }
    }

    public void ProjectileSeDeplaceSelonDirection(int directionProjectile){
        if (directionProjectile == 1) {
            Fleche.getInstance().setyProjectile(Fleche.getInstance().getyProjectile()-2);
        } else if (directionProjectile == 2) {
            Fleche.getInstance().setxProjectile(Fleche.getInstance().getxProjectile() + 2);
        } else if (directionProjectile == 3) {
            Fleche.getInstance().setyProjectile(Fleche.getInstance().getyProjectile() + 2);
        } else if (directionProjectile == 4) {
            Fleche.getInstance().setxProjectile(Fleche.getInstance().getxProjectile() - 2);
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
            Fleche.getInstance().setxProjectileNull();
            Fleche.getInstance().setyProjectileNull();
            Fleche.getInstance().setDire(0);
            tempAvantDisparitionDeLaFleche = 0;

            Environnement.getInstance().getProjectiles().remove(Fleche.getInstance());


        }
    }


    public static Fleche getInstance() {
        if(uniqueInstance==null) {
            uniqueInstance= new Fleche("arrows.png");
        }
        return uniqueInstance;
    }






/*
    public void faitDesDegatAuMonstre(Monstre monstre){
        monstre.setPv(monstre.getPv() - (Link.getInstance().getPointAttaque() + Fleche.getInstance().getDegat()));
        System.out.println(monstre.getPv());
    }

 */
}
