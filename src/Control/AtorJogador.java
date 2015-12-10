package Control;

public class AtorJogador { 

	protected Game game;
	
	public AtorJogador(Game game) {
		this.game = game;
	}
		
	public void selectHand(int[] positionHand, int[] positionField) {
		game.selectHand(positionHand, positionField);
	}
	
	public void attackOpponent(int[] positionCamp1, int[] positionCamp2) {
		game.attackOpponent(positionCamp1, positionCamp2);
	}
	
	public void endTurn() {
		game.endTurn();
	}
	
	public void exit() {
		game.exit();
	}
	
	public void connect(String host) {
		if (host == null)
			host = "0";
		game.connect(host);
	}
	
	public void startMatch() {
		game.startMatch();
	}
	
	public void disconnect() {
		game.disconnect();
		game.showStartScreen();
	}
	
	public void endMatch() {
		game.endTurn();
		game.endMatch(EndStatus.FINISHED_BY_LOCAL_USER);
	}
	
}
