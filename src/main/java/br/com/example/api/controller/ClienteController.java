package br.com.example.api.controller;

import br.com.example.api.entity.Cliente;
import br.com.example.api.exception.ResourceNotFoundException;
import br.com.example.api.service.ClienteService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/cliente")
public class ClienteController {

    final ClienteService clienteService;

    final ModelMapper modelMapper;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public Cliente salvar(@Valid @RequestBody Cliente cliente) {
        return clienteService.save(cliente);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<Cliente> listaCliente() {
        return clienteService.listClient();
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/{id}")
    public Cliente buscarClientePorId(@PathVariable("id") Long id) {
        return clienteService.findById(id).get();
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void removerCliente(@PathVariable("id") Long id) {
        clienteService.findById(id)
                .map(cliente -> {
                    clienteService.deleteId(cliente.getId());
                    return Void.TYPE;
                });
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void atualizarCliente(@PathVariable("id") Long id, @RequestBody Cliente cliente) {
        clienteService.findById(id)
                .map(clienteBase -> {
                    modelMapper.map(cliente, clienteBase);
                    clienteService.save(clienteBase);
                    return Void.TYPE;
                }).orElseThrow(() -> new ResourceNotFoundException("Não foi possível atualizar este cliente"));
    }
}
