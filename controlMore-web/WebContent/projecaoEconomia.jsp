<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<div class="container-fluid">
<%@include file="menu.jsp"%>
	<div class="col-md-8">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Proje��o de Economia
		  	</div>
		  	<div class="panel-body">
				<form action="acao=salvar" method="POST">
					De acordo com o que analizamos, se voc� gastar somente com itens marcados como importantes, voc� ir� gastar em m�dia <strong>R$0,00</strong> e economizar� <strong>R$0,00</strong>.
					<table class="table table-hover">
			 			<thead><!-- cabe�alho -->
					     	<tr><!-- linha -->
					     		<!-- colunas -->
					        	<th></th>
					        	<th>Categoria</th>
					        	<th>Descri��o</th>
					        	<th>Valor</th>
					      	</tr>
					    </thead><!-- end cabe�alho -->
					    <tbody><!-- corpo da tabela -->
					      	
					      		<!-- colunas -->
					   			<tr>
									<td></td>
									<td>Alimenta��o</td>
									<td>Compra Mensal</td>
									<td>R$489,97</td>
								</tr>
								
								<tr>
									<td></td>
									<td>Moradia</td>
									<td>Conta de Luz</td>
									<td>R39,97</td>
								</tr>
								
								<tr>
									<td></td>
									<td>Moradia</td>
									<td>Conta de Agua</td>
									<td>R$39,97</td>
								</tr>
								
								<tr>
									<td></td>
									<td>Moradia</td>
									<td>Net</td>
									<td>R$159,99</td>
								</tr>
						</tbody>
					</table>
					<div class="row" align="left">
						<button type='submit' class="btn btn-primary" id='acao'>Salvar</button>
						<button type='submit' class="btn btn-default" id='acao'>Imprimir</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</div>
</body>
</html>