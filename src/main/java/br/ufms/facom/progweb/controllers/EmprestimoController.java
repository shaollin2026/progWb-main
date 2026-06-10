package br.ufms.facom.progweb.controllers;

import br.ufms.facom.progweb.models.Emprestimo;
import br.ufms.facom.progweb.services.EmprestimoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emprestimos")
public class EmprestimoController {
    private final EmprestimoService emprestimoService;

    public EmprestimoController(EmprestimoService emprestimoService) {
        this.emprestimoService = emprestimoService;
    }

    // Registrar empréstimo
    @PostMapping("/usuario/{usuarioId}/livro/{livroId}")
    public Emprestimo emprestarLivro(@PathVariable Long usuarioId,
                                     @PathVariable Long livroId,
                                     @RequestParam(defaultValue = "7") int dias) {
        return emprestimoService.emprestarLivro(usuarioId, livroId, dias);
    }
}
