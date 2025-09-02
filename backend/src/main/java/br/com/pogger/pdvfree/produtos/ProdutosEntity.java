package br.com.pogger.pdvfree.produtos;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Version;

import java.math.BigDecimal;

import jakarta.persistence.Column;

@Entity
@Table(name = "cad_produtos")
public class ProdutosEntity {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Version
    @Column(name = "nu_vrs")
    private Long numeroVersao;

    @Column(name = "descricao", length = 255, nullable = false, unique = true)
    private String descricao;

    @Column(name = "preco_custo", precision = 15, scale = 2, nullable = true)
    private BigDecimal precoCusto;

    @Override
    public String toString() {
        return this.id.toString().concat(" - ").concat(this.descricao)
            .concat(" - ").concat(this.numeroVersao.toString());
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumeroVersao() {
        return numeroVersao;
    }

    public void setNumeroVersao(Long numeroVersao) {
        this.numeroVersao = numeroVersao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPrecoCusto() {
        return precoCusto;
    }

    public void setPrecoCusto(BigDecimal precoCusto) {
        this.precoCusto = precoCusto;
    }
    
}
