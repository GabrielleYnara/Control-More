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

		if(relatorio.getInicio()==null && relatorio.getFim()==null){
			sql.append("SELECT * FROM Resumo_Por_Categoria_Mes_Atual");
		}
		
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			List<EntidadeDominio> categorias = new ArrayList<EntidadeDominio>(); //cria uma lista de saidas
			
			while(result.next()){
				RelatorioCategoria categoria = new RelatorioCategoria();
				categoria.setDescricao(result.getString("Descricao"));
				categoria.setValorTotal(result.getFloat("ValorTotal"));
				
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
