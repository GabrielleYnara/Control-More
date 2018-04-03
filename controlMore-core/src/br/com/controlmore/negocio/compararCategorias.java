package br.com.controlmore.negocio;

import java.util.Date;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.vm.compararCategoriaVM;

public class compararCategorias implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		Resultado r = (Resultado) entidade;
		compararCategoriaVM compararVM = (compararCategoriaVM) r.getModeloVisao();
		if(compararVM.getIdCategoria1() == compararVM.getIdCategoria2())
			return "Não é possivel comprar duas categorias iguais.";
		return null;
	}

	@Override
	public String processar(Date dtInicio, Date dtFinal) {
		// TODO Auto-generated method stub
		return null;
	}

}
