package br.com.pogger.pdvfree.produtos;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository repositorio;

    public String incluir(){
        ProdutosEntity produto = new ProdutosEntity();
        produto.setDescricao("Produto 1");
        produto.setPrecoCusto(BigDecimal.valueOf(10.555));
        //produto.setPrecoVenda(15.0);
        //produto.setQuantidadeEstoque(100);
        repositorio.save(produto);
        return "OK";
    }

}
