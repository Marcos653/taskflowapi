package taskflowapi.domain.service.contract;

import taskflowapi.application.dto.request.TarefaRequest;
import taskflowapi.application.dto.response.TarefaResponse;

import java.util.List;

public interface ITarefaService {

    TarefaResponse save(TarefaRequest tarefaRequest);

    TarefaResponse alocar(Long pessoaId);

    TarefaResponse finalizar(Long id);

    List<TarefaResponse> getTarefasAntigasPendentes();
}
