package View;

import javax.swing.JOptionPane;

public final class JOptionPaneTools extends JOptionPane {

	public static String askString(String text, String defaultValue){
		return showInputDialog(null, text, defaultValue);
	}
	
	public static void message(String text, String title){
		showMessageDialog(null, text, title, INFORMATION_MESSAGE);
	}
	
	public static int askOption(String text, String[] options){
		return showOptionDialog(null, text, "Marvel vs. DC", 
	        1, PLAIN_MESSAGE, null, options, null);
	
	}
	
}
