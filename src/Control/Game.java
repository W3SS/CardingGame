package Control;

import java.util.List;
import java.util.Random;

import Exception.EmptyDeckException;
import Exception.FullHandException;
import Exception.InvalidPositionException;
import Model.*;
import View.JOptionPaneTools;
import View.MainWindow;
import View.Menu;
import br.ufsc.inf.leobr.cliente.Jogada;
import rede.AtorNetGames;

public final class Game {

	private Field field;
	private MainWindow mainWindow;
	private Menu menu;
	private int lastPositionClick[];
	private GameState state;
	private AtorNetGames netGames;
	private int whoStart;
	private DeckEnum decktype;
	private StartMessage startMessage;
	private boolean isConnected;
	
	public static void main(String[] args) {
		boolean saiu = false;
		Game game = new Game();
		game.showMenu();
	}
	
	public Game() {
		
		this.mainWindow = new MainWindow(this);
		this.netGames = new AtorNetGames(this);
		this.lastPositionClick = new int[2];
		this.whoStart = new Random().nextInt(1000);
		this.state = GameState.INICIANDO_PARTIDA;
		this.isConnected = false;
		//this.decktype = DeckEnum.DC;
		
	}
	
	private void showMenu() {
		if (!this.isConnected) {
			showConnectMenu();
		} else {
			showStartMenu();
		}
	}
	
	public void setState(GameState state) {
		this.state = state;
		this.mainWindow.setState(state);
	}
	
	private void showConnectMenu() {
		int connect = JOptionPaneTools.askOption("", new String[] {"Conectar", "Sair"});
		if (connect == 0) {
			this.isConnected = this.connect();
			if (!this.isConnected) {
				JOptionPaneTools.message("Não foi possível conectar ao servidor", "");
			}
		} else if (connect == 1) {
			return;
		}
		this.showMenu();
	}
	
	private void showStartMenu() {
		int start = JOptionPaneTools.askOption("", new String[] {"Iniciar", "Desconectar"});
		if (start == 0) {
			this.startMatch();
		} else if (start == 1) {
			this.netGames.desconectar();
			this.showMenu();
		}
	}

	public void setNotConnected() {
		JOptionPaneTools.message("NetGames Desconectado", "");
		this.isConnected = false;
	}
	
	public void handClick(int[] position) {
//		System.out.println(position[1]);
		if (this.state == GameState.JG_ESCOLHER_CARTA_MAO) {
			if (this.field.validHandPosition(position)) {
				this.lastPositionClick = position;
				setState(GameState.JG_ESCOLHER_CARTA_CAMPO1);
			}
		}
		
	}
	
	public void camp1Click(int[] position) {
		
		if (this.state == GameState.JG_ESCOLHER_CARTA_CAMPO1) {
			if (this.field.validAddPosition(position)) {
				setState(GameState.AO_ESCOLHER_CARTA_CAMPO1);
				this.selectHand(this.lastPositionClick, position);
			}
		}
		else if (this.state == GameState.AO_ESCOLHER_CARTA_CAMPO1) {
			if (this.field.getCardOnPosition(position) != null) {
				this.lastPositionClick = position;
				this.setState(GameState.AO_ESCOLHER_CARTA_CAMPO2);
//				System.out.println("ESCOLHER C1 " + position[1]);
			}
		}
	}
	
	public void camp2Click(int[] position) {
		
		if (this.state == GameState.AO_ESCOLHER_CARTA_CAMPO2)
			if (this.field.getCardOnPosition(position) != null 	) {
//				System.out.println("ESCOLHER C2 " + position[1]);
				this.setState(GameState.AO_ESCOLHER_CARTA_CAMPO1);
				this.attackOpponent(this.lastPositionClick, position);
			}
			
	}
	
	public void clickEndTurn() {
		
		if (this.state == GameState.AO_ESCOLHER_CARTA_CAMPO1 || this.state == GameState.AO_ESCOLHER_CARTA_CAMPO2) {
			this.setState(GameState.RECEBER_JOGADA);
			this.endTurn();
		}
	}
	
	public boolean connect() {
		Random rand = new Random();
		String playerId = rand.nextInt()+"";
		String defaultValue = "localhost";
		String host = JOptionPaneTools.askString("Insira o host do servidor:", defaultValue);
		return netGames.conectar(playerId, host);
	}
	
	public void finalizeMatch(int status) {
		String message;
		switch(status) {
			case 1:
				message = "A partida foi encerrada por um jogador.";
				break;
			default:
				message = "Partida encerrada.";
		}
//		enviar novo tipo de mensagem (encerrar parrtida) via netgames
		this.endMatch();
		//this.netGames.finalizarPartidaComErro(message);
	}
	
	public GameState getState() {
		return state;
	}

	public void endMatch() {
		//TODO
		this.mainWindow.setVisible(false);
		this.mainWindow = new MainWindow(this);
		this.showMenu();
	}
	
	public void startMatch() {
//		this.startMessage = new StartMessage(this.whoStart);
		netGames.iniciarPartida();
//		netGames.enviarJogada(startMessage);
		
	}
	
	public void startNewMatch(int order) {
		if (order == 1) {
			this.setState(GameState.JG_ESCOLHER_CARTA_MAO);
			this.decktype = DeckEnum.DC;
		} else {
			this.setState(GameState.RECEBER_JOGADA);
			this.decktype = DeckEnum.MARVEL;
		}
		
		CardShop cardShop = new CardShop();
		List<Card> deck = cardShop.getDeck(this.decktype);
		
		Player player1 = new Player("Jogador1", this.decktype, deck);
		Player player2 = new Player("Jogador2", null, null);
		this.field = new Field(player1, player2);

		Random generator = new Random();
		for (int i=0; i<5; i++) {
			int indexRand = generator.nextInt(player1.getDeck().size());
			try {
				player1.addHandCard(player1.getDeck().get(indexRand));
				player1.removeFromDeck(player1.getDeck().get(indexRand));
			} catch (FullHandException e) {
				//fazer um joptionpane para avisar mão cheia
				e.printStackTrace();
			}
		}
		for (Card card : player1.getDeck() ) {
			System.out.println(card.getName());
			System.out.println(card.getId());
		}
		
		mainWindow.setVisible(true);
		mainWindow.draw(this.field);
		
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
		System.out.println("ENVIANDO");
		Move move = new Move();
		move.setBattles(this.field.getBattles());
		move.setCardsOn1(this.field.getCardsOn1());
		move.setCardsOn2(this.field.getCardsOn2());
		move.setPoints(this.field.getPoints());
		this.netGames.enviarJogada(move);
		System.out.println("ENVIADO");
	}
	
	public void receiveMove(Jogada jogada) {
		System.out.println("RECEBEU");
		if (this.state == GameState.RECEBER_JOGADA) {
			if (jogada instanceof Move) {
				Move move = (Move) jogada;
				move.invertData();
				this.field.parseMove(move);
				this.setState(GameState.JG_ESCOLHER_CARTA_MAO);
				System.out.println(this.field.getPlayer1Hand().size());
				if (this.field.getPlayer1Hand().size() < 5) {
					System.out.println("HUE\nassas");
					try {
//						System.out.println(this.field.getPlayer1().popDeck().getName());
						this.field.getPlayer1().addHandCard(this.field.getPlayer1().popDeck());
						this.field.getPlayer1().popDeck();
						this.field.getPlayer1().popDeck();
						this.field.getPlayer1().popDeck();
						this.field.getPlayer1().popDeck();
						this.field.getPlayer1().popDeck();
					} catch (FullHandException e) {
					} catch (EmptyDeckException e) {
						if (this.field.getPlayer1Hand().isEmpty()) {
							this.endMatch();
						}
					} 
				}
				System.out.println("BABA");
				this.mainWindow.showNewField(this.field);
			}
		}
		System.out.println("PROCESSOU RECEBIDO");
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
