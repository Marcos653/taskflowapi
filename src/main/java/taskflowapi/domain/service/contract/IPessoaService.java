package taskflowapi.domain.service.contract;

import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.application.dto.response.PessoaResponse;

public interface IPessoaService {

    PessoaResponse save(PessoaRequest pessoaRequest);

    void deleteById(Long id);
}
