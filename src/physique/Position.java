package physique;

/**
 * Représente une position dans une grille bidimensionnelle (ligne j, colonne i).
 * 
 * Fournit des méthodes pour :
 * - Accéder et modifier les coordonnées.
 * - Effectuer des opérations vectorielles de base : addition, soustraction, multiplication.
 * - Cloner et comparer des positions.
 * 
 * Utilisée notamment pour représenter les coordonnées des cases dans un labyrinthe.
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Abdaoui
 * @version Été 2025 - TP1
 */

public class Position {

	private int i;
	private int j;
	
	public Position(int j, int i) {
		this.i = i;
		this.j = j;
	}
	
	public Position(Position autrePosition) {
		this.i = autrePosition.i;
		this.j = autrePosition.j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
	public void additionnerPos(Position pos) {
		this.i += pos.getI();
		this.j += pos.getJ();
	}
	
	public void soustrairePos(Position pos) {
		this.i -= pos.getI();
		this.j -= pos.getJ();
	}
	
	public void multiplierPos(double posI, double posJ) {
		this.i *= posI;
		this.j *= posJ;
	}
	
	@Override
	public Position clone() {
		return new Position(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Position) {
			Position autrePosition = (Position)obj;
			return (this.i == autrePosition.i && this.j == autrePosition.j);
		}
		return false;
	}
}
