<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Import da taglib pra uso de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>ControlMore</title>
</head>
<body>

<%@include file="menu.jsp"%>
<div class="container-fluid" >

	<div class="col-md-12">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Analisar Contas à Pagar Atrasadas
		  	</div>
		  	<div class="panel-body">
				<form action="acao=salvar" method="POST">
				  <div class="table-responsive">
					<table class="table table-hover">
			 			<thead><!-- cabeçalho -->
					     	<tr><!-- linha -->
					     		<!-- colunas -->
					        	<th></th>
					        	<th>Descrição</th>
					        	<th>Categoria</th>
					        	<th>Vencimento</th>
					        	<th>Valor</th>
					        	<th>Multa</th>
					        	<th>Juros</th>
					        	<th>Total</th>
					      	</tr>
					    </thead><!-- end cabeçalho -->
					    <tbody><!-- corpo da tabela -->
					      	<c:if test="${empty resultado.modeloVisao.aPagar}">
					      	  <tr>
					    		<td align="center"> Nenhuma conta atrasada.</td>
					    	  </tr>
					      	</c:if>
					    	<c:forEach var="aPagar" items="${resultado.modeloVisao.aPagar}">
					    	  <tr>
					    		<td><a href="javascript:location.href='Saida?acao=consultar&txtId=${aPagar.id}';"> &bull;	&nbsp;	${aPagar.descricao}</a></td>
					    		<td>${aPagar.categoria.descricao}</td>
					    		<td><fmt:formatDate value="${aPagar.data}" pattern="dd/MM/YY"/></td>
					    		<td>R$${aPagar.valor}</td>
					    		<td>R$${aPagar.juros.multa}</td>
					    		<td>R$${aPagar.juros.juros}</td>
					    		<td>R$${aPagar.valor + aPagar.juros.multa + aPagar.juros.juros}</td>
					    	  </tr>
					    	</c:forEach>
					      		
								
						</tbody>
					</table>
				  </div>
				  <div class="row" align="left">
						<button type='submit' class="btn btn-primary" id='acao'>Salvar</button>
						<button type='submit' class="btn btn-default" id='acao'>Imprimir</button>

					</div>
				</form>
			</div><!-- end painel body -->
		</div><!-- end painel -->
	</div><!-- end col-4 -->
</div> <!--  container-fluid -->
</body>
</html>