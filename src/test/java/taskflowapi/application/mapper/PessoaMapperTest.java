package taskflowapi.application.mapper;

import taskflowapi.domain.model.Tarefa;
import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.dto.response.TarefaResponse;
import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.application.dto.response.PessoaResponse;
import org.mockito.Mockito;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.Set;
import java.util.HashSet;

import static org.mockito.Mockito.when;
import static org.mockito.ArgumentMatchers.any;
import static taskflowapi.helper.PessoaHelper.*;
import static taskflowapi.helper.TarefaHelper.*;
import static org.assertj.core.api.Assertions.assertThat;

class PessoaMapperTest {

    private final PessoaMapper mapper = new PessoaMapperImpl();
    private final long pessoaId = 1L;
    private final int indexInicial = 0;

    @MockBean
    private TarefaMapper tarefaMapper;

    private Pessoa pessoa;
    private PessoaRequest pessoaRequest;
    private PessoaResponse pessoaResponse;
    private Set<TarefaResponse> tarefaResponseSet;
    private Set<Tarefa> tarefaSet;

    @BeforeEach
    void setUp() {
        tarefaResponseSet = umSetTarefaResponse();
        tarefaSet = umSetTarefa();
        pessoa = umaPessoa(pessoaId, "Marcos", EDepartamento.DESENVOLVIMENTO, new HashSet<>());
        pessoaRequest = umaPessoaRequest("Marcos", EDepartamento.DESENVOLVIMENTO);
        pessoaResponse = umaPessoaResponse(pessoaId, "Marcos", EDepartamento.DESENVOLVIMENTO, new HashSet<>());
    }

    @Test
    void convertToPessoaResponse_deveConverterParaPessoaResponse_quandoSolicitado() {
        assertThat(mapper.convertToPessoaResponse(pessoa))
                .isEqualTo(pessoaResponse);
    }

    @Test
    void convertToPessoaResponse_naoDeveConverterParaPessoaResponse_quandoPessoaForNula() {
        assertThat(mapper.convertToPessoaResponse(null))
                .isNull();
    }

    @Test
    void convertToPessoa_deveConverterParaPessoa_quandoSolicitado() {
        assertThat(mapper.convertToPessoa(pessoaRequest))
                .extracting("nome", "departamento")
                .containsExactly(pessoa.getNome(), pessoa.getDepartamento());
    }

    @Test
    void convertToPessoa_naoDeveConverterParaPessoa_quandoPessoaRequestForNulo() {
        assertThat(mapper.convertToPessoa(null))
                .isNull();
    }

    @Test
    void tarefasToTarefasResponse_deveConverterParaSetTarefaResponse_quandoSolicitado() {
        tarefaMapper = Mockito.mock(TarefaMapper.class);
        ReflectionTestUtils.setField(mapper, "tarefaMapper", tarefaMapper);

        when(tarefaMapper.convertToTarefaResponse(any(Tarefa.class)))
                .thenAnswer(invocation -> {
                    Tarefa tarefa = invocation.getArgument(indexInicial);
                    return new TarefaResponse(tarefa.getId(), tarefa.getTitulo(), tarefa.getDescricao(),
                            tarefa.getPrazo(), tarefa.getDepartamento(),
                            tarefa.getDuracao(), tarefa.getPessoa() != null ? tarefa.getPessoa().getId() : null,
                            tarefa.getFinalizado());
                });

        assertThat(mapper.tarefasToTarefasResponse(tarefaSet))
                .usingRecursiveComparison()
                .ignoringFields("prazo")
                .isEqualTo(tarefaResponseSet);
    }

    @Test
    void tarefasToTarefasResponse_naodeveConverterParaSetTarefaResponse_quandoTarefasForemNulos() {
        assertThat(mapper.tarefasToTarefasResponse(null))
                .isNull();
    }
}
