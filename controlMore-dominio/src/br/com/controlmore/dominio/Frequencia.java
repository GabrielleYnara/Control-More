package br.com.controlmore.dominio;

public class Frequencia extends EntidadeDominio{
	private String descricao;
	private int QtdeDias;

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getQtdeDias() {
		return QtdeDias;
	}

	public void setQtdeDias(int qtdeDias) {
		QtdeDias = qtdeDias;
	}
	
}
