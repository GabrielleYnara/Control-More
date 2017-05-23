package br.com.controlmore.dominio;

public class Juros extends EntidadeDominio{
	private float multa;
	private float juros;
	private String intervaloCobranca;
	public float getMulta() {
		return multa;
	}
	public void setMulta(float multa) {
		this.multa = multa;
	}
	public float getJuros() {
		return juros;
	}
	public void setJuros(float juros) {
		this.juros = juros;
	}
	public String getIntervaloCobranca() {
		return intervaloCobranca;
	}
	public void setIntervaloCobranca(String intervaloCobranca) {
		this.intervaloCobranca = intervaloCobranca;
	}
	
}
