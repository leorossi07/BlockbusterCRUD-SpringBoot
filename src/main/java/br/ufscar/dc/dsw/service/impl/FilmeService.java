package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IFilmeDAO;
import br.ufscar.dc.dsw.domain.Filme;
import br.ufscar.dc.dsw.service.spec.IFilmeService;

@Service
@Transactional(readOnly = false)
public class FilmeService implements IFilmeService {

	@Autowired
	IFilmeDAO dao;
	
	public void salvar(Filme filme) {
		dao.save(filme);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Filme buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Filme> buscarTodos() {
		return dao.findAll();
	}
}