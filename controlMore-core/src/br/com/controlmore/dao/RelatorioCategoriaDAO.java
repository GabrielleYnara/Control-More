package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.RelatorioCategoria;
import br.com.controlmore.dominio.Relatorios;
import br.com.controlmore.dominio.Saida;

public class RelatorioCategoriaDAO extends AbstractDAO{

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
		RelatorioCategoria relatorio = (RelatorioCategoria) entidade;
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT SUM(valor) as valorTotal, ");
		sql.append("c.DESCRICAO ");
		if(relatorio.getCategoria1()!=null && relatorio.getCategoria2()!=null){
		    sql.append(",TO_NUMBER(TO_CHAR(s.DataSaida, 'mm')) as mes, ");
		    sql.append("TO_NUMBER(TO_CHAR(s.DataSaida, 'yyyy')) as ano ");
		}
		sql.append("FROM Saida s ");
		sql.append("JOIN Categoria c ON s.CATEGORIA = c.id ");
		sql.append("WHERE s.DataSaida BETWEEN ? AND ? ");
		if(relatorio.getCategoria1()!=null && relatorio.getCategoria2()!=null){
			sql.append("AND c.Descricao=? OR c.Descricao=? " );
			sql.append("GROUP BY c.DESCRICAO, ");
			sql.append("TO_NUMBER(TO_CHAR(s.DataSaida, 'mm')), ");
		    sql.append("TO_NUMBER(TO_CHAR(s.DataSaida, 'yyyy')) ");
		    sql.append("Order by c.DESCRICAO, ano, mes");
		}else{
			sql.append("GROUP BY c.DESCRICAO ");
			sql.append("Order by c.DESCRICAO");
		}
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
			preparador.setDate(1, new java.sql.Date(relatorio.getInicio().getTime()));
			preparador.setDate(2, new java.sql.Date(relatorio.getFim().getTime()));
			if(relatorio.getCategoria1()!=null && relatorio.getCategoria2()!=null){
				preparador.setString(3, relatorio.getCategoria1());
				preparador.setString(4, relatorio.getCategoria2());
			}

			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>(); //cria uma lista de saidas
			
			while(result.next()){
				RelatorioCategoria categoria = new RelatorioCategoria();
				categoria.setDescricao(result.getString("Descricao"));
				categoria.setValorTotal(result.getFloat("ValorTotal"));
				if(relatorio.getCategoria1()!=null && relatorio.getCategoria2()!=null){
					categoria.setDataReferencia(result.getString("mes") + "/" + result.getString("ano"));
				}
				categorias.add(categoria);
			}
			return categorias;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	public float VerificaSaldo(EntidadeDominio entidade){
		Saida saida = (Saida) entidade;
		StringBuilder sql = new StringBuilder();

		if(saida.getCategoria().getId()==1){//Id da categoria Alimentação
			sql.append("SELECT * FROM VERIFICAR_SALDO_ALIMENTACAO");
		}
		
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			
			while(result.next()){
				float valorTotal = 0;
				valorTotal = result.getFloat("ValorTotal");
				return valorTotal;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
		
	}

}
