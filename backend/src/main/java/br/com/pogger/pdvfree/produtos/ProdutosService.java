package br.com.pogger.pdvfree.produtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository repositorio;

    public String incluir(){
        ProdutosEntity produto = new ProdutosEntity();
        produto.setNome("Produto 1");
        produto.setPreco(10.0);
        repositorio.save(produto);
        return "OK";
    }

}
