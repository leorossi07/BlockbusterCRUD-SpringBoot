package br.ufscar.dc.dsw.service.spec;

import java.util.List;

import br.ufscar.dc.dsw.domain.Filme;

public interface IFilmeService {

	Filme buscarPorId(Long id);
	
	List<Filme> buscarTodos();
	
	void salvar(Filme filme);
	
	void excluir(Long id);
	
}