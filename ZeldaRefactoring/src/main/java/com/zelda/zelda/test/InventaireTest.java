//package com.zelda.zelda.test;
//import static org.junit.jupiter.api.Assertions.*;
//
//import com.zelda.zelda.modele.Consommable.Consommable;
//import com.zelda.zelda.modele.Inventaire;
//import com.zelda.zelda.modele.armes.Arme;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//public class InventaireTest {
//
//    private Inventaire inventaire;
//
//    @BeforeEach
//    public void setUp() {
//        inventaire = new Inventaire();
//    }
//
//    @Test
//    void Inventaire(){
//        ArrayList<Consommable> listeConso = new ArrayList<>();
//        ArrayList<Arme> listeArme = new ArrayList<>();
//        assertEquals(0,listeArme.size());
//        assertEquals(0,listeConso.size());
//    }
//    @Test
//    public void testAjouterArme() {
//        Arme arme = new Arme();
//        inventaire.ajouterArme(arme);
//        ArrayList<Arme> armes = inventaire.getInventaireArme();
//        assertEquals(1, armes.size());
//        assertTrue(armes.contains(arme));
//    }
//
//    @Test
//    public void testAjouterConsommable() {
//        Consommable consommable = new Consommable();
//        inventaire.ajouterConsommable(consommable);
//        ArrayList<Consommable> consommables = inventaire.getInventaireConsommable();
//        assertEquals(1, consommables.size());
//        assertTrue(consommables.contains(consommable));
//    }
//
//    @Test
//    public void testGetInventaireArme() {
//        Arme arme1 = new Arme();
//        Arme arme2 = new Arme();
//        inventaire.ajouterArme(arme1);
//        inventaire.ajouterArme(arme2);
//        ArrayList<Arme> armes = inventaire.getInventaireArme();
//        assertEquals(2, armes.size());
//        assertTrue(armes.contains(arme1));
//        assertTrue(armes.contains(arme2));
//    }
//
//    @Test
//    public void testGetInventaireConsommable() {
//        Consommable consommable1 = new Consommable();
//        Consommable consommable2 = new Consommable();
//        inventaire.ajouterConsommable(consommable1);
//        inventaire.ajouterConsommable(consommable2);
//        ArrayList<Consommable> consommables = inventaire.getInventaireConsommable();
//        assertEquals(2, consommables.size());
//        assertTrue(consommables.contains(consommable1));
//        assertTrue(consommables.contains(consommable2));
//    }
//
//    @Test
//    public void testGetTotalItems() {
//        Arme arme = new Arme();
//        Consommable consommable = new Consommable();
//        inventaire.ajouterArme(arme);
//        inventaire.ajouterConsommable(consommable);
//        int totalItems = inventaire.getTotalItems();
//        assertEquals(2, totalItems);
//    }
//}