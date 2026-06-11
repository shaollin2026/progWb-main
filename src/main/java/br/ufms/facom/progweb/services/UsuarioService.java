package br.ufms.facom.progweb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.ufms.facom.progweb.models.Role;
import br.ufms.facom.progweb.models.Usuario;
import br.ufms.facom.progweb.repositories.UsuarioRepository;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario salvar(Usuario usuario) {
        // Regra de negócio: novos cadastros sem papel definido nascem como CLIENTE
        if (usuario.getId() == null && usuario.getRole() == null) {
            usuario.setRole(Role.CLIENTE);
        }
        return usuarioRepository.save(usuario);
    }

    public void deletar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public Optional<Usuario> realizarLogin(String email, String senha) {
        return usuarioRepository.findByEmail(email)
                .filter(usuario -> usuario.getSenha().equals(senha));
    }

    // ADICIONE ESTE MÉTODO: Necessário para a validação de e-mail duplicado no cadastro
    public Optional<Usuario> buscarPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }
}