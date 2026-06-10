package br.ufms.facom.progweb.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Carrinho {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long usuarioId;

    @ElementCollection
    private List<Long> livrosIds; // lista de livros adicionados

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public List<Long> getLivrosIds() { return livrosIds; }
    public void setLivrosIds(List<Long> livrosIds) { this.livrosIds = livrosIds; }
}
