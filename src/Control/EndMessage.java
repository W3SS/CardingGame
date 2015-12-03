package Control;

import br.ufsc.inf.leobr.cliente.Jogada;

public class EndMessage implements Jogada{

	private EndStatus status;
	
	EndMessage(EndStatus status) {
		this.status = status;
	}

	public EndStatus getStatus() {
		return this.status;
	}

	public void setStatus(EndStatus status) {
		this.status = status;
	}
	
}
