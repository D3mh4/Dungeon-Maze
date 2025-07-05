package physique;

import java.util.Random;

/**
 * Classe utilitaire représentant les directions cardinales.
 *
 * Fournit des constantes pour les directions HAUT, BAS, GAUCHE, DROITE,
 * ainsi que des méthodes pour obtenir la direction opposée, 
 * convertir une direction en déplacement de position,
 * déduire une direction à partir d'une position,
 * et obtenir une direction aléatoire.
 *
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */
public class Direction {
	
	public static Random rand = new Random();
	
	public static final int HAUT = 0;
	public static final int BAS = 1;
	public static final int GAUCHE = 2;
	public static final int DROITE = 3;
	
	/**
	 * Retourne la direction opposée à celle donnée.
	 *
	 * @param direction, la direction dont on veut l'opposée
	 * @return la direction opposée (HAUT <-> BAS, GAUCHE <-> DROITE), ou -1 si invalide
	 */
	public static int directionOpposee(int direction) {
		switch(direction) {
			case HAUT : 
				return BAS;
			case BAS :
				return HAUT;
			case GAUCHE :
				return DROITE;
			case DROITE :
				return GAUCHE;
			default : 
				// Affiche un message d'erreur si la direction est invalide
				System.out.print("Erreur : la direction demandé n'est pas valide.");
				return -1;
		}
	}
	
	/**
	 * Convertit une direction en déplacement de position.
	 *
	 * @param direction, direction parmi HAUT, BAS, GAUCHE, DROITE
	 * @return une Position représentant le déplacement correspondant
	 */
	public static Position directionAPosition(int direction){
		switch(direction) {
			case HAUT :
				// Déplacement vers la ligne au-dessus
				return new Position(-1,0);
			case BAS : 
				// Déplacement vers la ligne en dessous
				return new Position(1,0);
			case GAUCHE :
				// Déplacement vers la colonne de gauche
				return new Position(0,-1);
			case DROITE : 
				// Déplacement vers la colonne de droite
				return new Position(0,1);
			default :
				// Direction invalide, retourne (0,0) sans déplacement
				System.out.print("Erreur : la direction demandé n'est pas valide.");
				return new Position(0,0);
		}
	}
	
	/**
	 * Déduit la direction à partir d'une différence de position.
	 *
	 * @param pos, différence de position (delta ligne j, delta colonne i)
	 * @return la direction correspondante, ou -1 si invalide
	 */
	public static int positionADirection(Position pos) {
		if(pos.getJ() == -1 && pos.getI() == 0) {
			return HAUT;
		} else if(pos.getJ()==1 && pos.getI()==0) {
			return BAS;
		} else if(pos.getJ()==0 && pos.getI()==-1) {
			return GAUCHE;
		} else if(pos.getJ()==0 && pos.getI()==1) {
			return DROITE;
		} else {
			// Erreur si la position ne correspond pas à une direction valide
			System.out.print("Erreur : la direction demandé n'est pas valide.");
			return -1;
		}
	}
	
	/**
	 * Retourne une direction aléatoire parmi HAUT, BAS, GAUCHE, DROITE.
	 *
	 * @return un entier entre 0 et 3 inclus représentant la direction
	 */
	public static int obtenirDirAlea() {
		return rand.nextInt(4);
	}
}
