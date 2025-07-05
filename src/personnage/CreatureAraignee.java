package personnage;

/**
 * Classe d'une araignée.
 * 
 * Représente une créature araignée qui se déplace deux fois plus vite que les autres.
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */

import physique.Position;

public class CreatureAraignee extends AbstractCreature {

    /**
     * Constructeur de l'araignée.
     * 
     * @param pos La position initiale de l'araignée dans le labyrinthe.
     */
    public CreatureAraignee(Position pos) {
        super(pos);
    }
    
    /**
     * Déplace l'araignée dans une direction donnée.
     * L'araignée effectue un déplacement en deux cases.
     * 
     * @param direction La direction du déplacement.
     */
    @Override
    public void seDeplacer(int direction) {
        super.seDeplacer(direction); // Premier déplacement
        super.seDeplacer(direction); // Deuxième déplacement
    }

    /**
     * Indique si l'araignée est vivante.
     * 
     * @return true si l'araignée est vivante, false sinon.
     */
    @Override
    public boolean isVivant() {
        return isVivant; // Champ protégé hérité d'AbstractPersonnage
    }
    
}
