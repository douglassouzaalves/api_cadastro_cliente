package br.com.example.api.service;

import br.com.example.api.entity.Cliente;
import br.com.example.api.exception.ResourceNotFoundException;
import br.com.example.api.repository.ClienteRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@Service

//service usa o design facade?
public class ClienteService {

    final ClienteRepository clienteRepository;


    public Cliente save(Cliente cliente) {
        Optional<Cliente> clienteUpdate = clienteRepository.findById(cliente.getId());
        if (clienteUpdate.isPresent()) {
            return clienteRepository.save(cliente);
        } else {
            throw new ResourceNotFoundException("Não foi possivel realizar a atualização de cliente");
        }
    }

    public List<Cliente> listClient() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> findById(Long id) {
        Optional<Cliente> clienteById = clienteRepository.findById(id);
        if (clienteById.isPresent()) {
            return clienteRepository.findById(id);
        } else {
            throw new ResourceNotFoundException("Id não encontrado");
        }
    }


    public void deleteId(Long id) {
        Optional<Cliente> clienteDelete = clienteRepository.findById(id);
        if (clienteDelete.isPresent()) {
            clienteRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException("Este id não existe");
        }

    }

}
