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
	
	public void createMagicCard(String name, String mana, Effect effect) {
		
		try {
			
			ResultSet result = this.statement.executeQuery( "SELECT * FROM MAGICCARD;" );
			int id = 999;
			while (result.next()) {
				id = result.getInt("id") + 1;
			}
			String insert = "INSERT INTO MAGICCARD (ID, NAME, MANA, ATACKBUFF, DEFENSEBUFF, MANABUFF) " +
			"VALUES (" + id + ", '" + name + "', " + mana + ", " + effect.getBuffAttack() + ", " + effect.getBuffDefense() +
			", " + effect.getBuffMana() + " );";
			
			System.out.println(insert);
			
			this.statement.executeUpdate(insert);
			this.statement.close();
			this.connection.commit();
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

}
