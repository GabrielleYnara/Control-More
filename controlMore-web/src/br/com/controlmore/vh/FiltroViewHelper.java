package br.com.controlmore.vh;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Filtro;

public class FiltroViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String acao = request.getParameter("acao");
		Filtro filtro = new Filtro();
		LocalDate data = LocalDate.now();

		if(request.getParameter("txtDataInicial")!= null){
			data = LocalDate.parse(request.getParameter("txtDataInicial"));
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			filtro.setDtInicio(Date.from(instant));
		}else{//atribui o primeiro dia do ano
			data = LocalDate.now().with(TemporalAdjusters.firstDayOfYear());
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			filtro.setDtInicio(Date.from(instant));
		}
		if(request.getParameter("txtDataFinal")!= null){
			data = LocalDate.parse(request.getParameter("txtDataFinal"));
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			filtro.setDtFinal(Date.from(instant));
		}else{//atribui ultimo dia do ano
			data = LocalDate.now().with(TemporalAdjusters.lastDayOfYear());
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			filtro.setDtFinal(Date.from(instant));
		}
		if(request.getParameter("txtConsulta")!=null){
			filtro.setConsulta(request.getParameter("txtConsulta"));
		}
		return filtro;
	}


	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
RequestDispatcher d = null; //Será responsável por redirecionamento
		
		String acao = request.getParameter("acao");
		
		if(resultado.getMsg() == null){
			if(acao.equals("salvar")){
				
			}
			if (acao.equals("alterar")) {
				
			}
			if (acao.equals("excluir")){
				
			}
			if(acao.equals("consultar")){
				request.getSession().setAttribute("filtro", resultado);
				d = request.getRequestDispatcher("/lancamentos.jsp");
			}
			if(acao.equals("visualizar")){
				
			}
		}
		d.forward(request, response);

	}

}
