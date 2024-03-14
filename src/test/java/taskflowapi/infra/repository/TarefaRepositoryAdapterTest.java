package taskflowapi.infra.repository;

import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.model.Tarefa;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.dto.response.TarefaResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.HashSet;
import java.util.Optional;
import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static taskflowapi.helper.TarefaHelper.*;
import static taskflowapi.helper.PessoaHelper.umaPessoa;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TarefaRepositoryAdapterTest {

    private final long tarefaId = 1L;
    private final long pessoaId = 1L;

    @InjectMocks
    private TarefaRepositoryAdapter tarefaRepositoryAdapter;
    @Mock
    private TarefaJpaRepository tarefaJpaRepository;

    private Tarefa tarefa;
    private Pessoa pessoa;
    private List<TarefaResponse> listaTarefaResponse;

    @BeforeEach
    void setUp() {
        listaTarefaResponse = umaListaTarefaResponse();
        pessoa = umaPessoa(pessoaId, "Marcos", EDepartamento.DESENVOLVIMENTO, new HashSet<>());
        tarefa = umaTarefa(tarefaId, "Titulo da Tarefa", "Descrição da Tarefa",
                LocalDateTime.now(), EDepartamento.DESENVOLVIMENTO, 2.0, pessoa, false);
    }

    @Test
    void findById_deveDelegarParaTarefaJpaRepository_quandoSolicitado() {
        when(tarefaJpaRepository.findById(tarefaId))
                .thenReturn(Optional.of(tarefa));

        var resultado = tarefaRepositoryAdapter.findById(tarefaId);

        assertEquals(Optional.of(tarefa), resultado);
        verify(tarefaJpaRepository).findById(tarefaId);
    }

    @Test
    void getTopByDepartamento_deveDelegarParaTarefaJpaRepository_quandoSolicitado() {
        when(tarefaJpaRepository.getTopByDepartamento(EDepartamento.DESENVOLVIMENTO))
                .thenReturn(tarefa);

        var resultado = tarefaRepositoryAdapter.getTopByDepartamento(EDepartamento.DESENVOLVIMENTO);

        assertEquals(tarefa, resultado);
        verify(tarefaJpaRepository).getTopByDepartamento(EDepartamento.DESENVOLVIMENTO);
    }

    @Test
    void getTarefasAntigasPendentes_deveDelegarParaTarefaJpaRepository_quandoSolicitado() {
        when(tarefaJpaRepository.getTarefasAntigasPendentes())
                .thenReturn(listaTarefaResponse);

        var resultado = tarefaRepositoryAdapter.getTarefasAntigasPendentes();

        assertThat(resultado)
                .usingRecursiveFieldByFieldElementComparatorOnFields("id", "titulo", "descricao",
                        "departamento", "duracao", "pessoaId", "finalizado")
                .containsExactlyElementsOf(listaTarefaResponse);

        verify(tarefaJpaRepository).getTarefasAntigasPendentes();
    }

    @Test
    void save_deveDelegarParaTarefaJpaRepository_quandoSolicitado() {
        when(tarefaJpaRepository.save(tarefa))
                .thenReturn(tarefa);

        var resultado = tarefaRepositoryAdapter.save(tarefa);

        assertEquals(tarefa, resultado);
        verify(tarefaJpaRepository).save(tarefa);
    }
}
