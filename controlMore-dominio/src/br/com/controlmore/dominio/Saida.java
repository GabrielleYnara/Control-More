package br.com.controlmore.dominio;

import java.util.ArrayList;
import java.util.Date;

import jdk.nashorn.internal.runtime.ListAdapter;

public class Saida extends EntidadeDominio{
	private float valor;
	private String descricao;
	private Date data;
	private Frequencia frequencia;
	private ArrayList<Parcela> parcelas = new ArrayList<Parcela>();
	private Conta conta;
	private Cartao cartao;
	private Categoria categoria;
	private Date vencimento;
	private Juros juros;
	private int importancia;
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
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public Frequencia getFrequencia() {
		return frequencia;
	}
	public void setFrequencia(Frequencia frequencia) {
		this.frequencia = frequencia;
	}
	public ArrayList<Parcela> getParcelas() {
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
	
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public Date getVencimento() {
		return vencimento;
	}
	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}
	public Juros getJuros() {
		return juros;
	}
	public void setJuros(Juros juros) {
		this.juros = juros;
	}
	public int getImportancia() {
		return importancia;
	}
	public void setImportancia(int importancia) {
		this.importancia = importancia;
	}
	public String getSituacao() {
		return situacao;
	}
	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}
	
	
}
