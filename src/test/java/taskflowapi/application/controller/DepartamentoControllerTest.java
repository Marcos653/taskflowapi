package taskflowapi.application.controller;

import taskflowapi.application.controller.DepartamentoController;
import taskflowapi.domain.service.contract.IDepartamentoService;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import lombok.SneakyThrows;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DepartamentoController.class)
class DepartamentoControllerTest {

    private static final String API_URL = "/api/departamentos";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private IDepartamentoService service;

    @Test
    @SneakyThrows
    void findAllDepartamento_deveRetornarOk_quandoSolicitado() {
        mockMvc.perform(get(API_URL))
                .andExpect(status().isOk());
    }
}
