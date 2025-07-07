package com.api.pedidosApi.services;

import com.api.pedidosApi.Repositories.ProdutoRepository;
import com.api.pedidosApi.models.Produto;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
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

    public Produto inserirProduto(Produto produto){
        produto.setId(null);
        return produtoRepository.save(produto);
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteProdutoById(Integer id) {
        if (!produtoRepository.existsById(id)) {
            throw new ObjectNotFoundException("Produto não encontrada. Id: " + id);
        }
        produtoRepository.deleteById(id);
    }

   public Produto update(Integer id, Produto produto) {
       Produto existente = produtoRepository.findById(id)
               .orElseThrow(() ->  new ObjectNotFoundException("Produto com id " + id + " não encontrada"));

       existente.setNome(produto.getNome());
       existente.setPreco(produto.getPreco());

       return produtoRepository.save(existente);
   }

}

