package br.ufms.facom.progweb.services;

import br.ufms.facom.progweb.models.Compra;
import br.ufms.facom.progweb.models.Livro;
import br.ufms.facom.progweb.repositories.CompraRepository;
import br.ufms.facom.progweb.repositories.LivroRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CompraService {
    private final CompraRepository compraRepository;
    private final LivroRepository livroRepository;
    private final JavaMailSender mailSender;

    public CompraService(CompraRepository compraRepository, LivroRepository livroRepository, JavaMailSender mailSender) {
        this.compraRepository = compraRepository;
        this.livroRepository = livroRepository;
        this.mailSender = mailSender;
    }

    public Compra comprarLivro(Long usuarioId, Long livroId, String emailDestino) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Compra compra = new Compra();
        compra.setUsuarioId(usuarioId);
        compra.setLivroId(livroId);
        compra.setDataCompra(LocalDateTime.now());
        compra.setValor(livro.getPreco());

        compraRepository.save(compra);

        // 📧 Envia confirmação por e-mail
        try {
            MimeMessage mensagem = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mensagem, true);

            helper.setFrom("projetosesolucoestec2026@gmail.com"); // remetente configurado
            helper.setTo(emailDestino);
            helper.setSubject("Confirmação de compra");
            helper.setText("Sua compra foi realizada com sucesso!\n\n" +
                    "Livro: " + livro.getTitulo() + "\n" +
                    "Autor: " + livro.getAutor() + "\n" +
                    "Valor: R$ " + livro.getPreco() + "\n" +
                    "Data: " + compra.getDataCompra());

            mailSender.send(mensagem);

        } catch (MessagingException e) {
            throw new RuntimeException("Erro ao enviar e-mail de confirmação", e);
        }

        return compra;
    }
}
