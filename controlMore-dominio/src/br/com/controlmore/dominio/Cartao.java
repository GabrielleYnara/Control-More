package br.com.controlmore.dominio;

import java.util.Date;

public class Cartao extends EntidadeDominio{
	private String bandeira;
	private float limite;
	private float limiteUtilizado;
	private int fechaDia;
	private int venceDia;
	private Conta conta;

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public float getLimite() {
		return limite;
	}

	public void setLimite(float limite) {
		this.limite = limite;
	}

	public float getLimiteUtilizado() {
		return limiteUtilizado;
	}

	public void setLimiteUtilizado(float limiteUtilizado) {
		this.limiteUtilizado = limiteUtilizado;
	}

	public int getFechaDia() {
		return fechaDia;
	}

	public void setFechaDia(int fechaDia) {
		this.fechaDia = fechaDia;
	}

	public int getVenceDia() {
		return venceDia;
	}

	public void setVenceDia(int venceDia) {
		this.venceDia = venceDia;
	}

	public Conta getConta() {
		return conta;
	}

	public void setConta(Conta conta) {
		this.conta = conta;
	}
	
	public int getContaId(){
		Conta conta;
		conta = getConta();
		return conta.getId();
	}

}
