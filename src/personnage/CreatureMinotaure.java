package personnage;

/**
 * Classe d'un minotaure.
 * 
 * Représente une créature minotaure dans le jeu.
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */

import physique.Position;

public class CreatureMinotaure extends AbstractCreature {

    /**
     * Constructeur du minotaure.
     * 
     * @param pos La position initiale du minotaure dans le labyrinthe.
     */
    public CreatureMinotaure(Position pos) {
        super(pos);
    }

    /**
     * Indique si le minotaure est vivant.
     * 
     * @return true si le minotaure est vivant, false sinon.
     */
    @Override
    public boolean isVivant() {
        return isVivant; // Champ protégé hérité d'AbstractPersonnage
    }
    
}
