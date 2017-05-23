package br.com.controlmore.dominio;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Entrada extends EntidadeDominio {
	private float valor;
	private String descricao;
	private Date dataEntrada;
	private Frequencia frenquencia;
	private List<Parcela> parcelas = new ArrayList<Parcela>();
	private Conta conta;
	private Cartao cartao;
	private String observacoes;
	private String situacao;
	
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Date getDataEntrada() {
		return dataEntrada;
	}
	public void setDataEntrada(Date dataEntrada) {
		this.dataEntrada = dataEntrada;
	}
	public Frequencia getFrenquencia() {
		return frenquencia;
	}
	public void setFrenquencia(Frequencia frenquencia) {
		this.frenquencia = frenquencia;
	}
	public List<Parcela> getParcelas() {
		
		return parcelas;
	}
	public void setParcela(Parcela parcela) {
		parcelas.add(parcela);
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}

	public Cartao getCartao() {
		return cartao;
	}

	public void setCartao(Cartao cartao) {
		this.cartao = cartao;
	}

	public String getObservacoes() {
		return observacoes;
	}
	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

}
	