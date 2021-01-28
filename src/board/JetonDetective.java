package board;

public class JetonDetective {
	private Detective detective;
	private int positionX;
	private int positionY;
	
	public int getPositionX() {
		return this.positionX;
	}
	public int getPositionY() {
		return this.positionY;
	}
	
	public void setPositionX(int x) {
		this.positionX = x;
	}
	public void setPositionY(int y) {
		this.positionY = y;
	}
	public Detective getDetective() {
		return detective;
	}
	public void setDetective(Detective detective) {
		this.detective = detective;
	}
	
	public JetonDetective(Detective detective, int x, int y) {
		this.detective = detective;
		this.positionX = x;
		this.positionY = y;
	}
}
