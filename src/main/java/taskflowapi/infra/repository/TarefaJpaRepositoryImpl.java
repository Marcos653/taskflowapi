package taskflowapi.infra.repository;

import taskflowapi.domain.model.Tarefa;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.dto.response.TarefaResponse;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import jakarta.persistence.EntityManager;

import static taskflowapi.domain.model.QTarefa.tarefa;

@RequiredArgsConstructor
public class TarefaJpaRepositoryImpl implements TarefaJpaRepositoryCustom {

    private final EntityManager entityManager;
    private final int limiteTarefasPendentes = 3;

    @Override
    public Tarefa getTopByDepartamento(EDepartamento departamento) {
        return new JPAQueryFactory(entityManager)
                .selectFrom(tarefa)
                .where(tarefa.departamento.eq(departamento)
                        .and(tarefa.pessoa.isNull()))
                .orderBy(tarefa.prazo.asc())
                .fetchFirst();
    }

    @Override
    public List<TarefaResponse> getTarefasAntigasPendentes() {
        return new JPAQueryFactory(entityManager)
                .select(createTarefaResponse())
                .from(tarefa)
                .where(tarefa.pessoa.isNull()
                        .and(tarefa.finalizado.isFalse()))
                .limit(limiteTarefasPendentes)
                .orderBy(tarefa.prazo.asc())
                .fetch();
    }

    private Expression<TarefaResponse> createTarefaResponse() {
        return Projections.constructor(TarefaResponse.class,
                tarefa.id,
                tarefa.titulo,
                tarefa.descricao,
                tarefa.prazo,
                tarefa.departamento,
                tarefa.duracao,
                tarefa.pessoa.id,
                tarefa.finalizado);
    }
}
