package br.ufms.facom.progweb.controllers;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufms.facom.progweb.models.Livro;
import br.ufms.facom.progweb.services.LivroService;

@Controller
public class HomeController {

    @Autowired
    private LivroService livroService;

    @GetMapping("/")
    public String home(@RequestParam(name = "termo", required = false) String termo, Model model) {
        List<Livro> livros;

        if (termo != null && !termo.isBlank()) {
            livros = livroService.buscarPorTituloOuAutor(termo);
            model.addAttribute("termo", termo);
        } else {
            livros = livroService.listarTodos();
        }

        // Extrai todas as categorias dos livros cadastrados sem repetição
        // Garante que não dê erro caso o campo categoria de algum livro esteja nulo
        Set<String> categorias = livros.stream()
                .map(Livro::getCategoria)
                .filter(cat -> cat != null && !cat.isBlank())
                .collect(Collectors.toSet());

        model.addAttribute("livros", livros);
        model.addAttribute("categorias", categorias); // Envia a lista dinâmica para o HTML
        
        return "home";
    }

    @GetMapping("/Livro/{id}")
    public String exibirDetalhesLivro(@PathVariable("id") Long id, Model model) {
        Optional<Livro> livroOpt = livroService.buscarPorId(id);

        if (livroOpt.isPresent()) {
            model.addAttribute("livro", livroOpt.get());
            return "paginas/livro"; 
        }

        return "redirect:/";
    }
}