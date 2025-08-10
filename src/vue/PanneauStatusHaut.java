package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GridLayout;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

import joueur.Joueur;
import modele.PlanDeJeu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Panneau situé en haut de la zone de statut.
 * 
 * Affiche :
 * - le nom du joueur
 * - sa barre de vie
 * - son niveau
 * - le nombre d'ennemis tués
 * - le temps de jeu en secondes qui se met à jour en temps réel grâce à un Timer.
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @version ÉTÉ 2025 - Devoir 2
 */

@SuppressWarnings("serial")
public class PanneauStatusHaut extends JPanel {

	//déclaration des composantes du panneau
	private JLabel etiquetteNom = new JLabel("Leeroy Jenkins");
	private JProgressBar barreProgression = new JProgressBar();
	private JLabel etiquetteNiveau = new JLabel("Niveau : 0");
	private JLabel etiquetteNbEnnemi = new JLabel("Nombre d'ennemis tués : 0");
	private JLabel etiquetteTemps = new JLabel("Temps de jeu : 0 secondes");
	
	//référence au plan de jeu
	private PlanDeJeu planDeJeu = PlanDeJeu.getInstance();
	
	//référence au joueur
	private Joueur joueur = planDeJeu.getJoueur();
	
	//référence au Timer
	private Timer timer;
	
	
	/**
	 * Constructeur du panneau de statut haut.
	 * Configure l'apparence et démarre le minuteur d'affichage du temps.
	 */
	public PanneauStatusHaut() {
		configurerPanneau();
		configurerContenu();
		
		timer = new Timer(1000, new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        int secondesTotal = planDeJeu.getTempsJoueEnSecondes();
		        int minutes = secondesTotal / 60;
		        int secondes = secondesTotal % 60;
		        etiquetteTemps.setText(String.format("Temps de jeu : %d:%02d", minutes, secondes));
		    }
		});

		timer.start();
	}
	

	/**
	 * Configure l'apparence générale du panneau (bordures et marges).
	 */
	private void configurerPanneau() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		setBorder(new EmptyBorder(0, 15, 0, 15));
	}
	
	
	/**
	 * Configure le contenu du panneau :
	 * - charge les polices personnalisées
	 * - ajoute les composants graphiques (labels, barre de vie)
	 */
	private void configurerContenu() {
		setLayout(new GridLayout(5, 1));
		
		configurerPolice();
		
		//ajout et alignement du nom du joueur
		add(etiquetteNom);
		etiquetteNom.setHorizontalAlignment(SwingConstants.CENTER);
		
		//ajout et configuration des couleurs de la barre de progression
		add(barreProgression);
		barreProgression.setForeground(Color.GREEN);
		barreProgression.setBackground(Color.RED);

		//ajout et alignement du niveau
		add(etiquetteNiveau);
		etiquetteNiveau.setHorizontalAlignment(SwingConstants.CENTER);

		//ajout et alignement du compteur d'ennemis eliminés
		add(etiquetteNbEnnemi);
		etiquetteNbEnnemi.setHorizontalAlignment(SwingConstants.CENTER);

		//ajout et alignement du compteur de secondes jouées
		add(etiquetteTemps);
		etiquetteTemps.setHorizontalAlignment(SwingConstants.CENTER);
	}

	
	/**
	 * Configure la police d'écriture des textes
	 */
	private void configurerPolice() {
		try {
			Font policeMerriweather = Font.createFont(Font.TRUETYPE_FONT, new File("polices/Merriweather-VariableFont_opsz,wdth,wght.ttf")).deriveFont(Font.BOLD | Font.ITALIC, 24f);
			etiquetteNom.setFont(policeMerriweather);

			Font policeArsenal = Font.createFont(Font.TRUETYPE_FONT, new File("polices/Arsenal-Regular.ttf")).deriveFont(16f);
			etiquetteNiveau.setFont(policeArsenal);
			etiquetteNbEnnemi.setFont(policeArsenal);
			etiquetteTemps.setFont(policeArsenal);

		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Met à jour l'affichage :
	 * - la barre de vie selon les points de vie actuels
	 * - le niveau du joueur
	 * - le nombre d'ennemis tués
	 */
	public void mettreAJoursInfo() {
		barreProgression.setValue(joueur.getPointDeVie() * 100 / joueur.getPointDeVieMax());
		etiquetteNiveau.setText("Niveau : " + planDeJeu.getNiveau());
		etiquetteNbEnnemi.setText("Nombre d'ennemis tués : " + planDeJeu.getNbCreatureTuee());
	}
}
