package br.ufscar.dc.dsw.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.service.spec.IProdutoraService;

@Controller
@RequestMapping("/produtoras")
public class ProdutoraController {
	
	@Autowired
	private IProdutoraService service;
	
	@GetMapping("/cadastrar")
	public String cadastrar(Produtora produtora) {
		return "produtora/cadastro";
	}
	
	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("produtoras",service.buscarTodos());
		return "produtora/lista";
	}
	
	@PostMapping("/salvar")
	public String salvar(@Valid Produtora produtora, BindingResult result, RedirectAttributes attr) {
		
		if (result.hasErrors()) {
			return "produtora/cadastro";
		}
		
		service.salvar(produtora);
		attr.addFlashAttribute("sucess", "Produtora inserida com sucesso.");
		return "redirect:/produtoras/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("produtora", service.buscarPorId(id));
		return "produtora/cadastro";
	}
	
	@PostMapping("/editar")
	public String editar(@Valid Produtora produtora, BindingResult result, RedirectAttributes attr) {
		
		
		if (result.getFieldErrorCount() > 1 || result.getFieldError("CNPJ") == null) {
			return "produtora/cadastro";
		}

		service.salvar(produtora);
		attr.addFlashAttribute("sucess", "Produtora editada com sucesso.");
		return "redirect:/produtoras/listar";
	}
	
	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, ModelMap model) {
		if (service.produtoraTemFilmes(id)) {
			model.addAttribute("fail", "Produtora não excluída. Possui filme(s) vinculado(s).");
		} else {
			service.excluir(id);
			model.addAttribute("sucess", "Produtora excluída com sucesso.");
		}
		return listar(model);
	}
}