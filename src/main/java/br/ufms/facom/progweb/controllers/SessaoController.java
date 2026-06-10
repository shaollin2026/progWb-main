package br.ufms.facom.progweb.controllers;

import br.ufms.facom.progweb.services.SessaoService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sessao")
public class SessaoController {
    private final SessaoService sessaoService;

    public SessaoController(SessaoService sessaoService) {
        this.sessaoService = sessaoService;
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String senha) {
        boolean autenticado = sessaoService.autenticar(email, senha);
        if (autenticado) {
            return "Login realizado com sucesso!";
        } else {
            return "Falha na autenticação. Verifique email e senha.";
        }
    }

    @GetMapping("/permissao")
    public String verificarPermissao(@RequestParam String roleNecessaria, @RequestParam String roleUsuario) {
        boolean permitido = sessaoService.verificarPermissao(roleNecessaria, roleUsuario);
        return permitido ? "Acesso permitido." : "Acesso negado.";
    }
}
