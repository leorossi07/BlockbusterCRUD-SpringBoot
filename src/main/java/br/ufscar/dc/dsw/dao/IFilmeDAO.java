package br.ufscar.dc.dsw.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Filme;

@SuppressWarnings("unchecked")
public interface IFilmeDAO extends CrudRepository<Filme, Long>{

	Filme findById(long id);

	List<Filme> findAll();
	
	Filme save(Filme filme);

	void deleteById(Long id);
}