package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.RelatorioCategoria;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.vm.compararCategoriaVM;

public class CompararCategoriasDAO extends AbstractDAO{

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
		// TODO Auto-generated method stub
		return null;
	}
	
	public compararCategoriaVM consultar(compararCategoriaVM compararVM) {
		compararCategoriaVM cVM = (compararCategoriaVM) compararVM;
		CategoriaDAO cDAO = new CategoriaDAO();
		Categoria c = new Categoria();
		List<EntidadeDominio> categorias = cDAO.consultar(c);
		for(int i = 0; i<categorias.size(); i++){
			c = new Categoria();
			c = (Categoria) categorias.get(i);
			cVM.setCategorias(c);
		}
		
		if(cVM.getIdCategoria1()!=0 && cVM.getIdCategoria2()!=0){
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT SUM(valor) as valorTotal, ");
			sql.append("c.DESCRICAO, ");
			sql.append("s.Categoria as idCategoria, ");
		    sql.append("TO_NUMBER(TO_CHAR(s.DataSaida, 'mm')) as mes ");
			sql.append("FROM Saida s ");
			sql.append("JOIN Categoria c ON s.CATEGORIA = c.id ");
			sql.append("WHERE s.DataSaida BETWEEN ? AND ? ");
			sql.append("AND c.Id IN (?, ?) " );
			sql.append("GROUP BY c.DESCRICAO, ");
			sql.append("s.Categoria, ");
			sql.append("TO_NUMBER(TO_CHAR(s.DataSaida, 'mm')) ");
			sql.append("ORDER BY c.DESCRICAO, mes");
	
			try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
				preparador.setDate(1, new java.sql.Date(cVM.getDtInicial().getTime()));
				preparador.setDate(2, new java.sql.Date(cVM.getDtFinal().getTime()));
				preparador.setInt(3, cVM.getIdCategoria1());
				preparador.setInt(4, cVM.getIdCategoria2());
				
				ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
	
				
				while(result.next()){
					Saida s = new Saida();
					Categoria cat = new Categoria();
					s.setDescricao(result.getString("mes"));
					s.setValor(result.getFloat("ValorTotal"));
					cat.setId(result.getInt("idCategoria"));
					cat.setDescricao(result.getString("Descricao"));
					s.setCategoria(cat);
					if(cVM.getIdCategoria1()== result.getInt("idCategoria"))
						cVM.setCategoria1(s);
					if(cVM.getIdCategoria2()== result.getInt("idCategoria"))
						cVM.setCategoria2(s);
				}
				
			}catch (SQLException e) {
				e.printStackTrace();
			}
		}//Se categoria 1 e 2 for diferente de 0
		return cVM;
	}

	

}
