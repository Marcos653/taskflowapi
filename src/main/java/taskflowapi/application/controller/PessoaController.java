package taskflowapi.application.controller;

import taskflowapi.application.controller.contract.IPessoaController;
import taskflowapi.application.dto.filters.PessoaFiltros;
import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
import taskflowapi.application.dto.response.PessoaResponse;
import taskflowapi.application.dto.response.PessoaTotalHorasTrabalhadas;
import taskflowapi.domain.service.contract.IPessoaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;

import java.util.Set;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/pessoas")
public class PessoaController implements IPessoaController {

    private final IPessoaService service;

    @Override
    public List<PessoaTotalHorasTrabalhadas> getAllPessoas() {
        return service.getAllPessoas();
    }

    @Override
    public Set<PessoaMediaHorasTrabalhadas> getPessoasByNomeEPeriodo(PessoaFiltros filtros) {
        return service.getPessoasByNomeEPeriodo(filtros);
    }

    @Override
    public PessoaResponse save(PessoaRequest pessoaRequest) {
        return service.save(pessoaRequest);
    }

    @Override
    public PessoaResponse update(Long id, PessoaRequest pessoaRequest) {
        return service.update(id, pessoaRequest);
    }

    @Override
    public void deleteById(Long id) {
        service.deleteById(id);
    }
}
