package br.ufscar.dc.dsw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.ufscar.dc.dsw.domain.Filme;
import br.ufscar.dc.dsw.service.spec.IFilmeService;
import jakarta.validation.Valid;

@RestController
public class FilmeRestController {

	@Autowired
	private IFilmeService service;

	@GetMapping(path = "/api/filmes")
	public ResponseEntity<List<Filme>> lista() {
		List<Filme> lista = service.buscarTodos();
		if (lista.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT); // Mudança para indicar que a lista está vazia
		}
		return ResponseEntity.ok(lista);
	}

	@GetMapping(path = "/api/filmes/{id}")
	public ResponseEntity<Filme> lista(@PathVariable("id") long id) {
		Filme filme = service.buscarPorId(id);
		if (filme == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Retorno claro de erro
		}
		return ResponseEntity.ok(filme);
	}

	@PostMapping(path = "/api/filmes")
	@ResponseBody
	public ResponseEntity<?> cria(@Valid @RequestBody Filme filme, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(getValidationErrors(result), HttpStatus.BAD_REQUEST); // Retorna erros de validação
		} else {
			service.salvar(filme);
			return new ResponseEntity<>(filme, HttpStatus.CREATED); // Resposta adequada para criação
		}
	}

	@PutMapping(path = "/api/filmes/{id}")
	public ResponseEntity<?> atualiza(@PathVariable("id") long id, @Valid @RequestBody Filme filme, BindingResult result) {
		if (result.hasErrors()) {
			return new ResponseEntity<>(getValidationErrors(result), HttpStatus.BAD_REQUEST); // Validação para atualização
		} else {
			Filme existente = service.buscarPorId(id);
			if (existente == null) {
				return new ResponseEntity<>(HttpStatus.NOT_FOUND); // Caso não encontrado
			} else {
				filme.setId(id);
				service.salvar(filme);
				return ResponseEntity.ok(filme); // Retorno padrão para atualização
			}
		}
	}

	@DeleteMapping(path = "/api/filmes/{id}")
	public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
		Filme filme = service.buscarPorId(id);
		if (filme == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			service.excluir(id);
			return new ResponseEntity<>(true, HttpStatus.OK);
		}
	}

	// Método auxiliar para capturar erros de validação e retornar para o cliente
	private List<String> getValidationErrors(BindingResult result) {
		return result.getFieldErrors().stream()
				.map(error -> error.getField() + ": " + error.getDefaultMessage())
				.toList();
	}
}
