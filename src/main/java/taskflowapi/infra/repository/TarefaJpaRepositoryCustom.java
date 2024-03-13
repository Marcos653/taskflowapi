package taskflowapi.infra.repository;

import org.springframework.data.repository.NoRepositoryBean;
import taskflowapi.application.dto.response.TarefaResponse;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.domain.model.Tarefa;

import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface TarefaJpaRepositoryCustom {

    Optional<Tarefa> getTopByDepartamento(EDepartamento departamento);

    List<TarefaResponse> getTarefasAntigasPendentes();
}
