package pile;

/**
 * Représente une pile implémentée avec une structure simplement chaînée.
 *
 * @param <T> le type des éléments à empiler
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */
public class PileSChainee<T> {
    protected Noeud<T> tete;  // Référence vers la tête de la pile
    protected int nbElements; // Nombre d'éléments dans la pile
    
    /**
     * Représente un nœud de la pile.
     */
    private static class Noeud<T> {
        T valeur;          // Valeur stockée dans le nœud
        Noeud<T> suivant;  // Référence vers le nœud suivant

        /**
         * Constructeur du nœud.
         *
         * @param valeur, valeur contenue dans le nœud
         */
        public Noeud(T valeur) {
            this.valeur = valeur;
            this.suivant = null;
        }
    }

    /**
     * Crée une pile vide.
     */
    public PileSChainee() {
        this.tete = null;
        this.nbElements = 0;
    }

    /**
     * Vérifie si la pile est vide.
     *
     * @return vrai si la pile ne contient aucun élément
     */
    public boolean estVide() {
    	return nbElements == 0;
    }

    /**
     * Empile un nouvel élément sur la pile.
     *
     * @param element l’élément à empiler
     */
    public void empiler(T element) {
        Noeud<T> nouveau = new Noeud<>(element);
        nouveau.suivant = tete;  // On rattache le nouveau sommet à l'ancien
        tete = nouveau;          // On met à jour la tête
        nbElements++;
    }

    /**
     * Dépile le dernier élément de la pile.
     *
     * @return l’élément dépilé
     * @throws IllegalStateException si la pile est vide
     */
    public T depiler() {
        if (estVide()) {
            throw new IllegalStateException("Pile vide");
        }

        // Récupère la valeur à la tête de la pile puis le supprime
        T valeur = tete.valeur;
        tete = tete.suivant;
        nbElements--;
        return valeur;
    }

    /**
     * Retourne l’élément au sommet sans le dépiler.
     *
     * @return l’élément au sommet
     * @throws IllegalStateException si la pile est vide
     */
    public T regarder() {
        if (estVide()) {
            throw new IllegalStateException("Pile vide");
        }

        // Retourne la valeur au sommet sans la supprimer
        return tete.valeur;
    }
}
