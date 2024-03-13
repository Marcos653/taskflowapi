package taskflowapi.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskflowapi.application.controller.contract.ITarefaController;
import taskflowapi.application.dto.request.TarefaRequest;
import taskflowapi.application.dto.response.TarefaResponse;
import taskflowapi.domain.service.contract.ITarefaService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/tarefas")
public class TarefaController implements ITarefaController {

    private final ITarefaService service;

    @Override
    public TarefaResponse save(TarefaRequest tarefaRequest) {
        return service.save(tarefaRequest);
    }

    @Override
    public TarefaResponse alocar(Long pessoaId) {
        return service.alocar(pessoaId);
    }

    @Override
    public TarefaResponse finalizar(Long id) {
        return service.finalizar(id);
    }

    @Override
    public List<TarefaResponse> getTarefasAntigasPendentes() {
        return service.getTarefasAntigasPendentes();
    }
}
