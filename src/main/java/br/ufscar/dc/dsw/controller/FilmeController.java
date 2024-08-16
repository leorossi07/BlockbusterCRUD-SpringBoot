package br.ufscar.dc.dsw.controller;

import java.util.List;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.domain.Filme;
import br.ufscar.dc.dsw.service.spec.IProdutoraService;
import br.ufscar.dc.dsw.service.spec.IFilmeService;

@Controller
@RequestMapping("/filmes")
public class FilmeController {

	@Autowired
	private IFilmeService filmeService;

	@Autowired
	private IProdutoraService produtoraService;

	@GetMapping("/cadastrar")
	public String cadastrar(Filme filme) {
		return "filme/cadastro";
	}

	@GetMapping("/listar")
	public String listar(ModelMap model) {
		model.addAttribute("filmes", filmeService.buscarTodos());
		return "filme/lista";
	}

	@PostMapping("/salvar")
	public String salvar(@Valid Filme filme, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "filme/cadastro";
		}

		filmeService.salvar(filme);
		attr.addFlashAttribute("sucess", "Filme inserido com sucesso");
		return "redirect:/filmes/listar";
	}

	@GetMapping("/editar/{id}")
	public String preEditar(@PathVariable("id") Long id, ModelMap model) {
		model.addAttribute("filme", filmeService.buscarPorId(id));
		return "filme/cadastro";
	}

	@PostMapping("/editar")
	public String editar(@Valid Filme filme, BindingResult result, RedirectAttributes attr) {

		if (result.hasErrors()) {
			return "filme/cadastro";
		}

		filmeService.salvar(filme);
		attr.addFlashAttribute("sucess", "Filme editado com sucesso.");
		return "redirect:/filmes/listar";
	}

	@GetMapping("/excluir/{id}")
	public String excluir(@PathVariable("id") Long id, RedirectAttributes attr) {
		filmeService.excluir(id);
		attr.addFlashAttribute("sucess", "Filme excluído com sucesso.");
		return "redirect:/filmes/listar";
	}

	@ModelAttribute("produtoras")
	public List<Produtora> listaProdutoras() {
		return produtoraService.buscarTodos();
	}
}