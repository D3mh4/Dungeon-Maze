package donjon;

/**
 * Représente un donjon sous forme de labyrinthe généré aléatoirement.
 * 
 * Cette classe génère un labyrinthe en utilisant une pile et une approche
 * de parcours en profondeur (DFS). Chaque case du labyrinthe est liée à ses
 * voisines développées, formant un graphe connexe sans cycles (arbre couvrant).
 * 
 * Fonctionnalités principales :
 * - Génération d'un labyrinthe procédural.
 * - Sélection aléatoire d'une case de départ et d'une case de fin.
 * - Accès à des informations utiles comme les cases, la position aléatoire,
 *   et les voisins non développés.
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @version Été 2025 - TP1
 */

import physique.*;
import pile.*;

import java.util.Random;

public class Donjon {

    public static Random rand = new Random();
    Case[][] cases;
    Case caseDepart;
    Case caseFin;
    int nbrLignes = Configuration.getInstance().getConfig(0);
    int nbrColonnes = Configuration.getInstance().getConfig(1);

    public Donjon() {
        cases = new Case[nbrLignes][nbrColonnes];

        for (int i = 0; i < cases.length; i++)
            for (int j = 0; j < cases[i].length; j++)
                cases[j][i] = new Case(new Position(j, i));

        caseDepart = cases[rand.nextInt(nbrLignes)][rand.nextInt(nbrColonnes)];

        this.produireLabyrinthe();
        this.caseFin.setFin(true);
    }

    public Case getCaseFin() {
        return caseFin;
    }

    public Case[][] getCases() {
        return cases;
    }

    public Case getCaseDepart() {
        return caseDepart;
    }

    public Position getPositionAlea() {
        return new Position(rand.nextInt(nbrLignes), rand.nextInt(nbrColonnes));
    }

    public int getNbVoisinsNonDeveloppe(Position pos) {
        int compteur = 0;
        Position voisine;

        for (int i = 0; i < 4; i++) {
            voisine = pos.clone();
            voisine.additionnerPos(Direction.directionAPosition(i));

            if (voisine.getI() < nbrColonnes && voisine.getI() >= 0 &&
                voisine.getJ() < nbrLignes && voisine.getJ() >= 0) {
                if (!cases[voisine.getJ()][voisine.getI()].isDeveloppe()) {
                    compteur++;
                }
            }
        }

        return compteur;
    }

    public Position getVoisinAlea(Position pos) {
        Position voisine;

        do {
            voisine = pos.clone();
            voisine.additionnerPos(Direction.directionAPosition(rand.nextInt(4)));

        } while (voisine.getI() >= nbrColonnes || voisine.getI() < 0 ||
                 voisine.getJ() >= nbrLignes || voisine.getJ() < 0);

        return voisine;
    }

    public Position getVoisinLibreAlea(Position pos) {
        Position voisine;

        do {
            do {
                voisine = pos.clone();
                voisine.additionnerPos(Direction.directionAPosition(rand.nextInt(4)));

            } while (voisine.getI() >= nbrColonnes || voisine.getI() < 0 ||
                     voisine.getJ() >= nbrLignes || voisine.getJ() < 0);

        } while (cases[voisine.getJ()][voisine.getI()].isDeveloppe());

        return voisine;
    }

    public void produireLabyrinthe() {

        PileSChainee<Case> pile = new PileSChainee<Case>();
        pile.empiler(caseDepart);
        int cpt = 0;

        while (!pile.estVide()) {
            Case caseActuel = pile.regarder();
            Position posActuel = caseActuel.getPos();
            caseActuel.setDeveloppe(true);

            if (getNbVoisinsNonDeveloppe(posActuel) > 0) {

                Position posVoisin = getVoisinLibreAlea(posActuel);
                Case caseVoisin = cases[posVoisin.getJ()][posVoisin.getI()];

                posVoisin.soustrairePos(posActuel);

                cases[caseActuel.getPos().getJ()][caseActuel.getPos().getI()]
                    .setVoisin(caseVoisin, Direction.positionADirection(posVoisin));
                cases[caseVoisin.getPos().getJ()][caseVoisin.getPos().getI()]
                    .setVoisin(caseActuel, Direction.directionOpposee(Direction.positionADirection(posVoisin)));

                pile.empiler(caseVoisin);
                cpt++;
                this.caseFin = pile.regarder();
            } else {
                pile.depiler();
            }
        }

    }
}
