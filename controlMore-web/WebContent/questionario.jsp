<%@page import="br.com.controlmore.dominio.Pessoa"%>
<%@page import="br.com.controlmore.aplicacao.Resultado"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet" href="css/bootstrap-theme.min.css" >


<!-- Latest compiled and minified JavaScript -->
<script src="http://code.jquery.com/jquery-2.1.3.min.js"></script>
<script src="js/bootstrap.min.js" ></script>
<title>Questionario</title>
</head>
<body>
<%@include file="WEB-INF/util/mensagem.jsp" %>
<%
	Resultado resultado = (Resultado) request.getSession().getAttribute("pessoa");
	Pessoa pessoa = new Pessoa();
	pessoa = (Pessoa) resultado.getEntidades().get(0);
%>
<div class="container-fluid">
<h1>Olá, <%out.print(pessoa.getNome());%>!</h1>
<h2>Bem vindo(a) ao Control More</h2>
<h4>Precisamos de algumas informações antes de você começar usar o sistema.</h4>
<h4>Basta responder algumas questões e pode seguir adiante. <i class="glyphicon glyphicon-thumbs-up"></i></h4>
<h4>

<form class="form-inline" action="Questionario?acao=salvar" method="POST">
	<div class="form-group">
		<ol>
			<li>
				Você tem renda fixa? <br>
				<small>Se sim informe-nos o valor; Se não preencha com <abbr title="ZERO">0</abbr></small><br>
				<div class="input-group">
	      			<div class="input-group-addon">R$</div>
	      			<input type="text" name="txtRenda" class="form-control"  placeholder="0.00" required>
	    		</div>
    		</li>
    		<br><br>
    		<li>
    			Quanto você gasta por mês com alimentação se fizer uma compra no supermercado?<br>
    			<small>Se houver gasto, informe-nos o valor. Se não, preencha com <abbr title="ZERO">0</abbr></small><br>
    			<div class="input-group">
	      			<div class="input-group-addon">R$</div>
	      			<input type="text" name="txtCompra" class="form-control"  placeholder="0.00" required>
	    		</div>
    		</li>
    		<br><br>
    		<li>
    			Quanto você gasta por mês com a conta de água?<br>
    			<small>Se houver gasto, informe-nos o valor. Se não, preencha com <abbr title="ZERO">0</abbr></small><br>
    			<div class="input-group">
	      			<div class="input-group-addon">R$</div>
	      			<input type="text" name="txtAgua" class="form-control"  placeholder="0.00" required>
	    		</div>
    		</li>
    		<br><br>
    		<li>
    			Quanto você gasta por mês com a conta de luz?<br>
    			<small>Se houver gasto, informe-nos o valor. Se não, preencha com <abbr title="ZERO">0</abbr></small><br>
    			<div class="input-group">
	      			<div class="input-group-addon">R$</div>
	      			<input type="text" name="txtLuz" class="form-control"  placeholder="0.00" required>
	    		</div>
    		</li>
    		<br><br>
    		<li>
    			Quanto você gasta por mês com a conta de internet, tv a cabo e afins?<br>
    			<small>Se houver gasto, informe-nos o valor. Se não, preencha com <abbr title="ZERO">0</abbr></small><br>
    			<div class="input-group">
	      			<div class="input-group-addon">R$</div>
	      			<input type="text" name="txtInternet" class="form-control"  placeholder="0.00" required>
	    		</div>
    		</li>
    		<br><br>
    		<li>
    			Quanto você gasta por mês com recarga de celular?<br>
    			<small>Se houver gasto, informe-nos o valor. Se não, preencha com <abbr title="ZERO">0</abbr></small><br>
    			<div class="input-group">
	      			<div class="input-group-addon">R$</div>
	      			<input type="text" name="txtRecarga" class="form-control"  placeholder="0.00" required>
	    		</div>
    		</li>
    		<br><br>
    		<li>
    			Quanto você gasta por mês com a transporte? Seja gasolina ou passagens de onibus e/ou trem<br>
    			<small>Se houver gasto, informe-nos o valor. Se não, preencha com <abbr title="ZERO">0</abbr></small><br>
    			<div class="input-group">
	      			<div class="input-group-addon">R$</div>
	      			<input type="text" name="txtTransporte" class="form-control"  placeholder="0.00" required>
	    		</div>
    		</li>
    		<br><br>
    		<li>
    			Você paga aluguel ou condominio?<br>
    			<small>Se sim, informe-nos o valor. Se não, preencha com <abbr title="ZERO">0</abbr></small><br>
    			<div class="input-group">
	      			<div class="input-group-addon">R$</div>
	      			<input type="text" name="txtAluguel" class="form-control"  placeholder="0.00" required>
	    		</div>
    		</li>
    		<br><br>
    		<li>
    			Você tem gastos com educação? Como escola particular ou cursos?<br>
    			<small>Se sim, informe-nos o valor. Se não, preencha com <abbr title="ZERO">0</abbr></small><br>
    			<div class="input-group">
	      			<div class="input-group-addon">R$</div>
	      			<input type="text" name="txtEducacao" class="form-control"  placeholder="0.00" required>
	    		</div>
    		</li>
    		<br><br>
    		<li>
    			Você tem gastos com lazer? Como cinema, salão, academia e etc?<br>
    			<small>Se sim, informe-nos o valor. Se não, preencha com <abbr title="ZERO">0</abbr></small><br>
    			<div class="input-group">
	      			<div class="input-group-addon">R$</div>
	      			<input type="text" name="txtLazer" class="form-control"  placeholder="0.00" required>
	    		</div>
    		</li>
    		<br><br>
    		<li>
    			Você tem algum outro tipo de gasto mensal fixo?<br>
    			<small>Se sim, informe-nos o valor. Se não, preencha com <abbr title="ZERO">0</abbr></small><br>
    			<div class="input-group">
	      			<div class="input-group-addon">R$</div>
	      			<input type="text" name="txtOutros" class="form-control"  placeholder="0.00" required>
	    		</div>
    		</li>
    		<br><br>
		</ol>
		
	    <div class="text-center">
	    	<input type="submit" class="btn btn-info" value="Registrar">
	    </div>
	    <div hidden>
	    	<input type="text" name="txtId">
	    </div>
 	</div>
</form>
</h4>
</div>
</body>
</html>