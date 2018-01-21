package br.com.controlmore.vh;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
		String categoria1 = null;
		String categoria2 = null;
		if(request.getParameter("txtDataInicial")!= null){
			data = LocalDate.parse(request.getParameter("txtDataInicial"));
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			relCat.setInicio(Date.from(instant));
		}else{// atribui primeiro dia do mês
			data = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			relCat.setInicio(Date.from(instant));
		}
		if(request.getParameter("txtDataFinal")!= null){
			data = LocalDate.parse(request.getParameter("txtDataFinal"));
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			relCat.setFim(Date.from(instant));
		}else{// atribui ultimo dia do mês
			data = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
			Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			relCat.setFim(Date.from(instant));
		}
		if(request.getParameter("categoria1")!=null){
			categoria1 = request.getParameter("categoria1");
		}
		if(request.getParameter("categoria2")!=null){
			categoria2 = request.getParameter("categoria2");
		}
		relCat.setCategoria1(categoria1);
		relCat.setCategoria2(categoria2);
		
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
					
				RelatorioCategoria relatorio = (RelatorioCategoria) resultado.getEntidades().get(0);
				if ( relatorio.getCategoria1()!=null || relatorio.getCategoria2()!=null){
					List<EntidadeDominio> relCat = new ArrayList<EntidadeDominio>();
					for (EntidadeDominio entidadeDominio : resultado.getEntidades()) {
						relCat.add(entidadeDominio);
					}
					
					request.setAttribute("relCat", relCat);
					d = request.getRequestDispatcher("comparacaoCategoria.jsp");
				}
					
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
