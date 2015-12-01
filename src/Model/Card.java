
package Model;

import java.io.Serializable;

public class Card implements Serializable {
	
	private String name;
	private int id;
	private int attack;
	private int defense;
	private DeckEnum deckType;
	private int status;
	
	/** Create the card and a fake effect with 0/0 buff.
	 * 
	 * @param The name of the card
	 * @param The ID of the card
	 * @param The attack in number
	 * @param The defense in number
 	 * @param The type of the deck to which the card belongs to
*/
	public Card(String name, int id, int attack, int defense, DeckEnum deckType) {
		
		this.name = name;
		this.id = id;
		this.attack = attack;
		this.defense = defense;
		this.deckType = deckType;
		this.setStatus(0);
		
	}

	/** 
	 * @return Card name.
	 */
	public String getName() {
		
		return this.name;
		
	}
	
	/** 
	 * @return Card ID.
	 */
	public int getId() {
		
		return this.id;
		
	}

	/** 
	 * @return Card attack number.
	 */
	public int getAttack() {

		return this.attack;
		
	}

	/**
	 * @return Card defense number
	 */
	public int getDefense() {

		return this.defense;
		
	}
	
	/**
	 * @return Type of the deck of the card.
	 */
	public DeckEnum getDeckType() {

		return this.deckType;
		
	}
	
	/** Set a new status to the card
	 * 
	 * @param The card status
 	*/
	public void setStatus(int status) {

		this.status = status;
		
	}
	
}
