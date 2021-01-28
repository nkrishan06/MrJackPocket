package actions;

public class JetonAction {
	private Jeton jeton;
	private int numero;
	
	public JetonAction(Jeton jeton,int num) {
		this.jeton = jeton;
		this.setNumero(num);
	}
	
	public Jeton getJeton() {
		return this.jeton;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}
}
