<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastro Categoria</title>
</head>
<body>
<div class="container-fluid">
<%@include file="menu.jsp"%>
	<div class="col-md-4">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Cadastrar Categoria
		  	</div>
		  	<div class="panel-body">
				<form action="acao=salvar" method="POST">
					<div class="radio">
					  	<label>
					    	<input type="radio" name="optCategoria" value="categoria" checked>
					    	Categoria
					  	</label>
					</div>
					<div class="radio">
					  	<label>
					    	<input type="radio" name="optCategoria" value="subcategoria">
					    	Subcategoria
					  	</label>
					</div>
					<label>Nome</label>
					<input type="text" name="txtNome" class="form-control">
					
					<label>Cor</label>
					<input type="text" name="txtQtdeDias" class="form-control">
					
					<%//if(optCategoria.equals("subcategoria"){
						out.print("<select class='form-control'>"+
							"<option>lista de categorias</option>"+
						  	"<option>2</option>"+
						  	"<option>3</option>"+
						  	"<option>4</option>"+
						  	"<option>5</option>"+
						"</select>");
						%>
					
					<div class="row" align="center">
						<button type='submit' class="btn btn-primary" id='acao'>Salvar</button>
					</div>
				</form>
			</div><!-- end painel body -->
		</div><!-- end painel -->
	</div><!-- end col-4 -->
</div><!-- end container -->
</body>
</html>