package donjon;

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
        cases= new Case[nbrLignes][nbrColonnes];
        
        for(int i = 0; i < cases.length; i++)
        	for (int j = 0; j < cases[i].length; j++)
        		cases[i][j] = new Case(new Position(i,j));
        
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

    public Position getPositionAlea(){
        return new Position(rand.nextInt(nbrLignes), rand.nextInt(nbrColonnes));

    }

    // à tester plus tard
    public int getNbVoisinsNonDeveloppe(Position pos){
        int compteur = 0;
        Position voisine;
        
        for (int i = 0; i < 4; i++) {
        	voisine = pos.clone();
        	voisine.additionnerPos(Direction.directionAPosition(i));
        	
            if(voisine.getI() < 10 && voisine.getI() >= 0 && voisine.getJ() < 10 && voisine.getJ() >= 0){
            	if(!cases[voisine.getJ()][voisine.getI()].isDeveloppe())
            		compteur++;
            }
        }
        
        return compteur;
    }
    
    public Position getVoisinAlea(Position pos) {
    	Position voisine;
    	
    	do {
    		voisine = pos.clone();
    		voisine.additionnerPos(Direction.directionAPosition(rand.nextInt(4)));
    		
    		System.out.printf("posI de voisine : %d, posJ de voisine : %d\n", voisine.getI(), voisine.getJ());
    		
    	} while(voisine.getI() >= 10 || voisine.getI() < 0 || voisine.getJ() >= 10 || voisine.getJ() < 0);
    	
		return voisine;
    }
    
    // à tester plus tard
    public Position getVoisinLibreAlea(Position pos) {
    	Position voisine;
    	
    	do {
    		
	    	do {
	    		voisine = pos.clone();
	    		voisine.additionnerPos(Direction.directionAPosition(rand.nextInt(4)));
	    		
	    	} while(voisine.getI() >= 10 || voisine.getI() < 0 || voisine.getJ() >= 10 || voisine.getJ() < 0);
	    	
    	} while (cases[voisine.getJ()][voisine.getI()].isDeveloppe());
    	
		return voisine;
    }
    
    public void produireLabyrinthe() {
    	
    	PileSChainee<Case> pile = new PileSChainee<Case>(nbrLignes*nbrColonnes);
    	pile.empiler(caseDepart);
    	
    	while(!pile.estVide()) {
    		Case caseActuel = pile.regarder();
    		Position posActuel = caseActuel.getPosition();
    		caseActuel.setDeveloppe(true);
    		
    		if(getNbVoisinsNonDeveloppe(posActuel) > 0) {
    			Position posVoisin = getVoisinLibreAlea(posActuel);
    			Case caseVoisin = cases[posVoisin.getJ()][posVoisin.getI()];
    			posVoisin.soustrairePos(posActuel);
    			
    			cases[caseActuel.getPosition().getJ()][caseActuel.getPosition().getI()].setVoisin(caseVoisin, Direction.positionADirection(posVoisin));
    			cases[caseVoisin.getPosition().getJ()][caseVoisin.getPosition().getI()].setVoisin(caseActuel, Direction.directionOpposee(Direction.positionADirection(posActuel)));
    			
    			pile.empiler(caseVoisin);
    			
    			this.caseFin = (Case)pile.regarder();
    		}else {
    			pile.depiler();
    		}
    		
    		
    	}
    	
    	
    }
    
    
    
    
}
