package taskflowapi.infra.repository;

import org.springframework.data.repository.NoRepositoryBean;
import taskflowapi.application.dto.response.TarefaResponse;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.domain.model.Tarefa;

import java.util.List;

@NoRepositoryBean
public interface TarefaJpaRepositoryCustom {

    Tarefa getTopByDepartamento(EDepartamento departamento);

    List<TarefaResponse> getTarefasAntigasPendentes();
}
