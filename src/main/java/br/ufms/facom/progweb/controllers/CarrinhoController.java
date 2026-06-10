package br.ufms.facom.progweb.controllers;

import br.ufms.facom.progweb.models.Livro;
import br.ufms.facom.progweb.repositories.LivroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final LivroRepository livroRepository;
    private final List<Livro> carrinho = new ArrayList<>();

    public CarrinhoController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @PostMapping("/adicionar/{livroId}")
    public List<Livro> adicionarLivro(@PathVariable Long livroId) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
        carrinho.add(livro);
        return carrinho;
    }

    @GetMapping
    public List<Livro> listarCarrinho() {
        return carrinho;
    }

    @DeleteMapping("/remover/{livroId}")
    public List<Livro> removerLivro(@PathVariable Long livroId) {
        carrinho.removeIf(l -> l.getId().equals(livroId));
        return carrinho;
    }

    @DeleteMapping("/limpar")
    public void limparCarrinho() {
        carrinho.clear();
    }
}
