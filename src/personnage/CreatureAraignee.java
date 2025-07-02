package personnage;

/**
 * Classe d'une araignée
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */

import physique.Position;

public class CreatureAraignee extends AbstractCreature {

	public CreatureAraignee(Position pos) {
		super(pos);
	}
	
	@Override
	public void seDeplacer(int direction){
		super.seDeplacer(direction);
		super.seDeplacer(direction);
	}

	@Override
	public boolean isVivant() {
		return isVivant;
	}
	
}
