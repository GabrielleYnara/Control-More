<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<!-- Import da taglib pra uso de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<title>Control More</title>
</head>
<body>
<!-- Importando o arquivo de cabeçalho -->
<c:import url="menu.jsp" />
  <div class="container-fluid" >
	<form method="POST" action="Upload?acao=salvar">	  	
   	  <!-- Selecionar o arquivo -->
	  	<div class="form-group ">
    	  <label for="upload">Importação de arquivo</label><br>
    	  <input type="file" id="inputCSV" name="upload" onchange="pegaCSV(this)" class="form-control">
    	  <br>
   	  	<input type="submit" value="Carregar" class="btn btn-sm btn-default">
   	  	<br><br>
   	  	<textarea id="txtUpload" name="txtUpload" class="form-control" rows="10" >
		</textarea>
   	  	</div>
   	  	
	</form>
	<br><br>
	<button class="btn btn-info" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample">
  	  Exibir instruções <icon class="glyphicon glyphicon-chevron-down"/>
	</button>
	<div class="collapse" id="collapseExample">
  	  <div class="well">
    	Para que a importação do seu arquivo funcione é preciso seguir algumas orientações, são elas:
    	<ul>
    	  <ol>
    	    Estruture sua planilha para que fique da seguinte maneira:
    	    <img src="img/exemplo.png" class="img-responsive" alt="Exemplo de formatação da planilha"></h2>
    	  	<ul>
    	  	  <li>Na primeira coluna (A), você deve informar o tipo de conta. Ou seja, se é uma conta aPagar ou aReceber. (Escreva exatamente como no exemplo)</li>
    	  	  <li>A segunda coluna (B), deve ser preenchida com a data de vencimento. (Use a mesma formatação do exemplo).</li>
    	  	  <li>Em seguida, coluna C, informe a descrição da conta, não use o caractere ponto e virgula ; pois poderá causar problemas na sua importação.</li>
    	  	  <li>Na quarta coluna (D), informe o valor. Não utilize a formatação "Moeda" que o Excel disponibiliza.</li>
    	  	  <li>A quinta coluna (E) destina-se à categoria. Se for uma conta a receber, digite apenas um hífen (-), caso seja uma conta a pagar, informe a categoria e escreva exatamente igual ao sistema.</li>
    	  	  <li>A sexta e ultima coluna (F) é para informar a conta ou cartão e também deve ser escrita exatamente igual ao sistema.</li>
    	  	</ul>
    	  </ol>
    	</ul>
  	  </div>
	</div>
	

  </div> <!--  container-fluid -->

<br><br><br>
<script>
    var leitorDeCSV = new FileReader();

    window.onload = function init() {
        leitorDeCSV.onload = leCSV;
    }

    function pegaCSV(inputFile) {
        var file = inputFile.files[0]; 
        leitorDeCSV.readAsText(file);
    }

function leCSV(evt) {

    var fileArr = evt.target.result;//Pega o resultado do arquivo selecionado
    var strDiv ='';

    for (var i=0; i<fileArr.length; i++) {//Vai percorrer até o final do arrayList
        strDiv += fileArr[i]; //Pega cada registro e passa para a strDiv
    }
    var txtUpload = document.getElementById('txtUpload');
    txtUpload.innerHTML=strDiv;
    }
</script>
</body>
</html>