package br.com.pogger.pdvfree.produtos;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.pogger.pdvfree.security.AppRoles;
import jakarta.annotation.security.RolesAllowed;

@Service
public class ProdutosService {

    @Autowired
    private ProdutosRepository repositorio;

    //método para buscar todos os produtos
    //@PreAuthorize("hasRole('" + AppRoles.ADMIN + "')") 
    @RolesAllowed({AppRoles.PRODUTOS_WRITE, AppRoles.PRODUTOS_READ})
    public Iterable<ProdutosEntity> findAll(){
        return repositorio.findAll();
    }

    //método para incluir um produto de teste
    public String incluirTeste(){
        ProdutosEntity produto = new ProdutosEntity("Produto 1", BigDecimal.valueOf(10.555));
        repositorio.save(produto);
        return "OK";
    }

    //método para incluir um novo produto
    //@PreAuthorize("hasRole('" + AppRoles.ADMIN + "')") 
    @RolesAllowed({AppRoles.PRODUTOS_WRITE})
    public ProdutosEntity incluir(ProdutosEntity novoProduto){
        ProdutosEntity novo = repositorio.save(novoProduto);
        return novo;
    }
}
