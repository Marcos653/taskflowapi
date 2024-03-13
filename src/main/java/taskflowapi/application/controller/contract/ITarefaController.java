package taskflowapi.application.controller.contract;

import taskflowapi.application.dto.request.TarefaRequest;
import taskflowapi.application.dto.response.TarefaResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import jakarta.validation.Valid;

public interface ITarefaController {

    @PostMapping
    @Operation(summary = "Cadastrar nova tarefa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos")
    })
    TarefaResponse save(@RequestBody @Valid TarefaRequest tarefaRequest);

    @PutMapping("alocar/{pessoaId}")
    @Operation(summary = "Alocar tarefa para pessoa pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa alocada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa ou tarefa não encontrada")
    })
    TarefaResponse alocar(@PathVariable Long pessoaId);

    @PutMapping("finalizar/{id}")
    @Operation(summary = "Finalizar tarefa pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa finalizada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    TarefaResponse finalizar(@PathVariable Long id);

    @GetMapping("pendentes")
    @Operation(summary = "Obter lista de tarefas antigas pendentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de tarefas pendentes obtida com sucesso")
    })
    List<TarefaResponse> getTarefasAntigasPendentes();
}
