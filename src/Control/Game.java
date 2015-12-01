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

	private Field field;
	private MainWindow mainWindow;
	private Menu menu;
	private int lastPositionClick[];
	private GameState state;
	
	public Game(String server) {
		
		this.mainWindow = new MainWindow(this);
		this.startGame(server);
		this.lastPositionClick = new int[2];
		
	}
	
	public static void main(String[] args) {
		Game game = new Game(null);
		game.startGame(null);
	}
	
	public void handClick(int[] position) {
		
		if (this.state == GameState.JG_ESCOLHER_CARTA_MAO) {
			this.lastPositionClick = position;
			this.state = GameState.JG_ESCOLHER_CARTA_CAMPO1;
		}
		
	}
	
	public void camp1Click(int[] position) {
		
		if (this.state == GameState.JG_ESCOLHER_CARTA_CAMPO1) {
			if (this.field.validAddPosition(position)) {
				this.state = GameState.AO_ESCOLHER_CARTA_CAMPO1;
				this.selectHand(this.lastPositionClick, position);
			}
		}
		else if (this.state == GameState.AO_ESCOLHER_CARTA_CAMPO1) {
			if (this.field.getCardOnPosition(position) != null) {
				this.lastPositionClick = position;
				this.state = GameState.AO_ESCOLHER_CARTA_CAMPO2;
			}
		}
	}
	
	public void camp2Click(int[] position) {
		
		if (this.field.getCardOnPosition(position) != null) {
			this.state = GameState.RECEBER_JOGADA;
			this.attackOpponent(this.lastPositionClick, position);
		}
			
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
		
		this.state = GameState.JG_ESCOLHER_CARTA_MAO;
		mainWindow.setVisible(true);
		mainWindow.showNewField(this.field);
		
	}


	public void selectHand(int[] positionHand, int[] positionField) {
		
		List<Card> player1Hand = this.field.getPlayer1Hand();
		Card selectedCard = player1Hand.get(positionHand[1]);
		try {
			this.field.addCard(selectedCard, positionField);
			this.field.getPlayer1().removeFromHand(selectedCard);
		} catch (InvalidPositionException e) {
			// fazer um joptionpane para avisar posiçao invalida
			e.printStackTrace();
		}
		
		mainWindow.showNewField(this.field);
	}




	public void attackOpponent(int[] positionCamp1, int[] positionCamp2) {
		
		Battle battle = new Battle(this.field.getCardOnPosition(positionCamp1), this.field.getCardOnPosition(positionCamp2));
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
