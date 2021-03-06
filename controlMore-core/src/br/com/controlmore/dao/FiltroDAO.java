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
		
		if(filtro.getConsulta().equals("EntradaSaida")){//(Anual/Per�odo)
			if(filtro.getDtInicio()!=null && filtro.getDtFinal()!=null){
				sql.append("SELECT SUM(Valor) AS Total, ");
				sql.append("TO_CHAR(DataEntrada, 'mm') as Mes ");
				sql.append("FROM Entrada ");
				sql.append("WHERE DataEntrada BETWEEN ? AND ? ");
				sql.append("GROUP BY TO_CHAR(DataEntrada, 'mm') ");
				sql.append("ORDER BY TO_CHAR(DataEntrada, 'mm') ");
			}else{
				sql.append("SELECT SUM(Valor) AS Total, ");
				sql.append("TO_CHAR(DataEntrada, 'mm') as Mes ");
				sql.append("FROM Entrada ");
				sql.append("WHERE DataEntrada BETWEEN (SELECT TO_DATE('01/01'||TO_CHAR(sysdate,'/yyyy') , 'dd/mm/yyyy') ");
				sql.append("FROM dual) AND Last_day((SELECT TO_DATE('01/12'||TO_CHAR(sysdate,'/yyyy') , 'dd/mm/yyyy') ");
				sql.append("FROM dual)) ");
				sql.append("GROUP BY TO_CHAR(DataEntrada, 'mm') ");
				sql.append("ORDER BY TO_CHAR(DataEntrada, 'mm') ");
			}
			try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
				if(filtro.getDtInicio()!=null && filtro.getDtFinal()!=null){
					preparador.setDate(1, new java.sql.Date(filtro.getDtInicio().getTime()));
					preparador.setDate(2, new java.sql.Date(filtro.getDtFinal().getTime()));
				}
				ResultSet result = preparador.executeQuery(); //passa o resultado da execu��o da Query para a vari�vel result
				
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
			if(filtro.getDtInicio()!=null && filtro.getDtFinal()!=null){
				sql.append("SELECT SUM(Valor) AS Total, ");
				sql.append("TO_CHAR(DataSaida, 'mm') as Mes ");
				sql.append("FROM Saida ");
				sql.append("WHERE DataSaida BETWEEN ? AND ? ");
				sql.append("GROUP BY TO_CHAR(DataSaida, 'mm')");
				sql.append("ORDER BY TO_CHAR(DataSaida, 'mm') ");
			}else{
				sql.append("SELECT SUM(Valor) AS Total, ");
				sql.append("TO_CHAR(DataSaida, 'mm') as Mes ");
				sql.append("FROM Saida ");
				sql.append("WHERE DataSaida BETWEEN (SELECT TO_DATE('01/01'||TO_CHAR(sysdate,'/yyyy') , 'dd/mm/yyyy') ");
				sql.append("FROM dual) AND Last_day((SELECT TO_DATE('01/12'||TO_CHAR(sysdate,'/yyyy') , 'dd/mm/yyyy') ");
				sql.append("FROM dual)) ");
				sql.append("GROUP BY TO_CHAR(DataSaida, 'mm')");
				sql.append("ORDER BY TO_CHAR(DataSaida, 'mm') ");
			}
			try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
				if(filtro.getDtInicio()!=null && filtro.getDtFinal()!=null){
					preparador.setDate(1, new java.sql.Date(filtro.getDtInicio().getTime()));
					preparador.setDate(2, new java.sql.Date(filtro.getDtFinal().getTime()));
				}
					
				ResultSet result = preparador.executeQuery(); //passa o resultado da execu��o da Query para a vari�vel result
				
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
		}//if EntradaSaida (Anual/Per�odo)
		
		if(filtro.getConsulta().equals("EntradaSaidaMensal")){
			sql.append("SELECT SUM(Valor) AS Total, ");
			sql.append("TO_CHAR(DataEntrada, 'dd') as Dia ");
			sql.append("FROM Entrada ");
			sql.append("WHERE DataEntrada BETWEEN (SELECT TO_DATE('01'||TO_CHAR(sysdate,'/mm/yyyy') , 'dd/mm/yyyy') ");
			sql.append("						   FROM dual) AND Last_day((SELECT TO_DATE('01'||TO_CHAR(sysdate,'/mm/yyyy') , 'dd/mm/yyyy') ");
			sql.append("													FROM dual)) ");
			sql.append("GROUP BY TO_CHAR(DataEntrada, 'dd') ");
			sql.append("ORDER BY TO_CHAR(DataEntrada, 'dd') ");
			
			try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
				ResultSet result = preparador.executeQuery(); //passa o resultado da execu��o da Query para a vari�vel result
				
				while(result.next()){
					Entrada entrada = new Entrada();
					entrada.setValor(result.getFloat("Total"));
					entrada.setDescricao(result.getString("Dia"));
					
					entradasSaidas.add(entrada);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			sql.delete(0, sql.length());
			
			sql.append("SELECT SUM(Valor) AS Total, ");
			sql.append("TO_CHAR(DataSaida, 'dd') as Dia ");
			sql.append("FROM Saida ");
			sql.append("WHERE DataSaida BETWEEN (SELECT TO_DATE('01/'||TO_CHAR(sysdate,'mm/yyyy') , 'dd/mm/yyyy') ");
			sql.append("						FROM dual) AND Last_day((SELECT TO_DATE('01/'||TO_CHAR(sysdate,'mm/yyyy') , 'dd/mm/yyyy') ");
			sql.append("												 FROM dual)) ");
			sql.append("GROUP BY TO_CHAR(DataSaida, 'dd')");
			sql.append("ORDER BY TO_CHAR(DataSaida, 'dd') ");
			
			try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
				ResultSet result = preparador.executeQuery(); //passa o resultado da execu��o da Query para a vari�vel result
				
				while(result.next()){
					Saida saida = new Saida();
					saida.setValor(result.getFloat("Total"));
					saida.setDescricao(result.getString("Dia"));
						
					entradasSaidas.add(saida);
				}
			}catch(SQLException e){
				e.printStackTrace();
			}
			return entradasSaidas;
		}//if EntradaSaidaMensal
		
		return null;
	}
		
}
