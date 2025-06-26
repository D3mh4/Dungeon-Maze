package personnage;

import donjon.Case;
import physique.Position;

public class Joueur extends AbstractPersonnage {

	public Joueur(Position pos) {
		super(pos);
		
	}

	/**
	 * Sur-définition de la méthode seDépalcer pour afficher seulement les cases découvertes
	 *
	 * @param direction, entier qui représente la direction du déplacement
	 */
	@Override
	public void seDeplacer(int direction){
		super.seDeplacer(direction);
		caseCourante.setDecouverte(true);
		for (int i = 0; i < 4; i++) {
			if(caseCourante.getVoisin(i) != null){
				Case voisin = caseCourante.getVoisin(i);
				voisin.setDecouverte(true);
			}
		}
	}

}
