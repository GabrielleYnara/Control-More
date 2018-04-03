package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.Cartao;
import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Frequencia;
import br.com.controlmore.dominio.Juros;
import br.com.controlmore.dominio.Saida;

public class SaidaDAO extends AbstractDAO{

	/**
	 * Metodo Salvar Saida
	 * @param entidade - deve receber uma instancia de saida (completa ou simples)
	 * @return retornará: nulo caso dê tudo certo, se der erro irá retornar uma string do erro
	 */
	@Override
	public String salvar(EntidadeDominio entidade) {
		Saida saida = (Saida) entidade; //Casting de entidade para saida
		StringBuilder sql = new StringBuilder();
		
		if(saida.getConta().getId() !=0 && saida.getJuros().getJuros() != 0){
			sql.append("INSERT INTO Saida(Descricao, Valor, DataCadastro, Situacao, Importancia, ");
			sql.append("Vencimento, Qtde_Parcelas, Frequencia, Juros, Categoria, Conta, DataSaida) ");
			sql.append("VALUES (?,?,?,?,?,?,?,?, (select MAX(Id) from Juros),?,?,?) ");
		}
		if(saida.getConta().getId() !=0 && saida.getJuros().getJuros() == 0){
			sql.append("INSERT INTO Saida(Descricao, Valor, DataCadastro, Situacao, Importancia, ");
			sql.append("Vencimento, Qtde_Parcelas, Frequencia, Categoria, Conta, DataSaida, Juros) ");
			sql.append("VALUES (?,?,?,?,?,?,?,?,?,?,?, 3) ");
		}
		if(saida.getCartao().getId() !=0 && saida.getJuros().getJuros() != 0){
			sql.append("INSERT INTO Saida(Descricao, Valor, DataCadastro, Situacao, Importancia, ");
			sql.append("Vencimento, Qtde_Parcelas, Frequencia, Juros, Categoria, Cartao, DataSaida) ");
			sql.append("VALUES (?,?,?,?,?,?,?,?, (select MAX(Id) from Juros),?,?,?) ");
		}
		if(saida.getCartao().getId() !=0 && saida.getJuros().getJuros() == 0){
			sql.append("INSERT INTO Saida(Descricao, Valor, DataCadastro, Situacao, Importancia, ");
			sql.append("Vencimento, Qtde_Parcelas, Frequencia, Categoria, Cartao, DataSaida, Juros) ");
			sql.append("VALUES (,?,?,?,?,?,?,?,?,?,?,?, 3) ");
		}
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){//Preparador que vai gerenciar o SQL
			if(saida.getJuros().getJuros()!=0 && saida.getJuros().getMulta()!=0){
				sql = new StringBuilder();
				sql.append("INSERT INTO Juros(Multa, Juros, Intervalo_Cobranca) VALUES(?,?,?) ");
				try(PreparedStatement prepara = conexao.prepareStatement(sql.toString())){//Preparador que vai gerenciar o SQL
					prepara.setFloat(1, saida.getJuros().getMulta());
					prepara.setFloat(2, saida.getJuros().getJuros());
					prepara.setString(3, saida.getJuros().getIntervaloCobranca());
						
					//executar comando
					prepara.execute();
					
				}catch (SQLException e) {
					
					e.printStackTrace();
				}
			}//end if juros not null
			//substitue as ? pelos "valores" que compõe o objeto
			
			preparador.setString(1, saida.getDescricao());
			preparador.setFloat(2, saida.getValor());
			Timestamp time = new Timestamp(saida.getDtCadastro().getTime());
			preparador.setTimestamp(3, time);
			preparador.setString(4, saida.getSituacao());
			preparador.setInt(5, saida.getImportancia());
			preparador.setDate(6, new java.sql.Date(saida.getVencimento().getTime()));
			preparador.setInt(7, saida.getParcelas().size());
			preparador.setInt(8, saida.getFrequencia().getId());
			preparador.setInt(9, saida.getCategoria().getId());
			if(saida.getConta().getId() !=0)
				preparador.setInt(10, saida.getConta().getId());
			if(saida.getCartao().getId() !=0)
				preparador.setInt(10, saida.getCartao().getId());
			preparador.setDate(11, new java.sql.Date(saida.getData().getTime()) );
			//executar comando
			preparador.execute();
			
			/*if(saida.getParcelas().size()!=0){
				sql = new StringBuilder();
				sql.append("INSERT INTO Parcela_Saida(Valor, Numero_Parcela, Situacao, Saida) ");
				sql.append("VALUES(?, ?, ?, (select MAX (Id) from Saida))");
				
				try(PreparedStatement prepara = conexao.prepareStatement(sql.toString())){//Preparador que vai gerenciar o SQL
					for(int i =0; i<= saida.getParcelas().size() ; i++){	
						prepara.setFloat(1, saida.getParcelas().get(i).getValorParcela());
						prepara.setInt(2, saida.getParcelas().get(i).getNumParcela());
						prepara.setString(3, saida.getParcelas().get(i).getSituacao());
						
						//executar comando
						preparador.execute();
					}//end for
				}catch (SQLException e) {
					try{
						//desfaz alteração no banco
						conexao.rollback();
					}catch(SQLException e1) {
						e1.printStackTrace();
					}
					e.printStackTrace();
				}
			}//end if parcelas*/
			
		}catch (SQLException e) {
			
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String alterar(EntidadeDominio entidade) {
		Saida saida = (Saida) entidade; //Casting de entidade para saida
		String sql;
		if(saida.getValor()!=0){
			sql = "UPDATE Saida SET Valor=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){//Preparador que vai gerenciar o SQL
				//substituir os ? pelo "valores" que compõe o objeto
				preparador.setFloat(1, saida.getValor());
				preparador.setInt(2, saida.getId());
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(saida.getDescricao()!=null){
			sql="UPDATE Saida SET Descricao=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setString(1, saida.getDescricao());
				preparador.setInt(2, saida.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(saida.getData()!=null){
			sql="UPDATE Saida SET DataSaida=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				Timestamp time = new Timestamp(saida.getData().getTime());
				preparador.setTimestamp(1, time);
				preparador.setInt(2, saida.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(saida.getSituacao()!=null){
			sql="UPDATE Saida SET Situacao=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setString(1, saida.getSituacao());
				preparador.setInt(2, saida.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(saida.getImportancia()!=0){
			sql="UPDATE Saida SET Importancia=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setInt(1, saida.getImportancia());
				preparador.setInt(2, saida.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(saida.getVencimento()!=null){
			sql="UPDATE Saida SET Vencimento=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setDate(1, new java.sql.Date(saida.getVencimento().getTime()));
				preparador.setInt(2, saida.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(saida.getParcelas()!=null){
			sql="UPDATE Saida SET Qtde_Parcelas=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setInt(1,saida.getParcelas().size());
				preparador.setInt(2, saida.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(saida.getFrequencia()!=null){
			sql="UPDATE Saida SET Frequencia=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setInt(1,saida.getFrequencia().getId());
				preparador.setInt(2, saida.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(saida.getJuros()!=null){
			sql="UPDATE Saida SET Juros=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setInt(1,saida.getJuros().getId());
				preparador.setInt(2, saida.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(saida.getCartao()!=null){
			sql="UPDATE Saida SET Cartao=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setInt(1,saida.getCartao().getId());
				preparador.setInt(2, saida.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(saida.getConta()!=null){
			sql="UPDATE Saida SET Conta=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setInt(1,saida.getConta().getId());
				preparador.setInt(2, saida.getId());
				
				//executa o SQL
				preparador.execute();
				
			}catch (SQLException e) {
				
				return e.toString();
			}
		}
		if(saida.getCategoria().getId()!=0){
			sql="UPDATE Saida SET Categoria=? WHERE Id=?";
			try(PreparedStatement preparador = conexao.prepareStatement(sql)){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setInt(1,saida.getCategoria().getId());
				preparador.setInt(2, saida.getId());
				
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
		Saida saida = (Saida) entidade;
		String sql = "DELETE FROM Saida WHERE Id=?";
		try(PreparedStatement preparador = conexao.prepareStatement(sql)){
			//Substitue as ? pelos "valores" que compõe o objeto
			preparador.setInt(1, saida.getId());
			
			//executa o SQL
			preparador.execute();
			
		}catch (SQLException e) {
			
			return e.toString();
		}
		return null;
	}

	@Override
	public List<EntidadeDominio> consultar(EntidadeDominio entidade) {
		Saida saida = (Saida) entidade; //Casting
		StringBuilder sql = new StringBuilder();
		if(saida.getId()!=0){
			sql.append("SELECT Saida.Id, ");
			sql.append("Saida.DESCRICAO, ");
			sql.append("Saida.VALOR, ");
			sql.append("Saida.DATACADASTRO, ");
			sql.append("Saida.SITUACAO, ");
			sql.append("Saida.IMPORTANCIA, ");
			sql.append("Saida.VENCIMENTO, ");
			sql.append("Saida.QTDE_PARCELAS, ");
			sql.append("Saida.FREQUENCIA, ");
			sql.append("Frequencia.DESCRICAO as Frequencia_DESCRICAO, ");
			sql.append("Frequencia.QTDE_DIAS as Frequencia_QTDE_DIAS, ");
			sql.append("Saida.JUROS, ");
			sql.append("Juros.MULTA as Juros_MULTA, ");
			sql.append("Juros.JUROS as Juros_JUROS, ");
			sql.append("Juros.INTERVALO_COBRANCA as Juros_INTERVALO, "); 
			sql.append("Saida.CATEGORIA, ");
			sql.append("Categoria.DESCRICAO as Categoria_DESCRICAO, ");
			sql.append("Categoria.COR as Categoria_COR, ");
			sql.append("Categoria.CATEGORIA as Categoria_CATEGORIA, ");
			sql.append("Saida.CONTA, ");
			sql.append("Conta.TIPO as Conta_TIPO, ");
			sql.append("Conta.BANCO as Conta_BANCO, ");
			sql.append("Conta.INFO as Conta_INFO, ");
			sql.append("Saida.CARTAO, ");
			sql.append("Cartao.LIMITE as Cartao_LIMITE , ");
			sql.append("Cartao.LIMITE_UTILIZADO as Cartao_LIMITE_UTILIZADO, ");
			sql.append("Cartao.BANDEIRA as Cartao_BANDEIRA, ");
			sql.append("Cartao.VENCE_DIA as Cartao_VENCE_DIA, ");
			sql.append("Cartao.FECHA_DIA as Cartao_FECHA_DIA, ");
			sql.append("Cartao.CONTA_PAGTO as Cartao_CONTA_PAGTO, ");
			sql.append("Saida.DATASAIDA ");
			sql.append("FROM Saida ");
			sql.append("LEFT JOIN Frequencia ON Saida.Frequencia = Frequencia.Id ");
			sql.append("LEFT JOIN Juros ON Saida.Juros = Juros.Id ");
			sql.append("LEFT JOIN Categoria ON Saida.Categoria = Categoria.Id ");
			sql.append("LEFT JOIN Conta ON Saida.Conta = Conta.Id ");
			sql.append("LEFT JOIN Cartao ON Saida.Cartao = Cartao.Id ");
			sql.append("WHERE Saida.Id = ? ");
		}else{
			sql.append("SELECT Saida.Id, ");
			sql.append("Saida.DESCRICAO, ");
			sql.append("Saida.VALOR, ");
			sql.append("Saida.DATACADASTRO, ");
			sql.append("Saida.SITUACAO, ");
			sql.append("Saida.IMPORTANCIA, ");
			sql.append("Saida.VENCIMENTO, ");
			sql.append("Saida.QTDE_PARCELAS, ");
			sql.append("Saida.FREQUENCIA, ");
			sql.append("Frequencia.DESCRICAO as Frequencia_DESCRICAO, ");
			sql.append("Frequencia.QTDE_DIAS as Frequencia_QTDE_DIAS, ");
			sql.append("Saida.JUROS, ");
			sql.append("Juros.MULTA as Juros_MULTA, ");
			sql.append("Juros.JUROS as Juros_JUROS, ");
			sql.append("Juros.INTERVALO_COBRANCA as Juros_INTERVALO, "); 
			sql.append("Saida.CATEGORIA, ");
			sql.append("Categoria.DESCRICAO as Categoria_DESCRICAO, ");
			sql.append("Categoria.COR as Categoria_COR, ");
			sql.append("Categoria.CATEGORIA as Categoria_CATEGORIA, ");
			sql.append("Saida.CONTA, ");
			sql.append("Conta.TIPO as Conta_TIPO, ");
			sql.append("Conta.BANCO as Conta_BANCO, ");
			sql.append("Conta.INFO as Conta_INFO, ");
			sql.append("Saida.CARTAO, ");
			sql.append("Cartao.LIMITE as Cartao_LIMITE , ");
			sql.append("Cartao.LIMITE_UTILIZADO as Cartao_LIMITE_UTILIZADO, ");
			sql.append("Cartao.BANDEIRA as Cartao_BANDEIRA, ");
			sql.append("Cartao.VENCE_DIA as Cartao_VENCE_DIA, ");
			sql.append("Cartao.FECHA_DIA as Cartao_FECHA_DIA, ");
			sql.append("Cartao.CONTA_PAGTO as Cartao_CONTA_PAGTO, ");
			sql.append("Saida.DATASAIDA ");
			sql.append("FROM Saida ");
			sql.append("LEFT JOIN Frequencia ON Saida.Frequencia = Frequencia.Id ");
			sql.append("LEFT JOIN Juros ON Saida.Juros = Juros.Id ");
			sql.append("LEFT JOIN Categoria ON Saida.Categoria = Categoria.Id ");
			sql.append("LEFT JOIN Conta ON Saida.Conta = Conta.Id ");
			sql.append("LEFT JOIN Cartao ON Saida.Cartao = Cartao.Id ");
			sql.append("ORDER BY DataSaida DESC ");
		}
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
			if(saida.getId()!=0){
				//Substitue as ? pelos "valores" que compõe o objeto
				preparador.setInt(1, saida.getId());
			}
			
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			List<EntidadeDominio> saidas = new ArrayList<EntidadeDominio>(); //cria uma lista de saidas
			
			while(result.next()){
				Saida sda = new Saida();
				Frequencia f = new Frequencia();
				Juros j = new Juros();
				Categoria c = new Categoria();
				Conta co = new Conta();
				Cartao ca = new Cartao();
				
				//Irá copiar as infomações do banco e criar um objeto de saida;
				sda.setId(result.getInt("Id"));
				sda.setDescricao(result.getString("DESCRICAO"));
				sda.setValor(result.getFloat("VALOR"));
				sda.setDtCadastro(result.getDate("DATACADASTRO"));
				sda.setSituacao(result.getString("SITUACAO"));
				sda.setImportancia(result.getInt("IMPORTANCIA"));
				sda.setVencimento(result.getDate("VENCIMENTO"));
				f.setId(result.getInt("FREQUENCIA"));
				f.setDescricao(result.getString("Frequencia_DESCRICAO"));
				f.setQtdeDias(result.getInt("Frequencia_QTDE_DIAS"));
				sda.setFrequencia(f);
				j.setId(result.getInt("JUROS"));
				j.setMulta(result.getFloat("Juros_MULTA"));
				j.setJuros(result.getFloat("Juros_JUROS"));
				j.setIntervaloCobranca(result.getString("Juros_INTERVALO"));
				sda.setJuros(j);
				c.setId(result.getInt("CATEGORIA"));
				c.setDescricao(result.getString("Categoria_DESCRICAO"));
				c.setCor(result.getString("Categoria_COR"));
				sda.setCategoria(c);
				co.setId(result.getInt("CONTA"));
				co.setTipo(result.getString("Conta_TIPO"));
				co.setBanco(result.getString("Conta_BANCO"));
				co.setInfo(result.getString("Conta_INFO"));
				sda.setConta(co);
				ca.setId(result.getInt("CARTAO"));
				ca.setBandeira(result.getString("Cartao_BANDEIRA"));
				sda.setData(result.getDate("DataSaida"));
				
				saidas.add(sda);
			}
			
			return saidas;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
