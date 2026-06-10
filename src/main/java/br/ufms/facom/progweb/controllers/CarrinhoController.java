package br.ufms.facom.progweb.controllers;

import br.ufms.facom.progweb.models.Carrinho;
import br.ufms.facom.progweb.services.CarrinhoService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    private final CarrinhoService carrinhoService;

    public CarrinhoController(CarrinhoService carrinhoService) {
        this.carrinhoService = carrinhoService;
    }

    // Criar carrinho para um usuário
    @PostMapping("/usuario/{usuarioId}")
    public Carrinho criarCarrinho(@PathVariable Long usuarioId) {
        return carrinhoService.criarCarrinho(usuarioId);
    }

    // Buscar carrinho por ID
    @GetMapping("/{id}")
    public Optional<Carrinho> buscarCarrinho(@PathVariable Long id) {
        return carrinhoService.buscarPorId(id);
    }

    // Atualizar carrinho (adicionar/remover livros)
    @PutMapping("/{id}")
    public Carrinho atualizarCarrinho(@PathVariable Long id, @RequestBody Carrinho carrinhoAtualizado) {
        Carrinho carrinho = carrinhoService.buscarPorId(id)
                .orElseThrow(() -> new RuntimeException("Carrinho não encontrado"));

        carrinho.setLivrosIds(carrinhoAtualizado.getLivrosIds());
        return carrinhoService.salvar(carrinho);
    }

    // Deletar carrinho
    @DeleteMapping("/{id}")
    public void deletarCarrinho(@PathVariable Long id) {
        carrinhoService.deletar(id);
    }
}
