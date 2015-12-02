package View;

import javax.swing.JOptionPane;

public final class JOptionPaneTools {
	
	public static void main(String[] args) {
		askOption("", new String[] {"Conectar", "Sair"});
		askOption("", new String[] {"Iniciar Partida", "Desconectar"});
	}

	public static String askString(String text, String defaultValue){
		return JOptionPane.showInputDialog(null, text, defaultValue);
	}
	
	public static void message(String text, String title){
		JOptionPane.showMessageDialog(null, text, title, JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static int askOption(String text, String[] options){
		return JOptionPane.showOptionDialog(null, text, "Marvel vs. DC", 
	        1, JOptionPane.PLAIN_MESSAGE, 
	        null, options, null);
	
	}
	
}
