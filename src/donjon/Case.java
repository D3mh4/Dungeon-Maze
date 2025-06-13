package donjon;

/**
 * Représente une case dans le labyrinthe.
 * 
 * Chaque case possède une position, des états (découverte, développée, fin),
 * et des références vers ses voisins dans les quatre directions cardinales.
 * 
 * Fonctionnalités principales :
 * - Suivi de l’état de la case (explorée, fin, développée).
 * - Gestion des voisins selon les directions (haut, bas, gauche, droite).
 * - Représentation textuelle de la case et de ses connexions.
 * 
 * Utilisée dans la génération du labyrinthe dans {@link donjon.Donjon}.
 * 
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @author Amine Abdaoui
 * @version Été 2025 - TP1
 */

import java.util.Arrays;

import physique.Position;

public class Case {
	
		private Position pos;
		private boolean decouverte;
		private boolean fin;
		private boolean developpe;
		private Case[] voisins;
		
		public Case(Position pos) {
			this.pos = pos;
			decouverte = false;
			fin = false;
			developpe = false;
			voisins = new Case[4];
		}
		
		public Position getPos() {
			return pos.clone();
		}
		
		public boolean isDecouverte() {
			return decouverte;
		}

		public void setDecouverte(boolean decouverte) {
			this.decouverte = decouverte;
		}

		public boolean getFin() {
			return fin;
		}

		public void setFin(boolean fin) {
			this.fin = fin;
		}

		public boolean isDeveloppe() {
			return developpe;
		}

		public void setDeveloppe(boolean developpe) {
			this.developpe = developpe;
		}
		
		public void setVoisin(Case voisin, int direction) {
			voisins[direction] = voisin;
		}
		
		public Case getVoisin(int direction) {
			return voisins[direction];
		}
		
		//
		@Override
		public String toString() {
		    String[] voisinsStr = new String[4];

		    for (int i = 0; i < 4; i++) {
		        if (voisins[i] != null && voisins[i].pos != null) {
		            voisinsStr[i] = String.format("{%d,%d}", voisins[i].pos.getI(), voisins[i].pos.getJ());
		        } else {
		            voisinsStr[i] = "{null}";
		        }
		    }

		    return String.format(
		        "Position : {%d,%d}, Decouverte ? %b, Fin ? %b, Developpé par l'alghorithme ? %b, Haut : %s, Bas : %s, Gauche : %s, Droite : %s",
		        pos.getJ(), pos.getI(), decouverte, fin, developpe,
		        voisinsStr[0], voisinsStr[1], voisinsStr[2], voisinsStr[3]
		    );
		    }
		
		
		
}
