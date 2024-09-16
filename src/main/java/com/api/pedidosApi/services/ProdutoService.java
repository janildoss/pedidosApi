package com.api.pedidosApi.services;

import com.api.pedidosApi.models.Produto;
import com.api.pedidosApi.repositories.ProdutoRepository;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> findAll() {
        return produtoRepository.findAll() ;
    }

    public Produto findById(Integer id) {
        Optional<Produto> produto = produtoRepository.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException(
                "Produto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public Produto insert(Produto produto){
        produto.setId(null);
        return produtoRepository.save(produto);
    }

    @Transactional
    public void deleteProdutoById(Integer id) {
        if (!produtoRepository.existsById(id)) {
            throw new ObjectNotFoundException("Produto não encontrada. Id: " + id);
        }
        produtoRepository.deleteById(id);
    }

    public Produto update(Integer id, Produto produto) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isPresent()) {
            Produto Produto = produtoExistente.get();
            produto.setNome(produto.getNome());
            return produtoRepository.save(produto);
        } else {
            throw new ObjectNotFoundException("Produto com id " + id + " não encontrada");
        }
    }

}
