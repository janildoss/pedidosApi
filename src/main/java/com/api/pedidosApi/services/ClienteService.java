package com.api.pedidosApi.services;

import com.api.pedidosApi.DTO.ClienteNewDTO;
import com.api.pedidosApi.Enums.TipoCliente;
import com.api.pedidosApi.Repositories.ClienteRepository;
import com.api.pedidosApi.models.Cidade;
import com.api.pedidosApi.models.Cliente;
import com.api.pedidosApi.models.Endereco;
import com.api.pedidosApi.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> findAll() {
        return clienteRepository.findAll();
    }

    public Cliente findById(Integer id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException(
                "Cliente não encontrada! Id: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteClienteById(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw new ObjectNotFoundException("Cliente não encontrado. Id: " + id);
        }
        clienteRepository.deleteById(id);
    }

    public Cliente updateCliente(Integer id, Cliente cliente) {
        Optional<Cliente> clienteExistente = clienteRepository.findById(id);

        if (clienteExistente.isPresent()) {
            Cliente cli = clienteExistente.get();
            cli.setNome(cliente.getNome());
            cli.setEmail(cliente.getEmail());
            cli.setCpfOuCnpj(cliente.getCpfOuCnpj());

            return clienteRepository.save(cli);
        } else {
            throw new ObjectNotFoundException("Cliente com id " + id + " não encontrado portanto não pode ser atualizada!");
        }
    }

    public Cliente inserirCliente(Cliente cliente){
        cliente.setId(null);
        return clienteRepository.save(cliente);
    }

    public Cliente fromDTO(ClienteNewDTO dto) {
        Cliente cli = new Cliente(null, dto.getNome(), dto.getEmail(), dto.getCpfOuCnpj(), TipoCliente.toEnum(dto.getTipo()));

        cli.getTelefones().add(dto.getTelefone1());
        if (dto.getTelefone2() != null) cli.getTelefones().add(dto.getTelefone2());
        if (dto.getTelefone3() != null) cli.getTelefones().add(dto.getTelefone3());

        return cli;
    }

}
