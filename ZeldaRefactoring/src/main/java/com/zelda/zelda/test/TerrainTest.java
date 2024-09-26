//package com.zelda.zelda.test;
//import com.zelda.zelda.modele.Terrain;
//import com.zelda.zelda.modele.dynamique.BlockDynamique;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class TerrainTest {
//
//    @Test
//    void testTerrainInitialization() {
//        Terrain terrain = new Terrain("src/main/resources/com/zelda/zelda/MapZelda.json");
//        assertNotNull(terrain.getSolLayer(), "Sol layer should be initialized");
//    }
//
//    @Test
//    void testConvertTo2DArray() {
//        Terrain terrain = new Terrain("src/main/resources/com/zelda/zelda/MapZelda.json");
//        int[] flatArray = {1, 2, 3, 4, 5, 6};
//        int[][] expected = {
//                {1, 2, 3},
//                {4, 5, 6}
//        };
//        int[][] result = terrain.convertTo2DArray(flatArray, 2, 3);
//        assertArrayEquals(expected, result, "The conversion to 2D array is incorrect");
//    }
//
//    @Test
//    void testDansTerrain() {
//        Terrain terrain = new Terrain("src/main/resources/com/zelda/zelda/MapZelda.json");
//        assertTrue(terrain.dansTerrain(50, 50), "Point should be within terrain bounds");
//        assertFalse(terrain.dansTerrain(-1, 50), "Point should be outside terrain bounds");
//        assertFalse(terrain.dansTerrain(50, -1), "Point should be outside terrain bounds");
//    }
//
//    @Test
//    void testCollisionAvecTuile() {
//        Terrain terrain = new Terrain("src/main/resources/com/zelda/zelda/MapZelda.json");
//        // Set up mock terrain data
//        int[][] solLayer = {
//                {1, 9, 1},
//                {1, 1, 1}
//        };
//        terrain.solLayer = solLayer;
//        assertTrue(terrain.collisionAvecTuile(32, 0), "Should collide with tile");
//        assertFalse(terrain.collisionAvecTuile(0, 0), "Should not collide with tile");
//    }
//
//    @Test
//    void testAjouterBlocDynamique() {
//        Terrain terrain = new Terrain("src/main/resources/com/zelda/zelda/MapZelda.json");
//        BlockDynamique block = new BlockDynamique(0, 0,"There should be one dynamic block",terrain);
//        terrain.ajouterBlocDynamique(block);
//        assertEquals(1, terrain.getBlocsDynamiques().size(), "There should be one dynamic block");
//    }
//}
