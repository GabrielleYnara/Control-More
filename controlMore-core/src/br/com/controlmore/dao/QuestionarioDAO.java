package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Questionario;
import br.com.controlmore.dominio.Usuario;

public class QuestionarioDAO extends AbstractDAO{

	@Override
	public String salvar(EntidadeDominio entidade) {
		Questionario questionario = (Questionario) entidade;
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO Questionario(Renda, Compra, Agua, Luz, Internet, Recarga, ");
		sql.append("Transporte, Aluguel, Educacao, Lazer, Outros) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){//Preparador que vai gerenciar o SQL
			preparador.setFloat(1, questionario.getRenda());
			preparador.setFloat(2, questionario.getCompra());
			preparador.setFloat(3, questionario.getAgua());
			preparador.setFloat(4, questionario.getLuz());
			preparador.setFloat(5, questionario.getInternet());
			preparador.setFloat(6, questionario.getRecarga());
			preparador.setFloat(7, questionario.getTransporte());
			preparador.setFloat(8, questionario.getAluguel());
			preparador.setFloat(9, questionario.getEducacao());
			preparador.setFloat(10, questionario.getLazer());
			preparador.setFloat(11, questionario.getOutros());
			//executar comando
			preparador.execute();
			
		}catch (SQLException e) {
			try{
				//desfaz alteração no banco
				conexao.rollback();
			}catch(SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
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
		StringBuilder sql = new StringBuilder();
		sql.append("select MAX(Id) as Id from questionario");
		
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
			
			ResultSet resultado = preparador.executeQuery(); //Para obter retorno da Query que será executada
			List<EntidadeDominio> questionarios = new ArrayList<EntidadeDominio>(); //cria uma lista de saidas

			if(resultado.next()){
				Questionario questionario = new Questionario();
				questionario.setId(resultado.getInt("Id"));
				
				/*questionario.setRenda(resultado.getFloat("Renda"));
				questionario.setCompra(resultado.getFloat("Compra"));
				questionario.setAgua(resultado.getFloat("Agua"));
				questionario.setLuz(resultado.getFloat("Luz"));
				questionario.setInternet(resultado.getFloat("Internet"));
				questionario.setRecarga(resultado.getFloat("Recarga"));
				questionario.setTransporte(resultado.getFloat("Transporte"));
				questionario.setAluguel(resultado.getFloat("Aluguel"));
				questionario.setEducacao(resultado.getFloat("Educacao"));
				questionario.setLazer(resultado.getFloat("Lazer"));
				questionario.setOutros(resultado.getFloat("Outros"));*/

				questionarios.add(questionario);
				return questionarios;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
