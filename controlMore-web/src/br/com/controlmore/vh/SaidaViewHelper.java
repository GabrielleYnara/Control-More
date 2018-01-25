package br.com.controlmore.vh;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.Cartao;
import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Frequencia;
import br.com.controlmore.dominio.Juros;
import br.com.controlmore.dominio.Parcela;
import br.com.controlmore.dominio.Saida;

public class SaidaViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String acao = request.getParameter("acao");
		Saida saida = new Saida();
		Frequencia frequencia = new Frequencia();
		Conta conta = new Conta();
		Cartao cartao = new Cartao();
		Parcela parcela = new Parcela();
		Categoria categoria = new Categoria();
		Juros juros = new Juros();
		
		if(!acao.equals("visualizar")){
			int id = 0;
			double valor = 0;
			String descricao = null;
			int qteParcelas=0;
			LocalDate data = LocalDate.now();
			int importancia = 0;
			String situacao = null;
			
			if(request.getParameter("txtId")!=null && !request.getParameter("txtId").trim().equals("")){
				id = Integer.parseInt(request.getParameter("txtId"));
			}
			if(request.getParameter("txtValor")!= null){
				valor = Double.parseDouble(request.getParameter("txtValor").replace(",", "."));
			}
			if(request.getParameter("txtFreq")!= null){
				frequencia.setId(Integer.parseInt(request.getParameter("txtFreq")));
			}
			if(request.getParameter("txtDescricao")!= null){
				descricao = request.getParameter("txtDescricao");
			}
			if(request.getParameter("txtConta")!= null && request.getParameter("txtConta").trim().equals("")){
				String array[] = request.getParameter("txtConta").split("/");
				if(array[1].equals("conta"))
					conta.setId(Integer.parseInt(array[0]));
				if(array[1].equals("cartao"))
					cartao.setId(Integer.parseInt(array[0]));
			}
			if(request.getParameter("txtData")!= null){
				data = LocalDate.parse(request.getParameter("txtData"));
				Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				saida.setData(Date.from(instant));
			}
			else{
				Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				saida.setData(Date.from(instant));
			}
			if(request.getParameter("txtCategoria")!= null){
				categoria.setId(Integer.parseInt(request.getParameter("txtCategoria")));
			}
			if(request.getParameter("txtVencimento")!= null){
				data = LocalDate.parse(request.getParameter("txtVencimento"));
				Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				saida.setVencimento(Date.from(instant));
			}else{
				Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				saida.setVencimento(Date.from(instant));
			}
			if(request.getParameter("txtNumParcelas")!= null){
				qteParcelas = Integer.parseInt(request.getParameter("txtNumParcelas"));
			}
			if(request.getParameter("txtValorParcela")!=null){
				for(int i = 1; i<=qteParcelas;i++){
					parcela.setNumParcela(i);
					double valorP = Double.parseDouble(request.getParameter("txtValorParcela").replace(",", "."));
					parcela.setValorParcela( (float) valorP);
					parcela.setSituacao(request.getParameter("txtSituacao"));
					saida.setParcela(parcela);
				}
			}
			if(request.getParameter("txtMulta")!=null && !request.getParameter("txtMulta").trim().equals("")){
				juros.setMulta(Float.parseFloat(request.getParameter("txtMulta").replace(",", ".")));
			}
			if(request.getParameter("txtJuros")!=null && !request.getParameter("txtJuros").trim().equals("")){
				juros.setJuros(Float.parseFloat(request.getParameter("txtJuros").replace(",", ".")));
			}
			if(request.getParameter("txtIntervalo")!=null){
				juros.setIntervaloCobranca(request.getParameter("txtIntervalo"));
			}
			if(request.getParameter("txtImportancia")!= null){
				importancia = Integer.parseInt(request.getParameter("txtImportancia"));
			}
			if(request.getParameter("txtSituacao")!= null){
				situacao = request.getParameter("txtSituacao");
			}
			
			saida.setId(id);
			saida.setCartao(cartao);
			saida.setCategoria(categoria);
			saida.setConta(conta);
			saida.setDescricao(descricao);
			saida.setFrequencia(frequencia);
			saida.setImportancia(importancia);
			saida.setJuros(juros);
			saida.setSituacao(situacao);
			saida.setValor( (float) valor);
			
		}else{
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("saida");
			String txtId = request.getParameter("txtId");
			int id=0;
			
			if(txtId != null && !txtId.trim().equals("")){
				id = Integer.parseInt(txtId);
			}
				
			for(EntidadeDominio e: resultado.getEntidades()){
				if(e.getId() == id){
					saida = (Saida) e;
				}
			}
		}
	
		return saida;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null; //Será responsável por redirecionamento
		
		String acao = request.getParameter("acao");
		
			if(acao.equals("salvar")){
				request.getSession().setAttribute("resultado", resultado);
				request.getSession().setAttribute("saida", resultado);
				d = request.getRequestDispatcher("/Home?acao=resumo");//redireciona a pagina
			}
			if (acao.equals("alterar")) {
				request.getSession().setAttribute("saida", resultado);
				d = request.getRequestDispatcher("/lancamentos.jsp");
			}
			if (acao.equals("excluir")){
				request.getSession().setAttribute("saida", null);
				d = request.getRequestDispatcher("/lancamentos.jsp");
			}
			if(acao.equals("consultar")){
				request.getSession().setAttribute("saida", resultado);
				if(resultado.getEntidades().size()==1)
					d = request.getRequestDispatcher("/saidaCompleta.jsp");
				else
					d = request.getRequestDispatcher("/lancamentos.jsp");
			}
			if(acao.equals("visualizar")){
				request.getSession().setAttribute("saida", resultado);
				d = request.getRequestDispatcher("/saidaCompleta.jsp");
			}
		d.forward(request, response);

	}

}
