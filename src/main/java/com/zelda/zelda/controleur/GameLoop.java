package com.zelda.zelda.controleur;




import com.zelda.zelda.modele.Link;
import com.zelda.zelda.modele.Monstre;
import javafx.animation.AnimationTimer;


public class GameLoop extends AnimationTimer {
    // essayer de eviter ce systeme de delai et le faire via duration second


    private Link link;
    private Monstre monstre;
    private long dernierMouvement = 0;
    private long dernierMouvementSlime = 0;
    private final long DELAI_MOUVEMENT = 25_000_000;//100 000 000
    private final long DELAI_SLIME = 50_000_000;//100 000 000

    public GameLoop(Link link, Monstre monstre) {

        this.link = link;
        this.monstre = monstre;
    }



    @Override
    public void handle(long now) {
        if (now - this.dernierMouvement >= this.DELAI_MOUVEMENT) {
            rafraichirLink();
            this.dernierMouvement = now;
        }

        if (now - this.dernierMouvementSlime >= this.DELAI_SLIME) {
            rafraichirSlime();
            this.dernierMouvementSlime = now;
        }

    }

    // Méthode pour rafraîchir la position du personnage
    private void rafraichirLink() {

        link.seDeplace(link);
       // link.attaque(monstre);

    }
    private  void rafraichirSlime (){
        monstre.seDeplace(link,monstre);
        monstre.attaque(link);
    }
}