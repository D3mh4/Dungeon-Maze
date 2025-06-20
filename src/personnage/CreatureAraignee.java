package personnage;

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
