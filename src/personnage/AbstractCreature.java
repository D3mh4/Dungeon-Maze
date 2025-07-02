package personnage;

/**
 * Classe abstraite d'une créature d'un jeu
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */

import physique.Position;

public abstract class AbstractCreature extends AbstractPersonnage {

	public AbstractCreature(Position pos) {
		super(pos);
	}

	public abstract boolean isVivant();
}
