/**
 * 
 */
package Model;

import Exception.EffectFailEx;

/**
 * @author victorfeijo
 *
 */
public class MagicCard implements Card {
	
	private String name;
	private int mana;
	private Effect effect;
	
	/** Create the card and the effect of it, the magic card don't have attack and defense.
	 * 
	 * @param The name of the card.
	 * @param The attack buff from the effect of this card.
	 * @param The defense buff from the effect of this card.
	 */
	public MagicCard(String name, int mana,  int effectBuffAttack, int effectBuffDefense, int effectBuffMana) {
		
		this.name = name;
		this.mana = mana;
		this.effect = new Effect(effectBuffAttack, effectBuffDefense, effectBuffMana);
		
	}

	/** 
	 * @return Name of the card.
	 */
	@Override
	public String getName() {
		
		return this.name;
		
	}

	/** 
	 * @return Magic card don't have attack.
	 */
	@Override
	public int getAttack() {

		return 0;
		
	}

	/**
	 * @return Magic card don't have defense.
	 */
	@Override
	public int getDefense() {

		return 0;
		
	}

	/** 
	 * @return The object Effect from the card.
	 */
	@Override
	public Effect getEffect() {

		return this.effect;
		
	}

	/** On this case, nothing will happens because is a magic card, and you can't buff a magic. 
	 * 
	 * @param A effect from the same of different card.
	 * @throws EffectFailEx
	 */
	@Override
	public void makeEffectHappens(Effect effect) throws EffectFailEx {
		
		throw new EffectFailEx("You can't use a effect on a magic card");

	}
	
	/** 
	 * @param Magic card don't have attack.
	 */
	@Override
	public void setAttack(int attack) {
		
		return;
		
	}
	 
	 /**
	 * @param Magic card don't have defense.
	 */
	@Override
	public void setDefense(int defense) {
		
		return;
		
	}

	@Override
	public int getManaToUse() {

		return mana;
	}

	@Override
	public void setManaToUse(int mana) {
		
		return;
		
	}

}
