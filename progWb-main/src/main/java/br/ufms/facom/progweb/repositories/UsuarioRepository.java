package br.ufms.facom.progweb.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import br.ufms.facom.progweb.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
