package donjon;

/**
 * Représente une case dans le labyrinthe.
 *
 * Chaque case possède une position, des états (découverte, développée, fin),
 * et des références vers ses voisins dans les quatre directions cardinales.
 *
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Abdaoui
 * @version Été 2025 - TP1
 */

import physique.Position;

public class Case {
	
	private Position pos; // Position de la case dans le labyrinthe
	private boolean decouverte; // Indique si la case a été découverte
	private boolean fin; // Indique si la case est la case de fin
	private boolean developpe; // Indique si la case a été traitée par l'algorithme
	private Case[] voisins; // Tableau des voisins de la case : [0]=haut, [1]=bas, [2]=gauche, [3]=droite
	
	/**
	 * Constructeur de la case.
	 *
	 * @param pos La position de la case dans le labyrinthe.
	 */
	public Case(Position pos) {
		this.pos = pos;
		decouverte = false;  // Initialement non découverte
		fin = false;         // Non marquée comme fin
		developpe = false;   // Non traitée
		voisins = new Case[4]; // Initialise les voisins à null
	}
	
	/**
	 * Retourne une copie de la position de la case.
	 *
	 * @return La position de la case.
	 */
	public Position getPos() {
		return pos.clone(); // On retourne une copie pour éviter les effets de bord
	}
	
	/**
	 * Vérifie si la case a été découverte.
	 *
	 * @return true si la case est découverte, false sinon.
	 */
	public boolean isDecouverte() {
		return decouverte;
	}

	/**
	 * Définit si la case est découverte ou non.
	 *
	 * @param decouverte true pour marquer la case comme découverte, false sinon.
	 */
	public void setDecouverte(boolean decouverte) {
		this.decouverte = decouverte;
	}

	/**
	 * Vérifie si la case est une case de fin.
	 *
	 * @return true si c'est une case de fin, false sinon.
	 */
	public boolean getFin() {
		return fin;
	}

	/**
	 * Définit si la case est une case de fin.
	 *
	 * @param fin true pour indiquer que la case est une case de fin, false sinon.
	 */
	public void setFin(boolean fin) {
		this.fin = fin;
	}

	/**
	 * Vérifie si la case a été développée par l'algorithme.
	 *
	 * @return true si développée, false sinon.
	 */
	public boolean isDeveloppe() {
		return developpe;
	}

	/**
	 * Définit si la case a été développée.
	 *
	 * @param developpe true si la case est marquée comme développée, false sinon.
	 */
	public void setDeveloppe(boolean developpe) {
		this.developpe = developpe;
	}
	
	/**
	 * Définit un voisin de la case dans une direction donnée.
	 *
	 * @param voisin La case voisine.
	 * @param direction L'indice correspondant à la direction (0: haut, 1: bas, 2: gauche, 3: droite).
	 */
	public void setVoisin(Case voisin, int direction) {
		voisins[direction] = voisin; // Affecte le voisin à la direction spécifiée
	}
	
	/**
	 * Retourne le voisin de la case dans une direction donnée.
	 *
	 * @param direction L'indice correspondant à la direction (0: haut, 1: bas, 2: gauche, 3: droite).
	 * @return La case voisine dans la direction spécifiée.
	 */
	public Case getVoisin(int direction) {
		return voisins[direction];
	}
	
	/**
	 * Retourne une représentation textuelle de la case,
	 * incluant sa position, son état et ses voisins.
	 *
	 * @return Une chaîne décrivant la case.
	 */
	@Override
	public String toString() {
	    String[] voisinsPos = new String[4];

	    // Parcourt les 4 directions pour afficher la position de chaque voisin
	    for (int i = 0; i < 4; i++) {
	        if (voisins[i] != null && voisins[i].pos != null) {
	            voisinsPos[i] = String.format("{%d,%d}", voisins[i].pos.getJ(), voisins[i].pos.getI());
	        } else {
	            voisinsPos[i] = "{null}"; // Aucun voisin dans cette direction
	        }
	    }

	    // Retourne les informations complètes sur la case
	    return String.format(
	        "Position : {%d,%d}, Decouverte ? %b, Fin ? %b, Developpé par l'alghorithme ? %b, Haut : %s, Bas : %s, Gauche : %s, Droite : %s",
	        pos.getJ(), pos.getI(), decouverte, fin, developpe,
	        voisinsPos[0], voisinsPos[1], voisinsPos[2], voisinsPos[3]
	    );
	}
}
