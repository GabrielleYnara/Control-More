package br.com.controlmore.testes;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.controlmore.jdbc.Conexao;



public class TesteConexao {

	public static void main(String[] args) {
		try {
			Connection con = Conexao.getConnection();
			System.out.println("Conectou :D");
		} catch (Exception e) {
			e.printStackTrace();
		}

		

	}

}