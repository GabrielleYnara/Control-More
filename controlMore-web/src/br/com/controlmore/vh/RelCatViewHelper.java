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
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.RelatorioCategoria;

public class RelCatViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String acao = request.getParameter("acao");
		RelatorioCategoria relCat = new RelatorioCategoria();
		LocalDate data = LocalDate.now();

		if(request.getParameter("txtDataInicial")!= null){
			data = LocalDate.parse(request.getParameter("txtData"));
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			relCat.setInicio(Date.from(instant));
		}
		if(request.getParameter("txtDataFinal")!= null){
			data = LocalDate.parse(request.getParameter("txtData"));
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			relCat.setFim(Date.from(instant));
		}
		
		return relCat;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null; //Será responsável por redirecionamento
		
		String acao = request.getParameter("acao");
		
		if(resultado.getMsg() == null){
			if(acao.equals("salvar")){
				d = request.getRequestDispatcher("/principal.jsp");//redireciona a pagina
			}
			if (acao.equals("alterar")) {
				d = request.getRequestDispatcher("/principal.jsp");
			}
			if (acao.equals("excluir")){
				d = request.getRequestDispatcher("/principal.jsp");
			}
			if(acao.equals("consultar")){
				request.getSession().setAttribute("relCat", resultado);
				d = request.getRequestDispatcher("/ResumoCategoria.jsp");
			}
			if(acao.equals("visualizar")){
				d = request.getRequestDispatcher("/principal.jsp");
			}
		}
		d.forward(request, response);


	}

}
