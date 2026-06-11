package br.ufms.facom.progweb.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // Importante: Adicione este import
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufms.facom.progweb.models.Livro;
import br.ufms.facom.progweb.services.LivroService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/livros")
public class LivroController {

    private final LivroService livroService;

    public LivroController(LivroService livroService) {
        this.livroService = livroService;
    }

    @GetMapping("/novo")
    public String exibirFormularioCadastro(HttpSession session, Model model) {
        br.ufms.facom.progweb.models.Usuario usuarioLogado = 
            (br.ufms.facom.progweb.models.Usuario) session.getAttribute("usuarioLogado");

        if (usuarioLogado == null || !usuarioLogado.getRole().name().equals("ADMIN")) {
            return "redirect:/";
        }

        // Busca as categorias existentes no banco para preencher o datalist
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

        br.ufms.facom.progweb.models.Usuario usuarioLogado = 
            (br.ufms.facom.progweb.models.Usuario) session.getAttribute("usuarioLogado");

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
}