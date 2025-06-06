package programme;

import physique.*;
import pile.*;
import donjon.*;
import java.util.Arrays;

public class ProgrammePrincipal {

	public static void main(String[] args) {

		Donjon donjon = new Donjon();
		Position pos = new Position(9,6);
		
		Position nouvellePos = new Position(donjon.getVoisinAlea(pos));
		
		System.out.println(nouvellePos.getI());
		System.out.println(nouvellePos.getJ());
		
	}

}
