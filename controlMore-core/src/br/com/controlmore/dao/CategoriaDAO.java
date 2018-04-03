package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.EntidadeDominio;


public class CategoriaDAO extends AbstractDAO {

	@Override
	public String salvar(EntidadeDominio entidade) {
		Categoria categoria = (Categoria) entidade;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO Categoria (Descricao, Cor, Categoria, DataCadastro) VALUES (?, ?, ?, ?)");
		
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){//Preparador que vai gerenciar o SQL
			preparador.setString(1, categoria.getDescricao());
			preparador.setString(2, categoria.getCor());
			preparador.setString(3, categoria.getCategoria().getDescricao());
			Timestamp time = new Timestamp(categoria.getDtCadastro().getTime());
			preparador.setTimestamp(4, time);
						
			//executar comando
			preparador.execute();
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String alterar(EntidadeDominio entidade) {
		Categoria categoria = (Categoria) entidade;
		String sql = null;
		if(categoria.getDescricao() !=null){
			sql="UPDATE Categoria SET descricao=? WHERE id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setString(1,categoria.getDescricao());
				preparador.setInt(2, categoria.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(categoria.getCor() !=null){
			sql="UPDATE Categoria SET cor=? WHERE id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setString(1,categoria.getCor());
				preparador.setInt(2, categoria.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(categoria.getCategoria().getDescricao() !=null){
			sql="UPDATE Categoria SET categoria=? WHERE id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setString(1,categoria.getCategoria().getDescricao());
				preparador.setInt(2, categoria.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
				
		return null;
	}

	@Override
	public String excluir(EntidadeDominio entidade) {
		Categoria categoria = (Categoria) entidade;
		String sql = null;
		if(categoria.getId() !=0){
			sql="DELETE FROM Categoria WHERE id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setInt(1, categoria.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Categoria categoria = (Categoria) entidade;
		StringBuilder sql = new StringBuilder();
		if(categoria.getId()!=0){
			sql.append("SELECT Id, ");
			sql.append("Descricao, ");
			sql.append("Cor, ");
			sql.append("Categoria, ");
			sql.append("DataCadastro ");
			sql.append("FROM Categoria ");
			sql.append("WHERE Id=? ");
			sql.append("ORDER BY Categoria, Descricao" );
		}else{
			sql.append("SELECT Id, ");
			sql.append("Descricao, ");
			sql.append("Cor, ");
			sql.append("Categoria, ");
			sql.append("DataCadastro ");
			sql.append("FROM Categoria ");
			sql.append("ORDER BY Categoria, Descricao" );
		}
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString()) ){
			if(categoria.getId()!=0){
				preparador.setInt(1, categoria.getId());
			}
			ResultSet result = preparador.executeQuery();
			List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>();
			
			while(result.next()){
				Categoria c = new Categoria();
				Categoria Cat = new Categoria();
				
				//Atribui o conteudo da coluna, para a variavel
				c.setId(result.getInt("id"));
				c.setDescricao(result.getString("Descricao"));
				c.setCor(result.getString("Cor"));
				Cat.setDescricao(result.getString("Categoria"));
				c.setDtCadastro(result.getDate("dataCadastro"));
				c.setCategoria(Cat);
				
				categorias.add(c);
			}//end while
			
			return categorias;
		}catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}//end try
		
		return null;
	}

}
