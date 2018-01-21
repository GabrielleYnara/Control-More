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
import br.com.controlmore.dominio.Pessoa;
import br.com.controlmore.dominio.Usuario;

public class PessoaViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String acao = request.getParameter("acao");
		Pessoa pessoa =  null;
		Usuario usuario = null;
		
		if(acao.equals("logout")){
			HttpSession session = request.getSession(false);//Se não tiver sessão criada, ele não cria
			if(session!=null){
				session.invalidate();
			}
		}
		if(!acao.equals("visualizar")){
			String email = null;
			String senha= null;
			int id= 0;
			String nome= null;
			String telefone= null;
			LocalDate data = LocalDate.now();		
			
			pessoa = new Pessoa();
			usuario = new Usuario();
			
			if(request.getParameter("txtEmail")!=null){
				email = request.getParameter("txtEmail");
			}
			if(request.getParameter("txtSenha")!=null){
				senha = request.getParameter("txtSenha");
			}
			if(request.getParameter("txtId")!=null){
				id = Integer.parseInt(request.getParameter("txtId"));
			}
			if(request.getParameter("txtNome")!=null){
				nome = request.getParameter("txtNome");
			}
			if(request.getParameter("txtTelefone")!=null){
				telefone = request.getParameter("txtTelefone");
			}
			if(request.getParameter("txtDtNascimento")!=null){
				data = LocalDate.parse(request.getParameter("txtDtNascimento"));
				Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
				pessoa.setDtNascimento(Date.from(instant));
			}
			
			if(email != null && !email.trim().equals("")){
				usuario.setEmail(email);
			}
			
			if(senha != null && !senha.trim().equals("")){
				usuario.setSenha(senha);
			}
			
			if(id != 0){
				pessoa.setId(id);
			}
			
			if(nome != null && !nome.trim().equals("")){
				pessoa.setNome(nome);
			}
			if(telefone !=null && !telefone.trim().equals("")){
				pessoa.setTelefone(telefone);
			}
			if(usuario!=null){
				pessoa.setUsuario(usuario);
			}
		}else{
			HttpSession session = request.getSession();
			Resultado resultado = (Resultado) session.getAttribute("resultado");
			String txtId = request.getParameter("txtId");
			int id=0;
			
			if(txtId != null && !txtId.trim().equals("")){
				id = Integer.parseInt(txtId);
			}
			
			for(EntidadeDominio e: resultado.getEntidades()){
				if(e.getId() == id){
					pessoa = (Pessoa)e;
				}
			}
		}
		return pessoa;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null; //Será responsável por redirecionamento
		
		String acao = request.getParameter("acao");
		
		if(resultado.getMsg() == null){
			if(acao.equals("salvar")){
				resultado.setMsg("Usuario cadastrado com sucesso!");
				Pessoa pessoa = (Pessoa) resultado.getEntidades().get(0);
				request.getSession().setAttribute("pessoa_new", pessoa);
				request.getSession().setAttribute("pessoa", resultado);
				request.setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("/index.jsp");//redireciona a pagina
			}
			if (acao.equals("login")) {
				Pessoa pessoa = (Pessoa) resultado.getEntidades().get(0);
				request.getSession().setAttribute("pessoa_new", pessoa);
				request.getSession().setAttribute("pessoa", resultado);
				if(pessoa.getQuestionario().getId()!=0){
					d = request.getRequestDispatcher("/Home?acao=resumo");
				}else{
					d = request.getRequestDispatcher("/questionario.jsp");
				}
			}
			if (acao.equals("alterar")) {
				Pessoa pessoa = (Pessoa) resultado.getEntidades().get(0);
				request.getSession().setAttribute("pessoa_new", pessoa);
				request.getSession().setAttribute("pessoa", resultado);
				d = request.getRequestDispatcher("/Home?acao=resumo");
			}
			if (acao.equals("excluir") || acao.equals("logout")){
				request.getSession().setAttribute("pessoa", null);
				HttpSession session = request.getSession(false);// Se houver sessao, é atribuido para sessao. Se não houver, não é criado nova sessao
				if(session!=null)
					session.invalidate();
				d = request.getRequestDispatcher("/index.jsp");
			}
			if(acao.equals("consultar")){
				Pessoa pessoa = (Pessoa) resultado.getEntidades().get(0);
				request.getSession().setAttribute("pessoa_new", pessoa);
				request.getSession().setAttribute("pessoa", resultado);
				d = request.getRequestDispatcher("/Home?acao=resumo");
			}
			if(acao.equals("visualizar")){
				Pessoa pessoa = (Pessoa) resultado.getEntidades().get(0);
				request.getSession().setAttribute("pessoa_new", pessoa);
				request.getSession().setAttribute("pessoa", resultado);
				d = request.getRequestDispatcher("/principal.jsp#minhaConta");
			}
		} else{
			if (acao.equals("salvar") || acao.equals("login")){
				Pessoa pessoa = (Pessoa) resultado.getEntidades().get(0);
				request.getSession().setAttribute("pessoa_new", pessoa);
				request.getSession().setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("/index.jsp");
			}
			if(acao.equals("alterar")){
				Pessoa pessoa = (Pessoa) resultado.getEntidades().get(0);
				request.getSession().setAttribute("pessoa_new", pessoa);
				request.getSession().setAttribute("resultado", resultado);
				d = request.getRequestDispatcher("/Home?acao=resumo");
			}
		}
		d.forward(request, response);

	}

}
