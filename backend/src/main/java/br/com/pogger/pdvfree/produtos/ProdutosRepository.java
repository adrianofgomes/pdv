package br.com.pogger.pdvfree.produtos;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends CrudRepository<ProdutosEntity, Long> {

  List<ProdutosEntity> findByNome(String nome);

  ProdutosEntity findById(long id);

}