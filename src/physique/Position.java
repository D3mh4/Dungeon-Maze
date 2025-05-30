package physique;

public class Position {

	private int i;
	private int j;
	
	public Position(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public Position(Position autrePosition) {
		this.i = autrePosition.i;
		this.j = autrePosition.j;
	}

	public int getI() {
		return i;
	}

	public void setI(int i) {
		this.i = i;
	}

	public int getJ() {
		return j;
	}

	public void setJ(int j) {
		this.j = j;
	}
	
	//À confirmer pour le paramètre (tableau, int ou double | position)
	public void additionnerPos(int[] pos) {
		this.i += pos[0];
		this.j += pos[1];
	}
	
	public void soustrairePos(int[] pos) {
		this.i -= pos[0];
		this.j -= pos[1];
	}
	
	public void multiplierPos(int[] pos) {
		this.i *= pos[0];
		this.j *= pos[1];
	}
	
	@Override
	public Position clone() {
		return new Position(this);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Position) {
			Position autrePosition = (Position)obj;
			return (this.i == autrePosition.i && this.j == autrePosition.j);
		}
		return false;
	}
}
