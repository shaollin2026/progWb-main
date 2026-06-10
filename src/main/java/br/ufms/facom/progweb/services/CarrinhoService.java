package br.ufms.facom.progweb.services;

import br.ufms.facom.progweb.models.Carrinho;
import br.ufms.facom.progweb.repositories.CarrinhoRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoService {
    private final CarrinhoRepository carrinhoRepository;

    public CarrinhoService(CarrinhoRepository carrinhoRepository) {
        this.carrinhoRepository = carrinhoRepository;
    }

    public Carrinho criarCarrinho(Long usuarioId) {
        Carrinho carrinho = new Carrinho();
        carrinho.setUsuarioId(usuarioId);
        return carrinhoRepository.save(carrinho);
    }

    public Optional<Carrinho> buscarPorId(Long id) {
        return carrinhoRepository.findById(id);
    }

    public Carrinho salvar(Carrinho carrinho) {
        return carrinhoRepository.save(carrinho);
    }

    public void deletar(Long id) {
        carrinhoRepository.deleteById(id);
    }
}
