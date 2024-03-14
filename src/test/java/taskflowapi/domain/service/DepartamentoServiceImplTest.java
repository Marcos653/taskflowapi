package taskflowapi.domain.service;

import taskflowapi.domain.service.DepartamentoServiceImpl;
import taskflowapi.domain.repository.IDepartamentoRepository;
import taskflowapi.application.dto.response.DepartamentoResponse;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.assertj.core.api.Assertions.assertThat;
import static taskflowapi.helper.DepartamentoHelper.umaListaDepartamentoResponses;

@ExtendWith(MockitoExtension.class)
class DepartamentoServiceImplTest {

    @InjectMocks
    private DepartamentoServiceImpl departamentoService;
    @Mock
    private IDepartamentoRepository departamentoRepository;

    private List<DepartamentoResponse> departamentoResponses;

    @BeforeEach
    void setUp() {
        departamentoResponses = umaListaDepartamentoResponses();
    }

    @Test
    void findAllDepartamento_deveRetornarListaDeDepartamentosResponse_quandoSolicitado() {
        when(departamentoRepository.findAllDepartamento())
                .thenReturn(departamentoResponses);

        var resultado = departamentoService.findAllDepartamento();

        assertThat(resultado)
                .isInstanceOf(List.class)
                .isNotEmpty()
                .hasSize(departamentoResponses.size())
                .isEqualTo(departamentoResponses);

        verify(departamentoRepository).findAllDepartamento();
    }
}
