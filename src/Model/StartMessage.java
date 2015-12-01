package Model;

import br.ufsc.inf.leobr.cliente.Jogada;

public class StartMessage implements Jogada {

	private int randomStart;
	
	public StartMessage( int randomStart) {
		this.randomStart = randomStart;
	}
	
	public int getRandomStart() {
		return this.randomStart;
	}
	
	
}
