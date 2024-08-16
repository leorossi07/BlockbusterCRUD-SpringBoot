package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
// import jakarta.persistence.EntityResult;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

// import br.ufscar.dc.dsw.domain.Produtora;

@Entity
@Table(name = "Filme")
public class Filme extends AbstractEntity<Long>{
    
    @NotBlank(message = "{NotBlank.filme.titulo}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String titulo;

    @NotBlank(message = "{NotBlank.filme.diretor}")
    @Size(max = 60)
    @Column(nullable = false, length = 60)
    private String diretor;

	@NotNull(message = "{NotNull.filme.ano}")
	@Column(nullable = false, length = 5)
	private Integer ano;

	@NotNull(message = "{NotNull.filme.preco}")
	@Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal preco;

    @NotNull(message = "{NotNull.filme.produtora}")
	@ManyToOne
	@JoinColumn(name = "produtora_id")
	private Produtora produtora;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDiretor() {
        return diretor;
    }

    public void setDiretor(String diretor) {
        this.diretor = diretor;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Produtora getProdutora() {
        return produtora;
    }

    public void setProdutora(Produtora produtora) {
        this.produtora = produtora;
    }

}