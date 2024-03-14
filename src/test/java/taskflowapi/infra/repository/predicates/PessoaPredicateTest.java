package taskflowapi.infra.repository.predicates;

import com.querydsl.core.BooleanBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.time.LocalDateTime;

import static taskflowapi.domain.model.QPessoa.pessoa;
import static taskflowapi.domain.model.QTarefa.tarefa;
import static org.assertj.core.api.Assertions.assertThat;

class PessoaPredicateTest {

    private PessoaPredicate pessoaPredicate;
    private BooleanBuilder booleanBuilder;

    @BeforeEach
    void setUp() {
        pessoaPredicate = new PessoaPredicate();
        booleanBuilder = new BooleanBuilder();
    }

    @Test
    void comNome_deveMontarPredicate_quandoInformarNome() {
        var nome = "Teste";

        assertThat(pessoaPredicate.comNome(nome)
                .build())
                .isEqualTo(booleanBuilder.and(pessoa.nome.likeIgnoreCase("%" + nome + "%")));
    }

    @Test
    void comNome_naoDeveMontarPredicate_quandoInformarNomeNull() {
        assertThat(pessoaPredicate.comNome(null)
                .build())
                .isEqualTo(booleanBuilder);
    }

    @Test
    void noPeriodo_deveMontarPredicate_quandoInformarPeriodo() {
        var inicio = LocalDateTime.now().minusDays(10);
        var fim = LocalDateTime.now();

        assertThat(pessoaPredicate.noPeriodo(inicio, fim)
                .build())
                .isEqualTo(booleanBuilder.and(tarefa.prazo.between(inicio, fim)));
    }

    @Test
    void noPeriodo_naoDeveMontarPredicate_quandoSomenteInicioNull() {
        var fim = LocalDateTime.now();

        assertThat(pessoaPredicate.noPeriodo(null, fim).build())
                .isEqualTo(booleanBuilder);
    }

    @Test
    void noPeriodo_naoDeveMontarPredicate_quandoSomenteFimNull() {
        var inicio = LocalDateTime.now().minusDays(10);

        assertThat(pessoaPredicate.noPeriodo(inicio, null).build())
                .isEqualTo(booleanBuilder);
    }

    @Test
    void noPeriodo_naoDeveMontarPredicate_quandoInformarInicioOuFimNull() {
        assertThat(pessoaPredicate.noPeriodo(null, null)
                .build())
                .isEqualTo(booleanBuilder);
    }
}
