package actions;

import java.util.*;

import java.awt.*;


public class Cartes {
	private Character character;
	private int sablier;
	
	/** 
	 * * Définit une carte alibi avec le nom d'un personnage et le nombre de sablier
	 */
	public Cartes(Character character, int sablier) {
		this.character=character;
		this.sablier=sablier;
	}
	
	/**
	 * Renvoie le nom du personnage 
	 */
	
	public String getNom() {
		return character.name();
		
	}
	
	/**
	 * Renvoie le nombre de sablier
	 */
	
	public int getSablier() {
		return sablier;
	}
	
	/*
	 * 
	 */
	
	public void setNom(Character character) {
		this.character=character;
	}
	
	/*
	 * 
	 */
	
	public void setSablier(int sablier) {
		this.sablier=sablier;
	}
}
