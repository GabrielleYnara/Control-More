<%@page import="java.text.DecimalFormat"%>
<%@page import="br.com.controlmore.dominio.AvaliacaoGasto"%>
<%@page import="br.com.controlmore.dominio.Meta"%>
<%@page import="br.com.controlmore.dominio.EntidadeDominio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- CSS -->
<link rel="stylesheet" href="css/metas.css">

<title>Simular Metas</title>
</head>

<body>
<%@include file="menu.jsp"%>
<% 
	List<EntidadeDominio> metas = new ArrayList<EntidadeDominio>();
	resultado = (Resultado) request.getSession().getAttribute("meta");
	metas = resultado.getEntidades();
	
	resultado = (Resultado) request.getSession().getAttribute("metaSimulada");
	Meta metaSimulada = new Meta();
	if(request.getSession().getAttribute("metaSimulada")!=null){
		metaSimulada = (Meta) resultado.getEntidades().get(0);
	}
	AvaliacaoGasto avaliacao = new AvaliacaoGasto();
	if(request.getSession().getAttribute("avaliacao")!=null){
	resultado = (Resultado) request.getSession().getAttribute("avaliacao");
	avaliacao = (AvaliacaoGasto) resultado.getEntidades().get(0);
	}
%>
<script type="text/javascript">
function excluirMeta(id){
	
	location.href="Meta?acao=excluir&txtId="+ id;
}

function consultarMeta(id){
	
	location.href="Meta?acao=visualizar&txtId="+ id;
}
</script>
 	<div class="row">
		<div class="col-md-4 hidden">
		*Aqui deverá ter um gráfico de todas as metas cadastradas, uma linha deverá mostrar a meta projetada outra linha
		paralela deverá mostrar a meta realizada/alcançada.*
		</div>
		
		<div class="col-md-4">
			<div class="panel panel-info">
	  			<div class="panel-heading text-center">
	  				Simulador de Metas
	  			</div>
	  			<div class="panel-body">
	    			<form action="Meta?acao=consultar" method="post">
	    				<div class="col-md-6">
	    					<label for="txtValorTotal">Valor Total:</label>
							<input type="text" name="txtValorTotal" class="form-control" autofocus required placeholder="R$0,00"
							<%if(metaSimulada.getValorTotal()!= 0)
	    					out.print("value='"+metaSimulada.getValorTotal()+"'");
	    					%>>
					
							<label for="txtValorMensal">Valor Mensal:</label>
							<input type="text" name="txtValorMensal" class="form-control" autofocus required placeholder="R$0,00"
							<%if(metaSimulada.getValorMensal()!= 0)
	    					out.print("value='"+metaSimulada.getValorMensal()+"'");
	    					%>>
						
							<label for="txtDtPrazo">Prazo:</label><br>
							<input type="date" name="txtPrazo" class="form-control" autofocus placeholder="dd/mm/aaaa"
							<%if(metaSimulada.getPrazo()!=null)
	    					out.print("value='"+metaSimulada.getPrazo()+"'");
	    					%>>
							<div align="center">
								<button type='submit' class="btn btn-primary" id='acao'>Simular</button>
							</div>
						</div><!-- end 6 colunas -->
						</form><!-- end form -->
						<div class="col-md-6 bg-warning text-center">
							<%if(metaSimulada.getMsg()!=null){
	    					out.print(metaSimulada.getMsg());%>
	    					<form action="Avaliacao?acao=consultar" method="post">
	    						<div align="center">
									<button type='submit' class="btn btn-primary" id='acao'><i class="glyphicon glyphicon-plus-sign"></i></button>
								</div>
	    					</form>
	    					<%}%>
						</div>
	    			
	    		</div><!-- end body -->
	  		</div><!-- end painel -->
	  		
	  		<div class="panel panel-info">
	  			<div class="panel-heading text-center">
	  				Cadastrar Meta
	  			</div>
	  			<div class="panel-body">
					<form action="Meta?acao=salvar" method="POST">
						<div class="col-md-12">
							<label for="txtObjetivo">Objetivo:</label>
							<input type="text" name="txtObjetivo" class="form-control" autofocus required placeholder="Viajar para..."><br>
						</div>
						<div class="col-md-6">
				    		<label for="txtValorTotal">Valor Total:</label>
							<input type="text" name="txtValorTotal" class="form-control" autofocus required placeholder="R$0,00"><br>
						</div>
						<div class="col-md-6">
							<label for="txtValorMensal">Valor Mensal:</label>
							<input type="text" name="txtValorMensal" class="form-control" autofocus required placeholder="R$0,00"><br>
						</div>
						<div class="col-md-6">
							<label for="txtPrazo">Prazo:</label><br>
							<input type="date" name="txtPrazo" class="form-control" autofocus required><br>
						</div>
						<div class="col-md-6">
							<label for="txtSaldo">Saldo:</label>
							<input type="text" name="txtSaldo" class="form-control" autofocus placeholder="R$0,00">
						</div>
						<div class="col-md-12" align="center">
							<button type='submit' class="btn btn-primary">Salvar</button>
						</div>
					</form>
				</div><!-- end painel body -->
			</div><!-- end painel -->
	  	</div>
	  	<div class="col-md-8">
	  	<blockquote>Reparamos que nos ultimos 6 meses você tem gasto em média: </blockquote>
	  	<div class="row">
	  		<div class="col-md-3">
				<label for="txtPrazo">Alimentação:</label><br>
				<input type="text" name="txtAlimentacao" class="form-control" autofocus
					<%if(avaliacao.getSaidas().size()>0){
						for(int i=0; i<avaliacao.getSaidas().size(); i++){
							if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Alimentação")){
								out.print("value='" + avaliacao.getSaidas().get(i).getValor()+"'");
							}
						}
					}%>
				><br>
			</div>
			<div class="col-md-9">
				<% float aux = 0;
				DecimalFormat df = new DecimalFormat("0.00");
				if(avaliacao.getSaidas().size()>0){
					for(int i=0; i<avaliacao.getSaidas().size(); i++){
						if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Alimentação")){
							if(avaliacao.getQuestionario().getCompra()> avaliacao.getSaidas().get(i).getValor()){
								aux = avaliacao.getQuestionario().getCompra()- avaliacao.getSaidas().get(i).getValor();
								out.println("Você tem gastado R$" + aux +" menos.");
							}else{
								aux = avaliacao.getSaidas().get(i).getValor()-avaliacao.getQuestionario().getCompra();
								out.print("Você tem gastado <strong>R$" + aux + "</strong> a mais do que o esperado.");%> <br> <%
							}
							aux = 0;
							aux = (avaliacao.getSaidas().get(i).getValor() * 15)/100;
							out.println("Tente reduzir 15%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
								+ df.format(avaliacao.getSaidas().get(i).getValor()-aux)+ "</strong> por mes");%> <br> <%
							aux = 0;
							aux = (avaliacao.getSaidas().get(i).getValor() * 20)/100;
							out.println("Ou 20%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
									+ df.format(avaliacao.getSaidas().get(i).getValor()-aux)+ "</strong> por mes");
						}
					}
				}%>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<label for="txtPrazo">Moradia:</label><br>
				<input type="text" name="txtMoradia" class="form-control" autofocus
				<%if(avaliacao.getSaidas().size()>0){
						for(int i=0; i<avaliacao.getSaidas().size(); i++){
							if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Moradia")){
								out.print("value='" + avaliacao.getSaidas().get(i).getValor()+"'");
							}
						}
					}%>
				><br>
			</div>
			<div class="col-md-9">
				<% aux =0;
				if(avaliacao.getSaidas().size()>0){
					for(int i=0; i<avaliacao.getSaidas().size(); i++){
						if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Moradia")){
							float moradia = avaliacao.getQuestionario().getAgua() + avaliacao.getQuestionario().getAluguel()+
									avaliacao.getQuestionario().getInternet() + avaliacao.getQuestionario().getLuz();
							if(moradia> avaliacao.getSaidas().get(i).getValor()){
								aux = moradia - avaliacao.getSaidas().get(i).getValor();
								out.print("Você tem gastado <strong>R$" + df.format(aux) +"</strong> menos.");
							}else{
								aux = avaliacao.getSaidas().get(i).getValor()-moradia;
								out.print("Você tem gastado <strong>R$" + df.format(aux) +"</strong> a mais do que o esperado.<br>");
							}
							aux = 0;
							aux = (moradia * 15)/100;
							out.println("Tente reduzir 15%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
								+ df.format(moradia-aux)+ "</strong> por mes");%> <br> <%
							aux = 0;
							aux = (moradia * 20)/100;
							out.println("Ou 20%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
									+ df.format(moradia-aux)+ "</strong> por mes");
						}
					}
				}%>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<label for="txtPrazo">Lazer:</label><br>
				<input type="text" name="txtLazer" class="form-control" autofocus
				<%if(avaliacao.getSaidas().size()>0){
						for(int i=0; i<avaliacao.getSaidas().size(); i++){
							if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Lazer")){
								out.print("value='" + avaliacao.getSaidas().get(i).getValor()+"'");
							}
						}
					}%>
				><br>
			</div>
			<div class="col-md-9">
				<% aux = 0;
				if(avaliacao.getSaidas().size()>0){
					for(int i=0; i<avaliacao.getSaidas().size(); i++){
						if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Lazer")){
							if(avaliacao.getQuestionario().getLazer()> avaliacao.getSaidas().get(i).getValor()){
								aux = avaliacao.getQuestionario().getLazer()- avaliacao.getSaidas().get(i).getValor();
								out.println("Você tem gastado R$" + aux +" menos.");
							}else{
								aux = avaliacao.getSaidas().get(i).getValor()-avaliacao.getQuestionario().getLazer();
								out.print("Você tem gastado <strong>R$" + aux + "</strong> a mais do que o esperado.");%> <br> <%
							}
							aux = 0;
							aux = (avaliacao.getSaidas().get(i).getValor() * 15)/100;
							out.println("Tente reduzir 15%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
								+ df.format(avaliacao.getSaidas().get(i).getValor()-aux)+ "</strong> por mes");%> <br> <%
							aux = 0;
							aux = (avaliacao.getSaidas().get(i).getValor() * 20)/100;
							out.println("Ou 20%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
									+ df.format(avaliacao.getSaidas().get(i).getValor()-aux)+ "</strong> por mes");
						}
					}
				}%>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<label for="txtPrazo">Vestuario:</label><br>
				<input type="text" name="txtVestuario" class="form-control" autofocus
				<%if(avaliacao.getSaidas().size()>0){
						for(int i=0; i<avaliacao.getSaidas().size(); i++){
							if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Alimentação")){
								out.print("value='" + avaliacao.getSaidas().get(i).getValor()+"'");
							}
						}
					}%>
				><br>
			</div>
			<div class="col-md-9">
				<% aux = 0;
				if(avaliacao.getSaidas().size()>0){
					for(int i=0; i<avaliacao.getSaidas().size(); i++){
						if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Alimentação")){
							if(avaliacao.getQuestionario().getCompra()> avaliacao.getSaidas().get(i).getValor()){
								aux = avaliacao.getQuestionario().getCompra()- avaliacao.getSaidas().get(i).getValor();
								out.println("Você tem gastado R$" + aux +" menos.");
							}else{
								aux = avaliacao.getSaidas().get(i).getValor()-avaliacao.getQuestionario().getCompra();
								out.print("Você tem gastado <strong>R$" + aux + "</strong> a mais do que o esperado.");%> <br> <%
							}
							aux = 0;
							aux = (avaliacao.getSaidas().get(i).getValor() * 15)/100;
							out.println("Tente reduzir 15%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
								+ df.format(avaliacao.getSaidas().get(i).getValor()-aux)+ "</strong> por mes");%> <br> <%
							aux = 0;
							aux = (avaliacao.getSaidas().get(i).getValor() * 20)/100;
							out.println("Ou 20%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
									+ df.format(avaliacao.getSaidas().get(i).getValor()-aux)+ "</strong> por mes");
						}
					}
				}%>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<label for="txtPrazo">Outros:</label><br>
				<input type="text" name="txtOutros" class="form-control" autofocus
				<%if(avaliacao.getSaidas().size()>0){
						for(int i=0; i<avaliacao.getSaidas().size(); i++){
							if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Outros")){
								out.print("value='" + avaliacao.getSaidas().get(i).getValor()+"'");
							}
						}
					}%>
				><br>
			</div>
			<div class="col-md-9">
				<% aux = 0;
				if(avaliacao.getSaidas().size()>0){
					for(int i=0; i<avaliacao.getSaidas().size(); i++){
						if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Outros")){
							if(avaliacao.getQuestionario().getOutros()> avaliacao.getSaidas().get(i).getValor()){
								aux = avaliacao.getQuestionario().getOutros()- avaliacao.getSaidas().get(i).getValor();
								out.println("Você tem gastado R$" + aux +" menos.");
							}else{
								aux = avaliacao.getSaidas().get(i).getValor()-avaliacao.getQuestionario().getOutros();
								out.print("Você tem gastado <strong>R$" + aux + "</strong> a mais do que o esperado.");%> <br> <%
							}
							aux = 0;
							aux = (avaliacao.getSaidas().get(i).getValor() * 15)/100;
							out.println("Tente reduzir 15%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
								+ df.format(avaliacao.getSaidas().get(i).getValor()-aux)+ "</strong> por mes");%> <br> <%
							aux = 0;
							aux = (avaliacao.getSaidas().get(i).getValor() * 20)/100;
							out.println("Ou 20%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
									+ df.format(avaliacao.getSaidas().get(i).getValor()-aux)+ "</strong> por mes");
						}
					}
				}%>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<label for="txtPrazo">Educacao:</label><br>
				<input type="text" name="txtEducacao" class="form-control" autofocus
				<%if(avaliacao.getSaidas().size()>0){
						for(int i=0; i<avaliacao.getSaidas().size(); i++){
							if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Educacao")){
								out.print("value='" + avaliacao.getSaidas().get(i).getValor()+"'");
							}
						}
					}%>
				><br>
			</div>
			<div class="col-md-9">
				<% aux = 0;
				if(avaliacao.getSaidas().size()>0){
					for(int i=0; i<avaliacao.getSaidas().size(); i++){
						if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Educacao")){
							if(avaliacao.getQuestionario().getEducacao()> avaliacao.getSaidas().get(i).getValor()){
								aux = avaliacao.getQuestionario().getEducacao()- avaliacao.getSaidas().get(i).getValor();
								out.println("Você tem gastado R$" + aux +" menos.");
							}else{
								aux = avaliacao.getSaidas().get(i).getValor()-avaliacao.getQuestionario().getEducacao();
								out.print("Você tem gastado <strong>R$" + aux + "</strong> a mais do que o esperado.");%> <br> <%
							}
							aux = 0;
							aux = (avaliacao.getSaidas().get(i).getValor() * 15)/100;
							out.println("Tente reduzir 15%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
								+ df.format(avaliacao.getSaidas().get(i).getValor()-aux)+ "</strong> por mes");%> <br> <%
							aux = 0;
							aux = (avaliacao.getSaidas().get(i).getValor() * 20)/100;
							out.println("Ou 20%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
									+ df.format(avaliacao.getSaidas().get(i).getValor()-aux)+ "</strong> por mes");
						}
					}
				}%>
			</div>
		</div>
		<div class="row">
			<div class="col-md-3">
				<label for="txtPrazo">Transporte:</label><br>
				<input type="text" name="txtTransporte" class="form-control" autofocus
				<%if(avaliacao.getSaidas().size()>0){
						for(int i=0; i<avaliacao.getSaidas().size(); i++){
							if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Transporte")){
								out.print("value='" + avaliacao.getSaidas().get(i).getValor()+"'");
							}
						}
					}%>
				><br>
			</div>
			<div class="col-md-9">
				<% aux = 0;
				if(avaliacao.getSaidas().size()>0){
					for(int i=0; i<avaliacao.getSaidas().size(); i++){
						if(avaliacao.getSaidas().get(i).getCategoria().getDescricao().equals("Transporte")){
							if(avaliacao.getQuestionario().getCompra()> avaliacao.getSaidas().get(i).getValor()){
								aux = avaliacao.getQuestionario().getTransporte()- avaliacao.getSaidas().get(i).getValor();
								out.println("Você tem gastado R$" + aux +" menos.");
							}else{
								aux = avaliacao.getSaidas().get(i).getValor()-avaliacao.getQuestionario().getTransporte();
								out.print("Você tem gastado <strong>R$" + aux + "</strong> a mais do que o esperado.");%> <br> <%
							}
							aux = 0;
							aux = (avaliacao.getSaidas().get(i).getValor() * 15)/100;
							out.println("Tente reduzir 15%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
								+ df.format(avaliacao.getSaidas().get(i).getValor()-aux)+ "</strong> por mes");%> <br> <%
							aux = 0;
							aux = (avaliacao.getSaidas().get(i).getValor() * 20)/100;
							out.println("Ou 20%, gastando R$" + df.format(aux) + " a menos, ou seja, <strong>R$" 
									+ df.format(avaliacao.getSaidas().get(i).getValor()-aux)+ "</strong> por mes");
						}
					}
				}%>
			</div>
		</div>
	  	
		  	<blockquote>
		  		<% float soma = 0;
		  		for(int i=0; i<avaliacao.getSaidas().size(); i++){
		  			soma+= avaliacao.getSaidas().get(i).getValor();
		  		
		  		}
		  		out.print("Você gasta em média <strong>R$"+ soma+ "</strong>, ou seja, R$" + (soma - avaliacao.getQuestionario().getRenda())
		  			+ " a mais do que a renda que nos informou.<br>"
		  			+"Tente reduzir os gastos em 20%. Gastando R$" + df.format((soma*80)/100) + " e economizando R$" + df.format((soma*20)/100) + "<br>");
		  		out.print("Ou, tente reduzir 35% dos gastos. Gastando R$" + df.format((soma*65)/100) + "e economizando R$" + df.format((soma*35)/100) + "<br>");
		  		if(soma>avaliacao.getQuestionario().getRenda()){
		  			out.print("Recomendamos que vocês economize e se reestabilize antes de projetar novas metas.<br>");
		  		}
		  		%>
		  	</blockquote>
	  	</div>
		<div class="col-md-8">
			<table class="table table-hover">
	 			<thead><!-- cabeçalho -->
			     	<tr><!-- linha -->
			     		<!-- colunas -->
			        	<th></th>
			        	<th class="col-md-5">Objetivo</th>
			        	<th >Prazo</th>
			        	<th >Mensal</th>
			        	<th >Total</th>
			        	<th >Saldo</th>
			        	<th >Cadastro</th>
			      	</tr>
			    </thead><!-- end cabeçalho -->
			    <tbody><!-- corpo da tabela -->
			      	
			      		<!-- colunas -->
			      		<% for(EntidadeDominio entidade : metas){ 
			      			Meta meta = (Meta) entidade; %>
							<tr <%if(meta.getSaldo()==meta.getValorTotal())
									out.print("class='success'");%>>
								<td><a href="javascript:excluirMeta(<%out.print(meta.getId());%>);"><i class="glyphicon glyphicon-trash"></i></a>
									<a href="javascript:consultarMeta(<%out.print(meta.getId()); %>)"> <i class="glyphicon glyphicon-pencil"></i></a>
								</td>
								<td> <% out.print(meta.getObjetivo()); %></td>
					        	<td><% out.print(meta.getPrazo()); %></td>
					        	<td>R$<% out.print(meta.getValorMensal()); %></td>
					        	<td>R$<% out.print(meta.getValorTotal()); %></td>
					        	<td>R$<% out.print(meta.getSaldo()); %></td>
					        	<td><% out.print(meta.getDtCadastro()); %></td>
							</tr>
						<% } //final For lista de usuarios%>
				</tbody>
			</table>
		</div>
	</div>
			
			
		</div><!-- end col-md-8 -->
	</div><!-- end row -->
</div><!-- end container -->
</body>
</html>