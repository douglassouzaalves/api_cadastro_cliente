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
public class ClienteService {

    final ClienteRepository clienteRepository;

    public Cliente create(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente save(Cliente cliente, Long id) {
        Optional<Cliente> clienteUpdate = clienteRepository.findById(id);
        if (clienteUpdate.isPresent()) {
            cliente.setId(id);
            return clienteRepository.save(cliente);
        } else {
            throw new ResourceNotFoundException("Não foi possivel atualizar o cliente");
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
        }

    }

}
