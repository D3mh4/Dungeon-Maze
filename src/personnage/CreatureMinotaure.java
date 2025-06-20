package personnage;

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
