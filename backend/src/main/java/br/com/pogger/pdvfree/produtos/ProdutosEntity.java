package br.com.pogger.pdvfree.produtos;

import java.math.BigDecimal;

import br.com.pogger.pdvfree.base.AppBaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "cad_produtos")
public class ProdutosEntity extends AppBaseEntity {

    @Column(name = "descricao", length = 255, nullable = false, unique = true)
    private String descricao;

    @Column(name = "preco_custo", precision = 15, scale = 2, nullable = true)
    private BigDecimal precoCusto;

    @Override
    public String toString() {
        return this.id.toString().concat(" - ").concat(this.descricao)
            .concat(" - ").concat(this.numeroVersao.toString());
    }

    // Construtores
    public ProdutosEntity() {
    }

    public ProdutosEntity(String descricao, BigDecimal precoCusto) {
        this.descricao = descricao;
        this.precoCusto = precoCusto;
    }

    // Getters and Setters
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
