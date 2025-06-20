package modele;

/**
 * Le plan de jeu est la classe qui supporte le modèle du programme.
 * Il contient:
 * 	- le donjon
 *  - le joueur
 *  - les créatures
 *  
 * et actionne les mécaniques du jeu.
 * 
 * Le plan de jeu est implémenté en Lazy Singleton
 * 
 * @author Fred Simard | ETS
 * @version Hiver 2022 - TP2
 */


import java.util.Observable;
import java.util.Observer;
import java.util.Random;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import donjon.Case;
import donjon.Configuration;
import donjon.Donjon;
import interfaceUtilisateur.ControleurClavier;
import observer.MonObservable;
import observer.MonObserver;
import physique.Direction;
import physique.Position;
import personnage.*;

public class PlanDeJeu extends MonObservable implements MonObserver, Runnable {

	private Donjon donjon;
	private boolean partieEnCours = false;
	private int niveauCourant = 0;
	private Random rand = new Random(System.currentTimeMillis());
	private Vector<AbstractCreature> creatures = new Vector<AbstractCreature>();
	private Joueur joueur;
	
	
	public Joueur getJoueur() {
		return joueur;
	}

	private static final PlanDeJeu instance = new PlanDeJeu();
	private static Thread t;
	
	/**
	 * constructeur du plan de jeu
	 */
	public PlanDeJeu(){
		partieEnCours = true;
		nouveauNiveau();
	}

	/**
	 * méthode pour obtenir une référence au plan de jeu
	 * @return l'instance
	 */
	public static PlanDeJeu getInstance(){
		return instance;
	}
	
	/**
	 * méthode pour obtenir une référence au donjon
	 * @return référence au donjon
	 */
	public Donjon getDonjon(){
		return this.donjon;
	}

	@Override
	/**
	 * callback implémenté par l'observer
	 */
	public void avertir() {
		
		// vérifie les règles du jeu
		validerEtatJeu();
		
		// avertie les observers du plan de jeu
		this.avertirLesObservers();
	}

	@Override
	/**
	 * implémente la méthode run de Runnable
	 */
	public void run() {

		// tant qu'une partie est en cours
		while(partieEnCours){
			
			// déplace toutes les créatures
			for(AbstractCreature creature : creatures) {
				creature.seDeplacer(rand.nextInt(4));
			}
			
			
			// attend X nombre de secondes
			try {
				TimeUnit.SECONDS.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Vector<AbstractCreature> getCreatures() {
		return creatures;
	}

	private void initCreature(){
		
		Case[][] casesDuDonjon = donjon.getCases();
		Configuration configuration = Configuration.getInstance();
		
		if(!creatures.isEmpty()) {
			creatures.clear();
		}
		
		for(int i = 0; i < configuration.getConfig(Configuration.NB_CREATURES); i++) {
			
			switch(rand.nextInt(configuration.getConfig(Configuration.NB_TYPES_CREATURES))) {
			case 0 : 
				creatures.add(
						new CreatureAraignee(
								new Position(
										rand.nextInt(casesDuDonjon.length),
										rand.nextInt(casesDuDonjon[0].length))));
				break;
				
			case 1 : 
				creatures.add(
						new CreatureMinotaure(
								new Position(
										rand.nextInt(casesDuDonjon.length),
										rand.nextInt(casesDuDonjon[0].length))));
				break;
				
			case 2 : 
				creatures.add(
						new CreatureDragon(
								new Position(
										rand.nextInt(casesDuDonjon.length),
										rand.nextInt(casesDuDonjon[0].length))));
				break;
			}
			
			creatures.elementAt(i).attacherObserver(this);
			
			creatures.elementAt(i).setCase(
					casesDuDonjon[creatures.elementAt(i).getPos().getJ()]
							     [creatures.elementAt(i).getPos().getI()]);
		}
		
	}
	
	private void initJoueur(){
		
		Case[][] casesDuDonjon = donjon.getCases();
		joueur = new Joueur(donjon.getCaseDepart().getPos());
		
		joueur.setCase(donjon.getCaseDepart());
		
		joueur.attacherObserver(this);

		joueur.setCase(
				casesDuDonjon[joueur.getPos().getJ()]
						     [joueur.getPos().getI()]);
		}
	
	/**
	 * méthode qui valide les règles du jeu
	 */
	private void  validerEtatJeu(){
		

		// verifie si le joueur vient de trouver de l'équipement
			// oui, ajoute à l'inventaire
		
		// verifie s'il y a un combat
			// oui, fais la résolution du combat

		// verifie si le joueur est mort...
			// oui, partie perdu

	
	}
	
	/**
	 * méthode qui lance un nouveau niveau
	 */
	private void  nouveauNiveau(){
		
		// la partie est toujours en cours
		partieEnCours = true;
		// crée un nouveau donjon
		this.donjon = new Donjon();
		
		initJoueur();

		initCreature();
		
		// si la tâche qui gère les créature
		// n'a pas encore été lancé, la lance.
		if(t ==null){
			t = new Thread(this);
			t.start();
		}
	
		
	}

	/**
	 * méthode qui gère une partie gagné
	 */
	private void partieGagne(){
		
		// incrémente le compteur de niveau
		niveauCourant++;
		
		// obtient les configs
		Configuration config = Configuration.getInstance();
		int nbCols = (int)config.getConfig(Configuration.NB_COLONNES);
		int nbLignes = (int)config.getConfig(Configuration.NB_LIGNES);
		int nbCreatures = (int)config.getConfig(Configuration.NB_CREATURES);
		// mets à jours les configs
		config.setConfig(Configuration.NB_COLONNES,nbCols+1);
		config.setConfig(Configuration.NB_LIGNES,nbLignes+1);
		config.setConfig(Configuration.NB_CREATURES,nbCreatures+2);
		
		// lance un nouveau niveau
		nouveauNiveau();
	}

	/**
	 * gestion d'une partie perdu
	 */
	private void partiePerdu(){
		
		// plus de partie en cours
		partieEnCours = false;
		
		// attend la fin de la tâche
		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// remise à zéro du jeu
		Configuration.remiseAZero();
	}
		
}
