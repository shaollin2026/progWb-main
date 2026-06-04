package br.ufms.facom.progweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufms.facom.progweb.models.Livro;

public interface LivroRepository extends JpaRepository<Livro, Long> {
}

