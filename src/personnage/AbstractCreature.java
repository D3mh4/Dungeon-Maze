package personnage;

/**
 * Classe abstraite d'une créature d'un jeu.
 *
 * Une créature est un type de personnage ayant une position,
 * et qui peut être vivante ou non. Cette classe est conçue pour être héritée.
 *
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */

import physique.Position;

public abstract class AbstractCreature extends AbstractPersonnage {

	/**
	 * Constructeur d'une créature.
	 *
	 * @param pos La position initiale de la créature dans le jeu.
	 */
	public AbstractCreature(Position pos) {
		super(pos); // Appelle le constructeur de AbstractPersonnage
	}

	/**
	 * Vérifie si la créature est toujours en vie.
	 *
	 * @return true si la créature est vivante, false sinon.
	 */
	public abstract boolean isVivant(); // Méthode à implémenter dans les sous-classes
}
