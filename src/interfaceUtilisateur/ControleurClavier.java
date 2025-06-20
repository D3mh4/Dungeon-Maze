package interfaceUtilisateur;

/**
 * Cette classe est un listener pour les événements claviers utilisé pour contrôler
 * les éléments du jeu.
 * 
 * Ces méthodes hérités ne sont pas utilisées:
 *	public void keyReleased(KeyEvent e) {}
 *  public void keyTyped(KeyEvent e) {}
 * 
 * @author Fred Simard | ETS
 * @version Hiver 2022 - TP2
 */


import java.awt.event.*;
import java.util.Observable;

import modele.PlanDeJeu;
import physique.Direction;
import personnage.*;

public class ControleurClavier implements KeyListener{

	// référence au plan de jeu
	private PlanDeJeu planDeJeu;
	
	/**
	 * Méthode pour attacher le plan de jeu
	 * @param planDeJeu, référence au plan de jeu
	 */
	public void attacherPlanDeJeu(PlanDeJeu planDeJeu){
		this.planDeJeu = planDeJeu;
	}
	
	/**
	 * gestionnaire d'événement associé au clavier
	 * @param e(KeyEvent), evénement clavier
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		
		
		// obtient une référence au joueur courant
		Joueur joueurControlle =  planDeJeu.getJoueur();
		String msgh;
		// s'assure qu'un joueur a été initialisé
		if(joueurControlle != null){
			
			int keyCode = e.getKeyCode();
			msgh = KeyEvent.getKeyText(keyCode);
			// gestion de l'action en fonction de l'événement clavier
			switch(KeyEvent.getKeyText(keyCode)){
			
				case "Haut":
					     	planDeJeu.getJoueur().seDeplacer(0);
					     	System.out.println("HAUT");
					break;
				case "Bas":
							planDeJeu.getJoueur().seDeplacer(1);
					break;
				case "Gauche":
							planDeJeu.getJoueur().seDeplacer(2);
					break;
				case "Droite":
							planDeJeu.getJoueur().seDeplacer(3);
					break;
			
			}
		}
		
	}

	@Override
	public void keyReleased(KeyEvent e) {}

	@Override
	public void keyTyped(KeyEvent e) {}

}
