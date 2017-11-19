package br.com.controlmore.dominio;

public class RelatorioCategoria extends Relatorios{
	private float valorTotal;
	private String descricao;
	private String categoria1;
	private String categoria2;
	private String dataReferencia;
	
	public float getValorTotal() {
		return valorTotal;
	}
	public void setValorTotal(float valorTotal) {
		this.valorTotal = valorTotal;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public String getCategoria1() {
		return categoria1;
	}
	public void setCategoria1(String categoria1) {
		this.categoria1 = categoria1;
	}
	public String getCategoria2() {
		return categoria2;
	}
	public void setCategoria2(String categoria2) {
		this.categoria2 = categoria2;
	}
	public String getDataReferencia() {
		return dataReferencia;
	}
	public void setDataReferencia(String dataReferencia) {
		this.dataReferencia = dataReferencia;
	}
	
}
