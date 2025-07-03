package donjon;

/**
 * Génère et gère un donjon sous forme de labyrinthe.
 *
 * Le donjon est constitué d'une grille de cases, avec une case de départ
 * choisie aléatoirement et une case de fin située à l'extrémité du parcours.
 * Il est généré à l'aide d'un algorithme de backtracking (par pile).
 *
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Abdaoui
 * @version Été 2025 - TP1
 */

import physique.*;
import pile.*;

import java.util.Random;

public class Donjon {

    public static Random rand = new Random(); // Générateur de nombres aléatoires
    Case[][] cases; // Grille du donjon
    Case caseDepart; // Case de départ
    Case caseFin; // Case de fin
    int nbrLignes = Configuration.getInstance().getConfig(0); // Nombre de lignes du donjon
    int nbrColonnes = Configuration.getInstance().getConfig(1); // Nombre de colonnes du donjon

    /**
     * Constructeur de la classe Donjon.
     * Initialise la grille de cases, choisit la case de départ,
     * puis génère un labyrinthe en définissant les connexions.
     */
    public Donjon() {
        cases = new Case[nbrLignes][nbrColonnes];

        // Création de toutes les cases du donjon
        for (int i = 0; i < cases.length; i++)
            for (int j = 0; j < cases[i].length; j++)
                cases[j][i] = new Case(new Position(j, i));

        // Choix aléatoire de la case de départ
        caseDepart = cases[rand.nextInt(nbrLignes)][rand.nextInt(nbrColonnes)];

        // Génération du labyrinthe
        this.produireLabyrinthe();

        // Marque la dernière case atteinte comme case de fin
        this.caseFin.setFin(true);
    }

    /**
     * Retourne la case de fin du donjon.
     *
     * @return La case de fin.
     */
    public Case getCaseFin() {
        return caseFin;
    }

    /**
     * Retourne la grille de cases du donjon.
     *
     * @return Un tableau 2D contenant les cases.
     */
    public Case[][] getCases() {
        return cases;
    }

    /**
     * Retourne la case de départ du donjon.
     *
     * @return La case de départ.
     */
    public Case getCaseDepart() {
        return caseDepart;
    }

    /**
     * Retourne une position aléatoire valide dans la grille.
     *
     * @return Une position aléatoire dans les limites du donjon.
     */
    public Position getPositionAlea() {
        return new Position(rand.nextInt(nbrLignes), rand.nextInt(nbrColonnes));
    }

    /**
     * Calcule le nombre de voisins non développés autour d'une position.
     *
     * @param pos La position dont on veut analyser les voisins.
     * @return Le nombre de voisins non développés.
     */
    public int getNbVoisinsNonDeveloppe(Position pos) {
        int compteur = 0;
        Position voisine;

        for (int i = 0; i < 4; i++) {
            voisine = pos.clone();
            voisine.additionnerPos(Direction.directionAPosition(i));

            // Vérifie que la position voisine est dans les limites
            if (voisine.getI() < nbrColonnes && voisine.getI() >= 0 &&
                voisine.getJ() < nbrLignes && voisine.getJ() >= 0) {
                
                // Si la case n’a pas encore été développée, on l’ajoute au compteur
                if (!cases[voisine.getJ()][voisine.getI()].isDeveloppe()) {
                    compteur++;
                }
            }
        }

        return compteur;
    }

    /**
     * Retourne un voisin aléatoire valide d'une position donnée.
     *
     * @param pos La position de départ.
     * @return Une position voisine aléatoire valide.
     */
    public Position getVoisinAlea(Position pos) {
        Position voisine;

        do {
            voisine = pos.clone();
            voisine.additionnerPos(Direction.directionAPosition(rand.nextInt(4)));

        // Répète tant que la position voisine est invalide
        } while (voisine.getI() >= nbrColonnes || voisine.getI() < 0 ||
                 voisine.getJ() >= nbrLignes || voisine.getJ() < 0);

        return voisine;
    }

    /**
     * Retourne un voisin aléatoire non développé d'une position donnée.
     *
     * @param pos La position de départ.
     * @return Une position voisine non développée.
     */
    public Position getVoisinLibreAlea(Position pos) {
        Position voisine;

        do {
            do {
                voisine = pos.clone();
                voisine.additionnerPos(Direction.directionAPosition(rand.nextInt(4)));

            // Répète tant que la position est hors limites
            } while (voisine.getI() >= nbrColonnes || voisine.getI() < 0 ||
                     voisine.getJ() >= nbrLignes || voisine.getJ() < 0);

        // Répète tant que la case voisine a déjà été développée
        } while (cases[voisine.getJ()][voisine.getI()].isDeveloppe());

        return voisine;
    }

    /**
     * Génère un labyrinthe à partir de la case de départ
     * en utilisant une approche de backtracking avec une pile.
     * Les cases sont liées entre elles via leurs voisins.
     */
    public void produireLabyrinthe() {

        /* 
         * Algorithme de backtracking :
         * 1. On empile la case de départ
         * 2. Tant que la pile n'est pas vide :
         *    - On marque la case comme développée
         *    - Si elle a des voisins non développés :
         *        - Choisir un voisin libre aléatoire
         *        - Le relier dans les deux sens
         *        - L’empiler
         *        - Le considérer comme nouvelle fin
         *    - Sinon, on dépile
         */

        PileSChainee<Case> pile = new PileSChainee<Case>();
        pile.empiler(caseDepart);
        int cpt = 0;

        while (!pile.estVide()) {
            Case caseActuel = pile.regarder();
            Position posActuel = caseActuel.getPos();
            caseActuel.setDeveloppe(true);

            if (getNbVoisinsNonDeveloppe(posActuel) > 0) {

                // Trouve un voisin libre et non développé
                Position posVoisin = getVoisinLibreAlea(posActuel);
                Case caseVoisin = cases[posVoisin.getJ()][posVoisin.getI()];

                // Calcule la direction relative entre les deux cases
                posVoisin.soustrairePos(posActuel);

                // Lie la case actuelle à son voisin dans la bonne direction
                cases[caseActuel.getPos().getJ()][caseActuel.getPos().getI()]
                    .setVoisin(caseVoisin, Direction.positionADirection(posVoisin));

                // Lie le voisin à la case actuelle dans la direction opposée
                cases[caseVoisin.getPos().getJ()][caseVoisin.getPos().getI()]
                    .setVoisin(caseActuel, Direction.directionOpposee(Direction.positionADirection(posVoisin)));

                // Empile le voisin pour continuer la génération
                pile.empiler(caseVoisin);
                cpt++;

                // Met à jour la case de fin avec la dernière ajoutée
                this.caseFin = pile.regarder();
            } else {
                // Aucun voisin libre : on revient en arrière
                pile.depiler();
            }
        }
    }
}
