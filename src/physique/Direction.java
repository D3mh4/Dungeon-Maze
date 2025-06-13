package physique;

/**
 * Classe utilitaire représentant les directions cardinales dans un labyrinthe.
 * 
 * Fournit des constantes pour représenter les directions (HAUT, BAS, GAUCHE, DROITE),
 * ainsi que des méthodes utilitaires pour :
 * - Obtenir la direction opposée à une direction donnée.
 * - Convertir une direction en déplacement de position.
 * - Déduire une direction à partir d'une différence de positions.
 * - Obtenir une direction aléatoire.
 * 
 * Utilisée pour naviguer entre les cases d'un labyrinthe dans {@link donjon.Donjon}.
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Abdaoui
 * @version Été 2025 - TP1
 */

import java.util.Random;

public class Direction {
	
	public static Random rand = new Random();
	
	public static final int HAUT = 0;
	public static final int BAS = 1;
	public static final int GAUCHE = 2;
	public static final int DROITE = 3;
	
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
				System.out.print("Erreur : la direction demandé n'est pas valide.");
				return -1;
		}
	}
	
	public static Position directionAPosition(int direction){
		switch(direction) {
			case HAUT :
				return new Position(-1,0);
			case BAS : 
				return new Position(1,0);
			case GAUCHE :
				return new Position(0,-1);
			case DROITE : 
				return new Position(0,1);
			default :
				System.out.print("Erreur : la direction demandé n'est pas valide.");
				return new Position(0,0);
		}
	}
	
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
			System.out.print("Erreur : la direction demandé n'est pas valide.");
			return -1;
		}
	}
	
	public static int obtenirDirAlea() {
		return rand.nextInt(4);
	}
	
	
	
}
