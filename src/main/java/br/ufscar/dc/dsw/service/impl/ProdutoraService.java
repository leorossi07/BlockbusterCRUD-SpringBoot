package br.ufscar.dc.dsw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.ufscar.dc.dsw.dao.IProdutoraDAO;
import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.service.spec.IProdutoraService;

@Service
@Transactional(readOnly = false)
public class ProdutoraService implements IProdutoraService {

	@Autowired
	IProdutoraDAO dao;
	
	public void salvar(Produtora produtora) {
		dao.save(produtora);
	}

	public void excluir(Long id) {
		dao.deleteById(id);
	}

	@Transactional(readOnly = true)
	public Produtora buscarPorId(Long id) {
		return dao.findById(id.longValue());
	}

	@Transactional(readOnly = true)
	public List<Produtora> buscarTodos() {
		return dao.findAll();
	}
	
	@Transactional(readOnly = true)
	public boolean produtoraTemFilmes(Long id) {
		return !dao.findById(id.longValue()).getFilmes().isEmpty(); 
	}
}