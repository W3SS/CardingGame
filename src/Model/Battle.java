/**
 * 
 */
package Model;

/**
 * @author victorfeijo
 *
 */

public class Battle {
	
	private Card card1;
	private Card card2;
	private Card winner;
	private Card loser;
	
	public Battle(Card card1, Card card2) {
		
		this.card1 = card1;
		this.card2 = card2;
		this.startBattle();
		
	}
	
	public void startBattle() {
		
		if (card1.getAttack() > card2.getDefense()) {
			
			winner = card1;
			loser = card2;
			
		}
		else if (card1.getAttack() < card2.getDefense()) {
			
			winner = card2;
			loser = card1;
			
		} else {
			
			winner = null;
			loser = null;
			
		}
		
	}
	
	public Card getWinner() {
		
		return winner;
		
	}
	
	public Card getLoser() {
		
		return loser;
		
	}

}
