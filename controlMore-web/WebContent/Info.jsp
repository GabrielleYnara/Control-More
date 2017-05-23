<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Info - ControlMore</title>
</head>

<body>
<div class="container-fluid">
<%@include file="menu.jsp"%>	
	<div class="col-md-9 col-md-offset-1">
		<div class="panel panel-info">
		  	<div class="panel-heading text-center">
		  		Para deixar salvo:
		  	</div>
		  	<div class="panel-body">
		  		-- Criando a Seguence -- 
	<br>		CREATE SEQUENCE Entrada_seq MINVALUE 1 MAXVALUE 999999999999999999999999999 INCREMENT BY 1 START WITH 8;
	<br><br>
				-- Criando o gatilho --
	<br>		CREATE OR REPLACE TRIGGER Entrada_TRG BEFORE INSERT ON Entrada FOR EACH ROW 
	<br>		BEGIN &#139&#139Id&#155&#155 BEGIN IF :NEW.Id IS NULL 
	<br>		THEN SELECT Entrada_seq .NEXTVAL INTO :NEW.Id FROM DUAL; 
	<br>		END IF; END ID; END;
	<p>https://blackrockdigital.github.io/startbootstrap-sb-admin-2/pages/index.html</p>
		  	</div>
		</div>
	</div>
	<div>
		<input type="file" name="planilha" accept="application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/vnd.ms-excel">
	</div>
</div>
</body>
</html>