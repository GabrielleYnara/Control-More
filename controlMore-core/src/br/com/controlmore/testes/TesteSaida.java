package br.com.controlmore.testes;

import br.com.controlmore.dao.CartaoDAO;
import br.com.controlmore.dao.FrequenciaDAO;
import br.com.controlmore.dao.SaidaDAO;
import br.com.controlmore.dominio.Cartao;
import br.com.controlmore.dominio.Frequencia;
import br.com.controlmore.dominio.Saida;

public class TesteSaida {
	public static void main(String[] args) {

		//salvar();
		alterar();
		//excluir();
		//consultar();
	}
	public static void salvar(){
		Cartao c = new Cartao();
		CartaoDAO cDAO = new CartaoDAO();
		c.setId(1);
		c = (Cartao) cDAO.consultar(c);
		Frequencia f = new Frequencia();
		FrequenciaDAO fDAO = new FrequenciaDAO();
		f.setId(1);
		f = (Frequencia) fDAO.consultar(f);
		Saida s = new Saida();
		SaidaDAO sDAO = new SaidaDAO();
		s.setId(17);
		s.setDescricao("Teste Salvar C/P");
		s.setValor(23.99f);
		//s.setFrenquencia(f);
		s.setCartao(c);
		sDAO.salvar(s);
		System.out.println("ok");
	}
	public static void alterar(){
		Saida s = new Saida();
		SaidaDAO sDAO = new SaidaDAO();
		s.setId(17);
		//s.setDtCadastro("24/08/2016");
		s.setValor(29.85f);
		s.setImportancia(2);
		sDAO.alterar(s);
		System.out.println("ok");
	}
	public static void excluir(){
		Saida s = new Saida();
		SaidaDAO sDAO = new SaidaDAO();
		s.setId(17);
		sDAO.excluir(s);
	}
}
