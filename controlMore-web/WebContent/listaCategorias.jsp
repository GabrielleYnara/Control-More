<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Custom styles -->
<link rel="stylesheet" href="css/lancamentos.css">

<!-- Import da taglib pra uso de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<script src="https://code.highcharts.com/highcharts.js"></script>
<script src="https://code.highcharts.com/highcharts-3d.js"></script>
<script src="https://code.highcharts.com/modules/exporting.js"></script>

<title>Categorias</title>
</head>
<body>
<%@include file="menu.jsp"%>
<div class="container-fluid" >
  <table class="table table-striped">
	<thead>
	  <caption><h3>Categorias</h3></caption>
	  <tr>
	 	<th>Nome</th>
	    <th hidden>Cor</th>
	    <th hidden>Categoria(pai)</th>
	    <th>Data Cadastro</th>
	    <th></th>
	  </tr><!-- end linha -->
	</thead><!-- end cabeçalho -->
	<tbody>
	  <c:forEach var="categoria" items="${categoria}">
		<tr><!-- Linha -->
	  	  <!-- Colunas -->
		  <td>${categoria.descricao}</td>
		  <td hidden>${categoria.cor}</td>
		  <td hidden>${categoria.categoria.descricao}</td>
		  <td>${categoria.dtCadastro}</td>
		  <td>
			<a href="javascript:location.href='Categoria?acao=consultar&txtId=${categoria.id}';"><span class="glyphicon glyphicon-pencil"></span></a>
	      	<a href="javascript:location.href='Categoria?acao=excluir&txtId=${categoria.id}';"><span class="glyphicon glyphicon-trash"></span></a>
		  </td>
		</tr><!-- end linha -->
	  </c:forEach>
	</tbody>
  </table>
  <a href="cadastroCategoria.jsp" class="btn btn-primary">Incluir nova categoria</a>
</div>
</body>
</html>