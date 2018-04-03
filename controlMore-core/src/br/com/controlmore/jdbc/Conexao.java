package br.com.controlmore.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	private static Connection[] conexoes = new Connection[10];
	private static int qual = 0;
	
public static Connection getConnection() {
		
		try {
			if(qual >9) {
				return conexoes[(int)(Math.random() * 9)];
			} else {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				Connection conexao = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "gabi", "123");
				conexoes[qual++] = conexao;
				return conexao;
			}
		} catch (SQLException e) {
			//relançando a exception
			throw new RuntimeException(e);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
