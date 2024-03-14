package taskflowapi.infra.repository;

import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.infra.repository.dto.PessoaCount;
import taskflowapi.infra.repository.dto.TarefaCount;
import taskflowapi.application.dto.response.DepartamentoResponse;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.*;
import java.util.stream.Collectors;
import jakarta.persistence.EntityManager;

import static java.util.Comparator.comparing;
import static taskflowapi.domain.model.QPessoa.pessoa;
import static taskflowapi.domain.model.QTarefa.tarefa;

@RequiredArgsConstructor
public class PessoaJpaRepositoryImpl implements PessoaJpaRepositoryCustom {

    private final EntityManager entityManager;
    private final long defaultValue = 0L;

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
        var pessoas = getPessoasByDepartamento();
        var tarefas = getTarefasByDepartamento();
        var pessoaCountMap = toPessoaCountMap(pessoas);
        var tarefaCountMap = toTarefaCountMap(tarefas);

        return toDepartamentoResponseList(pessoaCountMap, tarefaCountMap);
    }

    private Expression<PessoaMediaHorasTrabalhadas> createPessoaMediaHorasTrabalhadas() {
        return Projections.constructor(PessoaMediaHorasTrabalhadas.class,
                pessoa.id,
                pessoa.nome,
                pessoa.departamento,
                tarefa.duracao.avg());
    }

    private List<PessoaCount> getPessoasByDepartamento() {
        return  new JPAQueryFactory(entityManager)
                .select(Projections.constructor(PessoaCount.class,
                        pessoa.departamento,
                        pessoa.count()))
                .from(pessoa)
                .groupBy(pessoa.departamento)
                .fetch();
    }

    private List<TarefaCount> getTarefasByDepartamento() {
        return new JPAQueryFactory(entityManager)
                .select(Projections.constructor(TarefaCount.class,
                        tarefa.departamento,
                        tarefa.count()))
                .from(tarefa)
                .groupBy(tarefa.departamento)
                .fetch();
    }

    private Map<EDepartamento, Long> toPessoaCountMap(List<PessoaCount> pessoas) {
        return pessoas.stream()
                .collect(Collectors.toMap(PessoaCount::departamento, PessoaCount::count));
    }

    private Map<EDepartamento, Long> toTarefaCountMap(List<TarefaCount> tarefas) {
        return tarefas.stream()
                .collect(Collectors.toMap(TarefaCount::departamento, TarefaCount::count));
    }

    private List<DepartamentoResponse> toDepartamentoResponseList(Map<EDepartamento, Long> pessoaCountMap,
                                                                  Map<EDepartamento, Long> tarefaCountMap) {
        return Arrays.stream(EDepartamento.values())
                .map(departamento -> new DepartamentoResponse(
                        departamento,
                        pessoaCountMap.getOrDefault(departamento, defaultValue),
                        tarefaCountMap.getOrDefault(departamento, defaultValue)))
                .sorted(comparing(departamentoResponse -> departamentoResponse.departamento().name()))
                .toList();
    }
}
