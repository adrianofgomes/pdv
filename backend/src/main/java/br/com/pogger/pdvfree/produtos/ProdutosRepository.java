package br.com.pogger.pdvfree.produtos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutosRepository extends JpaRepository<ProdutosEntity, Long> {

  List<ProdutosEntity> findByDescricaoContaining(String descricao);

  //ProdutosEntity findById(long id);

}