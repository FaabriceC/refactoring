package com.zelda.zelda.modele.dynamique;

import com.zelda.zelda.modele.Terrain;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class BlockDynamique {

    private IntegerProperty x = new SimpleIntegerProperty();
    private IntegerProperty y = new SimpleIntegerProperty();
    private String nom;
    private Terrain terrain;

    public BlockDynamique(int x, int y, String nom, Terrain terrain) {
        this.x.set(x);
        this.y.set(y);
        this.nom = nom;
        this.terrain = terrain;
    }

    public IntegerProperty xProperty() {
        return x;
    }

    public IntegerProperty yProperty() {
        return y;
    }

    public boolean enCollision(int x, int y) {
        boolean collision = x >= this.x.getValue() && x < this.x.getValue() + 32 && y >= this.y.getValue() && y < this.y.getValue() + 32;
        return collision;
    }

    public void bouge(int direction) {
        switch (direction) {
            case 1:
                y.set(y.getValue() - 1);
                break;
            case 2:
                x.set(x.getValue() + 1);
                break;
            case 3:
                y.set(y.getValue() + 1);
                break;
            case 4:
                x.set(x.getValue() - 1);
                break;
            default:
                break;
        }
    }

    public int getX() {
        return x.get();
    }

    public int getY() {
        return y.get();
    }
}
