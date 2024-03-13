package taskflowapi.application.controller.contract;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.GetMapping;
import taskflowapi.application.dto.response.DepartamentoResponse;

import java.util.List;

public interface IDepartamentoController {

    @GetMapping
    @Operation(summary = "Obter lista de todos os departamentos e a quantidade de pessoas e tarefas em cada um")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de departamentos obtida com sucesso")
    })
    List<DepartamentoResponse> findAllDepartamento();
}
