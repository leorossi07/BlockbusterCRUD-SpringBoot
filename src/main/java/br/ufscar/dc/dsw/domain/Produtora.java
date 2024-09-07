package br.ufscar.dc.dsw.domain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "Produtora")
public class Produtora extends AbstractEntity<Long> {

    @NotBlank
    @Size(min = 18, max = 18, message = "{Size.produtora.CNPJ}")
    @Column(nullable = false, unique = true, length = 60)
    private String CNPJ;

    @NotBlank
    @Size(min = 3, max = 60)
    @Column(nullable = false, unique = true, length = 60)
    private String nome;

    @JsonIgnore  // Evita a serialização recursiva da lista de filmes
    @OneToMany(mappedBy = "produtora")
    private List<Filme> filmes;

    // Getters e Setters
    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }
}
