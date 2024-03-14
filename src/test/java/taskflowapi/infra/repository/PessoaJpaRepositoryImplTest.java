package taskflowapi.infra.repository;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static taskflowapi.helper.PessoaHelper.umPessoaFiltro;
import static taskflowapi.helper.PessoaHelper.umPessoaFiltroComNomeInexistente;

@Sql(scripts = {
        "classpath:/scripts/pessoa.sql",
        "classpath:/scripts/tarefa.sql",
})
@DataJpaTest
class PessoaJpaRepositoryImplTest {

    @Autowired
    private PessoaJpaRepository repository;

    @Test
    void getPessoasByNomeEPeriodo_deveRetornarSetDePessoaMediaHorasTrabalhadas_quandoSolicitado() {
        assertThat(repository.getPessoasByNomeEPeriodo(umPessoaFiltro().createPredicate()))
                .isInstanceOf(Set.class)
                .isNotEmpty();
    }

    @Test
    void getPessoasByNomeEPeriodo_naoDeveRetornarSetDePessoaMediaHorasTrabalhadas_quandoNomeInexistente() {
        assertThat(repository.getPessoasByNomeEPeriodo(umPessoaFiltroComNomeInexistente().createPredicate()))
                .isInstanceOf(Set.class)
                .isEmpty();
    }

    @Test
    void findAllDepartamento_deveRetornarListaDeDepartamentoResponse_quandoSolicitado() {
        assertThat(repository.findAllDepartamento())
                .isInstanceOf(List.class)
                .isNotEmpty();
    }
}
