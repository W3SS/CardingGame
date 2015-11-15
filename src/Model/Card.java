/**
 * 
 */
package Model;

/**
 * @author victorfeijo
 *
 */
public class Card {
	
	private String name;
	private int attack;
	private int defense;
	
	/** Create the card and a fake effect with 0/0 buff.
	 * 
	 * @param The name of the card
	 * @param The attack in number
	 * @param The defense in number
	 */
	public Card(String name, int attack, int defense) {
		
		this.name = name;
		this.attack = attack;
		this.defense = defense;
		
	}

	/** 
	 * @return Name of the card.
	 */
	public String getName() {
		
		return this.name;
		
	}

	/** 
	 * @return Number of attack from the card.
	 */
	public int getAttack() {

		return this.attack;
		
	}

	/**
	 * @return Number of defense from the card.
	 */
	public int getDefense() {

		return this.defense;
		
	}
	
	/** 
	 * @param a new attack for the card.
	 */
	public void setAttack(int attack) {
		
		this.attack = attack;
		
	}
	 
	 /**
	 * @param a new defense for the card.
	 */
	public void setDefense(int defense) {
		
		this.defense = defense;
		
	}

}
