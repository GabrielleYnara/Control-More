package br.com.controlmore.dao;

import java.util.List;

import br.com.controlmore.dominio.EntidadeDominio;

public interface IDAO {
	public String salvar(EntidadeDominio entidade);
	public String alterar(EntidadeDominio entidade);
	public String excluir(EntidadeDominio entidade);
	public List<EntidadeDominio> consultar(EntidadeDominio entidade);
}