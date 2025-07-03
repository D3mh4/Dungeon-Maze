package personnage;

/**
 * Classe d'un joueur.
 * 
 * Représente le personnage joueur dans le jeu.
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */

import donjon.Case;
import physique.Position;

public class Joueur extends AbstractPersonnage {

    /**
     * Constructeur du joueur.
     * 
     * @param pos La position initiale du joueur dans le labyrinthe.
     */
    public Joueur(Position pos) {
        super(pos);
    }

    /**
     * Sur-définition de la méthode seDéplacer pour afficher seulement les cases découvertes.
     * 
     * Après déplacement, marque la case courante et ses voisins directs comme découverts.
     * 
     * @param direction Entier représentant la direction du déplacement.
     */
    @Override
    public void seDeplacer(int direction){
        super.seDeplacer(direction); // Effectue le déplacement de base

        caseCourante.setDecouverte(true); // Marque la case actuelle comme découverte

        // Parcourt les 4 directions autour pour révéler les cases voisines
        for (int i = 0; i < 4; i++) {
            if(caseCourante.getVoisin(i) != null){
                Case voisin = caseCourante.getVoisin(i);
                voisin.setDecouverte(true); // Découvre le voisin
            }
        }
    }

}
