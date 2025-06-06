package pile;

public class PileSChainee<T> {

	protected T[] elements;
    protected int nbElements;
    
    public PileSChainee(int capacite) {
        this.nbElements = 0;
        this.elements = (T[]) new Object[capacite];
    }
    
    public boolean estVide() {
        return nbElements == 0;
    }

    public boolean estPleine() {
        return nbElements == elements.length;
    }

    public int getNbElements() {
        return nbElements;
    }

    public void agrandirTableau() {
    	
        int nouvelleCapacite = elements.length * 2;
        T[] nouveauTableau = (T[]) new Object[nouvelleCapacite];
        System.arraycopy(elements, 0, nouveauTableau, 0, elements.length);
        elements = nouveauTableau;
        
    }
    
    public void empiler(T element) {
    	
        if (estPleine()) {
        	agrandirTableau();
        }
        
        elements[nbElements++] = element;
    }

    public T depiler() {
    	
        if (estVide()) {
            throw new IllegalStateException("Pile vide");
        }
        
        nbElements--;
        
        return elements[nbElements];
    }
    
    public T regarder() {
    	
        if (estVide()) {
            throw new IllegalStateException("Pile vide");
        }
        
    	return elements[nbElements-1];
    }
    
    
	
	
}
