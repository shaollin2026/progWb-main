package br.ufms.facom.progweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping; // Import simplificado
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufms.facom.progweb.models.Livro;
import br.ufms.facom.progweb.models.Usuario;
import br.ufms.facom.progweb.services.LivroService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    // --- CADASTRO ---

    @GetMapping("/novo")
    public String exibirFormularioCadastro(HttpSession session, Model model) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null || !usuarioLogado.getRole().name().equals("ADMIN")) {
            return "redirect:/";
        }

        model.addAttribute("categorias", livroService.listarTodasCategorias());
        return "paginas/livronew";
    }

    @PostMapping("/novo")
    public String processarCadastroLivro(@RequestParam("titulo") String titulo,
                                         @RequestParam("autor") String autor,
                                         @RequestParam("anoPublicacao") Integer anoPublicacao,
                                         @RequestParam("preco") Double preco,
                                         @RequestParam("categoria") String categoria,
                                         @RequestParam("capaUrl") String capaUrl,
                                         @RequestParam("downloadUrl") String downloadUrl,
                                         HttpSession session) {

        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null || !usuarioLogado.getRole().name().equals("ADMIN")) {
            return "redirect:/";
        }

        Livro novoLivro = new Livro();
        novoLivro.setTitulo(titulo);
        novoLivro.setAutor(autor);
        novoLivro.setAnoPublicacao(anoPublicacao);
        novoLivro.setPreco(preco);
        novoLivro.setCategoria(categoria);
        novoLivro.setCapaUrl(capaUrl);
        novoLivro.setDownloadUrl(downloadUrl);

        livroService.salvar(novoLivro);

        return "redirect:/";
    }

    // --- NOVA FUNCIONALIDADE: EXCLUSÃO ---

    @PostMapping("/deletar/{id}")
    public String deletarLivro(@PathVariable Long id, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");

        // Segurança: Apenas ADMIN pode deletar
        if (usuarioLogado != null && usuarioLogado.getRole().name().equals("ADMIN")) {
            livroService.deletar(id); // Certifique-se que este método existe no seu Service
        }

        return "redirect:/";
    }
}