package br.ufscar.dc.dsw.conversor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.service.spec.IProdutoraService;

@Component
public class ProdutoraConversor implements Converter<String, Produtora>{

	@Autowired
	private IProdutoraService service;
	
	@Override
	public Produtora convert(String text) {
		
		if (text.isEmpty()) {
		 return null;	
		}
		
		Long id = Long.valueOf(text);	
		return service.buscarPorId(id);
	}
}