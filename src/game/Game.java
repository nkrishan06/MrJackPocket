package game;
import board.Board;

public class Game{
	
	private Player joueur1;
	private Player joueur2;
	private static Player currentPlayer = null;
	private Board board;
	
	public Game() {
		
		//Display display = new Display(width, height);
		joueur1 = new Player("Joueur 1", false);
		joueur2 = new Player("Joueur 2", true);
		board = new Board();
		this.joueur2.setIdentiteJack(board.getCharacterJack());
		//display.addKeyListener(board);
		
	}

	public Player getJoueur1() {
		return this.joueur1;
	}
	
	public Player getJoueur2() {
		return this.joueur2;
	}

	public static void setCurrentPlayer(Player currentPlayer) {
		Game.currentPlayer = currentPlayer;
	}

	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void switchPlayer() {
		if (currentPlayer == joueur1) {
			setCurrentPlayer(joueur2);
		} else if (currentPlayer ==joueur2) {
			setCurrentPlayer(joueur1);
		}
	}
	
	public Board getBoard() {
		return this.board;
	}
	
}
