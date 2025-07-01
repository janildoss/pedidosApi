package com.api.pedidosApi.services;

import com.api.pedidosApi.DTO.EnderecoDTO;
import com.api.pedidosApi.Repositories.CidadeRepository;
import com.api.pedidosApi.Repositories.ClienteRepository;
import com.api.pedidosApi.Repositories.EnderecoRepository;
import com.api.pedidosApi.models.Cidade;
import com.api.pedidosApi.models.Cliente;
import com.api.pedidosApi.models.Endereco;
import com.api.pedidosApi.models.Estado;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {
    @Autowired
    private EnderecoRepository enderecoRepository;
    @Autowired
    private ClienteRepository clienteRepository;
    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Endereco> findAll() {
        return enderecoRepository.findAll() ;
    }

    public Endereco findById(Integer id) {
        Optional<Endereco> endereco = enderecoRepository.findById(id);
        return endereco.orElseThrow(() -> new ObjectNotFoundException(
                "Endereco não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
    }

    /*public Endereco inserirEndereco(Endereco endereco){
        endereco.setId(null);
        return enderecoRepository.save(endereco);
    }*/

    public Endereco inserirEndereco(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco fromDTO(EnderecoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Cidade cidade = cidadeRepository.findById(dto.getCidadeId())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        return new Endereco(
                null,
                dto.getLogradouro(),
                dto.getNumero(),
                dto.getComplemento(),
                dto.getBairro(),
                cliente,
                cidade,
                dto.getCep()
        );
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteEnderecoById(Integer id) {
        if (!enderecoRepository.existsById(id)) {
            throw new ObjectNotFoundException("Endereco não encontrada. Id: " + id);
        }
        enderecoRepository.deleteById(id);
    }

    public Endereco atualizar(Integer id, EnderecoDTO dto) {
        Endereco existente = enderecoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Endereço com ID " + id + " não encontrado"));

        Cliente cliente = clienteRepository.findById(dto.getClienteId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        Cidade cidade = cidadeRepository.findById(dto.getCidadeId())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));

        existente.setLogradouro(dto.getLogradouro());
        existente.setNumero(dto.getNumero());
        existente.setComplemento(dto.getComplemento());
        existente.setBairro(dto.getBairro());
        existente.setCep(dto.getCep());
        existente.setCliente(cliente);
        existente.setCidade(cidade);

        return enderecoRepository.save(existente);
    }
   /* public Endereco update(Integer id, Endereco endereco) {
        Optional<Endereco> enderecoExistente = enderecoRepository.findById(id);

        if (enderecoExistente.isPresent()) {
            Endereco end = enderecoExistente.get();
            end.setLogradouro(endereco.getLogradouro());
            end.setNumero(endereco.getNumero());
            end.setComplemento(endereco.getComplemento());
            end.setBairro(endereco.getBairro());
            end.setCep(endereco.getCep());

            return enderecoRepository.save(end);
        } else {
            throw new ObjectNotFoundException("Endereco com id " + id + " não encontrada");
        }
    }*/

}

