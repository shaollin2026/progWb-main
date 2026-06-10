package br.ufms.facom.progweb.controllers;

import br.ufms.facom.progweb.models.Compra;
import br.ufms.facom.progweb.repositories.CompraRepository;
import br.ufms.facom.progweb.services.CompraService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/compras")
public class CompraController {
    private final CompraService compraService;
    private final CompraRepository compraRepository;

    public CompraController(CompraService compraService, CompraRepository compraRepository) {
        this.compraService = compraService;
        this.compraRepository = compraRepository;
    }

    @PostMapping("/usuario/{usuarioId}/livro/{livroId}")
    public Compra comprar(@PathVariable Long usuarioId,
                          @PathVariable Long livroId,
                          @RequestParam String emailDestino) {
        return compraService.comprarLivro(usuarioId, livroId, emailDestino);
    }

    @GetMapping
    public List<Compra> listarCompras() {
        return compraRepository.findAll();
    }
}
