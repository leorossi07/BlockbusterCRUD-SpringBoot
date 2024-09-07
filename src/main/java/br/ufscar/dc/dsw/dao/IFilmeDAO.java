package br.ufscar.dc.dsw.dao;

import java.util.List;

// import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import br.ufscar.dc.dsw.domain.Filme;

@SuppressWarnings("unchecked")
public interface IFilmeDAO extends CrudRepository<Filme, Long>{

	Filme findById(long id);
	@SuppressWarnings("null")
	List<Filme> findAll();



	@SuppressWarnings("null")
	Filme save(Filme filme);
	@SuppressWarnings("null")
	void deleteById(Long id);
}