package br.org.generation.squadspringgames.repository;

import br.org.generation.squadspringgames.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    public List<Produto> findAllByNomeContainingIgnoreCase(String nome);
    public List<Produto> findAllByConsoleContainingIgnoreCase(String console);

    //busca valores maiores que o critério de consulta em ordem crescente
    public List <Produto> findByPrecoGreaterThanOrderByPreco(BigDecimal preco);

    //busca valores menores que o critério de consulta em ordem decrescente
    public List <Produto> findByPrecoLessThanOrderByPrecoDesc(BigDecimal preco);
}
