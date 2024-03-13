package taskflowapi.application.controller.contract;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import taskflowapi.application.annotations.PessoaFiltrosParameters;
import taskflowapi.application.dto.filters.PessoaFiltros;
import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
import taskflowapi.application.dto.response.PessoaResponse;
import taskflowapi.application.dto.response.PessoaTotalHorasTrabalhadas;

import java.util.List;
import java.util.Set;

public interface IPessoaController {

    @GetMapping
    @Operation(summary = "Obter todas as pessoas e suas total de horas trabalhadas")
    List<PessoaTotalHorasTrabalhadas> getAllPessoa();

    @GetMapping("gastos")
    @Operation(summary = "Buscar pessoas por nome e período")
    @PessoaFiltrosParameters
    Set<PessoaMediaHorasTrabalhadas> getPessoasByNomeEPeriodo(@Parameter(hidden = true) @Valid PessoaFiltros filtros);

    @PostMapping
    @Operation(summary = "Cadastrar nova pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Pessoa cadastrada com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content),
    })
    PessoaResponse save(@RequestBody @Valid PessoaRequest pessoaRequest);

    @PutMapping("{id}")
    @Operation(summary = "Atualizar dados da pessoa")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Dados da pessoa atualizados com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos", content = @Content),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content),
    })
    PessoaResponse update(@PathVariable Long id, @RequestBody @Valid PessoaRequest pessoaRequest);

    @DeleteMapping("{id}")
    @Operation(summary = "Deletar pessoa pelo ID")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Pessoa deletada com sucesso"),
            @ApiResponse(responseCode = "404", description = "Pessoa não encontrada", content = @Content),
    })
    void deleteById(@PathVariable Long id);
}
