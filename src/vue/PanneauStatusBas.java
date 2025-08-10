package vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import modele.PlanDeJeu;

/**
 * Panneau situé en bas de la zone de statut, affichant les messages de la console
 * du jeu dans une zone de texte défilante.
 * 
 * Utilise une police personnalisée et se met à jour à chaque notification du modèle.
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @version ÉTÉ 2025 - Devoir 2
 */

@SuppressWarnings("serial")
public class PanneauStatusBas extends JPanel {
	
	private JTextArea zoneTexte;
	private JScrollPane scrollPane;
	private PlanDeJeu planDeJeu = PlanDeJeu.getInstance();
	
	
	/**
	 * Constructeur du panneau de statut bas.
	 * Configure l'apparence et le contenu de la zone.
	 */
	public PanneauStatusBas() {
		configurerPanneau();
		configurerContenu();
	}

	
	/**
	 * Configure l'apparence générale du panneau, notamment la bordure.
	 */
	private void configurerPanneau() {
		//ajout d'une bordure autour du panneau
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
	}
	
	
	/**
	 * Configure le contenu du panneau :
	 * - charge la police personnalisée
	 * - crée la zone de texte
	 * - ajoute le défilement vertical
	 */
	private void configurerContenu() {
		
		configurerPolice();
		
		//Configure l'apparence générale du panneau (Layout et marges).
		setLayout(new BorderLayout());
		setBorder(new EmptyBorder(15, 15, 15, 15));
		
		//configure la zone de texte du panneau
	    zoneTexte.setEditable(false);
	    scrollPane = new JScrollPane(zoneTexte);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	    add(scrollPane, BorderLayout.CENTER);
	}
	
	/**
	 * Configure la police des textes.
	 */
	private void configurerPolice() {
		try {
			Font policeArsenal = Font.createFont(Font.TRUETYPE_FONT, new File("polices/Arsenal-Regular.ttf")).deriveFont(16f);
			zoneTexte = new JTextArea();
			zoneTexte.setFont(policeArsenal);
			
		} catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Met à jour la zone de texte avec les messages actuels du plan de jeu.
	 */
	public void mettreAJoursInfo() {
		zoneTexte.setText(planDeJeu.getConsoleMessage());
	}
}
