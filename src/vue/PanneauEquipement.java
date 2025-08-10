package vue;

/**
 * Panneau situé dans le panneau du milieu de la zone de statut.
 * 
 * Affiche :
 * - les informations d'attaque et de défense totales du joueur
 * - les équipements actuellement équipés (casque, armure, arme)
 * - l'inventaire des équipements disponibles via des listes déroulantes
 * - le nombre de potions possédées et un bouton pour les utiliser
 * 
 * Se met à jour en temps réel grâce au pattern Observer.
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @version ÉTÉ 2025 - Devoir 2
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import equipements.*;
import joueur.Joueur;
import modele.PlanDeJeu;

@SuppressWarnings("serial")
public class PanneauEquipement extends JPanel implements ItemListener {

	//Déclaration des composantes du panneau (Défense)
	private JLabel etiquetteDefense = new JLabel("Défense totale : 0");
    
    private JLabel titreCasque = new JLabel("Casque :");
    private JComboBox<Casque> comboBoxCasque = new JComboBox<>();

    private JLabel titreArmure = new JLabel("Armure :");
    private JComboBox<Armure> comboBoxArmure = new JComboBox<>();

    //Déclaration des composantes du panneau (Attaque)
    private JLabel etiquetteAttaque = new JLabel("Attaque totale : 0");

    private JLabel titreArme = new JLabel("Arme :");
    private JComboBox<Arme> comboBoxArme = new JComboBox<>();

    //Déclaration des composantes du panneau (Potion)
    private int nbPotions = 0;

    private JLabel titrePotion = new JLabel("Nombre de potions : " + nbPotions);

    private JButton buttonPotion = new JButton("Utiliser une potion");
    
    //Référence au plan de jeu
    private PlanDeJeu planDeJeu = PlanDeJeu.getInstance();
    
    //Référence au joueur
    private Joueur joueur = planDeJeu.getJoueur();
	
    
	/**
     * Constructeur.
     * Configure le contenu du panneau.
     */
    public PanneauEquipement() {
        configurerContenu();
    }

    
    /**
     * Configure la disposition générale du panneau
     * en ajoutant les sous-panneaux {@code panHero} et {@code panEquipement}.
     */
    private void configurerContenu() {

    	configurerPolice();
    	
    	//Configure l'apparence générale du panneau (Layout et marges).
        setLayout(new GridLayout(10, 1));
        setBorder(new EmptyBorder(15, 15, 15, 15));

        //Ajout des composantes et du gestionnaire d'événement (Defense)
        add(etiquetteDefense);
        add(titreCasque);
        add(comboBoxCasque);
        comboBoxCasque.addItemListener(this);

        add(titreArmure);
        add(comboBoxArmure);
        comboBoxArmure.addItemListener(this);

        //Ajout des composantes et du gestionnaire d'événement (Attaque)
        add(etiquetteAttaque);
        add(titreArme);
        add(comboBoxArme);
        comboBoxArme.addItemListener(this);

        //Ajout des composantes et du gestionnaire d'événement (Potion)
        add(titrePotion);
        add(buttonPotion);

        buttonPotion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                joueur.utiliserPotion();
                mettreAJoursInfo();
            }
        });

        buttonPotion.setEnabled(false);
    }

    
    /**
     * Met à jour l'affichage des informations et de l'inventaire.
     */
    public void mettreAJoursInfo() {
    	
    	//Met à jour les textes (attaque et défense)
        etiquetteAttaque.setText("Attaque totale : " + joueur.getForce());
        etiquetteDefense.setText("Défense totale : " + joueur.getArmure());

        //Met à jour l'inventaire
        comboBoxCasque.removeAllItems();
        comboBoxArmure.removeAllItems();
        comboBoxArme.removeAllItems();
        nbPotions = 0;

        comboBoxCasque.addItem(joueur.getCasqueEquipe());
        comboBoxArmure.addItem(joueur.getArmureEquipe());
        comboBoxArme.addItem(joueur.getArmeEquipe());

        for (AbstractEquipement equipement : joueur.getEquipements()) {
            if (equipement instanceof Casque) {
                comboBoxCasque.addItem((Casque) equipement);
            } else if (equipement instanceof Armure) {
                comboBoxArmure.addItem((Armure) equipement);
            } else if (equipement instanceof Arme) {
                comboBoxArme.addItem((Arme) equipement);
            } else if (equipement instanceof Potion) {
                nbPotions++;
            }
        }
        
        //Met à jour les textes et le bouton (Potions)
        titrePotion.setText("Nombre de potions : " + nbPotions);
        buttonPotion.setEnabled(nbPotions > 0);
    }
    
    private void configurerPolice() {
    	try {
            Font policeArsenal = Font.createFont(Font.TRUETYPE_FONT, new File("polices/Arsenal-Regular.ttf")).deriveFont(16f);
            etiquetteDefense.setFont(policeArsenal);
            titreCasque.setFont(policeArsenal);
            comboBoxCasque.setFont(policeArsenal);
            titreArmure.setFont(policeArsenal);
            comboBoxArmure.setFont(policeArsenal);
            etiquetteAttaque.setFont(policeArsenal);
            titreArme.setFont(policeArsenal);
            comboBoxArme.setFont(policeArsenal);
            titrePotion.setFont(policeArsenal);
            buttonPotion.setFont(policeArsenal);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Gère le changement d'équipement via les listes déroulantes.
     * 
     * @param e événement de changement d'élément
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            Object item = e.getItem();
            joueur.equiper((AbstractEquipement) item);
        }
    }
    
}
