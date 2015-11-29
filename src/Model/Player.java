package Model;

import java.util.ArrayList;
import java.util.List;

import Exception.EmptyDeckException;
import Exception.FullHandException;

public class Player {
	
	private static final int STARTPOINTS = 30;
	
	private String name;
	private int points;
	private CardShop shop;
	private List<Card> deck;
	private List<Card> handCards;
	private DeckEnum deckType;
	private int status;
	private boolean isTurn;
	
	public Player(String name, DeckEnum deckType, List<Card> deck) {
		
		this.name = name;
		this.points = STARTPOINTS;
		this.deckType = deckType;
		this.deck = deck;
		this.handCards = new ArrayList<Card>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getPoints() {
		return this.points;
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
		Card topCard = this.deck.get(0);
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
