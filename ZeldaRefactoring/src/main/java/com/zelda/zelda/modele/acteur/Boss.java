package com.zelda.zelda.modele.acteur;

import com.zelda.zelda.modele.Terrain;

public class Boss extends Monstre {

    private boolean anim =false;

    public Boss(int x, int y, Terrain t){
        super(100,x,y,"slime3.gif",t);
    }

    public void seDeplace(Link link) {
        anim=false;
        if (Math.abs(link.getX() - this.getX()) < 128 && Math.abs(link.getY() - this.getY()) < 128) {
            if(!link.isInvisible())
                this.indicePasProperty().setValue(1);
            bfs.seDeplace(link);
            anim=true;
        }

        if (!anim){
            this.indicePasProperty().setValue(0);
        }

    }

    public void attaque(Link link){
        long currentTime = System.currentTimeMillis();
        /*
        if( currentTime - actionTime >= 1500 && Math.abs(link.getY()-this.getY())  < 16 && Math.abs(link.getX()-this.getX()) < 16){
            link.setPv(link.getPv()-1);
            actionTime = currentTime;
        }

         */

        if(this.direction.getValue() == 1 && currentTime - actionTime >= 2500 &&  this.getY()-link.getY() < 16 && link.getY()-this.getY() >= 0  && Math.abs(this.getX()-link.getX()) < 8 ){
            link.setPv(link.getPv()-1);
            actionTime = currentTime;
        }
        if(this.direction.getValue() == 2 && currentTime - actionTime >= 2500 &&  link.getX()-this.getX() < 16 && link.getX()-this.getX() >= 0 && Math.abs(this.getY()-link.getY()) < 8 ){
            link.setPv(link.getPv()-1);
            actionTime = currentTime;
        }
        if(this.direction.getValue() == 3 && currentTime - actionTime >= 2500 && link.getY()-link.getY() < 16 && link.getY()-this.getY() >= 0 && Math.abs(this.getX()-link.getX()) < 8){
            link.setPv(link.getPv()-1);
            actionTime = currentTime;
        }
        if(this.direction.getValue() == 4 && currentTime - actionTime >= 2500 && this.getX()-link.getX() < 16 && this.getX()-link.getX() >= 0 && Math.abs(this.getY()-link.getY()) < 8){
            link.setPv(link.getPv()-1);
            actionTime = currentTime;
        }

    }

    @Override
    public int[] margeErreur(int margeX, int margeY) {
        int[]marge=new int[2];
        switch (direction.getValue()) {


            case 4:
                margeX = 24;
                margeY= 24;
                break;
            case 2:
                margeX = 24;
                margeY=24;
                break;


            case 1:
                margeX = 24;
                margeY = 24;
                break;
            case 3:
                margeX = 24;
                margeY = 24;
                break;
        }
        marge[0]=margeX;
        marge[1]=margeY;



        return marge;
    }
}
