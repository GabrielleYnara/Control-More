package br.com.controlmore.aplicacao;

import java.util.List;

import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.viewmodel.IViewModel;


// TODO:
// Nao deveria ser entidade de dominio. Verifique depois.
public class Resultado extends EntidadeDominio {

	private String msg;
	private List<EntidadeDominio> entidades;
	private IViewModel modeloVisao;
	
	/**
	 * Método de recuperação do campo msg
	 *
	 * @return valor do campo msg
	 */
	public String getMsg() {
		return msg;
	}
	/**
	 * Valor de msg atribuído a msg
	 *
	 * @param msg Atributo da Classe
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	/**
	 * Método de recuperação do campo entidades
	 *
	 * @return valor do campo entidades
	 */
	public List<EntidadeDominio> getEntidades() {
		return entidades;
	}
	/**
	 * Valor de entidades atribuído a entidades
	 *
	 * @param entidades Atributo da Classe
	 */
	public void setEntidades(List<EntidadeDominio> entidades) {
		this.entidades = entidades;
	}
	public IViewModel getModeloVisao() {
		return modeloVisao;
	}
	public void setModeloVisao(IViewModel modeloVisao) {
		this.modeloVisao = modeloVisao;
	}

	
	
}
