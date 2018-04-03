package br.com.controlmore.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.vm.compararCategoriaVM;

public class ordenarComparacao implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String processar(Date dtInicio, Date dtFinal) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String processar(compararCategoriaVM cVM){
		List<Integer> meses = new ArrayList<Integer>();
		List<Saida> cat1 = new ArrayList<Saida>();
		List<Saida> cat2 = new ArrayList<Saida>();

		//aux vai receber o tamanho das listas
		int aux = cVM.getCategoria1().size()+cVM.getCategoria2().size();
		for(int i =0; i<aux;i++){//vai atribuir a lista todas as datas de categoria1
			int c1= Integer.parseInt(cVM.getCategoria1().get(i).getDescricao());
			int c2= Integer.parseInt(cVM.getCategoria2().get(i).getDescricao());
			if(c1<c2){//Se c1 for menor que c2
				meses.add(c1);
				Saida s = new Saida();
				s.setDescricao(String.valueOf(c1));
				s.setValor(0);
				cat2.add(s);
				
			}
			if(c2<c1){//Se c1 for menor que c2
				meses.add(c2);
				Saida s = new Saida();
				s.setDescricao(String.valueOf(c2));
				s.setValor(0);
				cat1.add(s);
			}

		}
		return null;
	}
}