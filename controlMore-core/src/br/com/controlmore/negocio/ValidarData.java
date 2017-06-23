package br.com.controlmore.negocio;

import java.util.Date;

import br.com.controlmore.dominio.EntidadeDominio;

public class ValidarData implements IStrategy {

	@Override
	public String processar(EntidadeDominio entidade) {
		// TODO Auto-generated method stub
		return null;
	}
	public ValidarData() {
		// TODO Auto-generated constructor stub
	}
	public String processar(Date dtInicial, Date dtFinal){
		if(dtFinal.after(dtInicial))
		{
			return null;
		}else{
			return "Período de data Inválido!";
		}
		
	}

}
