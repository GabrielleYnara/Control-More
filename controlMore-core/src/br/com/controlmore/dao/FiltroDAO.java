package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Entrada;
import br.com.controlmore.dominio.Filtro;
import br.com.controlmore.dominio.Saida;

public class FiltroDAO extends AbstractDAO{

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
		Filtro filtro = (Filtro) entidade;
		StringBuilder sql = new StringBuilder();
		List<EntidadeDominio> entradasSaidas = new ArrayList<EntidadeDominio>(); //cria uma lista de entradas e saidas
		if(filtro.getConsulta().equals("EntradaSaida")){
			if(filtro.getDtInicio()!=null && filtro.getDtFinal()!=null){
				
			}else{
				sql.append("SELECT SUM(Valor) AS Total, ");
				sql.append("TO_CHAR(DataEntrada, 'mm') as Mes ");
				sql.append("FROM Entrada ");
				sql.append("WHERE DataEntrada BETWEEN (SELECT TO_DATE('01/01'||TO_CHAR(sysdate,'/yyyy') , 'dd/mm/yyyy') ");
				sql.append("FROM dual) AND Last_day((SELECT TO_DATE('01/12'||TO_CHAR(sysdate,'/yyyy') , 'dd/mm/yyyy') ");
				sql.append("FROM dual)) ");
				sql.append("GROUP BY TO_CHAR(DataEntrada, 'mm') ");
				sql.append("ORDER BY TO_CHAR(DataEntrada, 'mm') ");
				try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
					ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
					
					while(result.next()){
						Entrada entrada = new Entrada();
						entrada.setValor(result.getFloat("Total"));
						entrada.setDescricao(result.getString("Mes"));
						
						entradasSaidas.add(entrada);
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
				sql.delete(0, sql.length());
				sql.append("SELECT SUM(Valor) AS Total, ");
				sql.append("TO_CHAR(DataSaida, 'mm') as Mes ");
				sql.append("FROM Saida ");
				sql.append("WHERE DataSaida BETWEEN (SELECT TO_DATE('01/01'||TO_CHAR(sysdate,'/yyyy') , 'dd/mm/yyyy') ");
				sql.append("FROM dual) AND Last_day((SELECT TO_DATE('01/12'||TO_CHAR(sysdate,'/yyyy') , 'dd/mm/yyyy') ");
				sql.append("FROM dual)) ");
				sql.append("GROUP BY TO_CHAR(DataSaida, 'mm')");
				sql.append("ORDER BY TO_CHAR(DataSaida, 'mm') ");

				try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
					ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
					
					while(result.next()){
						Saida saida = new Saida();
						saida.setValor(result.getFloat("Total"));
						saida.setDescricao(result.getString("Mes"));
						
						entradasSaidas.add(saida);
					}
				}catch (SQLException e) {
					e.printStackTrace();
				}
				return entradasSaidas;
			}
			
		}
		return null;
	}
		
}
