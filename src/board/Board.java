package board;

import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;

import javax.print.attribute.standard.OrientationRequested;
import javax.swing.JFrame;



import actions.Cartes;
import actions.Character;
import actions.Jeton;
import actions.JetonAction;
import game.Game;
import game.Player;


public class Board{

	
	private int tourCourant = 1;
	private int mancheCourante = 1;
	
	private int numSwap1 = -1;
	private int numSwap2 = -1;
	
	private java.lang.String[][] displayConsole=new java.lang.String[7][13];
	
	private Tuiles[][] listeTuiles=new Tuiles[3][3];
	
	private JetonDetective[] listejetonsDetective = new JetonDetective[3];
	
	private JetonAction[] listeJetonsAction = new JetonAction[7];
	
	private Cartes[] listeCartes = new Cartes[9];
	
	private Character characterJack;
	
	public static final Scanner scan = new Scanner(System.in);
	
	private java.util.List<Tuiles> listeTuilesVues = new ArrayList<Tuiles>();
	
	
	public void initialiserListeCartes() {
		this.listeCartes[0] = new Cartes(Character.INSEPECTEUR_LESTRADE,0);
		this.listeCartes[1] = new Cartes(Character.JEREMY_BART,1);
		this.listeCartes[2] = new Cartes(Character.JOHN_PIZER,1);
		this.listeCartes[4] = new Cartes(Character.JOHN_SMITH,1);
		this.listeCartes[5] = new Cartes(Character.JOSEPH_LANE,1);
		this.listeCartes[6] = new Cartes(Character.MADAME,2);
		this.listeCartes[7] = new Cartes(Character.MISS_STEALTHY,1);
		this.listeCartes[8] = new Cartes(Character.SERGENT_GOODLEY,0);
		this.listeCartes[3] = new Cartes(Character.WILLIAM_GULL,1);
		
		shuffle(this.listeCartes);
		this.setCharacterJack();
	}
	
	public int getMancheCourante() {
		return this.mancheCourante;
	}
	
	public void setMancheCourante(int m) {
		this.mancheCourante = m;
	}
	
	public void updatelisteJetonsAction(int num) {
		
		for(int i=0;i<this.listeJetonsAction.length;i++) {
			try {
				if(this.listeJetonsAction[i].getNumero() == num) {
					this.listeJetonsAction[i] = null;
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	
	public void initialiserlisteJetonsAction() {
		this.listeJetonsAction[0] = new JetonAction(Jeton.Alibi,0);
		this.listeJetonsAction[1] = new JetonAction(Jeton.Echange,1);
		this.listeJetonsAction[2] = new JetonAction(Jeton.Holmes,2);
		this.listeJetonsAction[3] = new JetonAction(Jeton.Jocker,3);
		this.listeJetonsAction[4] = new JetonAction(Jeton.Rotation,4);
		this.listeJetonsAction[5] = new JetonAction(Jeton.Toby,5);
		this.listeJetonsAction[6] = new JetonAction(Jeton.Watson,6);
	}
	
	public void initialiseTuiles() {
		this.listeTuiles[0][0] = new Tuiles(Character.JOHN_PIZER, Orientation.EAST, false,1);
		this.listeTuiles[0][2] = new Tuiles(Character.INSEPECTEUR_LESTRADE, Orientation.WEST, false,3);
		this.listeTuiles[2][1] = new Tuiles(Character.JEREMY_BART, Orientation.NORTH, false,8);
		
		this.listeTuiles[0][1] = new Tuiles(Character.JOHN_SMITH, Orientation.randomOrientation(), false,2);
		this.listeTuiles[1][0] = new Tuiles(Character.JOSEPH_LANE, Orientation.randomOrientation(), false,4);
		this.listeTuiles[1][1] = new Tuiles(Character.MADAME, Orientation.randomOrientation(), true,5);
		this.listeTuiles[1][2] = new Tuiles(Character.MISS_STEALTHY, Orientation.randomOrientation(), false,6);
		this.listeTuiles[2][0] = new Tuiles(Character.SERGENT_GOODLEY, Orientation.randomOrientation(), false,7);
		this.listeTuiles[2][2] = new Tuiles(Character.WILLIAM_GULL, Orientation.randomOrientation(), false,9);
		
	}
	
	public void deleteInlisteTuiles(Cartes carte) {
		
		for(int i=0;i<this.listeTuiles.length;i++) {
			for(int j=0;j<this.listeTuiles[i].length;j++) {
				if(this.listeTuiles[i][j].getCharacter().name() == carte.getNom()) {
					this.listeTuiles[i][j] = null;
				}
			}
		}
	}
	
	public void updatelisteTuiles(int numAction, int numTuile1, int numTuile2, int sens) {
		int x1 = -1;
		int y1 = -1;
		int x2 = -1;
		int y2 = -1;
		switch (numTuile1) {
		case 1:
				x1 = 0;y1=0;
			break;
		case 2:
			x1 = 0;y1=1;
		break;
		case 3:
			x1 = 0;y1=2;
		break;
		case 4:
			x1 = 1;y1=0;
		break;
		case 5:
			x1 = 1;y1=1;
		break;
		case 6:
			x1 = 1;y1=2;
		break;
		case 7:
			x1 = 2;y1=0;
		break;
		case 8:
			x1 = 2;y1=2;
		break;
		case 9:
			x1 = 2;y1=2;
		break;

		default:
			break;
		}
		
		switch (numTuile2) {
		case 1:
				x2 = 0;y2=0;
			break;
		case 2:
			x2 = 0;y2=1;
		break;
		case 3:
			x2 = 0;y2=2;
		break;
		case 4:
			x2 = 1;y2=0;
		break;
		case 5:
			x2 = 1;y2=1;
		break;
		case 6:
			x2 = 1;y2=2;
		break;
		case 7:
			x2 = 2;y2=0;
		break;
		case 8:
			x2 = 2;y2=2;
		break;
		case 9:
			x2 = 2;y2=2;
		break;

		default:
			break;
		}
		
		
		switch (numAction) {
		case 1:
				Tuiles temp = this.listeTuiles[x1][y1];
				this.listeTuiles[x1][y1] = this.listeTuiles[x2][y2];
				this.listeTuiles[x2][y2] = temp;
			break;
		case 4:
				switch (numTuile2) {
				case 1:
					switch (this.listeTuiles[x1][y1].getOrientation().name()) {
					case "NORTH":
						this.listeTuiles[x1][y1].setOrientation(Orientation.SOUTH);
						break;
					case "SOUTH":
						this.listeTuiles[x1][y1].setOrientation(Orientation.NORTH);
						break;
					case "EAST":
						this.listeTuiles[x1][y1].setOrientation(Orientation.WEST);
						break;
					case "WEST":
						this.listeTuiles[x1][y1].setOrientation(Orientation.EAST);
						break;

					default:
						break;
					}
				break;
				case 2:
					switch (sens) {
					case 1:
						switch (this.listeTuiles[x1][y1].getOrientation().name()) {
						case "NORTH":
							this.listeTuiles[x1][y1].setOrientation(Orientation.EAST);
							break;
						case "SOUTH":
							this.listeTuiles[x1][y1].setOrientation(Orientation.WEST);
							break;
						case "EAST":
							this.listeTuiles[x1][y1].setOrientation(Orientation.SOUTH);
							break;
						case "WEST":
							this.listeTuiles[x1][y1].setOrientation(Orientation.NORTH);
							break;

						default:
							break;
						}
						break;
					case 2:
						switch (this.listeTuiles[x1][y1].getOrientation().name()) {
						case "NORTH":
							this.listeTuiles[x1][y1].setOrientation(Orientation.WEST);
							break;
						case "SOUTH":
							this.listeTuiles[x1][y1].setOrientation(Orientation.EAST);
							break;
						case "EAST":
							this.listeTuiles[x1][y1].setOrientation(Orientation.NORTH);
							break;
						case "WEST":
							this.listeTuiles[x1][y1].setOrientation(Orientation.SOUTH);
							break;

						default:
							break;
						}
						break;

					default:
						break;
					}
					break;
				default:
					break;
				}
			break;
		default:
			break;
		}
	}
	
	public void initialiserJetonsDetective() {
		this.listejetonsDetective[0] = new JetonDetective(Detective.Holmes, 0,0);
		this.listejetonsDetective[1] = new JetonDetective(Detective.Toby, 2,2);
		this.listejetonsDetective[2] = new JetonDetective(Detective.Watson, 0,4);
	}
	
	
	
	public Board() {
		
		this.initialiseTuiles();
		this.initialiserListeCartes();
		this.initialiserJetonsDetective();
		this.updateDisplayConsole(this.mancheCourante);
		
	}
	
	public void updateDisplayConsole(int temps) {
		
		
		// initialier les cases vides
		
		for(int i=0;i< this.displayConsole.length;i++) {
			for(int j=0;j< this.displayConsole[i].length;j++) {
				this.displayConsole[i][j] = "*************************";
				if(j == 0 || j == 1) {
					this.displayConsole[i][j] = "****";
				}
				if(j == 2) {
					this.displayConsole[i][j] = "|";
				}
				if(j == 3) {
					this.displayConsole[i][j] = "******";
				}
				if(j == 4) {
					this.displayConsole[i][j] = "|";
				}
				if(j == 8) {
					this.displayConsole[i][j] = "|";
				}
				if(j == 9) {
					this.displayConsole[i][j] = "******";
				}
				if(j == 10) {
					this.displayConsole[i][j] = "|";
				}
				if(j == 11) {
					this.displayConsole[i][j] = "********";
				}
				if(j == 12) {
					this.displayConsole[i][j] = "|";
				}
			}
		}
		
		
		//Tuiles
		
		for(int i=2;i< 2+this.listeTuiles.length;i++) {
			for(int j=5;j< 5+ this.listeTuiles.length;j++) {
				java.lang.String value = "                          ";
				java.lang.String orientation = "";
				
				try {
					switch (this.listeTuiles[i-2][j-5].getOrientation().name()){
		            case "SOUTH":
		                orientation = "T";
		                break;
		            case "WEST":
		            	orientation = "-|";
		            	break;
		            case "NORTH":
		            	orientation = "_|_";
		            	break;
		            case "EAST":
		            	orientation = "|-";
		            	break;

					}
					value  = (j-4+((i-2)*3))+"-"+this.listeTuiles[i-2][j-5].getCharacter().name()+" "+orientation+" "+this.listeTuiles[i-2][j-5].getOrientation().name();
					
				} catch (Exception e) {
					value =  (j-4+((i-2)*3))+"-                       ";
				}
				//System.out.println(value+" "+value.length());
				if(value.length() < 26) {
					for(int k=0; k< 26 - value.length() +1; k++) {
						value = value+" ";
					}
				}
				this.displayConsole[i][j] = value;
			}
		}
		
		//Temps
		for(int i=2;i< this.displayConsole.length-1;i++) {
			for(int j=0;j< 2;j++) {
				
					if(j%2 ==1){
						this.displayConsole[i][j] = ""+(i+(i-(j+1)))+"   ";
					}
					else {
						this.displayConsole[i][j] = ""+(i+(i-(j+3)))+"   ";
					}
					if ((i+(i-(j+1))) == temps || (i+(i-(j+3))) == temps) {
						if(temps%2 ==1 && (i+(i-(j+3))) == temps){
							this.displayConsole[i][j] = "SHER";
						}
						if(temps%2 ==0 && (i+(i-(j+1))) == temps){
							this.displayConsole[i][j] = "JACK";
						}
					}
				
				
			}
		}
		
		
		// Detective gauche
		for(int i = 2; i< 5; i++) {
			for (int j = 0; j<3; j++) {
				if(this.listejetonsDetective[j].getPositionY() == 0 & this.listejetonsDetective[j].getPositionX() == i-2) {
					this.displayConsole[i][3] = this.listejetonsDetective[j].getDetective().name();
					if(this.listejetonsDetective[j].getDetective().name().length() < 6) {
						this.displayConsole[i][3] = this.listejetonsDetective[j].getDetective().name()+"  ";
					}
				}
			}
			
		}
		
		//detective Droite
		for(int i = 2; i< 5; i++) {
			for (int j = 0; j<3; j++) {
				if(this.listejetonsDetective[j].getPositionY() == 4 & this.listejetonsDetective[j].getPositionX() == i-2) {
					this.displayConsole[i][9] = this.listejetonsDetective[j].getDetective().name();
					if(this.listejetonsDetective[j].getDetective().name().length() < 6) {
						this.displayConsole[i][9] = this.listejetonsDetective[j].getDetective().name()+"  ";
					}
				}
			}
			
		}
		
		//Detective haut
		for(int k = 5;k< 8; k++) {
			for (int j = 0; j<3; j++) {
				if(this.listejetonsDetective[j].getPositionY() == k-4 & this.listejetonsDetective[j].getPositionX() == 0) {
					java.lang.String value = this.listejetonsDetective[j].getDetective().name();
					if(value.length() < 26) {
						for(int i=0; i< 26 - value.length() +1; i++) {
							value = value+" ";
						}
					}
					this.displayConsole[0][k] = value;
				}
			}
			
		}
		
		//Detective Bas
		for(int k = 5;k< 8; k++) {
			for (int j = 0; j<3; j++) {
				if(this.listejetonsDetective[j].getPositionY() == k-4 & this.listejetonsDetective[j].getPositionX() == 2) {
					java.lang.String value = this.listejetonsDetective[j].getDetective().name();
					//System.out.println(value.length()+", "+(26-(value.length()+1)));
					if(value.length() < 26) {
						for(int l=0; l< 26-(value.length()+1); l++) {
							//System.out.println(l);
							value = value+" ";
						}
					}
					//System.out.println(value.length());
					
					this.displayConsole[6][k] = value;
				}
			}
			
		}
		
		//Jetons Action
		for(int k = 2;k< 6; k++) {
			java.lang.String value = "********";
			try {
				value = this.listeJetonsAction[k-2].getNumero()+"-"+this.listeJetonsAction[k-2].getJeton().name();
			} catch (Exception e) {
				// TODO: handle exception
			}
			
			//System.out.println(value.length()+", "+(26-(value.length()+1)));
			if(value.length() < 8) {
				for(int l=0; l< 8-(value.length()+1); l++) {
					//System.out.println(l);
					value = value+" ";
				}
			}
			//System.out.println(value.length());
			
			
			this.displayConsole[k][11] = value;
		}
		

		
	}
	
	public void displayConsole(java.lang.String message,java.lang.String jack) {
		
		if(Game.getCurrentPlayer().getisJack()) {
			System.out.println("Sablier = "+Game.getCurrentPlayer().getSablier());
		}
		
		
		for(int i=0;i< this.displayConsole.length;i++) {
			for(int j=0;j< this.displayConsole[i].length;j++) {
				System.out.print(this.displayConsole[i][j]+" ");
			}
			System.out.println();
			System.out.println();
		}
		System.out.println();
		System.out.println(jack);
		System.out.println();
		System.out.println(message);
		System.out.println();
	}

	
	
	public void updateTuiles(int visible) {
		this.updateListeTuilesVues();
		switch (visible) {
		case 2:
			for(int i=0;i<this.listeTuiles.length;i++) {
				for(int j=0;j<this.listeTuiles[i].length;j++) {
					if(this.listeTuilesVues.contains(this.listeTuiles[i][j])) {
						this.listeTuiles[i][j] = null;
					}
				}
				
			}
			break;
		case 1:
			for(int i=0;i<this.listeTuiles.length;i++) {
				for(int j=0;j<this.listeTuiles[i].length;j++) {
					if(!this.listeTuilesVues.contains(this.listeTuiles[i][j])) {
						this.listeTuiles[i][j] = null;
					}
				}
			}
			break;
		default:
			break;
		}
	}
	
	// s = de combien de cases deplacer
	public void updatePositionDetective(int s, int num) {
		switch (s) {
		case 1:
			switch (this.listejetonsDetective[num].getPositionX()) {
			case 0:
				if(this.listejetonsDetective[num].getPositionY() < 4) {
					this.listejetonsDetective[num].setPositionY(this.listejetonsDetective[num].getPositionY()+1);
				}
				else {
					this.listejetonsDetective[num].setPositionX(this.listejetonsDetective[num].getPositionX()+1);
				}
				break;
			case 1:
				if(this.listejetonsDetective[num].getPositionY() == 4) {
					this.listejetonsDetective[num].setPositionX(this.listejetonsDetective[num].getPositionX()+1);
				}
				else {
					this.listejetonsDetective[num].setPositionX(this.listejetonsDetective[num].getPositionX()-1);
				}
				break;
			case 2:
				if(this.listejetonsDetective[num].getPositionY() > 0) {
					this.listejetonsDetective[num].setPositionY(this.listejetonsDetective[num].getPositionY()-1);
				}
				else {
					this.listejetonsDetective[num].setPositionX(this.listejetonsDetective[num].getPositionX()-1);
				}
				break;
			default:
				break;
			}
			break;
		case 2:
			
			switch (this.listejetonsDetective[num].getPositionX()) {
			case 0:
				if(this.listejetonsDetective[num].getPositionY() < 3) {
					this.listejetonsDetective[num].setPositionY(this.listejetonsDetective[num].getPositionY()+2);
				}
				else {
					this.listejetonsDetective[num].setPositionX(this.listejetonsDetective[num].getPositionY()-2);
					this.listejetonsDetective[num].setPositionY(4);
					//System.out.println(this.listejetonsDetective[num].getPositionX()+", "+this.listejetonsDetective[num].getPositionY());
				}
				break;
			case 1:
				if(this.listejetonsDetective[num].getPositionY() == 4) {
					this.listejetonsDetective[num].setPositionX(this.listejetonsDetective[num].getPositionX()+1);
					this.listejetonsDetective[num].setPositionY(this.listejetonsDetective[num].getPositionY()-1);
				}
				else {
					this.listejetonsDetective[num].setPositionY(this.listejetonsDetective[num].getPositionX());
					this.listejetonsDetective[num].setPositionX(this.listejetonsDetective[num].getPositionX()-1);
					
				}
				break;
			case 2:
				if(this.listejetonsDetective[num].getPositionY() > 1) {
					this.listejetonsDetective[num].setPositionY(this.listejetonsDetective[num].getPositionY()-2);
				}
				else {
					this.listejetonsDetective[num].setPositionX(this.listejetonsDetective[num].getPositionY());
					this.listejetonsDetective[num].setPositionY(0);
				}
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}


	public static void shuffle(Object[] array) {
		 
		  // voir le tableau comme une List
		  java.util.List<Object> list = Arrays.asList(array);
		 
		  // mélanger
		  Collections.shuffle(list);
		}
	
	public void faireActions(int saisie) {
		switch (saisie) {
		case 0:
			if(Game.getCurrentPlayer().getisJack()) {
				 Cartes maCarte = this.listeCartes[0];
				 Game.getCurrentPlayer().addAlibiCard(maCarte);
				 this.listeCartes = Arrays.copyOfRange(this.listeCartes, 1, this.listeCartes.length);
			}
			else {
				Cartes maCarte = this.listeCartes[0];
				deleteInlisteTuiles(maCarte);
				this.listeCartes = Arrays.copyOfRange(this.listeCartes, 1, this.listeCartes.length);
			}
			break;
		case 1:
				System.out.println("Veuillez saisir le numero du premier quartier");
				int quartier1 = scan.nextInt();
				System.out.println("Veuillez saisir le numero du second quartier");
				int quartier2 = scan.nextInt();
				updatelisteTuiles(saisie, quartier1, quartier2, 0);
			break;
		case 2:
			System.out.println("Veuillez choisir entre deplacer Holmes de: (1 - une case), (2 - 2 cases)");
			int cases = scan.nextInt();
			updatePositionDetective(cases,0);
			break;
		case 3:
			if(Game.getCurrentPlayer().getisJack()) {
				System.out.println("Voulez-vous deplacer un Detective : (0 - Oui ), (1 - Non)");
				int vouloir = scan.nextInt();
				if(vouloir == 0) {
					System.out.println("Veuillez choisir quel Detective deplacer de une case: (0 -Holmes ), (1 - Toby), (2 - Watson)");
					int detective = scan.nextInt();
					updatePositionDetective(1,detective);
				}
			}
			else {
				System.out.println("Veuillez choisir quel Detective deplacer de une case: (0 -Holmes ), (1 - Toby), (2 - Watson)");
				int detective = scan.nextInt();
				updatePositionDetective(1,detective);
			}
			break;
		case 4:
			System.out.println("Veuillez saisir le numero du quartier");
			int quartier3 = scan.nextInt();
			System.out.println("Veuillez saisir le sens de rotation : (1 - droite), (2 - gauche)");
			int sens = scan.nextInt();
			System.out.println("Puis choisir entre: (1 - demi tour), (2 - quart de tour)");
			int tour = scan.nextInt();
			updatelisteTuiles(saisie, quartier3, tour, sens);
			break;
		case 5:
			System.out.println("Veuillez choisir entre deplacer Toby de: (1 - une case), (2 - 2 cases)");
			int cases1 = scan.nextInt();
			updatePositionDetective(cases1,1);
			break;
		case 6:
			System.out.println("Veuillez choisir entre deplacer Watson de: (1 - une case), (2 - 2 cases)");
			int cases2 = scan.nextInt();
			updatePositionDetective(cases2,2);
			break;

		default:
			break;
		}
	}
	
	public void refreshBoard(int saisie, int step) {
			switch (step) {
			case 1:
				this.initialiserlisteJetonsAction();
				shuffle(this.listeJetonsAction);
				this.updateDisplayConsole(this.mancheCourante);
				break;
			default:
				faireActions(saisie);
				updatelisteJetonsAction(saisie);
				this.updateDisplayConsole(this.mancheCourante);
				break;
			}
	}

	public int getTourCourant() {
		return tourCourant;
	}

	public void setTourCourant(int tourCourant) {
		this.tourCourant = tourCourant;
	}

	public Character getCharacterJack() {
		return characterJack;
	}
	
	public void removeElement(Object[] arr, int removedIdx) {
	    System.arraycopy(arr, removedIdx + 1, arr, removedIdx, arr.length - 1 - removedIdx);
	}
	
	public void setCharacterJack() {
		for (int i=0;i<this.listeTuiles.length;i++) {
			for (int j=0;j<this.listeTuiles.length;j++) {
				if(this.listeTuiles[i][j].getisJack()) {
					this.characterJack = this.listeTuiles[i][j].getCharacter();
				}
			}
		}
		int index = 0;
		for(int i=0;i<this.listeCartes.length;i++) {
			if(this.listeCartes[i].getNom() ==  this.characterJack.name() ) {
				index = i;
			}
		}
		
		
		//System.out.println();
		removeElement(this.listeCartes, index);
		
		this.listeCartes = Arrays.copyOfRange(this.listeCartes, 0, this.listeCartes.length-1);
		for(int p =0; p<this.listeCartes.length;p++) {
			//System.out.print(this.listeCartes[p].getNom()+ " ");
		}
		//System.out.println();
		
	}

	public java.util.List<Tuiles> getListeTuilesVues() {
		return listeTuilesVues;
	}

	public void updateListeTuilesVues() {
		for(int i=0;i<this.listejetonsDetective.length;i++) {
			JetonDetective detective = this.listejetonsDetective[i];
			java.util.List<Tuiles> listesNonVues = new ArrayList<Tuiles>();
			
			
			
			switch (detective.getPositionY() ) {
			case 0:
				for(int j = 0; j< this.listeTuiles.length; j++) {
					try {
						if(this.listeTuiles[detective.getPositionX()][j].getOrientation().name() == "EAST") {
							listesNonVues.add(this.listeTuiles[detective.getPositionX()][j]);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				
				break;
			case 4:
				for(int j = 0; j< this.listeTuiles.length; j++) {
					try {
						if(this.listeTuiles[detective.getPositionX()][j].getOrientation().name() == "WEST") {
							listesNonVues.add(this.listeTuiles[detective.getPositionX()][j]);
						}
					} catch (Exception e) {
						// TODO: handle exception
					}
				}
				break;
			default:
				switch (detective.getPositionX() ) {
				case 0:
					for(int j = 0; j< this.listeTuiles.length; j++) {
						try {
							if(this.listeTuiles[j][detective.getPositionY()-1].getOrientation().name() == "SOUTH") {
								listesNonVues.add(this.listeTuiles[j][detective.getPositionY()-1]);
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					
					break;
				case 2:
					for(int j = 0; j< this.listeTuiles.length; j++) {
						try {
							if(this.listeTuiles[j][detective.getPositionY()-1].getOrientation().name() == "NORTH") {
								listesNonVues.add(this.listeTuiles[j][detective.getPositionY()-1]);
							}
						} catch (Exception e) {
							// TODO: handle exception
						}
					}
					break;
				default:
					
					break;
				}
				break;
			}
			
			/*
			System.out.println( detective.getDetective().name()+" : "+detective.getPositionX()+","+detective.getPositionY());
			for (Iterator iterator = listesNonVues.iterator(); iterator.hasNext();) {
				Tuiles tuiles = (Tuiles) iterator.next();
				System.out.println(tuiles.getCharacter().name());
			}
			*/
			
			switch (detective.getPositionY() ) {
			case 0:
				for(int j = 0; j< this.listeTuiles.length; j++) {
					try {
						//System.out.println(detective.getPositionX()+","+j+":"+!listesNonVues.contains(this.listeTuiles[detective.getPositionX()][j]));
						if(!listesNonVues.contains(this.listeTuiles[detective.getPositionX()][j]) ) {
							
							this.listeTuilesVues.add(this.listeTuiles[detective.getPositionX()][j]);
						}
						else {
							break;
						}
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(j+","+(detective.getPositionY()-1)+":"+e.getMessage());
					}
				}
				
				break;
			case 4:
				for(int j = this.listeTuiles.length-1; j>=0; j--) {
					try {
						//System.out.println(detective.getPositionX()+","+j+":"+!listesNonVues.contains(this.listeTuiles[detective.getPositionX()][j]));
						if(!listesNonVues.contains(this.listeTuiles[detective.getPositionX()][j]) ) {
							
							this.listeTuilesVues.add(this.listeTuiles[detective.getPositionX()][j]);
						}
						else {
							break;
						}
					} catch (Exception e) {
						System.out.println(j+","+(detective.getPositionY()-1)+":"+e.getMessage());
					}
				}
				break;
			default:
				switch (detective.getPositionX() ) {
				case 0:
					for(int j = 0; j< this.listeTuiles.length; j++) {
						try {
							//System.out.println(j+","+(detective.getPositionY()-1)+":"+!listesNonVues.contains(this.listeTuiles[j][detective.getPositionY()-1]));
							if(!listesNonVues.contains(this.listeTuiles[j][detective.getPositionY()-1]) ) {
								
								this.listeTuilesVues.add(this.listeTuiles[j][detective.getPositionY()-1]);
							}
							else {
								break;
							}
						} catch (Exception  e) {
							// TODO: handle exception
							System.out.println(j+","+(detective.getPositionY()-1)+":"+e.getMessage());
						}
					}
					
					break;
				case 2:
					for(int j = this.listeTuiles.length-1; j>=0 ; j--) {
							try {
								//System.out.println(j+","+(detective.getPositionY()-1)+":"+!listesNonVues.contains(this.listeTuiles[j][detective.getPositionY()-1]));
								if(!listesNonVues.contains(this.listeTuiles[j][detective.getPositionY()-1]) ) {
									
									this.listeTuilesVues.add(this.listeTuiles[j][detective.getPositionY()-1]);
								}
								else {
									break;
								}
							} catch (Exception e2) {
								System.out.println(j+","+(detective.getPositionY()-1)+":"+e2.getMessage());
							}
					break;
					}
				default:
					
					break;
				}
				break;
			}
			/*System.out.println("------------------------------");
			for (Iterator iterator = this.listeTuilesVues.iterator(); iterator.hasNext();) {
				Tuiles tuiles = (Tuiles) iterator.next();
				try {
					System.out.println(tuiles.getCharacter().name());
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
			System.out.println("*************************************************************");
			*/
		}
	}
	
}


