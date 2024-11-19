package com.zelda.zelda.modele.Pattern.AbstractFabric;

import com.zelda.zelda.modele.Environnement;
import com.zelda.zelda.modele.acteur.ArbreMonstre;
import com.zelda.zelda.modele.acteur.Boss;
import com.zelda.zelda.modele.acteur.Monstre;

public class FabriqueMonstreAerien implements FabriqueMonstre {


    @Override
    public void creeMonstre() {
        Monstre arbreMonstre = new ArbreMonstre(900,472);
        Environnement.getInstance().getPersonnageListe().add(arbreMonstre);
    }

    @Override
    public void creeBoss() {
        Boss boss = new Boss(950,472);
        Environnement.getInstance().getPersonnageListe().add(boss);
    }
}
