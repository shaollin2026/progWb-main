package br.ufms.facom.progweb.repositories;

import br.ufms.facom.progweb.models.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findByCategoria(String categoria);
    List<Livro> findByAutor(String autor);
    List<Livro> findByPrecoLessThan(Double preco);
    List<Livro> findByAnoPublicacao(int anoPublicacao);
}
