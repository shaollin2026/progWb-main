package br.ufms.facom.progweb.controllers;

import br.ufms.facom.progweb.services.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.ufms.facom.progweb.models.Usuario;

@Controller
public class UsuarioController {

    @GetMapping("/login")
    public String login(){
        return "/login";
    }
    @GetMapping("/cadastro")
    public String cadastroForm(Model model){
        model.addAttribute("usuario", new Usuario());
        return "/usuarios/form";
    }
    @PostMapping("/cadastro")
    public String cadastrar(Usuario usuario){
        usuarioService.salvar(usuario);
        return "redirect:/login";
    }
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/usuarios/editar/{id}")
    public String editar(@PathVariable Long id, Model model){
        model.addAttribute("usuario", usuarioService.buscarPorId(id));
        return "usuarios/form";
    }
    @PostMapping("/usuarios/editar/{id}")
    public String atualizar(@PathVariable Long id, Usuario usuario){
        usuarioService.atualizar(id, usuario);
        return "redirect:/usuarios";
    }
    @GetMapping("/usuarios")
    public String listar(Model model){model.addAttribute("usuarios", usuarioService.listarTodos());
        return "usuarios/lista";
    }
    @GetMapping("/usuarios/excluir/{id}")
    public String excluir(@PathVariable Long id){
        usuarioService.deletar(id);
        return "redirect:/usuarios";
    }
}
