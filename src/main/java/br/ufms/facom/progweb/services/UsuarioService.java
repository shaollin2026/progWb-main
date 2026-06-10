package br.ufms.facom.progweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.ufms.facom.progweb.models.Usuario;
import br.ufms.facom.progweb.repositories.UsuarioRepository;

import java.util.List;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder encoder;

    public Usuario buscarPorEmail(String email){
        return usuarioRepository.findByEmail(email).orElse(null);
    }

    public List<Usuario> listarTodos(){
        return usuarioRepository.findAll();
    }
    public void deletar(Long id){
        if(!usuarioRepository.existsById(id)){
            throw new RuntimeException("Usuário não encontrado");
        }
        usuarioRepository.deleteById(id);
    }
    public Usuario buscarPorId(Long id){
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado){
        Usuario usuario = buscarPorId(id);

        usuario.setNome(usuarioAtualizado.getNome());
        usuario.setEmail(usuarioAtualizado.getEmail());
        return usuarioRepository.save(usuario);
    }
    public Usuario salvar(Usuario usuario){
        if(buscarPorEmail(usuario.getEmail()) !=null){
            throw new RuntimeException("Email já cadastrado");
        }
        usuario.setSenha(encoder.encode(usuario.getSenha()));
        usuario.setRole("ROLE_USER");
        return usuarioRepository.save(usuario);
    }
}
