package br.com.pogger.pdvfree.produtos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProdutosRest {
    
    @Autowired
    private ProdutosService service;

    @GetMapping("/produtos")
    public Iterable<ProdutosEntity> produtos() {
        return service.findAll();
    }

    @GetMapping("/produtos/incluirTeste")
    public String incluirTeste() {
        service.incluirTeste();
        return "OK";
    }

    //método para incluir um novo produto com POST
    @PostMapping("/produtos/incluir")
    public ProdutosEntity incluir(@RequestBody ProdutosEntity novoProduto) {
        novoProduto = service.incluir(novoProduto);
        System.out.println("Produto incluído: " + novoProduto);
        return novoProduto;
    }
}
