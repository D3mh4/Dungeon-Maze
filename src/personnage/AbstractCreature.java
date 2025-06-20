package personnage;

import physique.Position;

public abstract class AbstractCreature extends AbstractPersonnage {

	public AbstractCreature(Position pos) {
		super(pos);
	}

	public abstract boolean isVivant();
}
