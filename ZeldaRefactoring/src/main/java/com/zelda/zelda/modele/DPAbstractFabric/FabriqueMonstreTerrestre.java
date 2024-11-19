package com.zelda.zelda.modele.DPAbstractFabric;

import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.acteur.Boss;
import com.zelda.zelda.modele.acteur.Monstre;
import com.zelda.zelda.modele.acteur.Slime;

public class FabriqueMonstreTerrestre implements FabriqueMonstre{

    public FabriqueMonstreTerrestre(){}


    @Override
    public void creeMonstre() {
        Monstre slime = new Slime(800,472);
        Environnement.getInstance().ajouter(slime);
        Environnement.getInstance().ajouterListeMonstre(slime);
    }

    @Override
    public void creeBoss() {
        Boss boss = new Boss(850,472);
        Environnement.getInstance().ajouter(boss);
        Environnement.getInstance().ajouterListeMonstre(boss);

    }
}
