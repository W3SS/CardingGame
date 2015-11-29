package Model;


import java.sql.*;


public class teste {

	public static void main(String[] args) {
	/*	// TODO Auto-generated method stub
	 * 	// Criando arquivo de banco de dados com as tabelas
	 *  // Adicionando cartas ao banco de dados  
	*/
		
		
		CardShop cardShop = new CardShop();
		
		/* CONECTA, PRIMEIRA TABELA , FECHA BD
		cardShop.connectDatabase();
		cardShop.createTable("CREATE TABLE NORMALCARD " +
                			"(ID INT PRIMARY KEY     NOT NULL," +
                			" NAME           TEXT    NOT NULL, " + 
                			" ATACK          INT     NOT NULL, " +
                			" DEFENSE        INT     NOT NULL, " + 
                			" MANA           INT     NOT NULL)"  );
		//CONECTA, SEGUNDA TABELA, FECHA BD
		cardShop.connectDatabase();
		cardShop.createTable("CREATE TABLE MAGICCARD " +
                			"(ID INT PRIMARY KEY     NOT NULL," +
                			" NAME           TEXT    NOT NULL, " +
                			" MANA           INT     NOT NULL, " +
                			" ATACKBUFF      INT     NOT NULL, " +
                			" DEFENSEBUFF    INT     NOT NULL, " + 
                			" MANABUFF       INT     NOT NULL)"  );
		*/
		
		cardShop.connectDatabase("./database/cards.db");
		
		

	}

}
