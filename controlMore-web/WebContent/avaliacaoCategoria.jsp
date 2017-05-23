<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Avaliar Categoria</title>
</head>
<body>
<div class="container-fluid">
<%@include file="menu.jsp"%>
	<div class="col-md-8">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Avaliar Categoria
		  	</div>
		  	<div class="panel-body">
				<form action="acao=salvar" method="POST">
					Neste mês, você gastou R$0,00 na categoria "X".
					<table class="table table-hover">
			 			<thead><!-- cabeçalho -->
					     	<tr><!-- linha -->
					     		<!-- colunas -->
					        	<th></th>
					        	<th >Descricao</th>
					        	<th >Valor</th>
					        	<th >Data</th>
					        	<th >Indispensável</th>
					      	</tr>
					    </thead><!-- end cabeçalho -->
					    <tbody><!-- corpo da tabela -->
					      	
					      		<!-- colunas -->
					   			<tr>
									<td></td>
									<td>Compra</td>
									<td>R$497,85</td>
									<td>30/09/2016</td>
									<td>
										<div class="checkbox" align="center">
										  <input type="checkbox" value="">
										</div>
									</td>
								</tr>
								
						</tbody>
					</table>
					<div class="row" align="center">
						<button type='submit' class="btn btn-primary" id='acao'>Salvar</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>