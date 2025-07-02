package personnage;

/**
 * Classe d'un minotaure
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */

import physique.Position;

public class CreatureMinotaure extends AbstractCreature {

	public CreatureMinotaure(Position pos) {
		super(pos);
	}

	@Override
	public boolean isVivant() {
		return isVivant;
	}
	
}
