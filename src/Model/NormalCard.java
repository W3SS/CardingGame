/**
 * 
 */
package Model;

/**
 * @author victorfeijo
 *
 */
public class NormalCard implements Card {
	
	private String name;
	private int attack;
	private int defense;
	private int mana;
	private Effect effect;
	
	/** Create the card and a fake effect with 0/0 buff.
	 * 
	 * @param The name of the card
	 * @param The attack in number
	 * @param The defense in number
	 */
	public NormalCard(String name, int mana, int attack, int defense) {
		
		this.name = name;
		this.mana = mana;
		this.attack = attack;
		this.defense = defense;
		this.effect = new Effect(0, 0, 0);
		
	}

	/** 
	 * @return Name of the card.
	 */
	@Override
	public String getName() {
		
		return this.name;
		
	}

	/** 
	 * @return Number of attack from the card.
	 */
	@Override
	public int getAttack() {

		return this.attack;
		
	}

	/**
	 * @return Number of defense from the card.
	 */
	@Override
	public int getDefense() {

		return this.defense;
		
	}

	/** 
	 * @return The object Effect from the card ( will be a effect with 0/0 ).
	 */
	@Override
	public Effect getEffect() {

		return this.effect;
		
	}

	/** On this case, a effect from other card will occur on this card.
	 * 
	 * @param A effect from the same of different card.
	 */
	@Override
	public void makeEffectHappens(Effect effect)  {
		
		effect.happens(this);
		
		if (this.attack < 0) {
			this.attack = 0;
		}
		if (this.defense < 0) {
			this.defense = 0;
		}

	}
	
	/** 
	 * @param a new attack for the card.
	 */
	@Override
	public void setAttack(int attack) {
		
		this.attack = attack;
		
	}
	 
	 /**
	 * @param a new defense for the card.
	 */
	@Override
	public void setDefense(int defense) {
		
		this.defense = defense;
		
	}

	@Override
	public int getManaToUse() {

		return mana;
		
	}

	@Override
	public void setManaToUse(int mana) {
		
		this.mana = mana;
		
	}

}
