package br.com.controlmore.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Pessoa;
import br.com.controlmore.dominio.Questionario;
import br.com.controlmore.dominio.Usuario;

public class PessoaDAO extends AbstractDAO {

	@Override
	public String salvar(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade; //Casting
		
		String sql = "INSERT INTO Usuario (Dt_Cadastro, Nome, Email, Senha, Telefone, Dt_Nascimento) VALUES (?, ?, ?, ?, ?,?)";

		try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
			
			// substituir todas ? pelos valores corretos
			Timestamp time = new Timestamp(pessoa.getDtCadastro().getTime());
			preparador.setTimestamp(1, time);
			preparador.setString(2, pessoa.getNome());
			preparador.setString(3, pessoa.getUsuario().getEmail());
			preparador.setString(4, pessoa.getUsuario().getSenha());
			preparador.setString(5, pessoa.getTelefone());
			preparador.setDate(6, new java.sql.Date(pessoa.getDtNascimento().getTime()));
			
			// executar o comando no banco
			preparador.execute();
		} catch (SQLException e) {
			
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}
		
		return null;
	}

	@Override
	public String alterar(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade; //Casting
		String sql;
		Pessoa pDAO = (Pessoa) consultar(pessoa).get(0);
		if(pDAO.getQuestionario()!= pessoa.getQuestionario()){
			sql="UPDATE Usuario SET Questionario=? WHERE ID=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				// substituir todas ? pelos valores corretos
				preparador.setInt(1, pessoa.getQuestionario().getId());
				preparador.setInt(2, pessoa.getId());
				
				// executar o comando no banco
				preparador.execute();
			} catch (SQLException e) {
				try{
					//desfaz alteração no banco
					conexao.rollback();
				}catch(SQLException e1) {
					return e1.toString();
				}
				return e.toString();
			}
		}
		if(pessoa.getNome()!= pDAO.getNome()){
			sql = "UPDATE Usuario SET Nome=? WHERE ID=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				
				// substituir todas ? pelos valores corretos
				preparador.setString(1, pessoa.getNome());
				preparador.setInt(2, pessoa.getId());
				
				// executar o comando no banco
				preparador.execute();
			} catch (SQLException e) {
				try{
					//desfaz alteração no banco
					conexao.rollback();
				}catch(SQLException e1) {
					return e1.toString();
				}
				return e.toString();
			}
		}
		if(pessoa.getUsuario().getSenha()!= pDAO.getUsuario().getSenha()){
			sql = "UPDATE Usuario SET Senha=? WHERE ID=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				
				// substituir todas ? pelos valores corretos
				preparador.setString(1, pessoa.getUsuario().getSenha());
				preparador.setInt(2, pessoa.getId());
				
				// executar o comando no banco
				preparador.execute();
			} catch (SQLException e) {
				try{
					//desfaz alteração no banco
					conexao.rollback();
				}catch(SQLException e1) {
					return e1.toString();
				}
				return e.toString();
			}
		}
		if(pessoa.getUsuario().getEmail()!= pDAO.getUsuario().getEmail()){
			sql = "UPDATE Usuario SET Email=? WHERE ID=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				
				// substituir todas ? pelos valores corretos
				preparador.setString(1, pessoa.getUsuario().getEmail());
				preparador.setInt(2, pessoa.getId());
				
				// executar o comando no banco
				preparador.execute();
			} catch (SQLException e) {
				try{
					//desfaz alteração no banco
					conexao.rollback();
				}catch(SQLException e1) {
					return e1.toString();
				}
				return e.toString();
			}
		}
		if(pessoa.getDtNascimento() != pDAO.getDtNascimento()){
			sql = "UPDATE Usuario SET Dt_Nascimento=? WHERE ID=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				
				// substituir todas ? pelos valores corretos
				preparador.setDate(1, new java.sql.Date(pessoa.getDtNascimento().getTime()));
				preparador.setInt(2, pessoa.getId());
				
				// executar o comando no banco
				preparador.execute();
			} catch (SQLException e) {
				try{
					//desfaz alteração no banco
					conexao.rollback();
				}catch(SQLException e1) {
					return e1.toString();
				}
				return e.toString();
			}
		}
		if(pessoa.getTelefone() != pDAO.getTelefone()){
			sql = "UPDATE Usuario SET Telefone=? WHERE ID=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				
				// substituir todas ? pelos valores corretos
				preparador.setString(1, pessoa.getTelefone());
				preparador.setInt(2, pessoa.getId());
				
				// executar o comando no banco
				preparador.execute();
			} catch (SQLException e) {
				try{
					//desfaz alteração no banco
					conexao.rollback();
				}catch(SQLException e1) {
					return e1.toString();
				}
				return e.toString();
			}
		}
		return null;
	}

	@Override
	public String excluir(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade; //Casting
		
		String sql = "DELETE FROM Usuario WHERE ID=?";
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
			
			// substituir todas ? pelos valores corretos
			preparador.setInt(1, pessoa.getId());
			
			// executar o comando no banco
			preparador.execute();
			
		} catch (SQLException e) {
			
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();			
		}
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Pessoa pessoa = (Pessoa) entidade; //Casting
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT Usuario.Id as Id_Usu, Nome, Email, Senha, Dt_Nascimento, ");
		sql.append("Dt_Cadastro, Telefone, Questionario, Q.Id as Id_Quest, Renda, ");
		sql.append("Compra, Agua, Luz, Internet, Recarga, Transporte, Aluguel, ");
		sql.append("Educacao, Lazer, Outros ");
		sql.append("FROM Usuario ");
		sql.append("LEFT JOIN Questionario Q On Q.Id = Usuario.Questionario ");
		sql.append("WHERE Usuario.Id =?");
		
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){// Statement que vai gerenciar o SQL
			preparador.setInt(1, pessoa.getId());
			
			ResultSet resultado = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			List<EntidadeDominio> pessoas = new ArrayList<EntidadeDominio>(); //cria lista de frequencias
			
			while(resultado.next())
			{
				Usuario usuario = new Usuario();
				usuario.setEmail(resultado.getString("Email"));
				usuario.setSenha(resultado.getString("Senha"));
				
				Questionario questionario = new Questionario();
				questionario.setId(resultado.getInt("Id_Quest"));
				questionario.setRenda(resultado.getFloat("Renda"));
				questionario.setCompra(resultado.getFloat("Compra"));
				questionario.setAgua(resultado.getFloat("Agua"));
				questionario.setLuz(resultado.getFloat("Luz"));
				questionario.setInternet(resultado.getFloat("Internet"));
				questionario.setRecarga(resultado.getFloat("Recarga"));
				questionario.setTransporte(resultado.getFloat("Transporte"));
				questionario.setAluguel(resultado.getFloat("Aluguel"));
				questionario.setEducacao(resultado.getFloat("Educacao"));
				questionario.setLazer(resultado.getFloat("Lazer"));
				questionario.setOutros(resultado.getFloat("Outros"));
				
				pessoa = new Pessoa();
				pessoa.setUsuario(usuario);
				pessoa.setQuestionario(questionario);
				pessoa.setNome(resultado.getString("Nome"));
				pessoa.setDtNascimento(resultado.getDate("Dt_Nascimento"));
				pessoa.setTelefone(resultado.getString("Telefone"));
				pessoa.setId(resultado.getInt("Id_Usu"));

				pessoas.add(pessoa);
			}//fim while result
			return pessoas;
		}catch (SQLException e) {
			e.printStackTrace();
		}//fim try-catch
		return null;
	}

	public Pessoa autenticar (Pessoa pessoa){
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT Usuario.Id as Id_Usu, Nome, Email, Senha, Dt_Nascimento, ");
		sql.append("Dt_Cadastro, Telefone, Questionario, Q.Id as Id_Quest, Renda, ");
		sql.append("Compra, Agua, Luz, Internet, Recarga, Transporte, Aluguel, ");
		sql.append("Educacao, Lazer, Outros ");
		sql.append("FROM Usuario ");
		sql.append("LEFT JOIN Questionario Q On Q.Id = Usuario.Questionario ");
		sql.append("WHERE Email=? AND Senha=?");
		
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
			preparador.setString(1, pessoa.getUsuario().getEmail());
			preparador.setString(2,pessoa.getUsuario().getSenha());
			
			ResultSet resultado = preparador.executeQuery(); //Para obter retorno da Query que será executada
			if(resultado.next()){
				Usuario usuario = new Usuario();
				usuario.setEmail(resultado.getString("Email"));
				usuario.setSenha(resultado.getString("Senha"));
				
				Questionario questionario = new Questionario();
				questionario.setId(resultado.getInt("Id_Quest"));
				questionario.setRenda(resultado.getFloat("Renda"));
				questionario.setCompra(resultado.getFloat("Compra"));
				questionario.setAgua(resultado.getFloat("Agua"));
				questionario.setLuz(resultado.getFloat("Luz"));
				questionario.setInternet(resultado.getFloat("Internet"));
				questionario.setRecarga(resultado.getFloat("Recarga"));
				questionario.setTransporte(resultado.getFloat("Transporte"));
				questionario.setAluguel(resultado.getFloat("Aluguel"));
				questionario.setEducacao(resultado.getFloat("Educacao"));
				questionario.setLazer(resultado.getFloat("Lazer"));
				questionario.setOutros(resultado.getFloat("Outros"));
				
				pessoa.setUsuario(usuario);
				pessoa.setQuestionario(questionario);
				pessoa.setId(resultado.getInt("Id_Usu"));
				pessoa.setNome(resultado.getString("Nome"));
				pessoa.setDtNascimento(resultado.getDate("Dt_Nascimento"));
				pessoa.setTelefone(resultado.getString("Telefone"));
				
				return pessoa;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
