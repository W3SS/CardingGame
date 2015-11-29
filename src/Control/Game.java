package Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Exception.EmptyDeckException;
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
		DeckEnum type = mainWindow.getChoosenDeck();
		CardShop cardShop = new CardShop();
		List<Card> deck = cardShop.getDeck(type);
		
		Player player1 = new Player("Jogador1", type, deck);
		Player player2 = new Player("Jogador2", null, null);
		this.field = new Field(player1, player2);
		Random generator = new Random();
		for (int i=0; i<5; i++) {
			int indexRand = generator.nextInt(deck.size());
			try {
				player1.addHandCard(deck.get(indexRand));
				player1.removeFromDeck(deck.get(indexRand));
			} catch (FullHandException e) {
				//fazer um joptionpane para avisar mão cheia
				e.printStackTrace();
			}
		}
		mainWindow = new MainWindow();
		mainWindow.showNewField(this.field);
		this.selectHand(mainWindow.getSelectedHandPosition());
	}


	public void selectHand(int[] position) {
		
		List<Card> player1Hand = this.field.getPlayer1Hand();
		if (position[0] == 0) {
			Card selectedCard = player1Hand.get(position[1]);
			int fieldPos[] = mainWindow.getSelectedFieldPos();
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
		mainWindow.showNewField(this.field);
		this.attackOpponent();
	}




	public void attackOpponent() {
		
		while (!this.field.isEnemyCardsEmpty()) {
			int selectedPos[] = mainWindow.getSelectedFieldPos();
			while (selectedPos[0] != 1) {
				//verifica se clicou em encerrar partida
				if (mainWindow.getLastClick()[0] == 3 && mainWindow.getLastClick()[1] == 1) {
					this.endTurn();
				}
				//avisa posição errada
				selectedPos = mainWindow.getSelectedFieldPos();
			}
			int enemyPos[] = mainWindow.getSelectedFieldPos();
			while (enemyPos[0] != 2) {
				//verifica se clicou em encerrar partida
				if (mainWindow.getLastClick()[0] == 3 && mainWindow.getLastClick()[1] == 1) {
					this.endTurn();
				}
				//avisa posiçao enemigo errada
				enemyPos = mainWindow.getSelectedFieldPos();
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
			mainWindow.showNewField(this.field);
		}
		this.endTurn();
		
	}
	
	public void endTurn() {
		// TODO Auto-generated method stub
		
	}
	
	public void receiveMove() {

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
