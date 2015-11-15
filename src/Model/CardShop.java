/**
 * 
 */
package Model;

import java.sql.*;

/**
 * @author victorfeijo
 *
 */


public class CardShop {
	
	public static final String DC = "DC";
	public static final String MARVEL = "Marvel";
	private String dbPath;
	private Connection connection;
	private Statement statement;
	
	public CardShop(String dbPath) {
		
		this.dbPath = dbPath;
		
	}
	
	public void connectDatabase() {
		
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
	
	public void createTable(String table) {
		
		try {
			this.statement.executeUpdate(table);
			this.statement.close();
			this.connection.commit();
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Card getRandomCard(String type) {
		
		//TODO
		if (type == CardShop.DC) return new Card("Dc", 0, 0);
		if (type == CardShop.MARVEL) return new Card("Marvel", 0, 0);
		
		return null;
		
	}

}
