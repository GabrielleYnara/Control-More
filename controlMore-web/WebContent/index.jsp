<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<title>ControlMore Login</title>

<!-- Bootstrap core CSS -->
    <link href="plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->
    <link href="css/ie10-viewport-bug-workaround.css" rel="stylesheet">

    <!-- Custom styles -->
    <link rel="stylesheet" href="css/index.css">

    <!-- Just for debugging purposes. Don't actually copy these 2 lines! -->
    <!--[if lt IE 9]><script src="../../assets/js/ie8-responsive-file-warning.js"></script><![endif]-->
    <script type="text/javascript" src="js/ie-emulation-modes-warning.js"></script>
    <script type="text/javascript" src="jquery.bootstrap.wizard.min.js"></script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js" integrity="sha384-vZ2WRJMwsjRMW/8U7i6PWi6AlO1L79snBrmgiDpgIWJ82z8eA5lenwvxbMV1PAh7" crossorigin="anonymous"></script>
</head>
<body>
<div class="container-fluid">
	<form class="form-signin" action="Pessoa?acao=login" method=post>

		<h2 class="form-signin-heading" align="center">
		<img src="img/Logo.png" alt="Control More logo"></h2>
		<label for="inputEmail" class="sr-only">Email</label>
		<input type="email" id="inputEmail" name="txtEmail" class="form-control" placeholder="Email" required autofocus>
		<label for="inputPassword" class="sr-only">Senha</label>
		<input type="password" id="inputPassword" name="txtSenha" class="form-control" placeholder="Senha" required>
		<div class="checkbox">
			<label>
				<input type="checkbox" value="remember-me"> Lembrar senha
			</label>
			<label>
				<a href="cadastroUsuario.jsp">Não sou cadastrado</a>
			</label>
		</div>
		<input type='submit' class="btn btn-lg btn-info btn-block" id='btnEntrar' value='Entrar'>
		
	</form>
</div> <!-- /container -->
<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});
</script>
<%@include file="WEB-INF/util/mensagem.jsp" %>
</body>
</html>