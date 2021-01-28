package board;

public class Tuiles {
	
	private actions.Character character;
	private boolean isJack;
	private Orientation orientation;
	private int abscisse;
	private int ordonnes;
	private int numero;
	
	public Tuiles(actions.Character character, Orientation orientation, boolean isJack,int num) {
		this.character=character;
		this.orientation=orientation;
		this.isJack=isJack;	
		this.numero = num;
	}
	
	public actions.Character getCharacter () {
		return character;
	}
	
	public boolean getisJack() {
		return isJack;
	}
	
	public Orientation getOrientation() {
		return orientation;
	}
	
	public void setOrientation(Orientation orientation) {
		this.orientation = orientation;
	}
	
	public int getAbscisse() {
		return abscisse;
	}

	public void setAbscisse(int abscisse) {
		this.abscisse= abscisse;
	}
	
	public int getOrdonnes() {
		return ordonnes;
	}

	public void setOrdonnes(int ordonnes) {
		this.ordonnes= ordonnes;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}


