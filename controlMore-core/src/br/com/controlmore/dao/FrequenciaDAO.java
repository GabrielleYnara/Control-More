package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Frequencia;

public class FrequenciaDAO extends AbstractDAO{

	/**
	 * Método Salvar Frequencia
	 * @param entidade - deve receber uma instancia de frequencia
	 * @return retornará: nulo caso dê tudo certo, se der erro irá retornar uma string do erro
	 */
	@Override
	public String salvar(EntidadeDominio entidade) {
		Frequencia frequencia = (Frequencia) entidade; //Casting de entidade para frequencia
		
			String sql = "INSERT INTO Frequencia (Id, Descricao, Qtde_Dias) VALUES (?,?,?)";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				
				// substituir todas ? pelos valores corretos
				preparador.setInt(1, frequencia.getId());
				preparador.setString(2, frequencia.getDescricao());
				preparador.setInt(3, frequencia.getQtdeDias());
				
				// executar o comando no banco
				preparador.execute();
				//salva alteração no banco
				conexao.commit();
			} catch (SQLException e) {
				
				return e.toString();
			}
		
		return null;
	}
	
	/**
	 * Metodo Alterar Frequencia
	 * Pode ser alterada a descricao, a quantidade de dias. Tanto separadamente, quanto amboas ao mesmo tempo
	 * @param entidade - deve receber uma instancia de Frequencia
	 * @return retornará: nulo caso dê tudo certo, se der erro irá retornar uma string do erro
	 */
	@Override
	public String alterar(EntidadeDominio entidade) {
		Frequencia frequencia = (Frequencia) entidade;
		String sql;
		if(frequencia.getId()== 0){
			return "Item não localizado!";
		}
		//Verifica se Descricao é diferente de nulo e se QtdeDias é 0, sendo assim, somente Descricao será alterada
		else if(frequencia.getDescricao()!=null && frequencia.getQtdeDias()== 0)
		{
			sql = "UPDATE Frequencia SET Descricao=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				
				// substituir todas ? pelos valores corretos
				preparador.setString(1, frequencia.getDescricao());
				preparador.setInt(2, frequencia.getId());
				
				// executar o comando no banco
				preparador.execute();
				//salva alteração no banco
				conexao.commit();
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
		// Verifica se descricao e QtdeDias possuem informações, assim, os dois campos serão alterados
		else if(frequencia.getDescricao()!=null && frequencia.getQtdeDias()!= 0)
		{
			sql = "UPDATE Frequencia SET Descricao=?, Qtde_Dias=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				
				// substituir todas ? pelos valores corretos
				preparador.setString(1, frequencia.getDescricao());
				preparador.setInt(2, frequencia.getQtdeDias());
				preparador.setInt(3, frequencia.getId());
				
				// executar o comando no banco
				preparador.execute();
				//salva alteração no banco
				conexao.commit();
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
		else if(frequencia.getQtdeDias()!= 0 && frequencia.getDescricao()==null)
		{
			sql = "UPDATE Frequencia SET Qtde_Dias=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				
				// substituir todas ? pelos valores corretos
				preparador.setInt(1, frequencia.getQtdeDias());
				preparador.setInt(2, frequencia.getId());
				
				// executar o comando no banco
				preparador.execute();
				//salva alteração no banco
				conexao.commit();
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

	/**
	 * Metodo Excluir Frequencia
	 * Irá excluir no banco de acordo com Id da frequencia
	 * @param entidade - deve receber uma instancia de Frequencia
	 * @return retornará: nulo caso dê tudo certo, se der erro irá retornar uma string do erro
	 */
	@Override
	public String excluir(EntidadeDominio entidade) {
		Frequencia frequencia = (Frequencia) entidade;
		String sql = "DELETE FROM Frequencia WHERE id=?";
		if(frequencia.getId()==0){
			return "Item não localizado!";
		}
		else{
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				
				// substituir todas ? pelos valores corretos
				preparador.setInt(1, frequencia.getId());
				
				// executar o comando no banco
				preparador.execute();
				//salva alteração no banco
				conexao.commit();
			} catch (SQLException e) {
				try{
					//desfaz alteração no banco
					conexao.rollback();
				}catch(SQLException e1) {
					return e1.toString();
				}
				return e.toString();
			}
			return null;
		}
	}

	/**
	 * Metodo Consultar Frequencia
	 * Pode trazer o resultado de uma lista de todas as frequencias
	 * Ou somente uma frequencia com determinado Id, ou descricao
	 * @param entidade - deve receber uma instancia de frequencia
	 * @return retonará uma lista de frequencias cadastradas
	 */
	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Frequencia frequencia = (Frequencia) entidade;
		String sql=null;

		if(frequencia.getDescricao()==null)
		{
			frequencia.setDescricao("");
		}
		if(frequencia.getId()==0 && frequencia.getDescricao().equals(""))
		{
			sql="SELECT * FROM Frequencia";
		}
		else if(frequencia.getId()!=0 && frequencia.getDescricao().equals(""))
		{
			sql="SELECT * FROM Frequencia WHERE Id=?";
		}
		else if(frequencia.getId()==0 && !frequencia.getDescricao().equals(""))
		{
			sql="SELECT * FROM Frequencia WHERE Descricao=?";
		}
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statement que vai gerenciar o SQL
			if(frequencia.getId()!=0 && frequencia.getDescricao().equals(""))
			{
				preparador.setInt(1,frequencia.getId());
			}//fim if id diferente de zero
			else if(frequencia.getId()==0 && !frequencia.getDescricao().equals(""))
			{
				preparador.setString(1, frequencia.getDescricao());
			}//fim else if descricao diferente de nulo
			
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			List<EntidadeDominio> frequencias = new ArrayList<EntidadeDominio>(); //cria lista de frequencias
			
			while(result.next())
			{
				Frequencia f = new Frequencia();
				f.setId(result.getInt("Id"));//pega um int da coluna Id e atribue para Id do objeto criado 
				f.setDescricao(result.getString("Descricao"));//Pega um String da coluna Descricao e atribue para Descricao do Objeto criado
				f.setQtdeDias(result.getInt("Qtde_Dias"));//Pega um int da coluna Qtde_Dias e atribue para QtdeDias do Objeto criado
				
				frequencias.add(f);
			}//fim while result
			return frequencias;
		}catch (SQLException e) {
			e.printStackTrace();
		}//fim try-catch
		return null;
	}

}
