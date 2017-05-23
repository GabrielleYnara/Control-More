<%@page import="br.com.controlmore.aplicacao.Resultado"%>
<%
	if(request.getSession().getAttribute("resultado")!= null){
		Resultado resultado = (Resultado) request.getSession().getAttribute("resultado");
		if (!resultado.getMsg().isEmpty()){%>			
			<script type="text/javascript">
				alert("<%=resultado.getMsg()%>");
			</script>
			<% request.getSession().setAttribute("resultado", null); // evita de chamar novamente a mensagem 
		}
	}
%>