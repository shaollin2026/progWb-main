package br.ufms.facom.progweb.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.ufms.facom.progweb.models.Carrinho;
import br.ufms.facom.progweb.models.Livro;
import br.ufms.facom.progweb.models.Usuario;
import br.ufms.facom.progweb.services.CarrinhoService;
import br.ufms.facom.progweb.services.LivroService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/carrinho")
public class CarrinhoController {

    private final CarrinhoService carrinhoService;
    private final LivroService livroService;

    public CarrinhoController(CarrinhoService carrinhoService, LivroService livroService) {
        this.carrinhoService = carrinhoService;
        this.livroService = livroService;
    }

    // 1. Exibir
    @GetMapping
    public String exibirCarrinho(HttpSession session, Model model) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) return "redirect:/usuarios/login";

        Carrinho carrinho = carrinhoService.obterOuCriarCarrinho(usuarioLogado.getId());
        List<Livro> listaLivros = new ArrayList<>();
        
        if (carrinho.getLivrosIds() != null) {
            for (Long livroId : carrinho.getLivrosIds()) {
                livroService.buscarPorId(livroId).ifPresent(listaLivros::add);
            }
        }
        model.addAttribute("itens", listaLivros);
        model.addAttribute("total", listaLivros.stream().mapToDouble(Livro::getPreco).sum());
        return "paginas/carrinho"; 
    }

    // 2. ADICIONAR (Essencial para não dar 404!)
    @GetMapping("/adicionar/{livroId}")
    public String adicionarAoCarrinho(@PathVariable Long livroId, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) return "redirect:/usuarios/login";

        livroService.buscarPorId(livroId).ifPresent(livro -> {
            Carrinho carrinho = carrinhoService.obterOuCriarCarrinho(usuarioLogado.getId());
            if (!carrinho.getLivrosIds().contains(livroId)) {
                carrinho.getLivrosIds().add(livroId);
                carrinhoService.salvar(carrinho);
            }
        });
        return "redirect:/carrinho";
    }

    // 3. Bloqueio de acesso direto ao checkout
    @GetMapping("/checkout")
    public String bloquearAcessoDireto() { return "redirect:/carrinho"; }

    // 4. Finalização
    @PostMapping("/checkout")
    public String finalizarCompra(@RequestParam("emailDestino") String emailDestino, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) return "redirect:/usuarios/login";

        Carrinho carrinho = carrinhoService.obterOuCriarCarrinho(usuarioLogado.getId());
        if (carrinho.getLivrosIds() != null && !carrinho.getLivrosIds().isEmpty()) {
            carrinhoService.finalizarCompraESendEmail(emailDestino, carrinho.getLivrosIds());
            carrinho.getLivrosIds().clear();
            carrinhoService.salvar(carrinho);
        }
        return "redirect:/";
    }

    @GetMapping("/remover/{livroId}")
    public String removerDoCarrinho(@PathVariable Long livroId, HttpSession session) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) return "redirect:/usuarios/login";

        Carrinho carrinho = carrinhoService.obterOuCriarCarrinho(usuarioLogado.getId());
        if (carrinho.getLivrosIds() != null) {
            carrinho.getLivrosIds().remove(livroId);
            carrinhoService.salvar(carrinho);
        }
        return "redirect:/carrinho";
    }
}