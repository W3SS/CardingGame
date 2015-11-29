package Control;

import Model.*;
import View.MainWindow;
import View.Menu;

public class Game {

	Field field;
	MainWindow mainWindow;
	Menu menu;
	
	public Game(String server) {
		
		this.startGame(server);
		
	}
	
	
	
	
	private void startGame(String server) {
		
		/* net games conexões
		 * 
		 * 
		 * 
		 */
		
		Player player1 = new Player("Jogador1", MainWindow.getChoosenDeck());
		
	}




	public static void selectHand() {
		
	}
		
	
	
}
