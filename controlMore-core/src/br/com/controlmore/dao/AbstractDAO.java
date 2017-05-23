package br.com.controlmore.dao;

import java.sql.Connection;
import java.util.List;

import br.com.controlmore.dominio.EntidadeDominio;
import br.com.controlmore.jdbc.Conexao;

public abstract class AbstractDAO implements IDAO {
	protected Connection conexao = Conexao.getConnection();

}
