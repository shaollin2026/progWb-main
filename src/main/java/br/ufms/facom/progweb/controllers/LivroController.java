package br.ufms.facom.progweb.controllers;

import br.ufms.facom.progweb.models.Livro;
import br.ufms.facom.progweb.services.LivroService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    // Criar livro
    @PostMapping
    public Livro criarLivro(@RequestBody Livro livro) {
        return livroService.salvar(livro);
    }

    // Listar todos
    @GetMapping
    public List<Livro> listarLivros() {
        return livroService.listarTodos();
    }

    // Buscar por ID
    @GetMapping("/{id}")
    public Livro buscarPorId(@PathVariable Long id) {
        return livroService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));
    }

    // Atualizar livro
    @PutMapping("/{id}")
    public Livro atualizarLivro(@PathVariable Long id, @RequestBody Livro livroAtualizado) {
        Livro livro = livroService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        livro.setTitulo(livroAtualizado.getTitulo());
        livro.setAutor(livroAtualizado.getAutor());
        livro.setPreco(livroAtualizado.getPreco());
        livro.setCategoria(livroAtualizado.getCategoria());
        livro.setAnoPublicacao(livroAtualizado.getAnoPublicacao());

        return livroService.salvar(livro);
    }

    // Deletar livro
    @DeleteMapping("/{id}")
    public void deletarLivro(@PathVariable Long id) {
        livroService.deletar(id);
    }
}
