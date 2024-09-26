package com.zelda.zelda.modele.deplacement;

import com.zelda.zelda.modele.acteur.Link;
import com.zelda.zelda.modele.acteur.Monstre;
import com.zelda.zelda.modele.Terrain;

import java.util.*;

public class BFS {
    private Monstre monstre;
    private Terrain terrain;

    //private List<int[]> chemin; // Liste pour stocker le chemin
    private List<int[]> chemin; // Liste pour stocker le chemin
    private int cheminIndex = 0; // Index pour suivre la progression dans le chemin

    private int nbTours;

    private boolean enMarche = false;


    public BFS(Monstre monstre, Terrain t) {
        this.monstre = monstre;
        this.terrain = t;
        this.nbTours = 0;
    }



    public void seDeplace(Link link) {
        boolean laB = link.getLinkABouger();
        boolean dircAl = monstre.isDircAlea();


        // Si le chemin n'est pas défini ou si le monstre a atteint la fin du chemin, recalculer le chemin
        if (chemin == null || cheminIndex >= chemin.size() || nbTours % 10 == 0 || dircAl ) {
            recalculerChemin(link);
        }

        if (monstre.isMonsSubitDegat()) {
            if (this.nbTours % 10 == 0) {
                if (chemin != null && cheminIndex < chemin.size()) {
                    int[] prochaineEtape = chemin.get(cheminIndex);
                    if (prochaineEtape[0] < monstre.getX()) {
                        monstre.setDirectionValue(4);
                    } else if (prochaineEtape[0] > monstre.getX()) {
                        monstre.setDirectionValue(2);
                    } else if (prochaineEtape[1] > monstre.getY()) {
                        monstre.setDirectionValue(3);
                    } else if (prochaineEtape[1] > monstre.getY()) {
                        monstre.setDirectionValue(1);
                    }
                    monstre.setX(prochaineEtape[0]);
                    monstre.setY(prochaineEtape[1]);
                    cheminIndex++;
                    monstre.setMonsSubitDegat(false);
                }
            }
            this.nbTours++;
        }
        else {
            // Si un chemin a été trouvé, déplacer le monstre d'une étape
            if (chemin != null && cheminIndex < chemin.size()) {
                int[] prochaineEtape = chemin.get(cheminIndex);
                if (prochaineEtape[0] < monstre.getX()) {
                    monstre.setDirectionValue(4);
                } else if (prochaineEtape[0] > monstre.getX()) {
                    monstre.setDirectionValue(2);
                } else if (prochaineEtape[1] > monstre.getY()) {
                    monstre.setDirectionValue(3);
                } else if (prochaineEtape[1] > monstre.getY()) {
                    monstre.setDirectionValue(1);
                }
                monstre.setX(prochaineEtape[0]);
                monstre.setY(prochaineEtape[1]);
                cheminIndex++;
            }
            this.nbTours++;
        }
    }


    private void recalculerChemin (Link link){
        Point2D l = new Point2D(link.getX(), link.getY());
        Point2D m = new Point2D(monstre.getX(), monstre.getY());
        Point2D haut = new Point2D(0, 1);
        Point2D bas = new Point2D(1, 0);
        Point2D gauche = new Point2D(0, -1);
        Point2D droite = new Point2D(-1, 0);


        // BFS nécessite une file d'attente pour les positions à visiter
        Queue<Point2D> queue = new LinkedList<>();
        Set<Point2D> visited = new HashSet<>();

        // Ajouter la position de départ de Link à la file d'attente
        queue.add(new Point2D(l.getPointX(), l.getPointY()));
        visited.add(l);

        // Directions pour se déplacer (haut, bas, gauche, droite)
        Point2D[] directions = {haut, bas, gauche, droite};

        // Carte des précédents pour reconstruire le chemin
        Map<Point2D, Point2D> previous = new HashMap<>();

        boolean found = false;

        while (!queue.isEmpty() && !found) {
            Point2D current = queue.poll();
            int currX = current.getPointX();
            int currY = current.getPointY();

            // Si Link atteint la position du monstre
            if (currX == m.getPointX() && currY == m.getPointY()) {
                found = true;
                break;
            }

            // Explorer les voisins
            for (Point2D dir : directions) {


                int newX = currX + dir.getPointX();
                int newY = currY + dir.getPointY();

                Point2D nXY = new Point2D(newX,newY);


                // Vérifier les limites du terrain et si la position est visitée ou bloquée
                if (terrain.dansTerrain(newX, newY) && !visited.contains(nXY) && monstre.peutSeDeplacer(newX,newY)) {
                    queue.add(nXY);
                    visited.add(nXY);
                    previous.put(nXY, new Point2D(currX, currY));
                }
            }
        }

        // Si on a trouvé la position du monstre, reconstruire le chemin
        if (found) {
            chemin = new ArrayList<>();
            Point2D step = m;
            while (!step.equals(l)) {
                int tempX = step.getPointX();
                int tempY = step.getPointY();

                chemin.add(new int[]{tempX, tempY});
                step = previous.get(step);
            }
            cheminIndex = 0;
        } else {
            chemin = null;
        }
    }


}
