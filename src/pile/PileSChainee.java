package pile;

/**
 * Représente une pile générique implémentée avec une structure simplement chaînée.
 *
 * @param <T> le type des éléments à empiler
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Aabdaoui
 * @version Été 2025 - TP1
 */
public class PileSChainee<T> {

    /**
     * Représente un nœud de la pile.
     */
    private static class Noeud<E> {
        E valeur;      // Valeur stockée dans le nœud
        Noeud<E> suivant;  // Référence vers le nœud suivant

        /**
         * Constructeur du nœud.
         *
         * @param valeur, valeur contenue dans le nœud
         * @param suivant, référence vers le nœud suivant
         */
        Noeud(E valeur, Noeud<E> suivant) {
            this.valeur = valeur;
            this.suivant = suivant;
        }
    }

    private Noeud<T> sommet;  // Référence vers le sommet de la pile
    private int nbElements;   // Nombre d'éléments dans la pile

    /**
     * Crée une pile vide.
     */
    public PileSChainee() {
        this.sommet = null;
        this.nbElements = 0;
    }

    /**
     * Vérifie si la pile est vide.
     *
     * @return vrai si la pile ne contient aucun élément
     */
    public boolean estVide() {
        return sommet == null;
    }

    /**
     * Retourne le nombre d’éléments dans la pile.
     *
     * @return le nombre d’éléments
     */
    public int getNbElements() {
        return nbElements;
    }

    /**
     * Empile un nouvel élément sur la pile.
     *
     * @param element l’élément à empiler
     */
    public void empiler(T element) {
        // Création d'un nouveau nœud pointant vers l'ancien sommet
        sommet = new Noeud<>(element, sommet);
        nbElements++;
    }

    /**
     * Dépile l’élément au sommet de la pile.
     *
     * @return l’élément dépilé
     * @throws IllegalStateException si la pile est vide
     */
    public T depiler() {
        if (estVide()) {
            throw new IllegalStateException("Pile vide");
        }

        // Récupère la valeur au sommet puis supprime le sommet
        T valeur = sommet.valeur;
        sommet = sommet.suivant;
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
        return sommet.valeur;
    }
}
