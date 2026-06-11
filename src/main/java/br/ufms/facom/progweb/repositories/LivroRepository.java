package br.ufms.facom.progweb.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.ufms.facom.progweb.models.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    
    List<Livro> findByCategoria(String categoria);
    List<Livro> findByAutor(String autor);
    List<Livro> findByPrecoLessThan(Double preco);
    List<Livro> findByAnoPublicacao(Integer anoPublicacao);

    // 🔎 ATUALIZADO: Agora busca por Título, Autor ou Categoria
    List<Livro> findByTituloContainingIgnoreCaseOrAutorContainingIgnoreCaseOrCategoriaContainingIgnoreCase(String titulo, String autor, String categoria);

    @Query("SELECT DISTINCT l.categoria FROM Livro l WHERE l.categoria IS NOT NULL AND l.categoria <> ''")
    List<String> findAllCategorias();
}