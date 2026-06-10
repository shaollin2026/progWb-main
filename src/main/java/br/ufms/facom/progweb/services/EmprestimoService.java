package br.ufms.facom.progweb.services;

import br.ufms.facom.progweb.models.Emprestimo;
import br.ufms.facom.progweb.models.Livro;
import br.ufms.facom.progweb.repositories.EmprestimoRepository;
import br.ufms.facom.progweb.repositories.LivroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmprestimoService {
    private final EmprestimoRepository emprestimoRepository;
    private final LivroRepository livroRepository;

    public EmprestimoService(EmprestimoRepository emprestimoRepository, LivroRepository livroRepository) {
        this.emprestimoRepository = emprestimoRepository;
        this.livroRepository = livroRepository;
    }

    public Emprestimo emprestarLivro(Long usuarioId, Long livroId, int dias) {
        Livro livro = livroRepository.findById(livroId)
                .orElseThrow(() -> new RuntimeException("Livro não encontrado"));

        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setUsuarioId(usuarioId);
        emprestimo.setLivroId(livroId);
        emprestimo.setDataEmprestimo(LocalDate.now());
        emprestimo.setDataDevolucao(LocalDate.now().plusDays(dias));

        // 🔢 cálculo proporcional: preço / 30 * dias
        double valor = (livro.getPreco() / 30) * dias;
        emprestimo.setValor(valor);

        return emprestimoRepository.save(emprestimo);
    }
}
