<%@page import="br.com.controlmore.dominio.Meta"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.controlmore.dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Control More</title>
</head>
<body>
<div class="container-fluid">
<%@include file="menu.jsp"%>
<% 
	List<EntidadeDominio> metas = new ArrayList<EntidadeDominio>();
	resultado = (Resultado) request.getSession().getAttribute("meta");
	metas = resultado.getEntidades();
	Meta meta = new Meta();
	meta = (Meta) metas.get(0);
%>
<div class="col-md-5">
	<div class="panel panel-info">
	  	<div class="panel-heading text-center">
	  		Cadastrar Meta
	  	</div>
	  	<div class="panel-body">
			<form action="Meta?acao=alterar" method="POST">
				<div class="col-md-12" >
					<label for="txtId">Id:</label>
					<input type="text" name="txtId" class="form-control" value="<%= meta.getId()%>" readonly>
				</div>
				<div class="col-md-12">
					<label for="txtObjetivo">Objetivo:</label>
					<input type="text" name="txtObjetivo" class="form-control" value="<%= meta.getObjetivo()%>">
				</div>
				<div class="col-md-6">
		    		<label for="txtValorTotal">Valor Total:</label>
					<input type="text" name="txtValorTotal" class="form-control" autofocus value="<%= meta.getValorTotal()%>">
				</div>
				<div class="col-md-6">
					<label for="txtValorMensal">Valor Mensal:</label>
					<input type="text" name="txtValorMensal" class="form-control" autofocus value="<%= meta.getValorMensal()%>">
				</div>
				<div class="col-md-6">
					<label for="txtDtPrazo">Prazo:</label><br>
					<input type="text" name="txtPrazo" class="form-control" autofocus value="<%= meta.getPrazo()%>"><br>
				</div>
				<div class="col-md-6">
					<label for="txtSaldo">Saldo:</label>
					<input type="text" name="txtSaldo" class="form-control" autofocus value="<%= meta.getSaldo()%>">
				</div>
				<div class="col-md-12" align="center">
					<button type='submit' class="btn btn-primary">Salvar</button>
				</div>
			</form>
		</div><!-- end painel body -->
	</div><!-- end painel -->
</div>
</div>
</body>
</html>