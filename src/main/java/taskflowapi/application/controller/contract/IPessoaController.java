package taskflowapi.application.controller.contract;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import taskflowapi.application.dto.filters.PessoaFiltros;
import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
import taskflowapi.application.dto.response.PessoaResponse;
import taskflowapi.application.dto.response.PessoaTotalHorasTrabalhadas;

import java.util.List;
import java.util.Set;

public interface IPessoaController {

    @GetMapping
    List<PessoaTotalHorasTrabalhadas> getAllPessoa();

    @GetMapping("gastos")
    Set<PessoaMediaHorasTrabalhadas> getPessoasByNomeEPeriodo(@Valid PessoaFiltros filtros);

    @PostMapping
    PessoaResponse save(@RequestBody @Valid PessoaRequest pessoaRequest);

    @PutMapping("{id}")
    PessoaResponse update(@PathVariable Long id, @RequestBody @Valid PessoaRequest pessoaRequest);

    @DeleteMapping("{id}")
    void deleteById(@PathVariable Long id);
}
