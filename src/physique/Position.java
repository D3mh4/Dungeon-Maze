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
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */
public class Position {

    private int i;  // Coordonnée colonne
    private int j;  // Coordonnée ligne
    
    /**
     * Constructeur qui initialise une position avec ses coordonnées.
     *
     * @param j, coordonnée ligne dans la grille
     * @param i, coordonnée colonne dans la grille
     */
    public Position(int j, int i) {
        this.i = i;
        this.j = j;
    }
    
    /**
     * Constructeur par copie d'une autre position.
     *
     * @param autrePosition, position à copier
     */
    public Position(Position autrePosition) {
        this.i = autrePosition.i;
        this.j = autrePosition.j;
    }

    /**
     * Retourne la coordonnée i (colonne).
     *
     * @return la coordonnée i
     */
    public int getI() {
        return i;
    }

    /**
     * Définit la coordonnée i (colonne).
     *
     * @param i, nouvelle valeur de la coordonnée i
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     * Retourne la coordonnée j (ligne).
     *
     * @return la coordonnée j
     */
    public int getJ() {
        return j;
    }

    /**
     * Définit la coordonnée j (ligne).
     *
     * @param j, nouvelle valeur de la coordonnée j
     */
    public void setJ(int j) {
        this.j = j;
    }
    
    /**
     * Additionne les coordonnées d'une autre position à cette position.
     *
     * @param pos, position dont les coordonnées seront additionnées
     */
    public void additionnerPos(Position pos) {
        // Ajoute les coordonnées i et j de pos à cette position
        this.i += pos.getI();
        this.j += pos.getJ();
    }
    
    /**
     * Soustrait les coordonnées d'une autre position à cette position.
     *
     * @param pos, position dont les coordonnées seront soustraites
     */
    public void soustrairePos(Position pos) {
        // Soustrait les coordonnées i et j de pos à cette position
        this.i -= pos.getI();
        this.j -= pos.getJ();
    }
    
    /**
     * Multiplie les coordonnées par des facteurs donnés.
     *
     * @param posI, facteur de multiplication pour la coordonnée i
     * @param posJ, facteur de multiplication pour la coordonnée j
     */
    public void multiplierPos(double posI, double posJ) {
        /* Multiplie i par posI et j par posJ
         * Cette méthode modifie directement la position courante */
        this.i *= posI;
        this.j *= posJ;
    }
    
    /**
     * Crée et retourne une copie de cette position.
     *
     * @return une nouvelle Position identique à cette position
     */
    @Override
    public Position clone() {
        // Renvoie une nouvelle instance avec les mêmes coordonnées
        return new Position(this);
    }
    
    /**
     * Vérifie si cette position est égale à un autre objet.
     *
     * @param obj, objet à comparer avec cette position
     * @return true si obj est une position avec les mêmes coordonnées, false sinon
     */
    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Position) {
            Position autrePosition = (Position)obj;
            // Compare les coordonnées i et j
            return (this.i == autrePosition.i && this.j == autrePosition.j);
        }
        return false;
    }
}
