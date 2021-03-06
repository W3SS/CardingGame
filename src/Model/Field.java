/**
 * 
 */
package Model;

import Exception.InvalidPositionException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Control.EndStatus;


/**
 * @author victorfeijo
 *
 */
public class Field {
	
	private static final int FIELDWIDTH = 5;
	
	// Player on this game:
	private Player player1;
	// Enemy:
	private Player player2;
	// Cards on the field of player1:
	private Map<Integer, Card> cardsOn1;
	// Cards on the field of player1:
	private Map<Integer, Card> cardsOn2;
	// Battle history from this game 
	private List<Battle> battles;
	
	public Field(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.cardsOn1 = new HashMap<Integer, Card>();
		this.cardsOn2 = new HashMap<Integer, Card>();
		this.battles = new ArrayList<Battle>();
	}

	public Map<Integer, Card> getCardsOn1() {
		return this.cardsOn1;
	}

	public void setCardsOn1(Map<Integer, Card> cardsOn1) {
		this.cardsOn1 = cardsOn1;
	}

	public Map<Integer, Card> getCardsOn2() {
		return this.cardsOn2;
	}

	public void setCardsOn2(Map<Integer, Card> cardsOn2) {
		this.cardsOn2 = cardsOn2;
	}

	public List<Battle> getBattles() {
		return this.battles;
	}
	
	public int[] getPoints() {
		int[] points = new int[2];
		points[0] = this.player1.getPoints();
		points[1] = this.player2.getPoints();
		return points;
	}
	
	public void setPoints(int[] points) {
		this.player1.setPoints(points[0]);
		this.player2.setPoints(points[1]);
	}
	
	public boolean isEnemyCardsEmpty() {
		return this.cardsOn2.isEmpty();
	}
	
	public void removeCard(Card card) {
		if (this.cardsOn1.containsValue(card)) {
			removeCardFromCollection(card, this.cardsOn1);
		} else {
			removeCardFromCollection(card, this.cardsOn2);
		}
	}
	
	private void removeCardFromCollection(Card card, Map<Integer, Card> collection) {
		for (Map.Entry<Integer, Card> actualEntry : collection.entrySet()) {
			if (card.getId() == actualEntry.getValue().getId()) {
				collection.remove(actualEntry.getKey());
				break;
			}
		}
	}
	
	// check if the field exists
	public boolean validPosition(int[] fieldPos) {
		// X=fieldPos[0] represents cardsOnX
		// fieldPos[1] represents the key (position) inside cardsOnX

		// fieldPos must have length 2:
		if (fieldPos.length != 2)
			return false;
		
		// fieldPos[0] must be 1 or 2:
		else if (fieldPos[0] != 1 && fieldPos[0] != 2)
			return false;
		
		// checks if the field exists:
		else if (fieldPos[1] < 0 && fieldPos[1] >= FIELDWIDTH)
			return false;
		
		// position is valid:
		else
			return true;
		
	}
	
	public boolean validHandPosition(int[] fieldPos) {
		return (fieldPos[1] < this.getPlayer1Hand().size()) && (fieldPos[0] == 0); 
	}
	
	public boolean validAddPosition(int[] fieldPos) {
		
		if (!this.validPosition(fieldPos)) {
			return false;
		}
	
		else if (fieldPos[0] == 2)
			return false;
		
		else
			return true;
	}	
	
	public void addCard(Card card, int[] fieldPos) throws InvalidPositionException {
		
		if (!this.validAddPosition(fieldPos)) {
			throw new InvalidPositionException("Attempt to add card in an invalid position");
		}
		
		this.cardsOn1.put(fieldPos[1], card);
		
	}
	
	public void parseMove(Move move) {
		
		this.setPoints(move.getPoints());
		this.cardsOn1 = move.getCardsOn1();
		this.cardsOn2 = move.getCardsOn2();
		this.setBattles(move.getBattles());
		
	}
	
	private void setBattles(List<Battle> battles) {
		this.battles = battles;
	}

	public List<Card> getPlayer1Hand() {
		return this.player1.getHandCards();
	}
	
	public Player getPlayer1() {
		return this.player1;
	}
	
	public Card getCardOnPosition(int position[]) {
		if (position[0] == 1) {
			return cardsOn1.get(position[1]);
		} else {
			return cardsOn2.get(position[1]);
		}
	}

	public Player getPlayer2() {
		return this.player2;
	}

	public void addBattle(Battle battle) {
		this.battles.add(battle);
	}
	
	public boolean isPlayer1HandFull() {
		return this.getPlayer1Hand().size() >= 5;
	}
	
	public void addCamp2(Card card, int position[]) {
		this.cardsOn2.put(position[1], card);
	}

	public String getLog(EndStatus endStatus) {
		String log = "SEU DECK: " + this.player1.getDeckType() + "\n\n\nBATALHAS:";
		for (Battle battle : this.battles) {
			log += "\n\n" + battle.getReport();
		}
		switch (endStatus) {
			case NETGAMES_PROBLEM:
				log += "\n\n\nPartida encerrada pelo NetGames.";
			break;
			case FINISHED_BY_LOCAL_USER:
				log += "\n\n\nVocê encerrou a partida.";
			break;
			case FINISHED_BY_REMOTE_USER:
				log += "\n\n\nO outro jogador encerrou a partida.";
			break;
			case HAND_EMPTY:
				log += "\n\n\nA mão de um dos jogadores ficou vazia.";
			break;
			case POINTS_OVER:
				log += "\n\n\nUm dos jogadores ficou sem pontos.";
			break;
			case NOT_FINISHED:
			break;
		}
		if (endStatus != EndStatus.NOT_FINISHED) {
			log += "\n\n\nPONTUAÇÃO FINAL:\n\nVocê " + this.player1.getPoints() +
					"\nInimigo: " + this.player2.getPoints() + "\n\n";
			if (this.player1.getPoints() > this.player2.getPoints()) {
				log += "VENCEDOR";
			} else if (this.player1.getPoints() < this.player2.getPoints()) {
				log += "PERDEDOR";
			} else {
				log += "EMPATE";
			}
		}
		return log;
	}
}
