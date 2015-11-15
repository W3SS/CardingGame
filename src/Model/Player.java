package Model;

import java.util.ArrayList;
import java.util.List;

import Exception.EmptyDeckException;
import Exception.FullHandException;

public class Player {
	
	private String name;
	private int lifePoints;
	private List<Card> deck;
	private List<Card> handCards;
	
	public Player(String name, int lifePoints, List<Card> deck) {
		
		this.name = name;
		this.lifePoints = lifePoints;
		this.deck = deck;
		this.handCards = new ArrayList<Card>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getLifePoints() {
		return this.lifePoints;
	}
	
	public List<Card> getDeck() {
		return this.deck;
	}
	
	public List<Card> getHandCards() {
		return this.handCards;
	}
	
	public void setLifePoints(int lifePoints) {
		this.lifePoints = lifePoints;
	}
	
	public Card popDeck() throws EmptyDeckException {
		if (this.deck.isEmpty()) {
			throw new EmptyDeckException("O baralho está vazio");
		}
		Card topCard = this.deck.get(0);
		this.deck.remove(topCard);
		return topCard;
	}
	
	public void addHandCard(Card card) throws FullHandException {
		if (this.handCards.size() > 5) {
			throw new FullHandException("A mão está cheia para comprar uma nova carta!");
		}
		else {
			this.handCards.add(card);
		}
	}

}
