package br.ufms.facom.progweb.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Livro {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String titulo;
        private String autor;
        private int anoPublicacao;
        private String categoria;
        private String capaUrl;

        // Getters e Setters
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }

        public String getTitulo() { return titulo; }
        public void setTitulo(String titulo) { this.titulo = titulo; }

        public String getAutor() { return autor; }
        public void setAutor(String autor) { this.autor = autor; }

        public int getAnoPublicacao() { return anoPublicacao; }
        public void setAnoPublicacao(int anoPublicacao) { this.anoPublicacao = anoPublicacao; }

        public String getCategoria() { return categoria; }
        public void setCategoria(String categoria) { this.categoria = categoria; }

        public String getCapaUrl() { return capaUrl; }
        public void setCapaUrl(String capaUrl) {this.capaUrl = capaUrl; }
    }

