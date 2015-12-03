package Model;

import java.util.Map;
import br.ufsc.inf.leobr.cliente.Jogada;

import java.util.HashMap;
import java.util.List;

public class Move implements Jogada {

	protected Card[] cardsOn1;
	protected Card[] cardsOn2;
	protected List<Battle> battles;
	protected int points1;
	protected int points2;
	
	
	// Convert hashmap to array
	private Card[] serializeMap(Map<Integer, Card> map) {
		Card[] array = new Card[5];
		for (int k = 0; k < 5; k++) {
			if (map.containsKey(k)) {
				array[k] = map.get(k); 
			} else {
				array[k] = null;
			}
		}
		return array;
	}
	
	// Convert array to hashmap
	private Map<Integer, Card> deserializeMap(Card[] array) {
		Map<Integer, Card> map = new HashMap<Integer, Card>(); 
		for (int k = 0; k < 5; k++) {
			if (array[k] != null) {
				map.put(k, array[k]);
			}
		}
		return map;
	}

	public Map<Integer, Card> getCardsOn1() {
		return this.deserializeMap(this.cardsOn1);
	}
	
	public void setCardsOn1(Map<Integer, Card> cardsOn1) {
		this.cardsOn1 = this.serializeMap(cardsOn1);		
	}
	
	public Map<Integer, Card> getCardsOn2() {
		return this.deserializeMap(this.cardsOn2);
	}
	
	public void setCardsOn2(Map<Integer, Card> cardsOn2) {
		this.cardsOn2 = this.serializeMap(cardsOn2);	
	}
	
	public List<Battle> getBattles() {
		return this.battles;
	}
	
	public void setBattles(List<Battle> battles) {
		this.battles = battles;
	}
	
	public void setPoints(int[] points) {
		this.points1 = points[0];
		this.points2 = points[1];
	}
	
	public int[] getPoints() {
		int[] points = new int[2];
		points[0] = this.points1;
		points[1] = this.points2;
		return points;
	}
	
	public void invertData() {
		Card[] tempCards;
		int tempPoints;
		tempCards = this.cardsOn1;
		this.cardsOn1 = this.cardsOn2;
		this.cardsOn2 = tempCards;
		tempPoints = this.points1;
		this.points1 = this.points2;
		this.points2 = tempPoints;
	}
	
}
