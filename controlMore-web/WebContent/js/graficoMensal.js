 import="java.util.Locale"
 import="java.time.format.TextStyle"
 import="java.time.LocalDate"
 import="br.com.controlmore.aplicacao.Resultado"
 import="br.com.controlmore.vm.ResumoVM"

 Resultado resultado = (Resultado) request.getAttribute("resultado"); 
		ResumoVM rVM = new ResumoVM();
		rVM = (ResumoVM) resultado.getModeloVisao();

	Highcharts.chart('graficoMensal',{
    chart: {
        type: 'line'
    },
    title: {
        text: '<% 
        		LocalDate data = LocalDate.now();
        		out.print("Gasto Mensal " + data.getMonth().getDisplayName(TextStyle.FULL, new Locale("pt")));
        	 %>'
    },
    subtitle: {
        text: 'Contas a pagar e receber'
    },
    xAxis: {//Dias
    	 categories: [<%
    	 				if(rVM.getDias().size()>0){
    	 					for(int i=0; i<rVM.getDias().size(); i++){
  		   		   				out.print(rVM.getDias().get(i)+ ", ");
		   					}
	   					}
    	 			%>]
    				
    },
    yAxis: {
        title: {
            text: 'R$'
        }
    },
    plotOptions: {
        line: {
            dataLabels: {
                enabled: true
            },
            enableMouseTracking: true
        }
    },
    series: [{
            name: 'A Receber',
            data:[<%
            		if(rVM.getaReceber().size()>0){
						for(int i=0; i<rVM.getaReceber().size(); i++){
	 		   				out.print(rVM.getaReceber().get(i)+ ", ");
						}
					}
            	 %>]
        		},
        	{name: 'A Pagar',
            data:[<% 
		            if(rVM.getaPagar().size()>0){
						for(int i=0; i<rVM.getaPagar().size(); i++){
				   				out.print(rVM.getaPagar().get(i)+ ", ");
						}
					}
            	 %>]
        	}]
	});
</script>