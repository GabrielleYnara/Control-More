package br.com.controlmore.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.AvaliacaoGasto;
import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Questionario;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.negocio.Avaliacao;

public class AvaliacaoDAO extends AbstractDAO{

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
		List<EntidadeDominio> list = new ArrayList<EntidadeDominio>();
		AvaliacaoGasto avaliacao = (AvaliacaoGasto) entidade;
		List<Saida> saidas = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM MEDIA_CATEGORIA_6M");
		
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
			
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			
			while(result.next()){
				Saida sda = new Saida();
				Categoria c = new Categoria();
				//Irá copiar as infomações do banco e criar um objeto de saida;
				sda.setValor(result.getFloat("ValorTotal"));
				c.setDescricao(result.getString("Descricao"));
				sda.setCategoria(c);
				
				saidas.add(sda);
			}
			avaliacao.setSaidas(saidas);
		}catch (SQLException e) {
			e.printStackTrace();
		}
		sql.delete(0, sql.length());
		sql.append("SELECT * FROM Questionario");
		try(PreparedStatement preparador = conexao.prepareStatement(sql.toString())){
			
			ResultSet result = preparador.executeQuery(); //passa o resultado da execução da Query para a variável result
			
			while(result.next()){
				Questionario q = new Questionario();
				//Irá copiar as infomações do banco e criar um objeto de saida;
				q.setId(result.getInt("ID"));
				q.setRenda(result.getFloat("RENDA"));
				q.setCompra(result.getFloat("COMPRA"));
				q.setAgua(result.getFloat("AGUA"));
				q.setLuz(result.getFloat("LUZ"));
				q.setInternet(result.getFloat("INTERNET"));
				q.setRecarga(result.getFloat("RECARGA"));
				q.setTransporte(result.getFloat("TRANSPORTE"));
				q.setAluguel(result.getFloat("ALUGUEL"));
				q.setEducacao(result.getFloat("EDUCACAO"));
				q.setLazer(result.getFloat("LAZER"));
				q.setOutros(result.getFloat("OUTROS"));
				
				avaliacao.setQuestionario(q);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		list.add(avaliacao);
		Avaliacao ava = new Avaliacao();
		ava.processar(avaliacao);
		return list;
	}
	
}
