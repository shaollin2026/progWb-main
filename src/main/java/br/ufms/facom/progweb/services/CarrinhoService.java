package br.ufms.facom.progweb.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import br.ufms.facom.progweb.models.Carrinho;
import br.ufms.facom.progweb.repositories.CarrinhoRepository;
import br.ufms.facom.progweb.repositories.LivroRepository;

@Service
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;
    private final LivroRepository livroRepository;
    @Autowired(required = false) private JavaMailSender mailSender;

    public CarrinhoService(CarrinhoRepository cr, LivroRepository lr) { 
        this.carrinhoRepository = cr; this.livroRepository = lr; 
    }

    public Carrinho obterOuCriarCarrinho(Long uid) {
        return carrinhoRepository.findByUsuarioId(uid).orElseGet(() -> {
            Carrinho novo = new Carrinho(); novo.setUsuarioId(uid); novo.setLivrosIds(new ArrayList<>());
            return carrinhoRepository.save(novo);
        });
    }

    public Carrinho salvar(Carrinho c) { return carrinhoRepository.save(c); }

    public void finalizarCompraESendEmail(String email, List<Long> ids) {
        StringBuilder sb = new StringBuilder("Muito obrigado por comprar na Libreoteca!\n\nSeus livros:\n");
        for (Long id : ids) {
            livroRepository.findById(id).ifPresent(l -> sb.append("- ").append(l.getTitulo()).append(": ").append(l.getDownloadUrl()).append("\n"));
        }
        
        if (mailSender != null) {
            try {
                SimpleMailMessage msg = new SimpleMailMessage();
                msg.setFrom("projetosesolucoestec2026@gmail.com");
                msg.setTo(email);
                msg.setSubject("Seus Livros - Libreoteca");
                msg.setText(sb.toString());
                mailSender.send(msg);
                System.out.println("✅ E-mail enviado!");
            } catch (Exception e) { System.err.println("❌ Erro no envio: " + e.getMessage()); }
        }
    }
}