package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Exception.EmptyDeckException;
import Exception.FullHandException;

public class Player {
	
	private static final int STARTPOINTS = 35;
	
	private int points;
	private List<Card> deck;
	private List<Card> handCards;
	private DeckEnum deckType;
	
	public Player(DeckEnum deckType, List<Card> deck) {
		
		this.points = STARTPOINTS;
		this.deckType = deckType;
		this.deck = deck;
		this.handCards = new ArrayList<Card>();
	}
	
	public int getPoints() {
		return this.points;
	}
	
	public List<Card> getDeck() {
		return deck;
	}

	public List<Card> getHandCards() {
		return this.handCards;
	}
	
	public void setPoints(int points) {
		this.points = points;
	}
	
	public void applyDamage(int damage) {
		this.points -= damage;
	}
	
	public void removeFromHand(Card card) {
		this.handCards.remove(card);
	}

	
	public Card popDeck() throws EmptyDeckException {
		if (this.deck.isEmpty()) {
			throw new EmptyDeckException("O baralho está vazio");
		}
		Card topCard = this.deck.get(new Random().nextInt(this.deck.size()));
		this.deck.remove(topCard);
		return topCard;
	}
	
	public void addHandCard(Card card) throws FullHandException {
		if (this.handCards.size() > 4) {
			throw new FullHandException("A mão está cheia para comprar uma nova carta!");
		}
		else {
			this.handCards.add(card);
		}
	}
	
	public void removeFromDeck(Card card) {
		this.deck.remove(card);
	}
	
	public DeckEnum getDeckType() {
		return this.deckType;
	}

	
}
