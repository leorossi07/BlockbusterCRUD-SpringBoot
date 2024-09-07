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

import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.service.spec.IProdutoraService;
import jakarta.validation.Valid;

@RestController
public class ProdutoraRestController {

    @Autowired
    private IProdutoraService service;

    // Listar todas as produtoras
    @GetMapping(path = "/api/produtoras")
    public ResponseEntity<List<Produtora>> lista() {
        List<Produtora> lista = service.buscarTodos();
        if (lista.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Retorna 204 se não houver produtoras
        }
        return ResponseEntity.ok(lista);
    }

    // Buscar uma produtora por ID
    @GetMapping(path = "/api/produtoras/{id}")
    public ResponseEntity<Produtora> lista(@PathVariable("id") long id) {
        Produtora produtora = service.buscarPorId(id);
        if (produtora == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retorna 404 se a produtora não for encontrada
        }
        return ResponseEntity.ok(produtora);
    }

    // Criar uma nova produtora
    @PostMapping(path = "/api/produtoras")
    @ResponseBody
    public ResponseEntity<?> cria(@Valid @RequestBody Produtora produtora, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(getValidationErrors(result), HttpStatus.BAD_REQUEST);  // Valida e retorna erros
        } else {
            service.salvar(produtora);
            return new ResponseEntity<>(produtora, HttpStatus.CREATED);  // Retorna 201 Created
        }
    }

    // Atualizar uma produtora existente
    @PutMapping(path = "/api/produtoras/{id}")
    public ResponseEntity<?> atualiza(@PathVariable("id") long id, @Valid @RequestBody Produtora produtora, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(getValidationErrors(result), HttpStatus.BAD_REQUEST);  // Valida e retorna erros
        } else {
            Produtora existente = service.buscarPorId(id);
            if (existente == null) {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retorna 404 se a produtora não for encontrada
            } else {
                produtora.setId(id);
                service.salvar(produtora);
                return ResponseEntity.ok(produtora);  // Retorna 200 OK
            }
        }
    }

    // Excluir uma produtora
    @DeleteMapping(path = "/api/produtoras/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") long id) {
        Produtora produtora = service.buscarPorId(id);
        if (produtora == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);  // Retorna 404 se a produtora não for encontrada
        } else {
            if (service.produtoraTemFilmes(id)) {  // Verifica se a produtora tem filmes associados
                return new ResponseEntity<>(false, HttpStatus.FORBIDDEN);  // Retorna 403 se não puder ser excluída
            } else {
                service.excluir(id);
                return new ResponseEntity<>(true, HttpStatus.OK);  // Retorna 200 OK se excluída com sucesso
            }
        }
    }

    // Método auxiliar para capturar erros de validação e retornar para o cliente
    private List<String> getValidationErrors(BindingResult result) {
        return result.getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList();
    }
}
