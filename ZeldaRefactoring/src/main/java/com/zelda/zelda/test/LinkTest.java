//import static org.junit.jupiter.api.Assertions.*;
//
//import com.zelda.zelda.modele.Terrain;
//import com.zelda.zelda.modele.acteur.Link;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//public class LinkTest {
//
//    private Terrain terrain;
//    private Link link;
//
//    @BeforeEach
//    public void setUp() {
//        terrain = new Terrain(); // Initialisez votre objet Terrain
//        link = new Link("Link", 0, 0, terrain);
//    }
//
//    @Test
//    public void testSeDeplace() {
//        // Test de déplacement vers le bas
//        link.setDirection(3); // Direction vers le bas
//        link.seDeplace();
//        assertEquals(2, link.getY());
//
//        // Test de déplacement vers la droite
//        link.setDirection(2); // Direction vers la droite
//        link.seDeplace();
//        assertEquals(2, link.getX());
//    }
//}
