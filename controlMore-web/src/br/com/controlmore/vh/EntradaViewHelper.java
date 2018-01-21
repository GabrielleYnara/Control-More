package br.com.controlmore.vh;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Entrada;
import br.com.controlmore.dominio.Frequencia;
import br.com.controlmore.dominio.Parcela;

public class EntradaViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String acao = request.getParameter("acao");
		Entrada entrada = new Entrada();
		Frequencia frequencia = new Frequencia();
		Conta conta = new Conta();
		Cartao cartao = new Cartao();
		Parcela parcela = new Parcela();
		
			
		if(!acao.equals("visualizar")){
			int id = 0;
			float valor =0;
			String descricao = null;
			String observacao = null;
			String situacao = null;
			LocalDate data = LocalDate.now();

			
			//copiando as informações do formulário web
			if(request.getParameter("txtId")!=null && !request.getParameter("txtId").trim().equals("") ){
				id = Integer.parseInt(request.getParameter("txtId"));
			}
			if(request.getParameter("txtValor")!= null){
				valor = Float.parseFloat(request.getParameter("txtValor"));
			}
			if(request.getParameter("txtDescricao")!=null){
				descricao = request.getParameter("txtDescricao");
			}
			if(request.getParameter("txtData")!= null){
				data = LocalDate.parse(request.getParameter("txtData"));
				Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				entrada.setDataEntrada(Date.from(instant));
			}
			else{
				Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				entrada.setDataEntrada(Date.from(instant));
			}
			if(request.getParameter("txtFreq")!=null){
				frequencia.setId(Integer.parseInt(request.getParameter("txtFreq")));
			}
			if(request.getParameter("txtConta")!=null){
				conta.setId(Integer.parseInt(request.getParameter("txtConta")));
			}
			if(request.getParameter("txtCartao")!=null){
				cartao.setId(Integer.parseInt(request.getParameter("txtCartao")));
			}
			if(request.getParameter("txtObservacao")!=null){
				observacao = request.getParameter("txtObservacao");
			}
			if(request.getParameter("txtSituacao")!= null){
				situacao = request.getParameter("txtSituacao");
			}
			
			//atribuindo
			entrada.setId(id);
			entrada.setValor(valor);
			entrada.setDescricao(descricao);
			entrada.setFrenquencia(frequencia);
			entrada.setConta(conta);
			entrada.setCartao(cartao);
			entrada.setObservacoes(observacao);
			entrada.setSituacao(situacao);
			
		}else{
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("entrada");
			String txtId = request.getParameter("txtId");
			int id=0;
			
			if(txtId != null && !txtId.trim().equals("")){
				id = Integer.parseInt(txtId);
			}
			
			for(EntidadeDominio e: resultado.getEntidades()){
				if(e.getId() == id){
					entrada = (Entrada)e;
				}
			}
		}

		return entrada;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null; //Será responsável por redirecionamento
		
		String acao = request.getParameter("acao");
		
		if(resultado.getMsg() == null){
			if(acao.equals("salvar")){
				resultado.setMsg("Conta a receber cadastrada com sucesso!");
				request.setAttribute("resultado", resultado);
				request.getSession().setAttribute("entrada", resultado);
				d = request.getRequestDispatcher("/Home?acao=resumo");//redireciona a pagina
			}
			if (acao.equals("alterar")) {
				resultado.setMsg("Conta a receber alterada com sucesso!");
				request.setAttribute("resultado", resultado);
				request.getSession().setAttribute("entrada", resultado);
				d = request.getRequestDispatcher("/lancamentos.jsp");
			}
			if (acao.equals("excluir")){
				resultado.setMsg("Conta a receber excluida com sucesso!");
				request.setAttribute("resultado", resultado);
				request.getSession().setAttribute("entrada", null);
				d = request.getRequestDispatcher("/lancamentos.jsp");
			}
			if(acao.equals("consultar")){
				request.getSession().setAttribute("entrada", resultado);
				if(resultado.getEntidades().size()==1)
					d = request.getRequestDispatcher("/entradaCompleta.jsp");
				else
					d = request.getRequestDispatcher("/lancamentos.jsp");
			}
			if(acao.equals("visualizar")){
				request.getSession().setAttribute("entradaR", resultado);
				d = request.getRequestDispatcher("/entradaCompleta.jsp");
			}
		}
		d.forward(request, response);
	}

}
