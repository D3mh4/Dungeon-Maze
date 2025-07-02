package personnage;

/**
 * Classe d'un dragon
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */

import physique.Position;

public class CreatureDragon extends AbstractCreature {

	public CreatureDragon(Position pos) {
		super(pos);
	}

	@Override
	public boolean isVivant() {
		return isVivant;
	}
	
}
