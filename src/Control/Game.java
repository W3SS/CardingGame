package Control;

import java.util.List;
import java.util.Random;

import Exception.EmptyDeckException;
import Exception.FullHandException;
import Exception.InvalidPositionException;
import Model.*;
import View.LogWindow;
import View.MainWindow;
import br.ufsc.inf.leobr.cliente.Jogada;
import rede.AtorNetGames;

public final class Game {

	private Field field;
	private MainWindow mainWindow;
	private int lastPositionClick[];
	private GameState state;
	private AtorNetGames netGames;
	private AtorJogador jogador;
	private boolean isConnected;
	
	public static void main(String[] args) {
		Game game = new Game();
		game.showStartScreen();
	}
	
	public Game() {
		this.jogador = new AtorJogador(this);
		this.netGames = new AtorNetGames(this);
		this.mainWindow = new MainWindow(this, jogador);
		this.lastPositionClick = new int[2];
		this.state = GameState.STARTING_MATCH;
		this.isConnected = false;
	}
	
	public void showStartScreen() {
		this.mainWindow.showStartScreen(this.isConnected);
	}
	
	public void disconnect() {
		this.netGames.desconectar();
	}
	
	public void setState(GameState state) {
		this.state = state;
		this.mainWindow.setState(state);
	}
	
	
	public void setNotConnected() {
		this.isConnected = false;
		if (this.state == GameState.STARTING_MATCH) {
			this.mainWindow.showStartScreen(this.isConnected);
		} else {
			this.endMatch(EndStatus.NETGAMES_PROBLEM);
		}
	}
	
	public void handClick(int[] position) {
		if (this.state == GameState.SELECT_HAND_CHOOSING_CARD_ON_HAND) {
			if (this.field.validHandPosition(position)) {
				this.lastPositionClick = position;
				this.setState(GameState.SELECT_HAND_CHOOSING_CARD_ON_1);
			}
		}
		
	}
	
	public void camp1Click(int[] position) {
		
		if (this.state == GameState.SELECT_HAND_CHOOSING_CARD_ON_1) {
			if (this.field.validAddPosition(position)) {
				this.setState(GameState.ATACK_CHOOSING_CARD_ON_1);
				jogador.selectHand(this.lastPositionClick, position);
			}
		}
		else if (this.state == GameState.ATACK_CHOOSING_CARD_ON_1) {
			if (this.field.getCardOnPosition(position) != null) {
				this.lastPositionClick = position;
				this.setState(GameState.ATACK_CHOOSING_CARD_ON_2);
			}
		}
	}
	
	public void camp2Click(int[] position) {
		
		if (this.state == GameState.ATACK_CHOOSING_CARD_ON_2)
			if (this.field.getCardOnPosition(position) != null 	) {
				this.setState(GameState.ATACK_CHOOSING_CARD_ON_1);
				this.attackOpponent(this.lastPositionClick, position);
			}
			
	}
	
	public void alert(String text) {
		this.mainWindow.alert(text);
	}
	
	public void clickEndTurn() {
		
		if (this.state == GameState.ATACK_CHOOSING_CARD_ON_1 || this.state == GameState.ATACK_CHOOSING_CARD_ON_2) {
			this.setState(GameState.RECEIVE_MOVE);
			jogador.endTurn();
		}
	}
	
	public void connect(String host) {
		
		String playerId = new Random().nextInt()+"";
		this.isConnected = this.netGames.conectar(playerId, host);
		if (!this.isConnected) {
			this.mainWindow.alert("Não foi possível conectar ao servidor.");
		}
		this.mainWindow.showStartScreen(this.isConnected);
	}
	
	public GameState getState() {
		return this.state;
	}

	public void endMatch(EndStatus status) {
		if (this.state == GameState.STARTING_MATCH)
			return;
		if (status == EndStatus.FINISHED_BY_LOCAL_USER) {
			this.netGames.enviarJogada(new EndMessage(EndStatus.FINISHED_BY_REMOTE_USER));
		} 
		this.mainWindow.setVisible(false);
		this.mainWindow = new MainWindow(this, jogador);
		this.setState(GameState.STARTING_MATCH);
		this.mainWindow.showStartScreen(this.isConnected);
		new LogWindow(this.field.getLog(status));
	}
	
	public void startMatch() {
		netGames.iniciarPartida();		
	}
	
	public void startNewMatch(int order) {
		if (order == 1) {
			DeckEnum deckType = null;
			while (deckType == null) {
				deckType = mainWindow.chooseDeck();
			}
			StartMessage startMessage = new StartMessage();
			startMessage.setSelectedDeck(deckType);
			netGames.enviarJogada(startMessage);
			this.setState(GameState.SELECT_HAND_CHOOSING_CARD_ON_HAND);
			this.beginMatch(deckType);
		} else {
			this.mainWindow.alertEnemyStarted();			
		}

		
	}

	private void beginMatch(DeckEnum deckType) {
		CardShop cardShop = new CardShop();
		List<Card> deck = cardShop.getDeck(deckType);
		
		Player player1 = new Player(deckType, deck);
		Player player2 = new Player(null, null);
		this.field = new Field(player1, player2);

		Random generator = new Random();
		for (int i=0; i<5; i++) {
			int indexRand = generator.nextInt(player1.getDeck().size());
			try {
				player1.addHandCard(player1.getDeck().get(indexRand));
				player1.removeFromDeck(player1.getDeck().get(indexRand));
			} catch (FullHandException e) {
				e.printStackTrace();
			}
		}
		this.mainWindow.draw(this.field);
	}

	public void selectHand(int[] positionHand, int[] positionField) {
		
		List<Card> player1Hand = this.field.getPlayer1Hand();
		Card selectedCard = player1Hand.get(positionHand[1]);
		try {
			this.field.addCard(selectedCard, positionField);
			this.field.getPlayer1().removeFromHand(selectedCard);
		} catch (InvalidPositionException e) {
			e.printStackTrace();
		}
		
		this.mainWindow.showNewField(this.field);
	}

	public void exit() {
		System.exit(0);
	}

	public void attackOpponent(int[] positionCamp1, int[] positionCamp2) {
		
		Card card1 = this.field.getCardOnPosition(positionCamp1);
		Card card2 = this.field.getCardOnPosition(positionCamp2);
		
		Battle battle = new Battle(card1, card2);
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
		this.mainWindow.showNewField(this.field);
		
	}
	
	public void endTurn() {
		Move move = new Move();
		move.setBattles(this.field.getBattles());
		move.setCardsOn1(this.field.getCardsOn1());
		move.setCardsOn2(this.field.getCardsOn2());
		move.setPoints(this.field.getPoints());
		this.netGames.enviarJogada(move);
		this.checkPoints(this.field.getPoints());
	}
	
	private void checkPoints(int[] points) {
		if (points[0] <=0 || points[1] <=0) {
			this.netGames.enviarJogada(new EndMessage(EndStatus.POINTS_OVER));
		}
	}
	
	public void receiveMove(Jogada jogada) {
		if (this.state == GameState.STARTING_MATCH) {
			if (jogada instanceof StartMessage) {
				StartMessage message = (StartMessage) jogada;
				DeckEnum deckType;
				if (message.getSelectedDeck() == DeckEnum.DC) {
					deckType = DeckEnum.MARVEL;
				} else {
					deckType = DeckEnum.DC;
				}
				this.setState(GameState.RECEIVE_MOVE);
				this.beginMatch(deckType);
			}
		}
		else if (this.state == GameState.RECEIVE_MOVE) {
			if (jogada instanceof Move) {
				Move move = (Move) jogada;
				move.invertData();
				this.field.parseMove(move);
				this.checkPoints(this.field.getPoints());
				this.setState(GameState.SELECT_HAND_CHOOSING_CARD_ON_HAND);
				if (!this.field.isPlayer1HandFull()) {
					try {
						this.field.getPlayer1().addHandCard(this.field.getPlayer1().popDeck());						
					} catch (FullHandException e) {
						e.printStackTrace();
					} catch (EmptyDeckException e) {
						if (this.field.getPlayer1Hand().isEmpty()) {
							this.endMatch(EndStatus.HAND_EMPTY);
							this.netGames.enviarJogada(new EndMessage(EndStatus.HAND_EMPTY));
						}
					} 
				}
				this.mainWindow.showNewField(this.field);
			}
		}
		if (jogada instanceof EndMessage) {
			if (this.state != GameState.STARTING_MATCH) {
				EndMessage message = (EndMessage) jogada; 
				this.endMatch(message.getStatus());
			}
		}
	}

	private Player getCardOwner(Card card) {
		
		Player player1 = this.field.getPlayer1();
		Player owner;
		if (card.getDeckType() == player1.getDeckType()) {
			owner = player1;
		} else {
			owner = this.field.getPlayer2();
		}
		return owner;
	}
	
}
