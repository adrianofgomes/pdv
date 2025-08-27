package br.com.pogger.pdvfree.produtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutosRest {
    @Autowired
    private ProdutosRepository repositorio;
    
    @Autowired
    private ProdutosService service;

    @GetMapping("/produtos")
    public Iterable<ProdutosEntity> produtos() {
        return repositorio.findAll();
    }

    @GetMapping("/produtos/adicionar")
    public String adicionar() {
        service.incluir();
        return "OK";
    }
}
