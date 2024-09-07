package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Produtora;

@SuppressWarnings("unchecked")
public interface IProdutoraDAO extends CrudRepository<Produtora, Long>{

	Produtora findById(long id);
	
	Produtora findByCNPJ (String CNPJ);
	@SuppressWarnings("null")
	List<Produtora> findAll();
	
	@SuppressWarnings("null")
	Produtora save(Produtora produtora);
	@SuppressWarnings("null")
	void deleteById(Long id);
}