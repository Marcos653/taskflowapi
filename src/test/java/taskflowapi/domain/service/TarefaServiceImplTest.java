package taskflowapi.domain.service;

import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.model.Tarefa;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.mapper.TarefaMapper;
import taskflowapi.domain.service.TarefaServiceImpl;
import taskflowapi.domain.repository.ITarefaRepository;
import taskflowapi.application.dto.request.TarefaRequest;
import taskflowapi.domain.service.contract.IPessoaService;
import taskflowapi.application.dto.response.TarefaResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;
import java.time.LocalDateTime;
import jakarta.persistence.EntityNotFoundException;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static taskflowapi.domain.utils.MensagemConstantes.TAREFA_ID_NOT_FOUND;
import static taskflowapi.helper.TarefaHelper.*;
import static taskflowapi.helper.PessoaHelper.umaPessoa;
import static taskflowapi.domain.utils.MensagemConstantes.PESSOA_ID_NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class TarefaServiceImplTest {

    private final long pessoaId = 1L;
    private final long tarefaId = 1L;
    private final long inexistenteId = 100L;

    @InjectMocks
    private TarefaServiceImpl tarefaService;
    @Mock
    private ITarefaRepository tarefaRepository;
    @Mock
    private IPessoaService pessoaService;
    @Mock
    private TarefaMapper mapper;

    private Tarefa tarefa;
    private TarefaRequest tarefaRequest;
    private TarefaResponse tarefaResponse;
    private Pessoa pessoa;
    private List<TarefaResponse> listaTarefaResponse;

    @BeforeEach
    void setUp() {
        listaTarefaResponse = umaListaTarefaResponse();
        pessoa = umaPessoa(pessoaId, "Marcos", EDepartamento.DESENVOLVIMENTO, new HashSet<>());
        tarefa = umaTarefa(tarefaId, "Titulo da Tarefa", "Descrição da Tarefa",
                LocalDateTime.now(), EDepartamento.DESENVOLVIMENTO, 2.0, pessoa, false);
        tarefaRequest = umaTarefaRequest("Titulo da Tarefa", "Descrição da Tarefa",
                LocalDateTime.now(), EDepartamento.DESENVOLVIMENTO, 2.0);
        tarefaResponse = umaTarefaResponse(tarefaId, "Titulo da Tarefa", "Descrição da Tarefa",
                LocalDateTime.now(), EDepartamento.DESENVOLVIMENTO, 2.0, pessoaId, false);
    }

    @Test
    void save_deveRetornarTarefaResponse_quandoSalva() {
        when(tarefaRepository.save(tarefa))
                .thenReturn(tarefa);
        when(mapper.convertToTarefa(tarefaRequest))
                .thenReturn(tarefa);
        when(mapper.convertToTarefaResponse(tarefa))
                .thenReturn(tarefaResponse);

        var resultado = tarefaService.save(tarefaRequest);

        assertThat(resultado)
                .isInstanceOf(TarefaResponse.class)
                .isNotNull();

        verify(mapper).convertToTarefa(tarefaRequest);
        verify(tarefaRepository).save(tarefa);
        verify(mapper).convertToTarefaResponse(tarefa);
    }

    @Test
    void alocar_deveRetornarTarefaResponse_quandoAlocar() {
        when(pessoaService.getPessoaById(pessoaId))
                .thenReturn(pessoa);
        when(tarefaRepository.getTopByDepartamento(pessoa.getDepartamento()))
                .thenReturn(tarefa);
        when(tarefaRepository.save(tarefa))
                .thenReturn(tarefa);
        when(mapper.convertToTarefaResponse(tarefa))
                .thenReturn(tarefaResponse);

        var resultado = tarefaService.alocar(pessoaId);

        assertThat(resultado)
                .isInstanceOf(TarefaResponse.class)
                .isNotNull();

        verify(pessoaService).getPessoaById(pessoaId);
        verify(mapper).convertToTarefaResponse(tarefa);
        verify(tarefaRepository).save(tarefa);
    }

    @Test
    void alocar_deveLancarException_quandoPessoaNaoEncontrada() {
        when(pessoaService.getPessoaById(inexistenteId))
                .thenThrow(new EntityNotFoundException(PESSOA_ID_NOT_FOUND + inexistenteId));

        assertThatThrownBy(() -> tarefaService.alocar(inexistenteId))
                .isInstanceOf(EntityNotFoundException.class)
                .hasMessageContaining(PESSOA_ID_NOT_FOUND + inexistenteId);

        verify(pessoaService).getPessoaById(inexistenteId);
        verifyNoInteractions(tarefaRepository);
        verifyNoInteractions(mapper);
    }

    @Test
    void finalizar_deveRetornarTarefaResponse_quandoFinalizada() {
        when(tarefaRepository.findById(tarefaId))
                .thenReturn(Optional.of(tarefa));
        when(tarefaRepository.save(tarefa))
                .thenReturn(tarefa);
        when(mapper.convertToTarefaResponse(tarefa))
                .thenReturn(tarefaResponse);

        var resultado = tarefaService.finalizar(pessoaId);

        assertThat(resultado)
                .isInstanceOf(TarefaResponse.class)
                .isNotNull();

        verify(tarefaRepository).findById(tarefaId);
        verify(mapper).convertToTarefaResponse(tarefa);
        verify(tarefaRepository).save(tarefa);
    }

    @Test
    void finalizar_deveLancarException_quandoTarefaNaoEncontrada() {
        when(tarefaRepository.findById(inexistenteId))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> tarefaService.finalizar(inexistenteId))
                .withMessage(TAREFA_ID_NOT_FOUND + inexistenteId);

        verify(tarefaRepository).findById(inexistenteId);
        verifyNoInteractions(mapper);
        verify(tarefaRepository, never()).save(tarefa);
    }

    @Test
    void getTarefasAntigasPendentes_deveRetornarListaDeTarefaResponse_quandoSolicitado() {
        when(tarefaRepository.getTarefasAntigasPendentes())
                .thenReturn(listaTarefaResponse);

        var resultado = tarefaService.getTarefasAntigasPendentes();

        assertThat(resultado)
                .isInstanceOf(List.class)
                .isNotNull();

        verify(tarefaRepository).getTarefasAntigasPendentes();
    }

    @Test
    void getTarefasAntigasPendentes_deveRetornarVazio_quandoNaoTiverTarefasPendentes() {
        when(tarefaRepository.getTarefasAntigasPendentes())
                .thenReturn(Collections.emptyList());

        var resultado = tarefaService.getTarefasAntigasPendentes();

        assertThat(resultado)
                .isInstanceOf(List.class)
                .isEmpty();

        verify(tarefaRepository).getTarefasAntigasPendentes();
    }
}
