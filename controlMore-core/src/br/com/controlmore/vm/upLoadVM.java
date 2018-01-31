package br.com.controlmore.vm;

import java.util.ArrayList;
import java.util.List;

import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.dominio.Entrada;
import br.com.controlmore.dominio.Saida;
import br.com.controlmore.viewmodel.IViewModel;

public class upLoadVM extends EntidadeDominio{
	//Após receber o arquivo e fazer uma leitura, a ViewHelper irá passar os dados recebidos para as listas abaixo
	List<Saida> saidas = new ArrayList<Saida>();
	List<Entrada> entradas = new ArrayList<Entrada>();
	
	public void setSaidas(List<Saida> s){
		this.saidas.addAll(s);
	}
	public List<Saida> getSaidas(){
		return saidas;
	}
	
	public void setEntradas(List<Entrada> e){
		this.entradas.addAll(e);
	}
	public List<Entrada> getEntradas(){
		return entradas;
	}

}
