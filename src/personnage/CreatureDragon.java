package personnage;

/**
 * Classe d'un dragon.
 * 
 * Représente une créature dragon dans le jeu.
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */

import physique.Position;

public class CreatureDragon extends AbstractCreature {

    /**
     * Constructeur du dragon.
     * 
     * @param pos La position initiale du dragon dans le labyrinthe.
     */
    public CreatureDragon(Position pos) {
        super(pos);
    }

    /**
     * Indique si le dragon est vivant.
     * 
     * @return true si le dragon est vivant, false sinon.
     */
    @Override
    public boolean isVivant() {
        return isVivant; // Champ protégé hérité d'AbstractPersonnage
    }
    
}
