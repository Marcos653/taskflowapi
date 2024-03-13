package taskflowapi.domain.repository;

import taskflowapi.application.dto.response.TarefaResponse;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.domain.model.Tarefa;

import java.util.List;
import java.util.Optional;

public interface ITarefaRepository {

    Optional<Tarefa> findById(Long id);

    Tarefa getTopByDepartamento(EDepartamento departamento);

    List<TarefaResponse> getTarefasAntigasPendentes();

    Tarefa save(Tarefa tarefa);
}
