package br.org.generation.squadspringgames.controller;

import br.org.generation.squadspringgames.model.Produto;
import br.org.generation.squadspringgames.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController {

    @Autowired
    private ProdutoRepository produtoRepository;

    @GetMapping
    public ResponseEntity<List<Produto>> getAll(){
        return ResponseEntity.ok(produtoRepository.findAll());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Produto> getById(@PathVariable long id){
        return produtoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @GetMapping("/nomes/{nome}")
    public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome){
        return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
    }

    @GetMapping("/consoles/{console}")
    public ResponseEntity<List<Produto>> getByConsole(@PathVariable String console){
        return ResponseEntity.ok(produtoRepository.findAllByConsoleContainingIgnoreCase(console));
    }

    @GetMapping("/menor_preco/{preco}")
    public ResponseEntity<List<Produto>> getByPrecoMenorQue(@PathVariable BigDecimal preco){
        return ResponseEntity.ok(produtoRepository.findByPrecoLessThanOrderByPrecoDesc(preco));
    }

    @GetMapping("/maior_preco/{preco}")
    public ResponseEntity<List<Produto>> getByPrecoMaiorQue(@PathVariable BigDecimal preco){
        return ResponseEntity.ok(produtoRepository.findByPrecoGreaterThanOrderByPreco(preco));
    }

    @PostMapping
    public ResponseEntity<Produto> postProduto(@RequestBody Produto produto){
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoRepository.save(produto));
    }

    @PutMapping
    public ResponseEntity<Produto> putProduto(@RequestBody Produto produto){
        return produtoRepository.findById(produto.getId())
                .map(resposta -> ResponseEntity.status(HttpStatus.OK)
                        .body(produtoRepository.save(produto)))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduto(@PathVariable long id){
        return produtoRepository.findById(id)
                .map(resposta -> {
                    produtoRepository.deleteById(id);
                    return ResponseEntity.noContent().build();
                })
                .orElse(ResponseEntity.notFound().build());
    }

}
