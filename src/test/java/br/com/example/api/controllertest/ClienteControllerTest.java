package br.com.example.api.controllertest;


import br.com.example.api.controller.ClienteController;
import br.com.example.api.entity.Cliente;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ClienteControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    ClienteController clienteController;

    @Test
    void test_GetCliente_Successful() throws Exception {
        mockMvc.perform(get("/cliente/1"))
                .andExpect(status().isOk());
    }

    @Test
    void test_Post() throws Exception {

        Cliente cliente = Cliente.builder()
                .id(1L)
                .email("teste@teste.com")
                .nome("Teste")
                .cpf("03204080005")
                .build();


        mockMvc.perform(post("/cliente")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(cliente)))
                .andExpect(status().isCreated());
    }

    @Test
    void test_Delete() throws Exception {
        mockMvc.perform(delete("/cliente/11"))
                .andExpect(status().isOk());
    }
}
