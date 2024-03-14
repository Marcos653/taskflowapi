package taskflowapi.application.controller;

import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.domain.service.contract.IPessoaService;
import taskflowapi.application.controller.PessoaController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import lombok.SneakyThrows;

import jakarta.persistence.EntityNotFoundException;

import static org.mockito.Mockito.doThrow;
import static taskflowapi.helper.PessoaHelper.*;
import static taskflowapi.helper.TestsHelper.convertObjectToJsonBytes;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PessoaController.class)
class PessoaControllerTest {

    private static final String API_URL = "/api/pessoas";
    private final long pessoaId = 1;
    private final long inexistenteId = 100;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IPessoaService service;

    private PessoaRequest pessoaRequest;

    @BeforeEach
    void setUp() {
        pessoaRequest = umaPessoaRequest("Marcos", EDepartamento.DESENVOLVIMENTO);
    }

    @Test
    @SneakyThrows
    void getAllPessoas_deveRetornarOk_quandoSolicitado() {
        mockMvc.perform(get(API_URL))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void getPessoasByNomeEPeriodo_deveRetornarOk_quandoSolicitado() {
        mockMvc.perform(get(API_URL + "/gastos")
                        .param("nome", "Marcos")
                        .param("inicio", "2024-01-15T09:00:00")
                        .param("fim", "2024-01-16T09:00:00"))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void getPessoasByNomeEPeriodo_deveRetornarBadRequest_quandoNomeVazio() {
        mockMvc.perform(get(API_URL + "/gastos")
                        .param("nome", "")
                        .param("inicio", "2024-01-15T09:00:00")
                        .param("fim", "2024-01-16T09:00:00"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void getPessoasByNomeEPeriodo_deveRetornarBadRequest_quandoInicioAusente() {
        mockMvc.perform(get(API_URL + "/gastos")
                        .param("nome", "Marcos")
                        .param("fim", "2024-01-16T09:00:00"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void getPessoasByNomeEPeriodo_deveRetornarBadRequest_quandoFimAusente() {
        mockMvc.perform(get(API_URL + "/gastos")
                        .param("nome", "Marcos")
                        .param("inicio", "2024-01-15T09:00:00"))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void save_deveRetornarOk_quandoSolicitado() {
        mockMvc.perform(post(API_URL)
                        .content(convertObjectToJsonBytes(pessoaRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void save_deveRetornarBadRequest_quandoPessoaRequestInvalido() {
        var pessoaRequestInvalido = umaPessoaRequest("Invalido", null);

        mockMvc.perform(post(API_URL)
                        .content(convertObjectToJsonBytes(pessoaRequestInvalido))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void update_deveRetornarOk_quandoSolicitado() {
        mockMvc.perform(put(API_URL + "/{id}", pessoaId)
                        .content(convertObjectToJsonBytes(pessoaRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void update_deveRetornarNotFound_quandoPessoaNaoEncontrada() {
        doThrow(new EntityNotFoundException())
                .when(service).update(inexistenteId, pessoaRequest);

        mockMvc.perform(put(API_URL + "/{id}", inexistenteId)
                        .content(convertObjectToJsonBytes(pessoaRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void update_deveRetornarBadRequest_quandoPessoaRequestInvalido() {
        var pessoaRequestInvalido = umaPessoaRequest("Invalido", null);

        mockMvc.perform(put(API_URL + "/{id}", pessoaId)
                        .content(convertObjectToJsonBytes(pessoaRequestInvalido))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void deleteById_deveRetornarNoContent_quandoSolicitado() {
        mockMvc.perform(delete(API_URL + "/{id}", pessoaId))
                .andExpect(status().isNoContent());
    }

    @Test
    @SneakyThrows
    void deleteById_deveRetornarNotFound_quandoPessoaNaoEncontrada() {
        doThrow(new EntityNotFoundException())
                .when(service).deleteById(inexistenteId);

        mockMvc.perform(delete(API_URL + "/{id}", pessoaId))
                .andExpect(status().isNoContent());
    }
}
