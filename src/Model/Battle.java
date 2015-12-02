package Model;

import java.io.Serializable;
import java.lang.Math;

public class Battle implements Serializable {
	
	protected Card winner;
	protected Card loser;
	protected String report;
	protected int damage;
	
	
	public Battle(Card card1, Card card2) {
		
		if (card1.getAttack() > card2.getDefense()) {
		
			this.winner = card1;
			this.loser = card2;
		
		} else {

			this.winner = card2;
			this.loser = card1;
			
		}
		
		this.damage = Math.abs(card1.getAttack() - card2.getDefense());
		
		if (damage != 0) {
			this.report = "Vencedor: "; 
		} else {
			this.report = "Perdedor: ";
		}
		this.report += this.winner.getName() + 
				"\nPerdedor: " + this.loser.getName() + 
				"\nDano: " + this.damage;
		
	}
	
	public Card getWinner() {
		
		return this.winner;
		
	}
	
	public Card getLoser() {
		
		return this.loser;
		
	}

	public String getReport() {
		
		return this.report;
		
	}
	
	public int getDamage() {
		
		return this.damage;
		
	}

}