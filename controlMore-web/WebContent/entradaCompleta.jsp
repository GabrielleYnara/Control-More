<%@page import="br.com.controlmore.dominio.Entrada"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.com.controlmore.dominio.EntidadeDominio"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- Import da taglib pra uso de jstl -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<title>Conta a Receber</title>
</head>
<body>

<%@include file="menu.jsp"%>
<script type="text/javascript">
function excluirE(id){
	location.href="Entrada?acao=excluir&txtId="+ id;
}
</script>
<%
	List<EntidadeDominio> entradas = new ArrayList<EntidadeDominio>();
	Entrada entrada = new Entrada();
	if(request.getSession().getAttribute("entrada")!=null){
		resultado = (Resultado) request.getSession().getAttribute("entrada");
		entradas = resultado.getEntidades();
		entrada = (Entrada) entradas.get(0);
	}
%>
	<div class="col-sm-12 col-md-7 col-md-offset-2">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Cadastrar Entrada
		  	</div>
		  	<div class="panel-body">
		  		<%if(entrada.getId()!=0)
		  			out.print("<form action='Entrada?acao=alterar' method='POST'>");
		  		  else
					out.print("<form action='Entrada?acao=salvar' method='POST'>");
		  		%>
				<div class="row">
					<div class="col-md-2">
	    				Valor R$
	    			</div>
	    			<div class="col-md-4">
	    				<input type="text" name="txtValor" class="form-control" placeholder="R$0,00" required autofocus
	    				<%if(entrada.getValor()!= 0)
	    					out.print("value='"+entrada.getValor()+"'");
	    				%>>
					</div>
					
					<div class="col-md-2" align="right">
						Frequ�ncia
	   				</div>
	   				<div class="col-md-3">
	      				<select class="form-control" name="txtFreq">
	      					<%if(entrada.getFrenquencia()!=null){
	      						out.print("<option value='"+entrada.getFrenquencia().getId()+"' > " +
	      						entrada.getFrenquencia().getDescricao() + "</option>");
	      					}
	      					%> 
				    		<option value="1" >Avulso</option>
							<option value="4" >Mensal</option>
							<option value="2" >Semanal</option>
						</select>
	    			</div>
	    			<div class="col-md-1" hidden>
	    				<a href="cadastroFrequencia.jsp"><i class="glyphicon glyphicon-plus"></i></a>
	    			</div>
	  			</div>
	  			
	  			<div class="row">
	  				<div class="col-md-2">
	    				Descri��o
	    			</div>
	    			<div class="col-md-9">
	    				<input type="text" name="txtDescricao" class="form-control" placeholder="" required autofocus
	    				<%if(entrada.getDescricao()!=null)
	    					out.print("value='"+entrada.getDescricao()+"'");
	    				%>
	    				>
					</div>
				</div>
				
				<div class="row">
	  				<div class="col-md-2">
						Conta
	   				</div>
	   				<div class="col-md-4">
	      				<select class="form-control"  name="txtConta">
	      					<%if(entrada.getConta()!=null){
	      						out.print("<option value='"+entrada.getConta().getId()+"' > " +
	      						entrada.getConta().getBanco() + "</option>");
	      					}
	      					%> 
			    			<!-- <option value="1">Ita� - CC</option>
						   	<option value="2">Ita� - CP</option>
						   	<option value="4">Carteira </option>-->
						   	<c:forEach var="conta" items="${resultado.modeloVisao.contas}">
						   		<option value="${conta.id}">${conta.banco} - ${conta.tipo}</option>
						   	</c:forEach>
						</select>
	    			</div>
	    			<div class="col-md-1">
	    				<a href="cadastroConta.jsp"><i class="glyphicon glyphicon-plus" data-toggle="tooltip" data-placement="bottom" title="Adicionar nova Conta ou Cart�o"></i></a>
	    			</div>
	    			<div class="col-md-1">
	    				Data
	    			</div>
	    			<div class="col-md-3">
	    				<input type="date" name="txtData" class="form-control" required autofocus data-toggle="tooltip" data-placement="bottom" title="Data de recebimento"
	    				<%if(entrada.getDataEntrada()!=null)
	    					out.print("value='"+entrada.getDataEntrada()+"'");
	    				%>>
					</div>
	    		</div>
	    		<!-- <div class="row">
	    			<div class="col-md-2">
		    			<div class="checkbox">
	  						<label><input type="checkbox" value="item" onclick="verificaChecks()">Parcelado</label>
						</div>
					</div>
					
					<div class="col-md-2">
						N� Parcelas
	   				</div>
					<div class="col-md-2">
	      				<select class="form-control">
		    				<option>1</option>
						   	<option>2</option>
						</select>
	    			</div>
	    			
	    			<div class="col-md-3">
	    				Valor Parcela
	    			</div>
	    			<div class="col-md-3">
	    				<input type="text" name="txtValorParcela" class="form-control" placeholder="R$0,00" autofocus>
					</div>
	    		</div>-->
	    		<div class="row">	
					<div class="col-md-11">
						Observa��es:
						<textarea name="txtObservacao" class="form-control" rows="5">
						<%if(entrada.getObservacoes()!=null)
	    					out.print(entrada.getObservacoes());
						%></textarea>
					</div>
				</div>
	    		<div class="col-md-2" hidden>
	    			Id
	    		</div>
	    		<div class="col-md-3" hidden>
	    			<input type="text" name="txtId" class="form-control" autofocus hidden
	    			<%if(entrada.getId()!=0)
	    				out.print("value='"+entrada.getId()+"'");
	    			%>
	    			>
				</div>
	  			<div class="row" >
					<div class="col-md-2">
						Situa��o
					</div>
					<div class="col-md-3">
			    	  	<select class="form-control" name="txtSituacao">
			    	  		<%if(entrada.getSituacao()!=null){
	    	  					out.print("<option value='"+entrada.getSituacao()+"'>"+
	    	  					entrada.getSituacao()+"</option>");
	    	  				}%>
			    		    <option value="Pendente">Pendente</option>
			    		    <option value="Pago">Pago</option>
			    		    <!-- Por enquanto o sistema n�o est� preparado para pagamento parcial
			    		     <option value="Pago parcial">Pago parcial</option> -->
			    		</select>
					</div>
				</div>
				
				<div align="left" class="col-xs-6 col-sm-6 col-md-6">
		    		<a href="javascript:location.href='Home?acao=resumo';" class="btn btn-default">Cancelar</a>
		    		<%if(entrada.getId()!=0){%>
		    		  <a href="javascript:excluirE(<%out.print(entrada.getId());%>);" class="btn btn-default">Excluir</a>
		    			
	    	  		<%}%>
	      		</div><!-- end row -->
		  		<div align="right" class="col-xs-6 col-sm-6 col-md-6">
		    		<button class="btn btn-primary" type="submit">Salvar</button>		    			
	      			
	      		</div>
 			  </form>
			</div><!-- end painel body -->
		</div><!-- end painel -->
	</div><!-- end col-4 -->
 	</div> <!-- col-sm-9 col-md-10 affix-content -->
  </div> <!--  container-fluid -->
</div> <!-- row affix-row -->
</body>
</html>