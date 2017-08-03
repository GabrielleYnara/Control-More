<%@page import="br.com.controlmore.aplicacao.Resultado"%>
<!-- Import da taglib pra uso de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	if(request.getSession().getAttribute("resultado")!= null){
		Resultado resultado = (Resultado) request.getSession().getAttribute("resultado");
		if (!resultado.getMsg().isEmpty()){%>			
			<script type="text/javascript">
				alert("<%=resultado.getMsg()%>");
			</script>
			<% 
		}
	}else if(request.getAttribute("resultado")!= null){
		Resultado resultado = (Resultado) request.getAttribute("resultado");
		if (!resultado.getMsg().isEmpty()){		
			
		}
	}

%>
<c:set value="${resultado}" var="mensagem" scope="request" />

<c:if test="${mensagem != null}">
	<div id="modalMsg" class="modal fade" role="dialog">
		<div class="modal-dialog">
	    	<!-- Modal content-->
	    	<div class="modal-content">
	     		<div class="modal-header">
	        		<button type="button" class="close" data-dismiss="modal">&times;</button>
	        		<h4 class="modal-title">Mensagem</h4>
	      		</div>
	      		<div class="modal-body">
	        		<b><c:out value="${mensagem.msg}"/></b>
	      		</div>
	    	</div>
	  	</div>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$('#modalMsg').modal().modal('open');
		});
	</script>
</c:if>

