package br.com.example.api.repositorytest;

import br.com.example.api.entity.Cliente;
import br.com.example.api.repository.ClienteRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;


import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DataJpaTest
@ExtendWith(SpringExtension.class)
@Sql(value = "/load-database.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
//usando pra me comunicar com a tabela
@TestPropertySource("classpath:application-test.properties")
@DisplayName("Tests for Cliente Repository")
class ClienteRepositoryTeste {


    @Autowired
    ClienteRepository clienteRepository;


    @Test
    @DisplayName("Test for List Client")
    void list_Cliente() {
        int size = clienteRepository.findAll().size();
        assertEquals(1, size);
    }

    @Test
    @DisplayName("Test Save Client")
    void save_Cliente() {
        Cliente cliente = new Cliente(1L, "teste", "teste", "teste");
        clienteRepository.save(cliente);
        int size = clienteRepository.findAll().size();
        assertEquals(1, size);

    }

    @Test
    @DisplayName("Test Delete Client")
    void delete_Id_Cliente() {
        clienteRepository.deleteById(1L);
        List<Cliente> listCliente = clienteRepository.findAll();
        assertEquals(0, listCliente.size());
    }

    @Test
    @DisplayName("Test Put Client")
    void put_Cliente() {
        Cliente cliente = createCliente();
        clienteRepository.findAll();
        Cliente byId = clienteRepository.getById(cliente.getId());
        assertEquals(1L, byId.getId());
    }

    @Test
    @DisplayName("Test Client Id Not Exist")
    void erro_Id_NotExist() {
        assertEquals(Optional.empty(), clienteRepository.findById(0L));
    }

    private Cliente createCliente() { //utilizado para os testes
        return Cliente.builder()
                .id(1L)
                .nome("Testando")
                .email("teste@teste.com")
                .cpf("000.000.000-05")
                .build();
    }


}
