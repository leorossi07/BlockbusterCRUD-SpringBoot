package br.ufscar.dc.dsw.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.ufscar.dc.dsw.dao.IProdutoraDAO;
import br.ufscar.dc.dsw.domain.Produtora;

@Component
public class UniqueCNPJValidator implements ConstraintValidator<UniqueCNPJ, String> {

	@Autowired
	private IProdutoraDAO dao;

	@Override
	public boolean isValid(String CNPJ, ConstraintValidatorContext context) {
		if (dao != null) {
			Produtora produtora = dao.findByCNPJ(CNPJ);
			return produtora == null;
		} else {

			return true;
		}

	}
}