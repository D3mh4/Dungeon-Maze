package donjon;

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
			return String.format("Position : {%d,%d}, "
					+ "Decouverte ? %b, Fin ? %b, "
					+ "Developpé par l'alghorithme ? %b, "
					+ "Haut : {%d,%d}, Bas : {%d,%d}, Gauche : {%d,%d}, Droite : {%d,%d} "
					, pos.getJ(), pos.getI(), decouverte, fin, developpe, voisins[0].pos.getI(), voisins[0].pos.getJ(), voisins[1].pos.getI(), voisins[1].pos.getJ(), voisins[2].pos.getI(), voisins[2].pos.getJ(), voisins[3].pos.getI(), voisins[3].pos.getJ());
			}
		
		
		
}
