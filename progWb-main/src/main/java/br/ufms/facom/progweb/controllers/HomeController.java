package br.ufms.facom.progweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import br.ufms.facom.progweb.services.LivroService;
import org.springframework.ui.Model;


@Controller
public class HomeController {

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    
    @Autowired
    private LivroService livroService;
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("livros", livroService.listarTodos());
        return "home";
    }
}
