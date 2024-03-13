package taskflowapi.infra.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import taskflowapi.application.dto.response.TarefaResponse;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.domain.model.Tarefa;
import taskflowapi.domain.repository.ITarefaRepository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TarefaRepositoryAdapter implements ITarefaRepository {

    private final TarefaJpaRepository tarefaJpaRepository;

    @Override
    public Optional<Tarefa> findById(Long id) {
        return tarefaJpaRepository.findById(id);
    }

    @Override
    public Optional<Tarefa> getTopByDepartamento(EDepartamento departamento) {
        return tarefaJpaRepository.getTopByDepartamento(departamento);
    }

    @Override
    public List<TarefaResponse> getTarefasAntigasPendentes() {
        return tarefaJpaRepository.getTarefasAntigasPendentes();
    }

    @Override
    public Tarefa save(Tarefa tarefa) {
        return tarefaJpaRepository.save(tarefa);
    }
}
