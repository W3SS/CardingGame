package Model;

import Exception.EffectFailEx;

public class teste {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Card card = new NormalCard("ogro", 3, 2, 1);
		// 2 mana to use, 5 buff atack, -3 buff def, 2 more mana to use
		Card card2 = new MagicCard("Spiked Club", 2, 5, -3, 2);
		Card card3 = new NormalCard("dragon", 3, 8, 2);
		System.out.println("Attack before effect: " + card.getAttack());
		System.out.println("Defense before effect: " + card.getDefense());
		System.out.println("Mana before effect: " + card.getManaToUse());
		try {
			card.makeEffectHappens(card2.getEffect());
		} catch (EffectFailEx e) {
			e.printStackTrace();
		}
		System.out.println("Attack after effect: " + card.getAttack());
		System.out.println("Defense after effect: " + card.getDefense());
		System.out.println("Mana after effect: " + card.getManaToUse());
		
		Battle btb = new Battle(card, card3);
		System.out.println("Winner : " + btb.getWinner().getName());
		System.out.println("Loser : " + btb.getLoser().getName());
		
		

	}

}
