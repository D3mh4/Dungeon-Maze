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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import modele.PlanDeJeu;
import observer.MonObserver;

@SuppressWarnings("serial")
public class PanneauPrincipal extends JPanel implements MonObserver{

	//récupère la taille de l'écran
	public static final Dimension TAILLE_ECRAN = Toolkit.getDefaultToolkit().getScreenSize();
	
	//Panneaux
	public PanneauDonjon panneauDonjon;
	private JPanel panneauStatus = new JPanel();
	
	private PanneauStatusHaut panneauStatusHaut = new PanneauStatusHaut();
	private JPanel panneauStatusMilieu = new JPanel();
	private PanneauStatusBas panneauStatusBas = new PanneauStatusBas();
	
	private JPanel panneauHero = new JPanel();
	private PanneauEquipement panneauEquipement = new PanneauEquipement();

	
	/**
	 * Constructeur
	 * @param taille La taille de l'ecran
	 */
	public PanneauPrincipal() {
		
		// assigne la tâche
		setSize(TAILLE_ECRAN);
		setPreferredSize(TAILLE_ECRAN);
		
		// initialise les composantes
		initComposantes();
		
		//attacher au plan de jeu en tant qu'observer
		PlanDeJeu.getInstance().attacherObserver(this);
	}
	
	
	/**
	 * Dimensionne et ajoute les differents panneaux à leur place.
	 */
	private void initComposantes() {

		// définit le layout
		setLayout(new BorderLayout());
		
		// définit le panneau de donjon
		panneauDonjon = new PanneauDonjon(TAILLE_ECRAN);
		
		//définit et configure le panneau status
		panneauStatus.setPreferredSize(new Dimension(TAILLE_ECRAN.width/3,TAILLE_ECRAN.height));
		panneauStatus.setLayout(new GridLayout(3, 1));
		panneauStatus.add(panneauStatusHaut);
		panneauStatus.add(panneauStatusMilieu);
		panneauStatus.add(panneauStatusBas);
	
		
		//ajoute les panneaux donjon et status au panneau principal
		add(panneauDonjon, BorderLayout.CENTER);
		add(panneauStatus, BorderLayout.LINE_END);
		
		//configure le panneau du millieu
		panneauStatusMilieu.setLayout(new GridLayout(1, 2));
		panneauStatusMilieu.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		panneauStatusMilieu.add(panneauHero);
		
		//configure le panneau hero
		panneauHero.setBorder(new EmptyBorder(-5, 0, 0, 0));
	    
		try {
			BufferedImage image = ImageIO.read(new File("images/hero.png"));
			panneauHero.add(new JLabel(new ImageIcon(image)));
	    } catch (IOException e) {
	    	throw new RuntimeException("Impossible de charger l'image du héros", e);
	    }
		
		//ajout du panneau d'équipement
		panneauStatusMilieu.add(panneauEquipement);

		panneauDonjon.requestFocus();
		
	}

	
	/**
	 * Met à jour les panneaux lors des changements du plan de jeu
	 */
	@Override
	public void avertir() {
		panneauStatusHaut.mettreAJoursInfo();
		panneauEquipement.mettreAJoursInfo();
		panneauStatusBas.mettreAJoursInfo();
	}

}
