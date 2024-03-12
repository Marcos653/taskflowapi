package taskflowapi.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.application.dto.response.PessoaResponse;
import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.repository.IPessoaRepository;
import taskflowapi.domain.service.contract.IPessoaService;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements IPessoaService {

    private final IPessoaRepository repository;

    @Override
    public PessoaResponse save(PessoaRequest pessoaRequest) {
        return null;
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
