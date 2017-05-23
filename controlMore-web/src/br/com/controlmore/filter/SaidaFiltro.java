package br.com.controlmore.filter;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.negocio.VerificarSaldo;
import br.com.controlmore.vh.SaidaViewHelper;

/**
 * Servlet Filter implementation class SaidaFiltro
 */
@WebFilter("/Saida")
public class SaidaFiltro implements Filter {

    /**
     * Default constructor. 
     */
    public SaidaFiltro() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request; //Casting para que request possa acessar os metodos do HttpServlet
		
		String acao = httpRequest.getParameter("acao");
		chain.doFilter(request, response);
		if(acao.equals("salvar")){
			
			SaidaViewHelper Svh = new SaidaViewHelper();
			Saida saida = (Saida) Svh.getEntidade((HttpServletRequest) request);
			VerificarSaldo verificar = new VerificarSaldo();
			String aviso = verificar.processar(saida);
			StringBuilder msg = new StringBuilder();
			Resultado resultado =  new Resultado();
			msg.append(aviso);
			resultado.setMsg(msg.toString());
			httpRequest.getSession().setAttribute("resultado", resultado);
	
		}	
			
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
