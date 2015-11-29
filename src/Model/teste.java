package Model;


import java.util.List;


public class teste {

	public static void main(String[] args) {
	/*	// TODO Auto-generated method stub
	 * 	// Criando arquivo de banco de dados com as tabelas
	 *  // Adicionando cartas ao banco de dados  
	*/
		
		
		CardShop cardShop = new CardShop();
		//cardShop.connectDatabase("./database/cards.db");
		
		List<Card> deck = cardShop.getDeck(DeckEnum.MARVEL);
		for (Card card : deck) {
			System.out.println(card.getName());
		}
		

	}

}
