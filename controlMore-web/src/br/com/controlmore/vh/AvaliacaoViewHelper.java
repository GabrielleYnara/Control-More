package br.com.controlmore.vh;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.AvaliacaoGasto;
import br.com.controlmore.dominio.EntidadeDominio;

public class AvaliacaoViewHelper implements IViewHelper{

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String acao = request.getParameter("acao");
		AvaliacaoGasto avaliacao = new AvaliacaoGasto();
		
		return avaliacao;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
RequestDispatcher d = null; //Será responsável por redirecionamento
		
		String acao = request.getParameter("acao");
		
		if(resultado.getMsg() == null){
			if(acao.equals("salvar")){
				resultado.setMsg("Saida cadastrada com sucesso!");
				request.getSession().setAttribute("resultado", resultado);
				request.getSession().setAttribute("saida", resultado);
				d = request.getRequestDispatcher("/principal.jsp");//redireciona a pagina
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
				request.getSession().setAttribute("avaliacao", resultado);
				d = request.getRequestDispatcher("/metas.jsp");
			}
			if(acao.equals("visualizar")){
				
			}
		}else{
			
		}
		d.forward(request, response);
		
	}

}
