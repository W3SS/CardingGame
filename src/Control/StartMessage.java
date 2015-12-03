package Control;

import Model.DeckEnum;
import br.ufsc.inf.leobr.cliente.Jogada;

public class StartMessage implements Jogada{

	private DeckEnum selectedDeck;

	
	public DeckEnum getSelectedDeck() {
		return this.selectedDeck;
	}

	public void setSelectedDeck(DeckEnum selectedDeck) {
		this.selectedDeck = selectedDeck;
	}
	
}
