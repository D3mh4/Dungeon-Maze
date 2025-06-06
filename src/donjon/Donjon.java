package donjon;

import physique.Position;

import java.util.Random;

public class Donjon {

    public static Random rand = new Random();
    Case[][] cases;
    Case caseDepart;
    Case caseFin;
    int nbrLignes = Configuration.getInstance().getConfig(0);
    int nbrColonnes = Configuration.getInstance().getConfig(1);

    public Donjon() {
        cases= new Case[nbrLignes][nbrColonnes];
        caseDepart = cases[rand.nextInt(nbrLignes)][rand.nextInt(nbrColonnes)];
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

    public Position getPositionAlea(){
        return new Position(rand.nextInt(nbrLignes), rand.nextInt(nbrColonnes));

    }

    public void getNbVoisinsNonDeveloppe(physique.Position pos){
        int compteur = 0;

        for (int i = 0; i < 4; i++) {
            if(){

            }
        }
    }
}
