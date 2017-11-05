<%@page import="br.com.controlmore.dominio.Saida"%>
<%@page import="br.com.controlmore.dominio.EntidadeDominio"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">

<title>Control More</title>
</head>
<body>
<%@include file="menu.jsp"%>
<%
	List<EntidadeDominio> saidas = new ArrayList<EntidadeDominio>();
	Saida saida = new Saida();
	if(request.getSession().getAttribute("saida") !=null){
		resultado = (Resultado) request.getSession().getAttribute("saida");
		saidas = resultado.getEntidades();
		saida = (Saida) saidas.get(0);
	}
%>
<script type="text/javascript">
$(document).ready(function(){
    $("#parcelado").click(function(){
        $(".parcelado").toggle();
    });
});
</script>
	<div class="col-md-6">
		<div class="panel panel-info">
			<div class="panel-heading text-center">
		  		Contas a pagar 
		  	</div>
		  	<div class="panel-body">
			  	<%if(saida.getId()==0){
			  		out.print("<form action='Saida?acao=salvar' method='POST'>");
			  	}else{
			  		out.print("<form action='Saida?acao=alterar' method='POST'>");
			  	}%>
		  			<div class="row">
		  				<div class="col-md-2" hidden >
	    	  				Id
	    	  			</div>
	    	  			<div class="col-md-3" hidden>
	    	  				<input type="text" name="txtId" class="form-control" autofocus 
	    	  					<%if(saida.getId()!=0){
	    	  						out.print("value='"+saida.getId()+"'");
	    	  					}%>
	    	  				>
	    	  			</div>
		  			</div>
		  			
            		<div class="row">
	    	  			<div class="col-md-2" >
	    	  				Valor R$
	    	  			</div>
	    	  			<div class="col-md-3">
	    	  				<input type="text" name="txtValor" class="form-control" placeholder="R$0,00" required autofocus
	    	  					<%if(saida.getValor()!=0){
	    	  						out.print("value='"+saida.getValor()+"'");
	    	  					}%>
	    	  				>
	    	  			</div>
	    	  			<div class="col-md-2">
	    	  				Frequência
	    	  			</div>
	    	  			<div class="col-md-4">
		      				<select class="form-control" name="txtFreq">
		      					<%if(saida.getFrequencia()!=null){
	    	  						out.print("<option value='"+saida.getFrequencia().getId()+"'>"+
		      						saida.getFrequencia().getDescricao()+"</option>");
	    	  					}%>
				    			<option value="1" >Avulso</option>
								<option value="4" >Mensal</option>
								<option value="2" >Semanal</option>
							</select>
	    				</div>
		    			<div class="col-md-1">
		    				<a href="cadastroFrequencia.jsp"><i class="glyphicon glyphicon-plus"></i></a>
		    			</div> 
	    			</div><!-- end row -->
	  
		    		<div class="row">
			      	  	<div class=col-md-2>
			      	  		Descrição 
			      	  	</div>
			      	  	<div class="col-md-9">
			      	  		<input type="text" name="txtDescricao" class="form-control" required autofocus
			      	  			<%if(saida.getDescricao()!=null){
	    	  						out.print("value='"+saida.getDescricao()+"'");
	    	  					}%>
			      	  		>
			      	  	</div>
		    		</div><!-- end row -->
	
					<div class="row">
		    			<div class="col-md-2">
							Conta/ Cartão
		   				</div>
		   				<div class="col-md-3">
		      				<select class="form-control"  name="txtConta">
		      					<%if(saida.getConta()!=null){
	    	  						out.print("<option value='"+saida.getConta().getId()+"'>"+
		      						saida.getConta().getBanco()+"</option>");
	    	  					}%>
			    				<option value="1">Itaú - CC</option>
							   	<option value="2">Itaú - CP</option>
							   	<option value="4">Carteira </option>
							</select>
		    			</div>
		    			<div class="col-md-1">
		    				<a href="cadastroConta.jsp"><i class="glyphicon glyphicon-plus"></i></a>
		    			</div>
		    			<div class="col-md-1">
		    				Data
		    			</div>
		    			<div class="col-md-4">
		    				<input type="date" name="txtData" class="form-control" placeholder="" required autofocus
		    					<%if(saida.getData()!=null){
	    	  						out.print("value='"+saida.getData()+"'");
	    	  					}%>
		    				>
						</div>
			 		</div>
			 		
			 		<div class="row">
			 	  		<div class="col-md-2">
		      				Categoria
		      			</div>
		      			<div class="col-md-9">
					      	<select class="form-control" name="txtCategoria">
					      		<%if(saida.getCategoria()!=null){
	    	  						out.print("<opntion value='"+saida.getCategoria().getId()+"'>"+
					      			saida.getCategoria().getDescricao()+"</option>");
	    	  					}%>
							    <option value="1">Alimentação</option>
							    <option value="6">Educação</option>
							    <option value="3">Lazer</option>
							    <option value="2">Moradia</option>
							    <option value="5">Outros</option>
							    <option value="7">Transporte</option>
							    <option value="4">Vestuario</option>
						   	</select>
						</div>
						<div class="col-md-1">
		    				<a href="cadastroCategoria.jsp"><i class="glyphicon glyphicon-plus"></i></a>
		    			</div> 
			 		</div><!-- end row -->
		 		<br>
			 		<!-- <div class="col-md-3">
			    		<input id="parcelado" value="Parcelado" type="checkbox">Parcelado
					</div>
					<div class="parcelado" style="display: none">
						<div class="col-md-2 col-md-offset-2">
			    			Vencimento
			    		</div>
			    		<div class='col-md-4'>
			    			<input type='date' name='txtVencimento' class='form-control' autofocus
			    				<%if(saida.getVencimento()!=null){
		    	  					out.print("value='"+saida.getVencimento()+"'");
		    	  				}%>
			    			>
						</div>
						<br>
						<div class="row">
							<div class="col-md-2">
								Nº Parcelas
		   					</div>
							<div class="col-md-2">
		      					<input type="number" name="txtNumParcelas" min="1" max="99" class="form-control"
		      						<%if(saida.getParcelas()!=null){
	    	  							out.print("value='"+saida.getParcelas().size()+"'");
	    	  						}
	    	  						else{
	    	  							out.print("value='0'");
	    	  						}%>
		      					>
		    				</div>
		    			
		    				<div class="col-md-3 text-right">
		    					Valor da parcela
		    				</div>
		    				<div class="col-md-3">
		    					<input type="text" name="txtValorParcela" class="form-control" placeholder="R$0,00" autofocus
		    						<%if(saida.getParcelas().size()!=0){
	    	  							out.print("value='"+saida.getParcelas().get(0).getValorParcela()+"'");
	    	  						}%>
		    					>
							</div>
		    			</div>
    				</div>-->
					<div class="row">
						<div class="col-md-12" >
	    					<br>Em caso de atraso:
	    				</div>
	    			</div>
    			
	    			<div class="row">
	    		  		<div class="col-md-1" >
	    		  			Juros
	    		  		</div>
	    		  		<div class="col-md-3">
		    	  			<input type="text" name="txtMulta" class="form-control" placeholder="R$0,00" autofocus
		    	  				<%if(saida.getJuros()!=null){
	    	  						out.print("value='"+saida.getJuros().getMulta()+"'");
	    	  					}%>
		    	  			>
	    				</div>
	    		  		<div class="col-md-1" >
	    		  			+
	    		  		</div>
	    		  		<div class="col-md-3">
		    	  			<input type="text" name="txtJuros" class="form-control" placeholder="R$0,00" autofocus
		    	  				<%if(saida.getJuros()!=null){
	    	  						out.print("value='"+saida.getJuros().getJuros()+"'");
	    	  					}%>
		    	  			>
	    		  		</div>
	    		  		<div class="col-md-3">
			    		  	<select class="form-control" name="txtIntervalo">
			    		  		<%if(saida.getJuros()!=null){
	    	  						out.print("<option value='"+saida.getJuros().getIntervaloCobranca()+"'>"+
	    	  						saida.getJuros().getIntervaloCobranca()+"</option>");
	    	  					}%>
			    		    	<option value="Por dia">Por dia</option>
			    		    	<option value="Por mês">Por mês</option>
			    		    	<option value="Por ano">Por ano</option>
			    		  	</select>
						</div>
	    			</div>
    				<br>
	    			<div class="row">
			    	 	<div class="col-md-4">
			    	 		Nível de importância: 
			    	 	</div>
			    	 	<div class="col-md-8">
				    	  	<label class="radio-inline"><input type="radio" name="txtImportancia" value="1">Pequeno</label>
						  	<label class="radio-inline"><input type="radio" name="txtImportancia" value="2">Médio</label>
						 	<label class="radio-inline"><input type="radio" name="txtImportancia" value="3">Grande</label><br><br>
						</div>
					</div>
					
					<div class="row" >
						<div class="col-md-2">
							Situação
						</div>
						<div class="col-md-3">
			    		  	<select class="form-control" name="txtSituacao">
			    		  		<%if(saida.getSituacao()!=null){
	    	  						out.print("<option value='"+saida.getSituacao()+"'>"+
	    	  						saida.getSituacao()+"</option>");
	    	  					}%>
			    		    	<option value="Pendente">Pendente</option>
			    		    	<option value="Pago">Pago</option>
			    		    	<option value="Pago parcial">Pago parcial</option>
			    		  	</select>
						</div>
						<div class="col-md-7 text-right">
							<button class="btn btn-default" type="submit" >Salvar</button>
						</div>
			  		</div>
				<%out.print("</form>");%>
			</div><!-- panel-body -->
		</div><!-- panel -->
	</div><!-- col-md-6 -->
 	</div> <!-- col-sm-9 col-md-10 affix-content -->
  </div> <!--  container-fluid -->
</div> <!-- row affix-row -->
</body>
</html>