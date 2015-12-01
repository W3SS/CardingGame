package Model;

import java.util.Map;

import br.ufsc.inf.leobr.cliente.Jogada;

import java.util.List;

public class Move implements Jogada {

//	protected Map<Integer, Card> cardsOn1;
//	protected Map<Integer, Card> cardsOn2;
//	protected List<Battle> battles;
	protected int points1;
	protected int points2;
	
	public Map<Integer, Card> getCardsOn1() {
		return null;//cardsOn1;
	}
	public void setCardsOn1(Map<Integer, Card> cardsOn1) {
//		this.cardsOn1 = cardsOn1;
	}
	public Map<Integer, Card> getCardsOn2() {
		return null;//cardsOn2;
	}
	public void setCardsOn2(Map<Integer, Card> cardsOn2) {
//		this.cardsOn2 = cardsOn2;
	}
	public List<Battle> getBattles() {
		return null;//battles;
	}
	public void setBattles(List<Battle> battles) {
//		this.battles = battles;
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
		Map<Integer, Card> tempCards;
		int tempPoints;
//		tempCards = this.cardsOn1;
//		this.cardsOn1 = this.cardsOn2;
//		this.cardsOn2 = tempCards;
		tempPoints = this.points1;
		this.points1 = this.points2;
		this.points2 = tempPoints;
	}

	
	
}
