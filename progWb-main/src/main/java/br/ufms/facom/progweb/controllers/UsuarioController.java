package br.ufms.facom.progweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.ufms.facom.progweb.models.Usuario;
import br.ufms.facom.progweb.repositories.UsuarioRepository;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    // Listar usuários
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("usuarios", usuarioRepo.findAll());
        return "usuarios/lista"; // conecta com templates/usuarios/lista.html
    }

    // Formulário para novo usuário
    @GetMapping("/novo")
    public String formNovo(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "usuarios/form"; // conecta com templates/usuarios/form.html
    }

    // Salvar usuário
    @PostMapping("/salvar")
    public String salvar(Usuario usuario) {
        usuarioRepo.save(usuario);
        return "redirect:/usuarios";
    }

    // Editar usuário existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Usuario usuario = usuarioRepo.findById(id).orElse(null);
        model.addAttribute("usuario", usuario);
        return "usuarios/form";
    }

    // Excluir usuário
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        usuarioRepo.deleteById(id);
        return "redirect:/usuarios";
    }
}
