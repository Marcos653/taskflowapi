package taskflowapi.infra.repository;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLTemplates;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import taskflowapi.application.dto.response.DepartamentoResponse;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.querydsl.core.group.GroupBy.groupBy;
import static taskflowapi.domain.model.QPessoa.pessoa;
import static taskflowapi.domain.model.QTarefa.tarefa;

@RequiredArgsConstructor
public class PessoaJpaRepositoryImpl implements PessoaJpaRepositoryCustom {

    private final EntityManager entityManager;

    @Override
    public Set<PessoaMediaHorasTrabalhadas> getPessoasByNomeEPeriodo(Predicate predicate) {
        return new HashSet<>(new JPAQueryFactory(entityManager)
                .select(createPessoaMediaHorasTrabalhadas())
                .from(pessoa)
                .leftJoin(pessoa.tarefas, tarefa).on(tarefa.finalizado.isTrue())
                .where(predicate)
                .groupBy(pessoa.id, pessoa.nome, pessoa.departamento)
                .fetch());
    }

    @Override
    public List<DepartamentoResponse> findAllDepartamento() {
        return new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager)
                .selectFrom(pessoa)
                .leftJoin(tarefa).on(pessoa.departamento.eq(tarefa.departamento))
                .groupBy(pessoa.departamento)
                .transform(groupBy(pessoa.departamento)
                        .list(createDepartamentoResponse()));
    }

    private Expression<PessoaMediaHorasTrabalhadas> createPessoaMediaHorasTrabalhadas() {
        return Projections.constructor(PessoaMediaHorasTrabalhadas.class,
                pessoa.id,
                pessoa.nome,
                pessoa.departamento,
                tarefa.duracao.avg());
    }

    private Expression<DepartamentoResponse> createDepartamentoResponse() {
        return Projections.constructor(DepartamentoResponse.class,
                pessoa.departamento,
                pessoa.countDistinct(),
                tarefa.countDistinct());
    }
}
