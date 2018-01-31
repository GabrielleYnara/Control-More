package br.com.controlmore.controle;

import br.com.controlmore.aplicacao.Resultado;
import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.vm.upLoadVM;

public interface IFachada {
	public Resultado salvar(EntidadeDominio entidade);
	public Resultado alterar(EntidadeDominio entidade);
	public Resultado excluir(EntidadeDominio entidade);
	public Resultado consultar(EntidadeDominio entidade);
	public Resultado visualizar (EntidadeDominio entidade);
	public Resultado login(EntidadeDominio entidade);
	public Resultado logout(EntidadeDominio entidade);
	public Resultado resumo();
	public Resultado contaReceber();
	public Resultado contaPagar();
}
