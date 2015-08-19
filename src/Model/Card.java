/**
 * 
 */
package Model;

import Exception.EffectFailEx;

/**
 * @author victorfeijo
 *
 */
public interface Card {
	
	public String getName();
	public int getAttack();
	public int getDefense();
	public int getManaToUse();
	public void setAttack(int attack);
	public void setDefense(int defense);
	public void setManaToUse(int mana);
	public Effect getEffect();
	public void makeEffectHappens(Effect effect) throws EffectFailEx;

}
