import java.awt.Frame;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Scanner;

import javax.swing.JFrame;

import game.*;

public class Launcher{
	
	 public static final Scanner scan = new Scanner(System.in);
	 private static int tour = 1;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Menu menu = new Menu();
		Game game = new Game();
		//System.out.println("test");
		
		
		while (game.getBoard().getMancheCourante() < 9) {
			switch (tour) {
			case 1:
				if(game.getBoard().getMancheCourante()%2 == 0) {
					game.setCurrentPlayer(game.getJoueur2());
				}
				else {
					game.setCurrentPlayer(game.getJoueur1());
				}
				if(game.getCurrentPlayer().getisJack()) {
					
					game.getBoard().displayConsole("Jack commence: veuillez saisir un chiffre pour lancer les jetons d'action.","Jack a l'identite de "+game.getJoueur2().getIdentiteJack().name());
					int l = scan.nextInt();
					game.getBoard().refreshBoard(l,1);
					game.getBoard().displayConsole("Jack choisi l'une des 4 actions, pour cela, saisir le numero de l'action","Jack a l'identite de "+game.getJoueur2().getIdentiteJack().name());
					int jetonAction = scan.nextInt();
					game.getBoard().refreshBoard(jetonAction,2);
					game.switchPlayer();
					tour=tour+1;
				}
				else {
					game.getBoard().displayConsole("Sherlock commence: veuillez saisir un chiffre pour lancer les jetons d'action.","");
					int l = scan.nextInt();
					game.getBoard().refreshBoard(l,1);
					game.getBoard().displayConsole("Sherlock choisi l'une des 4 actions, pour cela, saisir le numero de l'action","");
					int jetonAction = scan.nextInt();
					game.getBoard().refreshBoard(jetonAction,2);
					game.switchPlayer();
					tour=tour+1;
				}
				break;
				
			case 2:
				if(game.getCurrentPlayer().getisJack()) {
					
					game.getBoard().displayConsole("Jack choisi l'une des 3 actions restantes, pour cela, saisir le numero de l'action","Jack a l'identite de "+game.getJoueur2().getIdentiteJack().name());
					int jetonAction = scan.nextInt();
					game.getBoard().refreshBoard(jetonAction,3);
					tour=tour+1;
				}
				else {
					game.getBoard().displayConsole("Sherlock choisi l'une des 3 actions restantes, pour cela, saisir le numero de l'action","");
					int jetonAction = scan.nextInt();
					game.getBoard().refreshBoard(jetonAction,3);
					tour=tour+1;
				}
				break;
				
			case 3:
				if(game.getCurrentPlayer().getisJack()) {
					
					game.getBoard().displayConsole("Jack rechoisi l'une des 2 actions restantes, pour cela, saisir le numero de l'action","Jack a l'identite de "+game.getJoueur2().getIdentiteJack().name());
					int jetonAction = scan.nextInt();
					game.getBoard().refreshBoard(jetonAction,3);
					game.switchPlayer();
					tour=tour+1;
				}
				else {
					game.getBoard().displayConsole("Sherlock rechoisi l'une des 2 actions restantes, pour cela, saisir le numero de l'action","");
					int jetonAction = scan.nextInt();
					game.getBoard().refreshBoard(jetonAction,3);
					game.switchPlayer();
					tour=tour+1;
				}
				break;
				
			case 4:
				if(game.getCurrentPlayer().getisJack()) {
					
					game.getBoard().displayConsole("Jack joue, pour cela, saisir le numero de la derniere action disponnible","Jack a l'identite de "+game.getJoueur2().getIdentiteJack().name());
					int jetonAction = scan.nextInt();
					game.getBoard().refreshBoard(jetonAction,4);
					game.switchPlayer();
					tour=tour+1;
				}
				else {
					game.getBoard().displayConsole("Sherlock joue, pour cela, saisir le numero de la derniere action disponnible","");
					int jetonAction = scan.nextInt();
					game.getBoard().refreshBoard(jetonAction,4);
					game.switchPlayer();
					tour=tour+1;
				}
				
				break;
				
			case 5:
				if(game.getCurrentPlayer().getisJack()) {
					
					game.getBoard().displayConsole("Deuxieme partie: Appel a temoin","Jack a l'identite de "+game.getJoueur2().getIdentiteJack().name());
					System.out.println("Jack est il visible: (1 - oui), (2 - non)");
					int visible = scan.nextInt();
					game.getBoard().updateTuiles(visible);
					switch (visible) {
					case 1:
						System.out.println("Sherlock gagne le sablier de la manche");
						break;
					case 2:
						System.out.println("Jack gagne le sablier de la manche");
						game.getJoueur2().setSablier(game.getJoueur2().getSablier()+1);
						break;
					default:
						break;
					}
					tour = 1;
					game.getBoard().setMancheCourante(game.getBoard().getMancheCourante()+1);
					game.getBoard().updateDisplayConsole(game.getBoard().getMancheCourante());
				}
				else {
					game.switchPlayer();
					
				}
				
				break;

			default:
				break;
			}
			
			
			
		}
	}

}
