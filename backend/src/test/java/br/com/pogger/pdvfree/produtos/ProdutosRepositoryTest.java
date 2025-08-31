package br.com.pogger.pdvfree.produtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.test.annotation.Rollback;
import java.math.BigDecimal;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import jakarta.persistence.EntityManager;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class ProdutosRepositoryTest {

    @Autowired
    private ProdutosRepository produtosRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Deve salvar e buscar produto por id")
    @Rollback
    void testSaveAndFindById() {
        ProdutosEntity produto = new ProdutosEntity();
        produto.setDescricao("Produto Teste");
        produto.setPrecoCusto(new BigDecimal("12.34"));

        ProdutosEntity saved = produtosRepository.save(produto);

        ProdutosEntity found = produtosRepository.findById(saved.getId()).orElse(null);
        assertThat(found).isNotNull();
        assertThat(found.getDescricao()).isEqualTo("Produto Teste");
        assertThat(found.getPrecoCusto()).isEqualByComparingTo("12.34");
    }

    @Test
    @DisplayName("Deve buscar produto por descricao exclusiva")
    @Rollback
    void testFindByDescricao() {
        ProdutosEntity produto = new ProdutosEntity();
        produto.setDescricao("Descricao Unica");
        produto.setPrecoCusto(new BigDecimal("1.00"));
        produtosRepository.save(produto);

        List<ProdutosEntity> encontrados = produtosRepository.findByDescricaoContaining("Descricao Unica");
        assertThat(encontrados).hasSize(1);
        assertThat(encontrados.get(0).getDescricao()).isEqualTo("Descricao Unica");
    }

    @Test
    @DisplayName("Deve retornar null ao buscar por id inexistente")
    @Rollback
    void testFindByIdNotFound() {
        ProdutosEntity found = produtosRepository.findById(99999L).orElse(null);
        assertThat(found).isNull();
    }

    @Test
    @DisplayName("Deve retornar lista vazia ao buscar descricao inexistente")
    @Rollback
    void testFindByDescricaoNotFound() {
        List<ProdutosEntity> encontrados = produtosRepository.findByDescricaoContaining("NaoExiste");
        assertThat(encontrados).isEmpty();
    }

    @Test
    void deveBuscarProdutosPorDescricaoParcial() {
        // Arrange
        ProdutosEntity produto1 = new ProdutosEntity();
        produto1.setDescricao("Coca-Cola 2L");
        produtosRepository.save(produto1);

        ProdutosEntity produto2 = new ProdutosEntity();
        produto2.setDescricao("Coca-Cola Lata");
        produtosRepository.save(produto2);

        ProdutosEntity produto3 = new ProdutosEntity();
        produto3.setDescricao("Pepsi 2L");
        produtosRepository.save(produto3);

        // Act
        List<ProdutosEntity> resultado = produtosRepository.findByDescricaoContaining("Coca");

        // Assert
        assertThat(resultado)
                .hasSize(2)
                .extracting(ProdutosEntity::getDescricao)
                .containsExactlyInAnyOrder("Coca-Cola 2L", "Coca-Cola Lata");
    }

    @Test
    @DisplayName("Deve lançar ObjectOptimisticLockingFailureException ao ocorrer conflito de lock otimista")
    //@Transactional
    void testOptimisticLocking() {
        // Salva o produto inicial
        ProdutosEntity produto = new ProdutosEntity();
        produto.setDescricao("Produto Lock");
        produto.setPrecoCusto(new BigDecimal("10.00"));
        ProdutosEntity salvo = produtosRepository.saveAndFlush(produto);

        System.out.println("salvo: ".concat(salvo.toString()));

        // Simula dois usuários carregando o mesmo produto
        ProdutosEntity usuario1 = produtosRepository.findById(salvo.getId()).orElseThrow();
        ProdutosEntity usuario2 = produtosRepository.findById(salvo.getId()).orElseThrow();
        //ambos sao lidos com versao = 0 e o clear faz com q nao sejam mais gerenciados pelo JPA
        entityManager.clear();

        // Usuário 1 altera e salva
        usuario1.setDescricao("Produto Lock Alterado 1");
        //nesse ponto apenas usuario 1 está gerenciado e com versao = 1. 
        usuario1 = produtosRepository.saveAndFlush(usuario1);

        // Usuário 2 tenta alterar e salvar (deve falhar)
        assertThatThrownBy(() -> {
            usuario2.setDescricao("Produto Lock Alterado 2");
            produtosRepository.saveAndFlush(usuario2);
        }).isInstanceOf(ObjectOptimisticLockingFailureException.class);
    }
}