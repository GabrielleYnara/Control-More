package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.EntidadeDominio;


public class CategoriaDAO extends AbstractDAO {

	@Override
	public String salvar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String alterar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String excluir(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Categoria categoria = (Categoria) entidade;
		StringBuilder sql = new StringBuilder();
		if(categoria.getId()!=0){
			sql.append("SELECT Id, ");
			sql.append("Descricao, ");
			sql.append("Cor, ");
			sql.append("Categoria ");
			sql.append("FROM Categoria ");
			sql.append("WHERE Id=? ");
			sql.append("ORDER BY Categoria, Descricao" );
		}else{
			sql.append("SELECT Id, ");
			sql.append("Descricao, ");
			sql.append("Cor, ");
			sql.append("Categoria ");
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
