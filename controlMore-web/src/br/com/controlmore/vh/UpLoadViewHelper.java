package br.com.controlmore.vh;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.EventListener;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.print.DocFlavor.READER;
import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.SessionCookieConfig;
import javax.servlet.SessionTrackingMode;
import javax.servlet.FilterRegistration.Dynamic;
import javax.servlet.descriptor.JspConfigDescriptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.UploadContext;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.Cartao;
import br.com.controlmore.dominio.Categoria;
import br.com.controlmore.dominio.Conta;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Entrada;
import br.com.controlmore.dominio.Frequencia;
import br.com.controlmore.dominio.Juros;
import br.com.controlmore.dominio.Parcela;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.vm.upLoadVM;

public class UpLoadViewHelper implements IViewHelper {

	@Override
	public EntidadeDominio getEntidade(HttpServletRequest request) {
		String acao = request.getParameter("acao");			
		LocalDate data = LocalDate.now();
		List<Saida> saidas = new ArrayList<Saida>();
		List<Entrada> entradas = new ArrayList<Entrada>();
		upLoadVM upVM = new upLoadVM();
		
		if(request.getParameter("txtUpload")!=null){
			String[] linha = request.getParameter("txtUpload").split("\n"); //Separa o registro por linha
			
			for (int i=0; i<linha.length ; i++) {//Enquanto for menor que texto
		        
			    String[] coluna = linha[i].split(";");
			    if(coluna[0].equals("aPagar")){
			    	Saida s = new Saida();
			    	if(coluna[1]!=null){//Pega a data
			    		String dt[] = coluna[1].split("/");
				   		data = LocalDate.parse(dt[2]+"-"+dt[1]+"-"+dt[0]);
						Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
						s.setData(Date.from(instant));
			    	}else{
			    		Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			    		s.setData(Date.from(instant));
			    	}
			    	if(coluna[2]!=null){//Pega a descricao
			    		s.setDescricao(coluna[2]);
			        }else{
			        	s.setDescricao("Importação de arquivo, linha "+ i + ".");
			        }
			        if(coluna[3]!=null){//Pega valor
			        	s.setValor(Float.parseFloat(coluna[3].replace(",", ".")));
			        }
					if(coluna[4]!=null){//Pega categoria
						Categoria c = new Categoria();
						c.setId(0);
						c.setDescricao(coluna[4]);
						s.setCategoria(c);
					}else{
						s.setCategoria(null);
					}
					if(coluna[5]!=null){//Pega cartao ou conta
						Conta c = new Conta();//Inicialmete vai passar a descrição para conta, no DAO verifica se é conta ou cartao.
						c.setInfo(coluna[5].replace("\r", ""));
						c.setId(0);
						s.setConta(c);
						Cartao ct = new Cartao();
						ct.setId(0);
						s.setCartao(ct);
					}
					data = LocalDate.now();
					Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		    		s.setDtCadastro(Date.from(instant));
					saidas.add(s);
				}
			    if(coluna[0].equals("aReceber")){
			    	Entrada e = new Entrada();
			        if(coluna[1]!=null){//Pega a data
			        	String dt[] = coluna[1].split("/");
				    	data = LocalDate.parse(dt[2]+"-"+dt[1]+"-"+dt[0]);
				      	Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
						e.setDataEntrada(Date.from(instant));
			        }else{
			        	Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
			    		e.setDataEntrada(Date.from(instant));
			        }
			        if(coluna[2]!=null){//Pega a descricao
			        	e.setDescricao(coluna[2]);
			        }else{
			        	e.setDescricao("Importação de arquivo, linha "+ i + ".");
			        }
			        if(coluna[3]!=null){//Pega valor
			        	e.setValor(Float.parseFloat(coluna[3].replace(",", ".")));
			        }
			        //coluna[4] seria destinado à categoria, porem entrada não possui categoria
					if(coluna[5]!=null){//Pega cartao ou conta
						Conta c = new Conta();//Inicialmete vai passar a descrição para conta, no DAO verifica se é conta ou cartao.
						c.setInfo(coluna[5].replace("\r", ""));
						c.setId(0);
						e.setConta(c);
						Cartao ct = new Cartao();
						ct.setId(0);
						e.setCartao(ct);
					}
					data = LocalDate.now();
					Instant instant = data.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant();
		    		e.setDtCadastro(Date.from(instant));
					entradas.add(e);
			    }
		    }   
		}
	
		upVM.setEntradas(entradas);
		upVM.setSaidas(saidas);
	    
		return upVM;
	}

	@Override
	public void setView(Resultado resultado, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		RequestDispatcher d = null; //Será responsável por redirecionamento
		request.setAttribute("resultado", resultado);
		d = request.getRequestDispatcher("/Home?acao=resumo");//redireciona a pagina
		d.forward(request, response);
		
	}

}
