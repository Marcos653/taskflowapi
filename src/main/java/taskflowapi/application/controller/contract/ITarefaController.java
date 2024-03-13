package taskflowapi.application.controller.contract;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import taskflowapi.application.dto.request.TarefaRequest;
import taskflowapi.application.dto.response.TarefaResponse;

import java.util.List;

public interface ITarefaController {

    @PostMapping
    TarefaResponse save(@RequestBody @Valid TarefaRequest tarefaRequest);

    @PutMapping("alocar/{pessoaId}")
    TarefaResponse alocar(@PathVariable Long pessoaId);

    @PutMapping("finalizar/{id}")
    TarefaResponse finalizar(@PathVariable Long id);

    @GetMapping("pendentes")
    List<TarefaResponse> getTarefasAntigasPendentes();
}
