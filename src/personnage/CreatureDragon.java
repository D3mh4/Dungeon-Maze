package personnage;

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
