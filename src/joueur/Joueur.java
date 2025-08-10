package joueur;

/**
 * Définition du Joueur.
 * 
 * @author Fred Simard | ETS
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @version ÉTÉ 2025 - Devoir 2
 */

import java.util.Vector;

/**
 * Cette classe représente le joueur humain. Elle surcharge le
 * personnage abstrait pour le déplacement et propose une méthode
 * pour mettre à jours la visibilité des cases en fonction de la vision.
 * 
 * @author Fred Simard | ETS
 * @version ETE 2018 - TP2
 */


import dongon.Case;
import equipements.AbstractEquipement;
import equipements.Arme;
import equipements.Armure;
import equipements.Casque;
import equipements.Potion;
import modele.PlanDeJeu;
import personnage.AbstractPersonnage;
import physique.Direction;
import physique.Position;

public class Joueur extends AbstractPersonnage {
	
	private final int PROFONDEUR_VISION = 2;
	
	private Vector<AbstractEquipement> equipements = new Vector<>();
	private Casque casqueEquipe;
	private Armure armureEquipe;
	private Arme armeEquipe;

	
	/**
	 * Construceur par paramètre
	 * @param pos, position du joueur
	 */
	public Joueur() {
		pointDeVie=100;
		pointDeVieMax=100;
	}
	
	
	/**
	 * Retourne la liste des équipements possédés par le joueur.
	 * @return liste des équipements
	 */
	public Vector<AbstractEquipement> getEquipements(){
		return this.equipements;
	}
	
	
	/**
	 * Retourne le casque actuellement équipé par le joueur.
	 * @return casque équipé, ou null si aucun
	 */
	public Casque getCasqueEquipe() {
		return this.casqueEquipe;
	}

	
	/**
	 * Retourne l’armure actuellement équipée par le joueur.
	 * @return armure équipée, ou null si aucune
	 */
	public Armure getArmureEquipe() {
		return this.armureEquipe;
	}

	
	/**
	 * Retourne l’arme actuellement équipée par le joueur.
	 * @return arme équipée, ou null si aucune
	 */
	public Arme getArmeEquipe() {
		return this.armeEquipe;
	}
	
	
	/**
	 * Équipe un objet donné (casque, armure ou arme) et met à jour
	 * les bonus de défense et d’attaque du joueur.
	 * @param equipement équipement à équiper
	 */
	public void equiper(AbstractEquipement equipement) {
		
		if(equipement instanceof Casque) {
			this.casqueEquipe = (Casque) equipement;
			
		} else if(equipement instanceof Armure) {
			this.armureEquipe = (Armure) equipement;
			
		} else if (equipement instanceof Arme){
			this.armeEquipe = (Arme) equipement;
		}
		
		armure = 0;
		
		if(armureEquipe != null) {
			armure += armureEquipe.getValeur();
		}
		
		if(casqueEquipe != null) {
			armure += casqueEquipe.getValeur();
		}
		
		bonusAttaque = 0;
		
		if(armeEquipe != null) {
			bonusAttaque += armeEquipe.getValeur();
		}
		
	}
	
	
	/**
	 * Utilise une potion de l’inventaire si disponible.
	 * Restaure les points de vie au maximum et supprime la potion utilisée.
	 */
	public void utiliserPotion() {
		for(int i = 0; i < equipements.size(); i++) {
			if(equipements.get(i) instanceof Potion) {
				equipements.remove(i);
				this.pointDeVie = this.pointDeVieMax;
				break;
			}
		}
		
		//ajoute l'action dans la console du jeu
		PlanDeJeu.getInstance().addConsoleMessage("Le joueur utilise une potion.");
		PlanDeJeu.getInstance().avertir();
	}

	
	
	/**
	 * surcharge de la méthode pour déplacer le joueur dans la direction donnée
	 * @param direction(int), direction du mouvement
	 */
	public void seDeplacer(int direction){
		
		// se déplacer
		super.seDeplacer(direction);
		
		// mise à jour de la vision
		mettreAJourVision();
	}
	

	/**
	 * surcharge de la méthode pour placer le joueur à sa case de départ
	 * @param caseCourante(Case), case courante
	 */
	public void setCase(Case caseCourante){
		
		// assigne la case
		super.setCase(caseCourante);

		// mise à jour de la vision
		mettreAJourVision();
	}
	
	
	/**
	 * méthode qui mets à jour la vision
	 */
	private void mettreAJourVision(){
		
		// rend visible la case courante
		super.caseCourante.setDecouverte(true);
		
		// dans toutes les directions
		for(int i=0;i<Direction.NB_DIRECTIONS;i++){
			
			// dévoile les voisins jusqu'à la profondeur de la vision
			Case voisin = super.caseCourante.getVoisin(i);
			for(int j=0;j<PROFONDEUR_VISION;j++){
				if(voisin!=null){
					voisin.setDecouverte(true);
					voisin = voisin.getVoisin(i);
				}
			}
		}
	}
	
	
	/**
	 * Ajoute un équipement à l’inventaire du joueur et
	 * marque cet équipement comme ramassé.
	 * @param equipement équipement à ramasser
	 */
	public void ramasserEquipement(AbstractEquipement equipement) {
		equipement.setAuSol(false);
		this.equipements.add(equipement);
		
		//ajoute l'action dans la console du jeu
		PlanDeJeu.getInstance().addConsoleMessage("Le joueur ramasse un équipement : " + equipement.toString());
	}
	

	/**
	 * Remise à zéro du joueur
	 * - remet les points de vie à max
	 * - vide équipement
	 */
	public void remiseAZero(){
		this.pointDeVie = this.pointDeVieMax;
		this.equipements.clear();
		
		this.armeEquipe = null;
		this.casqueEquipe = null;
		this.armureEquipe = null;
		
		equiper(null);
	}

}
