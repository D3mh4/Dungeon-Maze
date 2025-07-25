package vue;

/**
 * Panneau principal du jeu
 * 
 * contient:
 * - le panneau du donjon
 * 
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import modele.PlanDeJeu;
import observer.MonObserver;

public class PanneauPrincipal extends JPanel{

	
	Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
	
	// Panneaux
	public PanneauDonjon panDonjon;
	public PanneauStatus panStatus;

	/**
	 * Constructeur
	 * @param taille La taille de l'ecran
	 */
	public PanneauPrincipal() {
		
		// assigne la tâche
		setSize(tailleEcran);
		setPreferredSize(tailleEcran);
		
		// initialise les composantes
		initComposantes();
	}
	

	/*
	 * Dimensionne et ajoute les differents panneaux e leur place.
	 */
	private void initComposantes() {

		// définit le layout
		setLayout(new BorderLayout());
		
		// définit le panneau de donjon
		panDonjon = new PanneauDonjon(tailleEcran);
		panStatus = new PanneauStatus(new Dimension(tailleEcran.width/3,tailleEcran.height));
		add(panDonjon, BorderLayout.CENTER);
		add(panStatus, BorderLayout.LINE_END);
		

		panDonjon.requestFocus();
		
	}

}
