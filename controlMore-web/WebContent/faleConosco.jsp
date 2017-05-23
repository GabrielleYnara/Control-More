<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Fale Conosco</title>
</head>
<body>
<%@include file="menu.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-md-1">
			<label>Tema: </label>
		</div>
		<div class="col-md-4">
			<select class="form-control">
				<option>Sugestão</option>
				<option>Crítica</option>
				<option>Problema</option>
			</select><br>
		</div>
	</div>
	<div class="row">	
		<div class="col-md-7">
			<textarea class="form-control" rows="9"></textarea><br>
		</div>
	</div>
	<div class="row col-md-7" align="right">
		<button type="submit" class="btn btn-primary">Enviar</button>
	</div>
</div><!-- end container -->

</body>
</html>