function graficoMensal() {
	var now = new Date();
	document.write(now);
	
	var dias = ['1', '2', '3', '4', '5', 'Junho',
	                'Julho', 'Agosto', 'Setembro', 'Outubro', 'Novembro', 'Dezembro'];
	
	// Obtém o mês inicial e final do relatório.
	if(entradaSaida.size()>0){
		int mesInicial = 12;
		int mesFinal = 1;
 	   	for(int i=0; i<entradaSaida.size(); i++){
 		   if(entradaSaida.get(i).getClass().getName().equals("br.com.controlmore.dominio.Entrada")){ 
 			   Entrada entrada = (Entrada) entradaSaida.get(i);
     		   if(Integer.valueOf(entrada.getDescricao()) < mesInicial) {
     			   mesInicial = Integer.valueOf(entrada.getDescricao());
     		   }
     		  	if(Integer.valueOf(entrada.getDescricao()) > mesFinal) {
    			   mesFinal = Integer.valueOf(entrada.getDescricao());
    		   }
 		   }
 	   }
 	   out.println("var mesInicial = " + mesInicial + ";");
 	   out.println("var mesFinal = " + mesFinal + ";");
    }
    
	var mesesDoRelatorio = [];
	console.log(mesInicial + "-" + mesFinal);
	for(i = mesInicial; i <= mesFinal; i++) {
		if(i > 12) {
			i = 1;
		}
		mesesDoRelatorio.push(meses[i-1]);
	}
	
    Highcharts.chart('container',{
        chart: {
            type: 'line'
        },
        title: {
            text: 'Gasto anual'
        },
        subtitle: {
            text: 'Contas a pagar e receber'
        },
        xAxis: {
        	 categories: mesesDoRelatorio
        				
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
	            data:[
	                   if(entradaSaida.size()>0){
	                	   for(int i=0; i<entradaSaida.size(); i++){
	                		   if(entradaSaida.get(i).getClass().getName().equals("br.com.controlmore.dominio.Entrada")){ 
	                			   Entrada entrada = (Entrada) entradaSaida.get(i);
		                		   out.print(entrada.getValor() + ", ");
	                		   }
	                	   }
	                   }
	                   ]
	        		},
	        	{name: 'A Pagar',
	            data:[
	                  if(entradaSaida.size()>0){
	               	   for(int i=0; i<entradaSaida.size(); i++){
	               		   if(entradaSaida.get(i).getClass().getName().equals("br.com.controlmore.dominio.Saida")){ 
	               			   Saida saida = (Saida) entradaSaida.get(i);
		                		   out.print(saida.getValor() + ", ");
	               		   }
	               	   }
	                  }
	                 ]
	        	}
	        	]
    });
});