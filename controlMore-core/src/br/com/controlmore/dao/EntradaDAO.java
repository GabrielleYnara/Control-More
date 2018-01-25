package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.Cartao;
import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Entrada;
import br.com.controlmore.dominio.Frequencia;
import br.com.controlmore.dominio.Parcela;

public class EntradaDAO extends AbstractDAO{

	@Override
	public String salvar(EntidadeDominio entidade) {
		Entrada entrada = (Entrada) entidade; //Casting de etidade para entrada
		
		String sql = null;
		if(entrada.getConta().getId()!=0){
			sql="INSERT INTO Entrada (Descricao, Valor, DataCadastro, Qtde_Parcelas, Frequencia, Conta, DataEntrada, Observacao, Situacao) VALUES (?,?,?,?,?,?,?,?,?)";
		}
		if(entrada.getCartao().getId()!=0){
			sql="INSERT INTO Entrada (Descricao, Valor, DataCadastro, Qtde_Parcelas, Frequencia, Cartao, DataEntrada, Observacao, Situacao) VALUES (?,?,?,?,?,?,?,?,?)";
		}
		try(PreparedStatement preparador = conexao.prepareStatement(sql))
		{
			//substituir os ? pelos valores
			preparador.setString(1, entrada.getDescricao());
			preparador.setFloat(2, entrada.getValor());
			preparador.setDate(3, new java.sql.Date(entrada.getDtCadastro().getTime()));
			preparador.setInt(4, entrada.getParcelas().size());
			preparador.setInt(5, entrada.getFrenquencia().getId());
			if(entrada.getCartao().getId()!=0)
				preparador.setInt(6, entrada.getCartao().getId());
			if(entrada.getConta().getId()!=0)
				preparador.setInt(6, entrada.getConta().getId());
			preparador.setDate(7, new java.sql.Date(entrada.getDataEntrada().getTime()));
			preparador.setString(8, entrada.getObservacoes());
			preparador.setString(9, entrada.getSituacao());

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
		Entrada entrada = (Entrada) entidade; //Casting de etidade para entrada
		List<EntidadeDominio> entradas = new ArrayList<EntidadeDominio>();
		Entrada entBanco = new Entrada();
		//entradas = consultar(entrada);
		//entBanco = (Entrada) entradas.get(0);
		String sql = null;
			
		if(entrada.getDescricao() != null /*entBanco.getDescricao()*/){
			sql="UPDATE Entrada SET Descricao=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql))
			{
				//substituir os ? pelos valores
				preparador.setString(1, entrada.getDescricao());
				preparador.setInt(2, entrada.getId());
				
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
		
		if(entrada.getCartao().getId() != 0 /*entBanco.getCartao().getId()*/){
			sql="UPDATE Entrada SET Cartao=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql))
			{
				//substituir os ? pelos valores
				preparador.setInt(1, entrada.getCartao().getId());
				preparador.setInt(2, entrada.getId());
				
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
		
		if(entrada.getConta().getId() != 0 /*entBanco.getConta().getId()*/){
			sql="UPDATE Entrada SET Conta=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql))
			{
				//substituir os ? pelos valores
				preparador.setInt(1, entrada.getConta().getId());
				preparador.setInt(2, entrada.getId());
				
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
		
		if(entrada.getDataEntrada() != null /*entBanco.getDataEntrada()*/){
			sql="UPDATE Entrada SET DataEntrada=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql))
			{
				//substituir os ? pelos valores
				preparador.setDate(1, new java.sql.Date(entrada.getDataEntrada().getTime()));
				preparador.setInt(2, entrada.getId());
	
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
		
		if(entrada.getFrenquencia().getId() != 0 /*entBanco.getFrenquencia().getId()*/){
			sql="UPDATE Entrada SET Frequencia=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql))
			{
				//substituir os ? pelos valores
				preparador.setInt(1, entrada.getFrenquencia().getId());
				preparador.setInt(2, entrada.getId());
				
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
		
		if(entrada.getObservacoes() != null /*entBanco.getObservacoes()*/){
			sql="UPDATE Entrada SET Observacao=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql))
			{
				//substituir os ? pelos valores
				preparador.setString(1, entrada.getObservacoes());
				preparador.setInt(2, entrada.getId());
				
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
		
		if(entrada.getValor() != 0 /*entBanco.getValor()*/){
			sql="UPDATE Entrada SET Valor=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql))
			{
				//substituir os ? pelos valores
				preparador.setFloat(1, entrada.getValor());
				preparador.setInt(2, entrada.getId());
								
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
		}
		if(entrada.getSituacao()!=null){
			sql="UPDATE Entrada SET Situacao=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setString(1, entrada.getSituacao());
				preparador.setInt(2, entrada.getId());
				
				//executa o SQL
				preparador.execute();
			}catch (SQLException e) {
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
		Entrada entrada = (Entrada) entidade; //Casting
		String sql = "DELETE FROM Entrada WHERE Id=?";
		
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){
			//substitue ? por parametros
			preparador.setInt(1, entrada.getId());
			//executa a query
			preparador.execute();
			//salvar alteração no banco
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

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Entrada entrada = (Entrada) entidade; //Casting
		StringBuilder sql= new StringBuilder();
		if(entrada.getId()!=0){
			sql.append("SELECT Entrada.Id, ");
			sql.append("Entrada.Descricao, ");
			sql.append("Entrada.Valor, ");
			sql.append("Entrada.DataCadastro, "); 
			sql.append("Entrada.Qtde_Parcelas, ");
			sql.append("Entrada.DataEntrada, ");
			sql.append("Entrada.Frequencia, ");
			sql.append("Frequencia.Descricao as Frequencia_Descricao, ");
			sql.append("Frequencia.Qtde_Dias as Frequencia_Dias, ");
			sql.append("Entrada.Conta, ");
			sql.append("Conta.Banco as Conta_Banco, ");
			sql.append("Conta.Tipo as Conta_Tipo, "); 
			sql.append("Conta.Info as Conta_Info, ");
			sql.append("Entrada.Cartao, ");
			sql.append("Cartao.Bandeira as Cartao_Bandeira, ");
			sql.append("Cartao.Conta_Pagto as Cartao_Conta, ");
			sql.append("(SELECT Banco ");
			sql.append("FROM Conta ");
			sql.append("WHERE Id = Cartao.Conta_Pagto)as Conta_Banco, ");
			sql.append("Cartao.Fecha_Dia as Cartao_Fecha_Dia, ");
			sql.append("Cartao.Limite as Cartao_Limite, ");
			sql.append("Cartao.Limite_Utilizado as Cartao_Limite_Utilizado, ");
			sql.append("Cartao.Vence_Dia as Cartao_Vence_Dia, ");
			sql.append("Entrada.Observacao, ");
			sql.append("Entrada.Situacao ");
			sql.append("FROM Entrada ");
			sql.append("LEFT JOIN Frequencia ON Entrada.Frequencia = Frequencia.id ");
			sql.append("LEFT JOIN Conta ON Entrada.Conta = Conta.Id ");
			sql.append("LEFT JOIN Cartao ON Entrada.Cartao = Cartao.Id ");
			sql.append("LEFT JOIN Parcela_Entrada Parcela ON Entrada.Id = Parcela.Entrada ");
			sql.append("WHERE Entrada.Id=?");
		}
		if(entrada.getId()==0){
			sql.append("SELECT Entrada.Id, ");
			sql.append("Entrada.Descricao, ");
			sql.append("Entrada.Valor, ");
			sql.append("Entrada.DataCadastro, "); 
			sql.append("Entrada.Qtde_Parcelas, ");
			sql.append("Entrada.DataEntrada, ");
			sql.append("Entrada.Frequencia, ");
			sql.append("Frequencia.Descricao as Frequencia_Descricao, ");
			sql.append("Frequencia.Qtde_Dias as Frequencia_Dias, ");
			sql.append("Entrada.Conta, ");
			sql.append("Conta.Banco as Conta_Banco, ");
			sql.append("Conta.Tipo as Conta_Tipo, "); 
			sql.append("Conta.Info as Conta_Info, ");
			sql.append("Entrada.Cartao, ");
			sql.append("Cartao.Bandeira as Cartao_Bandeira, ");
			sql.append("Cartao.Conta_Pagto as Cartao_Conta, ");
			sql.append("(SELECT Banco ");
			sql.append("FROM Conta ");
			sql.append("WHERE Id = Cartao.Conta_Pagto)as Conta_Banco, ");
			sql.append("Cartao.Fecha_Dia as Cartao_Fecha_Dia, ");
			sql.append("Cartao.Limite as Cartao_Limite, ");
			sql.append("Cartao.Limite_Utilizado as Cartao_Limite_Utilizado, ");
			sql.append("Cartao.Vence_Dia as Cartao_Vence_Dia, ");
			sql.append("Entrada.Observacao, ");
			sql.append("Entrada.Situacao ");
			sql.append("FROM Entrada ");
			sql.append("LEFT JOIN Frequencia ON Entrada.Frequencia = Frequencia.id ");
			sql.append("LEFT JOIN Conta ON Entrada.Conta = Conta.Id ");
			sql.append("LEFT JOIN Cartao ON Entrada.Cartao = Cartao.Id ");
			sql.append("LEFT JOIN Parcela_Entrada Parcela ON Entrada.Id = Parcela.Entrada ");
			sql.append("ORDER BY Entrada.DataEntrada");
		}
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
			if(entrada.getId()!=0){
				preparador.setInt(1, entrada.getId());
			}
			ResultSet result = preparador.executeQuery();
			List<EntidadeDominio> entradas = new ArrayList<EntidadeDominio>();
			
			while(result.next()){
				Entrada e = new Entrada();
				Parcela p = new Parcela();
				Frequencia f = new Frequencia();
				Conta conta = new Conta();
				Cartao cartao = new Cartao();
				
				//Atribui o conteudo da coluna, para a variavel
				e.setId(result.getInt("Id"));
				e.setDescricao(result.getString("Descricao"));
				e.setValor(result.getFloat("Valor"));
				e.setDtCadastro(result.getDate("DataCadastro"));
				p.setNumParcela(result.getInt("Qtde_Parcelas"));
				e.setDataEntrada(result.getDate("DataEntrada"));
				f.setId(result.getInt("Frequencia"));
				f.setDescricao(result.getString("Frequencia_Descricao"));
				f.setQtdeDias(result.getInt("Frequencia_Dias"));
				e.setFrenquencia(f);
				conta.setId(result.getInt("Conta"));
				conta.setBanco(result.getString("Conta_Banco"));
				conta.setTipo(result.getString("Conta_Tipo"));
				conta.setInfo(result.getString("Conta_Info"));
				e.setConta(conta);
				cartao.setId(result.getInt("Cartao"));
				cartao.setBandeira(result.getString("Cartao_Bandeira"));
				Conta contaPagto = new Conta();
				contaPagto.setId(result.getInt("Cartao_Conta"));
				contaPagto.setBanco(result.getString("Conta_Banco"));
				cartao.setConta(contaPagto);
				cartao.setFechaDia(result.getInt("Cartao_Fecha_Dia"));;
				cartao.setLimite(result.getFloat("Cartao_Limite"));
				cartao.setLimiteUtilizado(result.getFloat("Cartao_Limite_Utilizado"));
				cartao.setVenceDia(result.getInt("Cartao_Vence_Dia"));
				e.setCartao(cartao);
				e.setObservacoes(result.getString("Observacao"));
				e.setSituacao(result.getString("Situacao"));
				
				/*String sql2="SELECT * FROM Parcela_Entrada WHERE Entrada=?";
				try(PreparedStatement prepara = conexao.prepareStatement(sql2)){
					//substituir os ? pelos valores
					preparador.setInt(1, entrada.getId());
					
					ResultSet resultado = preparador.executeQuery();
					
					while(result.next()){
						p = new Parcela();
						p.setId(resultado.getInt("Id"));
						p.setNumParcela(resultado.getInt("Numero_Parcela"));
						p.setSituacao(resultado.getString("Situacao"));
						p.setDtCadastro(resultado.getDate("DataCadastro"));
						
						if(p!=null){
							e.setParcela(p);
						}
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}//end try parcelas*/
				entradas.add(e);
			}//end while
			return entradas;
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}//end try entradas
		return null;
	}//end consultar
}
