package Control;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;

import Exception.EmptyDeckException;
import Exception.FullHandException;
import Exception.InvalidPositionException;
import Model.*;
import View.JOptionPaneTools;
import View.MainWindow;
import View.Menu;
import br.ufsc.inf.leobr.cliente.Jogada;
import rede.AtorNetGames;

public class Game {

	private Field field;
	private MainWindow mainWindow;
	private Menu menu;
	private int lastPositionClick[];
	private GameState state;
	private AtorNetGames netGames;
	private int whoStart;
	private DeckEnum decktype;
	private StartMessage startMessage;
	
	public Game() {
		
		this.mainWindow = new MainWindow(this);
		this.netGames = new AtorNetGames(this);
		this.lastPositionClick = new int[2];
		this.whoStart = new Random().nextInt(1000);
		this.state = GameState.INICIANDO_PARTIDA;
		//this.decktype = DeckEnum.DC;
		
	}
	
	public static void main(String[] args) {
		boolean saiu = false;
		boolean connected = false;
		Game game = new Game();

//		while (!saiu) {

			if (!connected) {
				int connect = JOptionPaneTools.askOption("", new String[] {"Conectar", "Sair"});
				if (connect == 0) {
					connected = game.connect();
				} else if (connect == 1) {
					saiu = true;
				}
//				continue;
			}
				int start = JOptionPaneTools.askOption("", new String[] {"Iniciar", "Desonectar"});
				if (start == 0) {
					game.startMatch();
					
				} else if (start == 1) {
					//disconnect!!!
					connected = false;
				}
//		}
	}

	
	public void handClick(int[] position) {
//		System.out.println(position[1]);
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
//				System.out.println("ESCOLHER C1 " + position[1]);
			}
		}
	}
	
	public void camp2Click(int[] position) {
		
		if (this.state == GameState.AO_ESCOLHER_CARTA_CAMPO2)
			if (this.field.getCardOnPosition(position) != null 	) {
//				System.out.println("ESCOLHER C2 " + position[1]);
				this.state = GameState.AO_ESCOLHER_CARTA_CAMPO1;
				this.attackOpponent(this.lastPositionClick, position);
			}
			
	}
	
	public void clickEndTurn() {
		
		if (this.state == GameState.AO_ESCOLHER_CARTA_CAMPO1 || this.state == GameState.AO_ESCOLHER_CARTA_CAMPO2) {
			this.state = GameState.RECEBER_JOGADA;
			this.endTurn();
		}
	}
	
	public boolean connect() {
		return netGames.conectar("jogador", "localhost");
	}
	
	public void startMatch() {
//		this.startMessage = new StartMessage(this.whoStart);
		netGames.iniciarPartida();
//		netGames.enviarJogada(startMessage);
		
	}
	
	
	
	public void startNewMatch(int order) {
		
	//	JOptionPaneTools.message(order+"", "");
//		if (this.whoStart > startMessage.getRandomStart()) {
//			this.state = GameState.JG_ESCOLHER_CARTA_MAO;
//			this.decktype = DeckEnum.DC;
//		} else {
//			this.state = GameState.RECEBER_JOGADA;
//			this.decktype = DeckEnum.MARVEL;
//		}
////		this.netGames.enviarJogada(this.startMessage);
//		this.startGame();

		if (order == 1) {
			this.state = GameState.JG_ESCOLHER_CARTA_MAO;
			this.decktype = DeckEnum.DC;
		} else {
			this.state = GameState.RECEBER_JOGADA;
			this.decktype = DeckEnum.MARVEL;
		}
		
		CardShop cardShop = new CardShop();
		List<Card> deck = cardShop.getDeck(this.decktype);
//		List<Card> deck2 = cardShop.getDeck(DeckEnum.DC);
		
		Player player1 = new Player("Jogador1", this.decktype, deck);
		Player player2 = new Player("Jogador2", null, null);
		this.field = new Field(player1, player2);
		//
//		int postest[] = {2, 0};
//		this.field.addCamp2(deck2.get(0), postest);
		//
		//
//		int postest1[] = {2, 1};
//		this.field.addCamp2(deck2.get(2), postest1);
		//
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
		
		//this.state = GameState.JG_ESCOLHER_CARTA_MAO;
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
		
	}
	
	public void endTurn() {
		// TODO Auto-generated method stub
		Move move = new Move();
		move.setBattles(this.field.getBattles());
		move.setCardsOn1(this.field.getCardsOn1());
		move.setCardsOn2(this.field.getCardsOn2());
		this.netGames.enviarJogada(move);
	}
	
	public void receiveMove(Jogada jogada) {
		
		//System.out.println("JOGADA RECEBIDA");
		if (this.state == GameState.RECEBER_JOGADA) {
			if (jogada instanceof Move) {
				Move move = (Move) jogada;
				move.invertData();
				this.field.parseMove(move);
				this.state = GameState.JG_ESCOLHER_CARTA_MAO;
			}
		}
//		if (this.state == GameState.INICIANDO_PARTIDA) {
//			if (jogada instanceof StartMessage) {
//				StartMessage startMessage = (StartMessage)jogada;
//				if (this.whoStart > startMessage.getRandomStart()) {
//					this.state = GameState.JG_ESCOLHER_CARTA_MAO;
//					this.decktype = DeckEnum.DC;
//				} else {
//					this.state = GameState.RECEBER_JOGADA;
//					this.decktype = DeckEnum.MARVEL;
//				}
////				this.netGames.enviarJogada(this.startMessage);
//				this.startGame();
//			}
//		}
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
