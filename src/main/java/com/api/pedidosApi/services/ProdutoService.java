package com.api.pedidosApi.services;

import com.api.pedidosApi.models.Categoria;
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
    //@Transactional
    public Produto findOne(Integer id) {
        Optional<Produto> obj = produtoRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "Produto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
    }

    public Produto insert(Produto obj){
        obj.setId(null);
        return produtoRepository.save(obj);
    }

   public Produto update(Integer id, Produto produtoatualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isPresent()) {
            Produto produto = produtoExistente.get();
            produto.setNome(produtoatualizado.getNome());
            return produtoRepository.save(produto);
        } else {
            throw new ObjectNotFoundException("Produto  não encontrado impossivel atualizar registro com o  ID: : " + id);
        }
    }

    public boolean deleteProdutoById(Integer id) {
        if (!produtoRepository.existsById(id)) {
            throw new ObjectNotFoundException("Produto  não encontrado impossivel excluir registro com o  ID: : " + id);
        }
        produtoRepository.deleteById(id);
        return true;
    }

}
