package com.zelda.zelda.util;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TileSetCutter {
    public static void main(String[] args) {
        // Chemin vers le tileset
        String imagePath = "src/main/java/com/zelda/zelda/util/tuile.png";
        // Dossier de sortie pour les tuiles
        String outputDir = "src/main/resources/com/zelda/zelda/map";

        // Taille des tuiles
        int tileWidth = 32;
        int tileHeight = 32;

        try {
            // Lire l'image
            BufferedImage image = ImageIO.read(new File(imagePath));

            // Créer le dossier de sortie s'il n'existe pas
            File dir = new File(outputDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Parcourir l'image et découper les tuiles
            int imageWidth = image.getWidth();
            int imageHeight = image.getHeight();
            int tileIndex = 0;

            for (int y = 0; y < imageHeight; y += tileHeight) {
                for (int x = 0; x < imageWidth; x += tileWidth) {
                    // Découper une tuile
                    BufferedImage tile = image.getSubimage(x, y, tileWidth, tileHeight);
                    // Sauvegarder la tuile
                    File outputFile = new File(outputDir, "tile_" + tileIndex + ".png");
                    ImageIO.write(tile, "png", outputFile);
                    tileIndex++;
                }
            }

            System.out.println("Tileset découpé avec succès!");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

