package Model;

public class StartMessage implements Jogada {

	private DeckEnum deckType;
	
	StartMessage(DeckEnum deckType) {
		this.deckType = deckType;
	}
	
	public DeckEnum getDeckType() {
		return this.deckType;
	}
	
	public void setDeckType(DeckEnum deckType) {
		this.deckType = deckType;
	}
	
	
}
