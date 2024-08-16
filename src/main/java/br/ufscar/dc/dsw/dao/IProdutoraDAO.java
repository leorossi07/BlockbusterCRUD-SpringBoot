package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Produtora;

@SuppressWarnings("unchecked")
public interface IProdutoraDAO extends CrudRepository<Produtora, Long>{

	Produtora findById(long id);
	
	Produtora findByCNPJ (String CNPJ);

	List<Produtora> findAll();
	
	Produtora save(Produtora produtora);

	void deleteById(Long id);
}