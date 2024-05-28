package com.zelda.zelda.modele;




import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;




public class Personnage { //Crée un personnage
    private IntegerProperty x = new SimpleIntegerProperty();
    private IntegerProperty y = new SimpleIntegerProperty();
    protected Terrain terrain;
    private final String nom;
    protected IntegerProperty direction;
    protected IntegerProperty indicePas;

    private boolean statutPas;


    public Personnage(int x, int y, String nom, Terrain terrain) {
        this.x.set(x);
        this.y.set(y);
        this.nom = nom;
        this.terrain = terrain;
        this.direction = new SimpleIntegerProperty(0);
        this.indicePas= new SimpleIntegerProperty(0);



    }



    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public IntegerProperty directionProperty() {
        return direction;
    }

    public IntegerProperty indicePasProperty() {
        return indicePas;
    }

    public int[] margeErreur (int margeX,int margeY){
        int[]marge=new int[2];
        switch (direction.getValue()) {
            case 4:
                margeX = 10;
                margeY= 26;
                break;
            case 2:
                margeX = 26;
                margeY=26;
                break;
            case 1:
                margeX = 16;
                margeY = 26;
                break;
            case 3:
                margeX = 16;
                margeY = 26;
                break;
        }
        marge[0]=margeX;
        marge[1]=margeY;
        return marge;
    }



    public int getX() {
        return x.getValue();
    }



    public int getY() {
        return y.getValue();
    }



    public void setX(int x) {
        this.x.set(x);
    }

    public void setY(int y) {
        this.y.set(y);
    }

    public String getNom() {
        return nom;
    }

    public boolean isStatutPas() {
        return statutPas;
    }

    public void setStatutPas(boolean statutPas) {
        this.statutPas = statutPas;
    }
}


