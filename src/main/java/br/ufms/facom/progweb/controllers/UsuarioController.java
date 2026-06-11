package br.ufms.facom.progweb.controllers;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufms.facom.progweb.models.Usuario;
import br.ufms.facom.progweb.services.UsuarioService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {
    
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    // 1. Abre a tela de login (Acessível em: http://localhost:8080/usuarios/login)
    @GetMapping("/login")
    public String exibirLogin() {
        return "paginas/login"; 
    }

    // 2. Processa o envio dos dados do formulário de Login
    @PostMapping("/login") 
    public String processarLogin(@RequestParam("email") String email, 
                                 @RequestParam("senha") String senha, 
                                 HttpSession session, 
                                 Model model) {
                                 
        Optional<Usuario> usuarioOpt = usuarioService.realizarLogin(email, senha);

        if (usuarioOpt.isPresent()) {
            session.setAttribute("usuarioLogado", usuarioOpt.get());
            return "redirect:/"; 
        }

        model.addAttribute("erro", "E-mail ou senha incorretos.");
        return "paginas/login"; 
    }

    // 3. Abre a tela de cadastro (Acessível em: http://localhost:8080/usuarios/cadastro)
    @GetMapping("/cadastro")
    public String exibirCadastro() {
        return "paginas/cadastro";
    }

    // 4. Processa o envio do formulário de Cadastro de novos Clientes
    @PostMapping("/cadastro")
    public String processarCadastro(@RequestParam("nome") String nome,
                                    @RequestParam("email") String email,
                                    @RequestParam("senha") String senha,
                                    Model model) {
        
        // Verifica se o e-mail enviado já existe no sistema para evitar duplicidade
        if (usuarioService.buscarPorEmail(email).isPresent()) {
            model.addAttribute("erro", "Este e-mail já está cadastrado.");
            return "paginas/cadastro";
        }

        // Instancia e preenche o novo usuário
        Usuario novoUsuario = new Usuario();
        novoUsuario.setNome(nome);
        novoUsuario.setEmail(email);
        novoUsuario.setSenha(senha);
        
        // Define a role fixa de CLIENTE conforme o enum do seu sistema
        novoUsuario.setRole(br.ufms.facom.progweb.models.Role.CLIENTE); 

        // Salva no banco de dados
        usuarioService.salvar(novoUsuario);

        // Redireciona para o login após cadastrar com sucesso
        return "redirect:/usuarios/login";
    }

    // 5. Destrói a sessão atual (Acessível em: http://localhost:8080/usuarios/logout)
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); 
        return "redirect:/usuarios/login"; 
    }
}