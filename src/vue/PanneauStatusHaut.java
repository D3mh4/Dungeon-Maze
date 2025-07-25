package vue;

import java.awt.*;
import javax.swing.BorderFactory;
import javax.swing.*;
import joueur.*;
import modele.PlanDeJeu;

public class PanneauStatusHaut extends JPanel {

	JLabel etiquetteNom = new JLabel("Leeroy Jenkins");
	JProgressBar barreProgression = new JProgressBar();
	JLabel etiquetteNiveau = new JLabel("Niveau : 0");
	JLabel etiquetteNbEnnemi = new JLabel("Nombre d'ennemies tués : 0");
	JLabel etiquetteTemps = new JLabel("Temps de jeu : 0 secondes");
	
	PlanDeJeu planDeJeu = PlanDeJeu.getInstance();
	Joueur joueur = planDeJeu.getJoueur();
	
	public PanneauStatusHaut() {
		configurerPanneau();
		configurerContenu();
	}

	private void configurerPanneau() {
		
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
	}
	
	private void configurerContenu() {
		
		setLayout(new GridLayout(5,1));
		
		add(etiquetteNom);
		etiquetteNom.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 24));
		etiquetteNom.setHorizontalAlignment(SwingConstants.CENTER);
		
		add(barreProgression);
		barreProgression.setForeground(Color.GREEN);
		barreProgression.setBackground(Color.RED);

		add(etiquetteNiveau);
		etiquetteNiveau.setHorizontalAlignment(SwingConstants.CENTER);

		add(etiquetteNbEnnemi);
		etiquetteNbEnnemi.setHorizontalAlignment(SwingConstants.CENTER);

		add(etiquetteTemps);
		etiquetteTemps.setHorizontalAlignment(SwingConstants.CENTER);
		
	}

	public void mettreAJoursInfo() {
		barreProgression.setValue(joueur.getPointDeVie()*100/joueur.getPointDeVieMax());
		etiquetteNiveau.setText("Niveau : " + planDeJeu.getNiveau());
	}
	
	
}
