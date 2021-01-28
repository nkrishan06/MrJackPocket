package game;

import java.util.ArrayList;
import java.util.List;

import actions.Cartes;
import actions.Character;

public class Player {
	
	private String name;
	private List<Cartes> alibi = new ArrayList<>();
	private boolean isJack=false;
	private Character identiteJack = null;
	private int sablier;
	
	
	
	public Player(String name, boolean isJack) {
		setName(name);
		setisJack(isJack);
		this.sablier = 0;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}
	
	public boolean getisJack() {
		return isJack;
	}
	
	public void setisJack(boolean isJack) {
		this.isJack=isJack;
	}
	
	public List<Cartes> getAlibiCards() {
		return alibi;
	}

	public void setAlibiCards(List<Cartes> alibi) {
		this.alibi = alibi;
	}

	public void addAlibiCard(Cartes alibiCards) {
		System.out.println("Card added : " + alibiCards.getNom() + " ,  sablier "+ alibiCards.getSablier());
		alibi.add(alibiCards);
		this.sablier = this.sablier + alibiCards.getSablier();
	}

	public void removeAlibiCard(Cartes alibiCards) {
		alibi.remove(alibiCards);
	}

	public Character getIdentiteJack() {
		return identiteJack;
	}

	public void setIdentiteJack(Character identiteJack) {
		this.identiteJack = identiteJack;
	}

	public int getSablier() {
		return sablier;
	}

	public void setSablier(int sablier) {
		this.sablier = sablier;
	}

}
