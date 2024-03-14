package taskflowapi.infra.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.domain.model.Tarefa;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@Sql(scripts = {
        "classpath:/scripts/pessoa.sql",
        "classpath:/scripts/tarefa.sql",
})
@DataJpaTest
class TarefaJpaRepositoryImplTest {

    @Autowired
    private TarefaJpaRepository repository;

    @Test
    void getTopByDepartamento_deveRetornarTarefa_quandoSolicitado() {
        assertThat(repository.getTopByDepartamento(EDepartamento.FINANCAS))
                .isInstanceOf(Tarefa.class)
                .isNotNull();
    }

    @Test
    void getTopByDepartamento_naoDeveRetornarTarefa_quandoNaoTiverTarefas() {
        assertThat(repository.getTopByDepartamento(EDepartamento.DESENVOLVIMENTO))
                .isNull();
    }

    @Test
    void getTarefasAntigasPendentes_deveRetornarListaDeTarefaResponse_quandoSolicitado() {
        assertThat(repository.getTarefasAntigasPendentes())
                .isInstanceOf(List.class)
                .isNotNull();
    }
}
