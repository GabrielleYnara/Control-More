package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Meta;

public class MetaDAO extends AbstractDAO{

	@Override
	public String salvar(EntidadeDominio entidade) {
		Meta meta = (Meta) entidade;
		String sql = "INSERT INTO Meta (Objetivo, Valor_Total, Valor_Mensal, Prazo, Saldo, DataCadastro) VALUES (?, ?, ?, ?, ?, ?)";
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){
			//Substituir ? pelos valores corretos
			preparador.setString(1, meta.getObjetivo());
			preparador.setFloat(2, meta.getValorTotal());
			preparador.setFloat(3, meta.getValorMensal());
			preparador.setDate(4, new java.sql.Date(meta.getPrazo().getTime()));
			preparador.setFloat(5, meta.getSaldo());
			Timestamp time = new Timestamp(meta.getDtCadastro().getTime());
			preparador.setTimestamp(6, time);
				
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
		Meta meta = (Meta) entidade;
		String sql;
		
		if(meta.getId()!=0 && meta.getObjetivo()!=null){
			sql="UPDATE Meta SET Objetivo=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substituir ? pelos valores corretos
				preparador.setString(1, meta.getObjetivo());
				preparador.setInt(2, meta.getId());
				
				// executar o comando no banco
				preparador.execute();
			}catch(SQLException e){
				try{
					//desfaz alteração no banco
					conexao.rollback();
				}catch(SQLException e1) {
					return e1.toString();
				}
				return e.toString();
			}
		}
		if (meta.getId()!=0 && meta.getPrazo()!=null){
			sql= "UPDATE Meta SET Prazo=? WHERE Id =?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substituir ? pelos valores corretos
				preparador.setDate(1, new java.sql.Date(meta.getPrazo().getTime()));
				preparador.setInt(2, meta.getId());
				
				// executar o comando no banco
				preparador.execute();
			}catch(SQLException e){
				try{
					//desfaz alteração no banco
					conexao.rollback();
				}catch(SQLException e1) {
					return e1.toString();
				}
				return e.toString();
			}
		}
		if (meta.getId()!=0 && meta.getSaldo()!=0){
			sql="UPDATE Meta SET Saldo=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substituir ? pelos valores corretos
				preparador.setFloat(1, meta.getSaldo());
				preparador.setInt(2, meta.getId());
				// executar o comando no banco
				preparador.execute();
			}catch(SQLException e){
				try{
					//desfaz alteração no banco
					conexao.rollback();
				}catch(SQLException e1) {
					return e1.toString();
				}
				return e.toString();
			}
		}
		if (meta.getId()!=0 && meta.getValorMensal()!=0){
			sql="UPDATE Meta SET Valor_Mensal=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substituir ? pelos valores corretos
				preparador.setFloat(1, meta.getValorMensal());
				preparador.setInt(2, meta.getId());
				
				// executar o comando no banco
				preparador.execute();
			}catch(SQLException e){
				try{
					//desfaz alteração no banco
					conexao.rollback();
				}catch(SQLException e1) {
					return e1.toString();
				}
				return e.toString();
			}
		}
		if (meta.getId()!=0 && meta.getValorTotal()!=0){
			sql="UPDATE Meta SET Valor_Total=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substituir ? pelos valores corretos
				preparador.setFloat(1, meta.getValorTotal());
				preparador.setInt(2, meta.getId());
				
				// executar o comando no banco
				preparador.execute();
			}catch(SQLException e){
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
		Meta meta = (Meta) entidade;
		String sql = "DELETE FROM Meta WHERE id=?";
		
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statemente que vai gerenciar o SQL
				
			// substituir todas ? pelos valores corretos
			preparador.setInt(1, meta.getId());
				
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
	return null;
}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Meta meta = (Meta) entidade;
		String sql=null;
		List<EntidadeDominio> metas = new ArrayList<EntidadeDominio>(); //cria lista de metas
		if(meta.getValorTotal()!=0){
			metas.add(meta);
			return metas;
		}
			
		if(meta.getId()==0)
			sql="SELECT * FROM Meta";
		else if(meta.getId()!=0)
			sql="SELECT * FROM Meta WHERE Id=?";

		try(PreparedStatement preparador = conexao.prepareStatement(sql)){// Statement que vai gerenciar o SQL
			if(meta.getId()!=0)
			{
				preparador.setInt(1,meta.getId());
			}//fim if id diferente de zero
			
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			
			while(result.next())
			{
				Meta m = new Meta();
				m.setId(result.getInt("Id"));//pega um int da coluna Id e atribue para Id do objeto criado 
				m.setObjetivo(result.getString("Objetivo"));
				m.setPrazo(result.getDate("Prazo"));
				m.setSaldo(result.getFloat("Saldo"));
				m.setValorMensal(result.getFloat("Valor_Mensal"));
				m.setValorTotal(result.getFloat("Valor_Total"));
				m.setDtCadastro(result.getDate("DataCadastro"));
		
				metas.add(m);
			}//fim while result
			return metas;
		}catch (SQLException e) {
			e.printStackTrace();
		}//fim try-catch
		return null;
	}

}
