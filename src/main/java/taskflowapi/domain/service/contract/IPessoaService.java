package taskflowapi.domain.service.contract;

import taskflowapi.application.dto.filters.PessoaFiltros;
import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
import taskflowapi.application.dto.response.PessoaResponse;
import taskflowapi.application.dto.response.PessoaTotalHorasTrabalhadas;
import taskflowapi.domain.model.Pessoa;

import java.util.List;
import java.util.Set;

public interface IPessoaService {

    List<PessoaTotalHorasTrabalhadas> getAllPessoa();

    Set<PessoaMediaHorasTrabalhadas> getPessoasByNomeEPeriodo(PessoaFiltros filtros);

    Pessoa getPessoaById(Long id);

    PessoaResponse save(PessoaRequest pessoaRequest);

    PessoaResponse update(Long id, PessoaRequest pessoaRequest);

    void deleteById(Long id);
}
