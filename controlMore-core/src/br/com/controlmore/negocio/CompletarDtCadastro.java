package br.com.controlmore.negocio;

import java.util.Date;

import br.com.controlmore.dominio.EntidadeDominio;

public class CompletarDtCadastro implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {		
		if(entidade !=null){
			Date data = new Date();		
			entidade.setDtCadastro(data);
		}else{
			return "Entidade: "+entidade.getClass().getCanonicalName()+" nula!";
		}
		
		return null;
		}
	}


