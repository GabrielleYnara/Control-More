<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Cadastro ControlMore</title>
<!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

   	<!-- Custom styles -->
    <link rel="stylesheet" href="css/cadastroUsuario.css">
    
    <script type="text/javascript" src="jquery.bootstrap.wizard.min.js"></script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.2/js/bootstrap.min.js" integrity="sha384-vZ2WRJMwsjRMW/8U7i6PWi6AlO1L79snBrmgiDpgIWJ82z8eA5lenwvxbMV1PAh7" crossorigin="anonymous"></script>

</head>
<body>
<script type="text/javascript">
function redirecionar(){
	alert("Cadastro realizado com sucesso!");
	window.location="index.html";
}
</script>
<script>
$(document).ready(function(){
    $('[data-toggle="tooltip"]').tooltip();
});
</script>

<div class="container-fluid" >
	<form action="Pessoa?acao=salvar" method="post" class="col-md-8 col-md-offset-2 col-xs-12 form-cadastro">
		<h2 class="form-signin-heading" align="center">ControlMore</h2>
		<div class="col-md-3">
		  Nome Completo
		</div>
		<div class="col-md-9">
		  <input type="text" name="txtNome" class="form-control" placeholder="Ex: Joaquina da Silva" required autofocus><br>
		</div>
		<div class="col-md-3">
		  Email
		</div>
		<div class="col-md-9">
		  <input type="email" name="txtEmail" class="form-control" placeholder="Ex: joaquina@email.com" required autofocus><br>
		</div>
		<div class="col-md-3" hidden>
		  Confirmar Email
		</div>
		<div class="col-md-9" hidden>
		  <input type="email" name="txtConfirmEmail" class="form-control" placeholder="Ex: joaquina@email.com"><br>
		</div>
		<div class="col-md-3">
		  Senha
		</div>
		<div class="col-md-9">
		  <input type="password" name="txtSenha" class="form-control" placeholder="******" required><br>
		</div>
		<div class="col-md-3">
		  Confirmar Senha
		</div>
		<div class="col-md-9">
		  <input type="password" name="txtConfirmSenha" class="form-control" placeholder="******" required><br>
		</div>
		<div class="col-md-3">
		  Telefone
		</div>
		<div class="col-md-9">
		  <input type="text" name="txtTelefone" class="form-control" data-toggle="tooltip" title="Preencha seu telefone" placeholder="(11) 9 4002-8922"required autofocus><br>
		</div>
		<div class="col-md-3">
		  Data de Nascimento
		</div>
		<div class="col-md-9">
		  <input type="date" name="txtDtNascimento" class="form-control" data-toggle="tooltip" title="Insira sua Data de Nascimento" required autofocus><br>
		</div>
		<div class="col-md-4"></div>
		<div class="col-md-4">
		  <button class="btn btn-lg btn-info btn-block" id="btnCadastrar" type="submit" name="acao" value="salvar" placeholder="Cadastrar">Cadastar</button>
		  <button class="btn btn-lg btn-default btn-block" id="btnVoltar" type="submit" name="acao" value="voltar" placeholder="Voltar"><a href="index.jsp">Voltar</a></button>
		  
		</div>
	
	</form>
</div>
</body>
</html>