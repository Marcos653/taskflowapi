package taskflowapi.application.controller;

import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.dto.request.TarefaRequest;
import taskflowapi.domain.service.contract.ITarefaService;
import taskflowapi.application.controller.TarefaController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import lombok.SneakyThrows;

import java.time.LocalDateTime;
import jakarta.persistence.EntityNotFoundException;

import static org.mockito.Mockito.doThrow;
import static taskflowapi.helper.TarefaHelper.umaTarefaRequest;
import static taskflowapi.helper.TestsHelper.convertObjectToJsonBytes;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TarefaController.class)
class TarefaControllerTest {

    private static final String API_URL = "/api/tarefas";
    private final long tarefaId = 1;
    private final long pessoaId = 1;
    private final long inexistenteId = 100;

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private ITarefaService service;

    private TarefaRequest tarefaRequest;

    @BeforeEach
    void setUp() {
        tarefaRequest = umaTarefaRequest("Titulo da Tarefa", "Descrição da Tarefa",
                LocalDateTime.now(), EDepartamento.DESENVOLVIMENTO, 2.0);
    }

    @Test
    @SneakyThrows
    void save_deveRetornarOk_quandoSolicitado() {
        mockMvc.perform(post(API_URL)
                        .content(convertObjectToJsonBytes(tarefaRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void save_deveRetornarBadRequest_quandoTarefaRequestInvalida() {
        var tarefaRequestInvalida = umaTarefaRequest("Titulo Invalido", null,
                null, EDepartamento.DESENVOLVIMENTO, 2.0);

        mockMvc.perform(post(API_URL)
                        .content(convertObjectToJsonBytes(tarefaRequestInvalida))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    @SneakyThrows
    void alocar_deveRetornarOk_quandoSolicitado() {
        mockMvc.perform(put(API_URL + "/alocar/{pessoaId}", pessoaId))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void alocar_deveRetornarNotFound_quandoPessoaNaoEncontrada() {
        doThrow(new EntityNotFoundException())
                .when(service).alocar(inexistenteId);

        mockMvc.perform(put(API_URL + "/alocar/{pessoaId}", inexistenteId))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void finalizar_deveRetornarOk_quandoSolicitado() {
        mockMvc.perform(put(API_URL + "/finalizar/{tarefaId}", tarefaId))
                .andExpect(status().isOk());
    }

    @Test
    @SneakyThrows
    void finalizar_deveRetornarNotFound_quandoTarefaNaoEncontrada() {
        doThrow(new EntityNotFoundException())
                .when(service).finalizar(inexistenteId);

        mockMvc.perform(put(API_URL + "/finalizar/{tarefaId}", inexistenteId))
                .andExpect(status().isNotFound());
    }

    @Test
    @SneakyThrows
    void getTarefasAntigasPendentes_deveRetornarOk_quandoSolicitado() {
        mockMvc.perform(get(API_URL + "/pendentes"))
                .andExpect(status().isOk());
    }
}
