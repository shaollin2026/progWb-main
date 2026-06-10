package br.ufms.facom.progweb.services;

import br.ufms.facom.progweb.models.Livro;
import br.ufms.facom.progweb.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    public Optional<Livro> buscarPorId(Long id) {
        return livroRepository.findById(id);
    }

    public Livro salvar(Livro livro) {
        return livroRepository.save(livro);
    }

    public void deletar(Long id) {
        livroRepository.deleteById(id);
    }

    // 🔎 Filtros
    public List<Livro> filtrarPorCategoria(String categoria) {
        return livroRepository.findByCategoria(categoria);
    }

    public List<Livro> filtrarPorAutor(String autor) {
        return livroRepository.findByAutor(autor);
    }

    public List<Livro> filtrarPorPreco(Double valorMaximo) {
        return livroRepository.findByPrecoLessThan(valorMaximo);
    }

    public List<Livro> filtrarPorAno(int ano) {
        return livroRepository.findByAnoPublicacao(ano);
    }
}
