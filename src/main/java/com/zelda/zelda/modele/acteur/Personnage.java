package com.zelda.zelda.modele.acteur;



import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.Terrain;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;




public abstract class Personnage { //Crée un personnage
    private IntegerProperty x = new SimpleIntegerProperty();
    private IntegerProperty y = new SimpleIntegerProperty();
    protected Terrain terrain;
    private final String nom;
    protected IntegerProperty direction;
    protected IntegerProperty indicePas;
    private boolean statutPas;
    public static int compteur =0;
    private String id;

    protected Environnement env;//jsp


    public Personnage(int x, int y, String nom, Terrain terrain) {
        this.x.set(x);
        this.y.set(y);
        this.nom = nom;
        this.terrain = terrain;
        this.direction = new SimpleIntegerProperty(0);
        this.indicePas= new SimpleIntegerProperty(0);

        this.id="C"+ compteur;
        compteur++;

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

    public abstract int[] margeErreur(int margeX, int margeY);


    public abstract boolean peutSeDeplacer  (int tuileX, int tuileY);

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

    public String getId(){
        return id;
    }

}


