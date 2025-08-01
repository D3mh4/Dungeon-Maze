package vue;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.*;


import equipements.*;
import joueur.Joueur;
import modele.PlanDeJeu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class PanneauStatusMilieu extends JPanel implements ItemListener{

	JPanel panHero = new JPanel();
	JPanel panEquipement = new JPanel();
	
	JLabel etiquetteDefense = new JLabel("Defense totale : 0");
	JLabel titreCasque = new JLabel("Casque :");
	JComboBox<Casque> comboBoxCasque = new JComboBox<>();
	JLabel titreArmure = new JLabel("Armure :");
	JComboBox<Armure> comboBoxArmure = new JComboBox<>();
	
	JLabel etiquetteAttaque = new JLabel("Attaque totale : 0");
	JLabel titreArme = new JLabel("Arme :");
	JComboBox<Arme> comboBoxArme = new JComboBox<>();
	
	int nbPotions = 0;
	JLabel titrePotion = new JLabel("Nombre de potion : " + nbPotions);
	JButton buttonPotion = new JButton("Utiliser une potion");
	
	PlanDeJeu planDeJeu = PlanDeJeu.getInstance();
	Joueur joueur = planDeJeu.getJoueur();
	
	public PanneauStatusMilieu() {
		configurerPanneau();
		configurerContenu();
	}

	private void configurerPanneau() {
		
		setBackground(Color.BLUE);
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
	}
	
	private void configurerContenu() {
		
		setLayout(new GridLayout(1,2));
		
		add(panHero);
		configurerPanHero();

		add(panEquipement);
		configurerPanEquipement();
		
	}

	private void configurerPanHero() {
		panHero.setBorder(new EmptyBorder(-5, 0, 0, 0));
		BufferedImage image = null;
        try {
            image = ImageIO.read(new File("images/hero.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        panHero.add(new JLabel(new ImageIcon(image)));
	}
	
	private void configurerPanEquipement() {

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

		}catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}

		panEquipement.setLayout(new GridLayout(10,1));
		panEquipement.setBorder(new EmptyBorder(15, 15, 15, 15));
		
		panEquipement.add(etiquetteDefense);
		panEquipement.add(titreCasque);
		panEquipement.add(comboBoxCasque);
		comboBoxCasque.addItemListener(this);
		panEquipement.add(titreArmure);
		panEquipement.add(comboBoxArmure);
		comboBoxArmure.addItemListener(this);
		
		panEquipement.add(etiquetteAttaque);
		panEquipement.add(titreArme);
		panEquipement.add(comboBoxArme);
		comboBoxArme.addItemListener(this);
		
		panEquipement.add(titrePotion);
		panEquipement.add(buttonPotion);
		buttonPotion.addActionListener(new ActionListener() {
			 @Override
		        public void actionPerformed(ActionEvent e) {
		            joueur.utiliserPotion();
		            mettreAJoursInfo();
		        }
		});
			;
		buttonPotion.setEnabled(false);

	}
	
	
	
	public void mettreAJoursInfo() {
		etiquetteAttaque.setText("Attaque total : " + joueur.getForce());
		etiquetteDefense.setText("Defense totale : " + joueur.getArmure());
		
		comboBoxCasque.removeAllItems();
		comboBoxArmure.removeAllItems();
		comboBoxArme.removeAllItems();
		nbPotions = 0;
		
		comboBoxCasque.addItem(joueur.getCasqueEquipe());
		comboBoxArmure.addItem(joueur.getArmureEquipe());
		comboBoxArme.addItem(joueur.getArmeEquipe());
		
		for(AbstractEquipement equipement : joueur.getEquipements()) {
			if(equipement instanceof Casque) {
				comboBoxCasque.addItem((Casque) equipement);
				
			} else if(equipement instanceof Armure) {
				comboBoxArmure.addItem((Armure) equipement);
				
			} else if(equipement instanceof Arme) {
				comboBoxArme.addItem((Arme) equipement);
				
			} else if(equipement instanceof Potion) {
				nbPotions++;
		    }
	     }
		
		titrePotion.setText("Nombre de potion : " + nbPotions);
		
		buttonPotion.setEnabled(nbPotions > 0);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
				Object item = e.getItem();
				joueur.equiper((AbstractEquipement) item);
			}
		
	}
}
