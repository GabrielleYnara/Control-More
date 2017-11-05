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

	<label>Teste JSTL, pegando atributo pessoa da sessão </label>
	<br>
	<label>exibe objeto: ${usuario.nome}</label>
	
	<br>
	<form method="POST" enctype="multipart/form-data" action="fup.cgi">
		File to upload: <input type="file" name="upfile"><br/>
	  	<br/>
	  	<input type="submit" value="Press"> to upload the file!
	</form>
<script type="text/javascript">
	
	var date = new Date();
	var ultimoDia = new Date(date.getFullYear(), date.getMonth() + 1, 0);

	document.write(ultimoDia.getDate());
	

</script>

</body>
</html>