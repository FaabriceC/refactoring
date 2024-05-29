package com.zelda.zelda.modele;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.*;

public class Monstre extends Personnage{
    private IntegerProperty pv;

    public Monstre (int pv,int x,int y,String nom,Terrain t){
        super(x,y,nom,t);
        this.pv=new SimpleIntegerProperty(5);
        this.pv.setValue(5);

    }


    public void seDeplace(Link link , Monstre monstre) {
        int deplacementX = 0;
        int deplacementY = 0;
        if (monstre.vivant()){
            int lX = link.getX();
            int lY = link.getY();
            if (Math.abs(monstre.getX() - lX) < 128 && Math.abs(monstre.getY() - lY) < 128) {
                Queue<int[]> queue = new LinkedList<>();
                Set<String> visited = new HashSet<>();

                queue.add(new int[]{this.getX(), this.getY()});
                visited.add(this.getX() + "," + this.getY());

                int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

                Map<String, String> previous = new HashMap<>();

                boolean found = false;

                while (!queue.isEmpty() && !found) {
                    int[] current = queue.poll();
                    int currX = current[0];
                    int currY = current[1];

                    // Si le monstre atteint Link
                    if (currX == lX && currY == lY) {
                        found = true;
                        break;
                    }

                    for (int[] dir : directions) {
                        int newX = currX + dir[0];
                        int newY = currY + dir[1];

                        if (terrain.dansTerrain(newX, newY) && !visited.contains(newX + "," + newY) && !terrain.collisionAvecTuile(newX, newY, this)) {
                            queue.add(new int[]{newX, newY});
                            visited.add(newX + "," + newY);
                            previous.put(newX + "," + newY, currX + "," + currY);
                        }
                    }
                }

                if (found) {
                    String step = lX + "," + lY;
                    while (!step.equals(this.getX() + "," + this.getY())) {
                        String[] parts = step.split(",");
                        int tempX = Integer.parseInt(parts[0]);
                        int tempY = Integer.parseInt(parts[1]);

                        step = previous.get(step);

                        if (step.equals(this.getX() + "," + this.getY())) {
                            this.setX(tempX);
                            this.setY(tempY);
                            break;
                        }
                    }
                } else {
                    choisiDirectionAleatoire();
                    if (this.direction.getValue() == 2) {
                        deplacementX += 2;
                    } else if (this.direction.getValue() == 4) {
                        deplacementX -= 2;
                    } else if (this.direction.getValue() == 3) {
                        deplacementY += 2;
                    } else if (this.direction.getValue() == 1) {
                        deplacementY -= 2;
                    }


                    int newX = this.getX() + deplacementX;
                    int newY = this.getY() + deplacementY;
                    if (terrain.dansTerrain(newX, newY) && !terrain.collisionAvecTuile(newX, newY,monstre)) {
                        this.setX(newX);
                        this.setY(newY);
                    }
                }
            }
        }



    }

    public void choisiDirectionAleatoire(){
        if((int)(Math.random()*10) == 1){
            this.direction.setValue((int)((Math.random()*4)+1));
        }
    }


    public void attaque(Link link){
        if (Math.abs(this.getX() - link.getX()) < 32 && Math.abs(this.getY() - link.getY()) < 32){
            link.setPv(link.getPv()-1);
        }
    }

    public void setPv(int pv) {
        this.pv.set(pv);
    }

    public int getPv() {
        return pv.get();
    }

    public IntegerProperty pvProperty() {
        return pv;
    }

    public boolean vivant(){
        return this.pv.getValue()>0;
    }



}
