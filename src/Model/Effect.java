/**
 * 
 */
package Model;


/**
 * @author victorfeijo
 *
 */
public class Effect {
	
	private int buffAttack;
	private int buffDefense;
	private int buffMana;
	
	public Effect(int buffAttack, int buffDefense, int buffMana) {
		
		this.buffAttack = buffAttack;
		this.buffDefense = buffDefense;
		this.buffMana = buffMana;
		
	}
	
	public int getBuffAttack() {
		
		return this.buffAttack;
		
	}
	
	public int getBuffDefense() {
		
		return this.buffDefense;
		
	}
	
	public int getBuffMana() {
		
		return this.buffMana;
		
	}
	
	public void happens(Card card) {
		
		if (card instanceof MagicCard) {
			return;
		}
		
		card.setAttack(buffAttack + card.getAttack());
		card.setDefense(buffDefense + card.getDefense());
		card.setManaToUse(buffMana + card.getManaToUse());
		
	}

}
