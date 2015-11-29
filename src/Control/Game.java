package Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Exception.FullHandException;
import Exception.InvalidPositionException;
import Model.*;
import View.MainWindow;
import View.Menu;

public class Game {

	Field field;
	MainWindow mainWindow;
	Menu menu;
	
	public Game(String server) {
		
		this.startGame(server);
		
	}
	
	
	
	
	public void startGame(String server) {
		
		/* net games conexões
		 * 
		 * 
		 * 
		 */
		DeckEnum type = MainWindow.getChoosenDeck();
		CardShop cardShop = new CardShop();
		List<Card> deck = cardShop.getDeck(type);
		
		Player player1 = new Player("Jogador1", type, deck);
		Player player2 = new Player("Jogador2", null, null);
		this.field = new Field(player1, player2);
		Random generator = new Random();
		for (int i=0; i<5; i++) {
			int indexRand = generator.nextInt(20);
			try {
				player1.addHandCard(deck.get(indexRand));
			} catch (FullHandException e) {
				//fazer um joptionpane para avisar mão cheia
				e.printStackTrace();
			}
		}
		MainWindow.showNewField(this.field);
	}


	public void selectHand(int[] position) {
		
		List<Card> player1Hand = this.field.getPlayer1Hand();
		if (position[0] == 0) {
			Card selectedCard = player1Hand.get(position[1]);
			int fieldPos[] = MainWindow.getSelectedFieldPos();
			if (this.field.validAddPosition(fieldPos)) {
				try {
					this.field.addCard(selectedCard, fieldPos);
					this.field.getPlayer1().removeFromHand(selectedCard);
				} catch (InvalidPositionException e) {
					// fazer um joptionpane para avisar posiçao invalida
					e.printStackTrace();
				}
			}
			
		}
		MainWindow.showNewField(this.field);
		this.attackOpponent();
	}




	public void attackOpponent() {
		
		while (!this.field.isEnemyCardsEmpty()) {
			if (MainWindow.getLastClick()[0] == 3 && MainWindow.getLastClick()[1] == 1) {
				break;
			}
			int selectedPos[] = MainWindow.getSelectedFieldPos();
			while (selectedPos[0] != 1) {
				//avisa posição errada
				selectedPos = MainWindow.getSelectedFieldPos();
			}
			int enemyPos[] = MainWindow.getSelectedFieldPos();
			while (enemyPos[0] != 2) {
				//avisa posiçao enemigo errada
				enemyPos = MainWindow.getSelectedFieldPos();
			}
			Battle battle = new Battle(this.field.getCardOnPosition(selectedPos), this.field.getCardOnPosition(enemyPos));
			Card loserCard = battle.getLoser();
			this.field.removeCard(loserCard);
			Player loser = this.getCardOwner(loserCard);
			int damage = battle.getDamage();
			loser.applyDamage(damage);
			if (damage == 0) {
				Card winnerCard = battle.getWinner();
				this.field.removeCard(winnerCard);
			}
			this.field.addBattle(battle);
			MainWindow.showNewField(this.field);
		}
		
	}
	
	public Player getCardOwner(Card card) {
		
		Player player1 = this.field.getPlayer1();
		Player loser;
		if (card.getDeckType() == player1.getDeckType()) {
			loser = player1;
		} else {
			loser = this.field.getPlayer2();
		}
		return loser;
	}
	
}
