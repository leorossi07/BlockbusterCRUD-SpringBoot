package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Produtora;

public interface IProdutoraService {

	Produtora buscarPorId(Long id);

	List<Produtora> buscarTodos();

	void salvar(Produtora produtora);

	void excluir(Long id);
	
	boolean produtoraTemFilmes(Long id);
}