package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Entrada;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.vm.ResumoVM;

public class ResumoDAO extends AbstractDAO{

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
	
	public double saldoSaida(){
		double saldo;
		String sql = "SELECT SUM(valor) AS saida "
				   + "  FROM Saida"
				   + " WHERE UPPER(Situacao) like 'PAGO'";
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){//preparador que vai gerenciar o SQL
			//executa o SQL
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			while(result.next()){
				saldo = result.getDouble("saida");
				return saldo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public double saldoEntrada() {
		double saldo =0;
		String sql = "SELECT SUM(valor) AS entrada "
				   + "  FROM Entrada"
				   + " WHERE UPPER(Situacao) like 'PAGO'";
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){//preparador que vai gerenciar o SQL
			//executa o SQL
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			while(result.next()){
				saldo = result.getDouble("entrada");
				return saldo;
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public ResumoVM graficoMensal(){
		ResumoVM rVM = new ResumoVM();
		String sql = "SELECT SUM(Valor) AS Valor, "
				   + "		 TO_CHAR(DataSaida, 'DD') AS Data, "
				   + "		 'Saida' AS Tipo "
				   + "  FROM Saida "
				   + " WHERE DataSaida BETWEEN (SELECT to_date( '01/'||to_char(sysdate, 'mm/yyyy'), 'dd/mm/yyyy') "
				   + "                          FROM dual) AND LAST_DAY(SYSDATE) "
				   + "   AND UPPER(Situacao) like 'PAGO'"
				   + " GROUP BY DataSaida "
				   + " UNION ALL "
				   + "SELECT SUM(Valor) AS Valor, "
				   + "       TO_CHAR(DataEntrada, 'DD') AS Data, "
				   + "       'Entrada' AS Tipo "
				   + "  FROM Entrada "
				   + " WHERE DataEntrada BETWEEN (SELECT to_date( '01/'||to_char(SYSDATE, 'mm/yyyy'), 'dd/mm/yyyy') "
				   + "                            FROM dual) AND LAST_DAY(SYSDATE) "
				   + "   AND UPPER(Situacao) like 'PAGO'"
				   + " GROUP BY DataEntrada "
				   + " ORDER BY Data, Tipo";
		
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){//preparador que vai gerenciar o SQL
			//executa o SQL
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			while(result.next()){
				int i =0;
				if (rVM.getDias().size() !=0)
					i = rVM.getDias().size()-1;
					
				//Verificar se é primeiro registro
				if(i==0 || result.getInt("Data") != rVM.getDias().get(i)){//Se a data for diferente do ultimo registro no Array de dias
					rVM.setDias(result.getInt("Data"));//Acresccenta outra data ao array;
					//Se for o primeiro registro de data e for registro de Saida, significa que não tem entrada (devido à ordenação)
					//Portando, setar 0 em Receber para que tenha o mesmo numero de registros (Entrada e Saida)
					if(result.getString("Tipo").equals("Saida")){
						if(rVM.getcRecebidas().size() > rVM.getcPagas().size()){
							int r = rVM.getcRecebidas().size();
							int p = rVM.getcPagas().size();
							for(int aux = r-p; aux < r; aux++){
								rVM.setcPagas(0);
							}
						}
						rVM.setcPagas(result.getDouble("Valor"));
						rVM.setcRecebidas(0);
						
					}else{//Registrar entrada.
						rVM.setcRecebidas(result.getDouble("Valor"));
					}
				}else{//Já existe uma data, registrar saida
					rVM.setcPagas(result.getDouble("Valor"));
				}
			}
			if(rVM.getcRecebidas().size() > rVM.getcPagas().size()){
				int r = rVM.getcRecebidas().size();
				int p = rVM.getcPagas().size();
				for(int aux = r-p; aux < r; aux++){
					rVM.setcPagas(0);
				}
			}
			return rVM;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResumoVM proximosVencimentos(){
		ResumoVM rVM = new ResumoVM();
		String sql = "SELECT Id, "
				   + " 		 Valor, "
				   + "		 Descricao, "
				   + "		 DataSaida as Data "
				   + "  FROM Saida "
				   + " WHERE DataSaida BETWEEN (select sysdate from dual) AND (SELECT SYSDATE +10 FROM dual) "
				   + " ORDER BY DataSaida ";
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){//preparador que vai gerenciar o SQL
			//executa o SQL
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			while(result.next()){
				Saida s = new Saida();

				s.setId(result.getInt("Id"));
				s.setValor(result.getFloat("Valor"));
				s.setDescricao(result.getString("Descricao"));
				s.setData(result.getDate("Data"));
				
				rVM.setaPagar(s);
			}
			return rVM;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ResumoVM proximosRecebimentos(){
		ResumoVM rVM = new ResumoVM();
		String sql = "SELECT Id, "
				   + " 		 Valor, "
				   + "		 Descricao, "
				   + "		 DataEntrada as Data "
				   + "  FROM Entrada "
				   + " WHERE DataEntrada BETWEEN (select sysdate from dual) AND (SELECT SYSDATE +10 FROM dual) "
				   + " ORDER BY DataEntrada ";
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){//preparador que vai gerenciar o SQL
			//executa o SQL
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			while(result.next()){
				Entrada e = new Entrada();

				e.setId(result.getInt("Id"));
				e.setValor(result.getFloat("Valor"));
				e.setDescricao(result.getString("Descricao"));
				e.setDataEntrada(result.getDate("Data"));
				
				rVM.setaReceber(e);
			}
			return rVM;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	public ResumoVM aPagarVencidas(){
		ResumoVM rVM = new ResumoVM();
		String sql = "SELECT Id,"
				   + " 		 Valor, "
				   + "		 Descricao, "
				   + "       DataSaida "
				   + "  FROM Saida "
				   + " WHERE DataSaida < (SELECT SYSDATE FROM Dual) "
				   + "   AND UPPER(Situacao) <> 'PAGO' "
				   + " ORDER BY Valor, DataSaida";
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){//preparador que vai gerenciar o SQL
			//executa o SQL
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			while(result.next()){
				Saida s = new Saida();

				s.setId(result.getInt("Id"));
				s.setValor(result.getFloat("Valor"));
				s.setDescricao(result.getString("Descricao"));
				s.setData(result.getDate("DataSaida"));
				rVM.setaPagarVencida(s);
			}
			return rVM;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;	
	}
	
	public ResumoVM aReceberAtrasadas(){
		ResumoVM rVM = new ResumoVM();
		String sql = "SELECT Id, "
				   + " 		 Valor, "
				   + "       Descricao, "
				   + "       DataEntrada "
				   + "  FROM Entrada "
				   + " WHERE DataEntrada < (SELECT SYSDATE FROM Dual) "
				   + "   AND UPPER(Situacao) <> 'PAGO' "
				   + " ORDER BY Valor, DataEntrada ";
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){//preparador que vai gerenciar o SQL
			//executa o SQL
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			while(result.next()){
				Entrada e = new Entrada();

				e.setId(result.getInt("Id"));
				e.setValor(result.getFloat("Valor"));
				e.setDescricao(result.getString("Descricao"));
				e.setDataEntrada(result.getDate("DataEntrada"));
				
				rVM.setaReceberAtrasada(e);
			}
			return rVM;
		}catch (SQLException e1) {
			e1.printStackTrace();
		}
		return null;
	}
}
