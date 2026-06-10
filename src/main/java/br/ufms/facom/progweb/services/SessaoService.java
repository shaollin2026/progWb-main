package br.ufms.facom.progweb.services;

import org.springframework.stereotype.Service;

@Service
public class SessaoService {

    // Simulação de login/autenticação
    public boolean autenticar(String email, String senha) {
        // Aqui você poderia integrar com Spring Security ou banco de dados
        return email != null && senha != null && !email.isEmpty() && !senha.isEmpty();
    }

    // Verificação de permissões
    public boolean verificarPermissao(String roleNecessaria, String roleUsuario) {
        return roleNecessaria.equals(roleUsuario);
    }
}
