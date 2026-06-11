package br.ufms.facom.progweb.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.ufms.facom.progweb.models.Carrinho;

@Repository
public interface CarrinhoRepository extends JpaRepository<Carrinho, Long> {
    // Busca o carrinho salvo vinculado ao ID do usuário
    Optional<Carrinho> findByUsuarioId(Long usuarioId);
}