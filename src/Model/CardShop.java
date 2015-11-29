/**
 * 
 */
package Model;

import java.sql.*;
import java.util.List;

/**
 * @author victorfeijo
 *
 */


public class CardShop {
	
//	public static final String DC = "DC";
//	public static final String MARVEL = "Marvel";
//	private String dbPath;
	private Connection connection;
	private Statement statement;
	
	
	public void connectDatabase(String dbPath) {
		
		try {
			Class.forName("org.sqlite.JDBC");
			this.connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			this.connection.setAutoCommit(false);
			System.out.println("Opened database successfully");
			this.statement = this.connection.createStatement();		
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public List<Card> getDeck(DeckEnum type) {
		
		//TODO
		
		
	}

}
