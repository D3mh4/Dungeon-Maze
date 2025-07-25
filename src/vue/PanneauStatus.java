package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import modele.PlanDeJeu;
import observer.MonObserver;

public class PanneauStatus extends JPanel implements MonObserver{
	
	PanneauStatusHaut panneauHaut = new PanneauStatusHaut();
	PanneauStatusMilieu panneauMilieu = new PanneauStatusMilieu();
	PanneauStatusBas panneauBas = new PanneauStatusBas();
	
	public PanneauStatus(Dimension taille) {
		this.setPreferredSize(taille);
		
		PlanDeJeu.getInstance().attacherObserver(this);
		
		configurerPanneau();
		configurerContenu();
	}

	@Override
	public void avertir() {
		panneauHaut.mettreAJoursInfo();
		panneauMilieu.mettreAJoursInfo();
		panneauBas.mettreAJoursInfo();
	}
	
	private void configurerPanneau() {

		setBackground(Color.CYAN);
		
	}
	
	private void configurerContenu() {
		
		setLayout(new GridLayout(3,1));
		
		this.add(panneauHaut);
		this.add(panneauMilieu);
		this.add(panneauBas);
	
	}

}
