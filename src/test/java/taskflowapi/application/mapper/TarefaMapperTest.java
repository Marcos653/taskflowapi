package taskflowapi.application.mapper;

import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.model.Tarefa;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.dto.request.TarefaRequest;
import taskflowapi.application.dto.response.TarefaResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.HashSet;
import java.time.LocalDateTime;

import static taskflowapi.helper.PessoaHelper.*;
import static taskflowapi.helper.TarefaHelper.*;
import static taskflowapi.helper.TarefaHelper.umaTarefaResponse;
import static org.assertj.core.api.Assertions.assertThat;

class TarefaMapperTest {

    private TarefaMapper mapper = new TarefaMapperImpl();
    private final long pessoaId = 1L;
    private final long tarefaId = 1L;

    private Tarefa tarefa;
    private TarefaRequest tarefaRequest;
    private TarefaResponse tarefaResponse;
    private Pessoa pessoa;

    @BeforeEach
    void setUp() {
        pessoa = umaPessoa(pessoaId, "Marcos", EDepartamento.DESENVOLVIMENTO, new HashSet<>());
        tarefa = umaTarefa(tarefaId, "Titulo da Tarefa", "Descrição da Tarefa",
                LocalDateTime.now(), EDepartamento.DESENVOLVIMENTO, 2.0, pessoa, false);
        tarefaRequest = umaTarefaRequest("Titulo da Tarefa", "Descrição da Tarefa",
                LocalDateTime.now(), EDepartamento.DESENVOLVIMENTO, 2.0);
        tarefaResponse = umaTarefaResponse(tarefaId, "Titulo da Tarefa", "Descrição da Tarefa",
                LocalDateTime.now(), EDepartamento.DESENVOLVIMENTO, 2.0, pessoaId, false);
    }

    @Test
    void convertToTarefaResponse_deveConverterParaTarefaResponse_quandoSolicitado() {
        assertThat(mapper.convertToTarefaResponse(tarefa))
                .usingRecursiveComparison()
                .ignoringFields("prazo")
                .isEqualTo(tarefaResponse);
    }

    @Test
    void convertToTarefaResponse_naoDeveConverterParaTarefaResponse_quandoTarefaForNulo() {
        assertThat(mapper.convertToTarefaResponse(null))
                .isNull();
    }

    @Test
    void convertToTarefaResponse_deveDefinirZeroParaCampoDuracao_quandoCampoDuracaoForNulo() {
        tarefa.setDuracao(null);
        assertThat(mapper.convertToTarefaResponse(tarefa))
                .extracting( "duracao")
                .isEqualTo(0.0);
    }

    @Test
    void convertToTarefaResponse_deveDefinirNullParaCampoPessoaId_quandoPessoaNaoAssociada() {
        var tarefaSemPessoa = umaTarefa(tarefaId, "Titulo da Tarefa", "Descrição da Tarefa",
                LocalDateTime.now(), EDepartamento.DESENVOLVIMENTO, 2.0, null, false);
        var response = mapper.convertToTarefaResponse(tarefaSemPessoa);

        assertThat(response.pessoaId()).isNull();
    }

    @Test
    void convertToTarefaResponse_deveDefinirNullParaCampoPessoaId_quandoPessoaSemId() {
        pessoa.setId(null);

        var tarefaSemPessoa = umaTarefa(tarefaId, "Titulo da Tarefa", "Descrição da Tarefa",
                LocalDateTime.now(), EDepartamento.DESENVOLVIMENTO, 2.0, pessoa, false);
        var response = mapper.convertToTarefaResponse(tarefaSemPessoa);

        assertThat(response.pessoaId()).isNull();
    }

    @Test
    void convertToTarefa_deveConverterParaTarefa_quandoSolicitado() {
        assertThat(mapper.convertToTarefa(tarefaRequest))
                .extracting("titulo", "descricao", "departamento", "duracao")
                .containsExactly(tarefa.getTitulo(), tarefa.getDescricao(), tarefa.getDepartamento(), tarefa.getDuracao());
    }

    @Test
    void convertToTarefa_naoDeveConverterParaTarefa_quandoTarefaRequestForNulo() {
        assertThat(mapper.convertToTarefa(null))
                .isNull();
    }
}
