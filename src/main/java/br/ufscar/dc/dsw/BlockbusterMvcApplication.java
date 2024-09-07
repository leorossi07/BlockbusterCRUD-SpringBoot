package br.ufscar.dc.dsw;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ufscar.dc.dsw.domain.Filme;
import br.ufscar.dc.dsw.domain.Produtora;
import br.ufscar.dc.dsw.service.spec.IProdutoraService;
import br.ufscar.dc.dsw.service.spec.IFilmeService;

import java.math.BigDecimal;


@SpringBootApplication
public class BlockbusterMvcApplication implements CommandLineRunner {

    @Autowired
    private IProdutoraService produtoraService;

    @Autowired
    private IFilmeService filmeService;

    public static void main(String[] args) {
        SpringApplication.run(BlockbusterMvcApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // Criando produtoras de exemplo
        Produtora warner = new Produtora();
        warner.setCNPJ("55.789.390/0001-99");
        warner.setNome("Warner Bros");

        Produtora disney = new Produtora();
        disney.setCNPJ("33.222.123/0001-45");
        disney.setNome("Disney Studios");

        produtoraService.salvar(warner);
        produtoraService.salvar(disney);

        // Criando filmes de exemplo
        Filme filme1 = new Filme();
        filme1.setTitulo("Inception");
        filme1.setDiretor("Christopher Nolan");
        filme1.setAno(2010);
        filme1.setPreco(new BigDecimal("19.99"));
        filme1.setProdutora(warner);

        Filme filme2 = new Filme();
        filme2.setTitulo("The Dark Knight");
        filme2.setDiretor("Christopher Nolan");
        filme2.setAno(2008);
        filme2.setPreco(new BigDecimal("15.99"));
        filme2.setProdutora(warner);

        Filme filme3 = new Filme();
        filme3.setTitulo("Toy Story");
        filme3.setDiretor("John Lasseter");
        filme3.setAno(1995);
        filme3.setPreco(new BigDecimal("9.99"));
        filme3.setProdutora(disney);

        // Salvando filmes
        filmeService.salvar(filme1);
        filmeService.salvar(filme2);
        filmeService.salvar(filme3);
    }
}
