package br.ufms.facom.progweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import br.ufms.facom.progweb.models.Livro;
import br.ufms.facom.progweb.repositories.LivroRepository;

@Controller
@RequestMapping("/livros")
public class LivroController {
    
    @Autowired
    private LivroRepository livroRepo;

    // Listar livros
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("livros", livroRepo.findAll());
        return "livros/lista"; // conecta com templates/livros/lista.html
    }

    // Formulário para novo livro
    @GetMapping("/novo")
    public String formNovo(Model model) {
        model.addAttribute("livro", new Livro());
        return "livros/form"; // conecta com templates/livros/form.html
    }

    // Salvar livro
    @PostMapping("/salvar")
    public String salvar(Livro livro) {
        livroRepo.save(livro);
        return "redirect:/livros";
    }

    // Editar livro existente
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Livro livro = livroRepo.findById(id).orElse(null);
        model.addAttribute("livro", livro);
        return "livros/form";
    }

    // Excluir livro
    @GetMapping("/excluir/{id}")
    public String excluir(@PathVariable Long id) {
        livroRepo.deleteById(id);
        return "redirect:/livros";
    }

    
}
