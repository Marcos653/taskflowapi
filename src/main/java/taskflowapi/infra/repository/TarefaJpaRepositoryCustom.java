package taskflowapi.infra.repository;

import taskflowapi.domain.model.Tarefa;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.dto.response.TarefaResponse;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface TarefaJpaRepositoryCustom {

    Tarefa getTopByDepartamento(EDepartamento departamento);

    List<TarefaResponse> getTarefasAntigasPendentes();
}
