package taskflowapi.domain.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taskflowapi.application.dto.filters.PessoaFiltros;
import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
import taskflowapi.application.dto.response.PessoaResponse;
import taskflowapi.application.dto.response.PessoaTotalHorasTrabalhadas;
import taskflowapi.application.mapper.PessoaMapper;
import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.model.Tarefa;
import taskflowapi.domain.repository.IPessoaRepository;
import taskflowapi.domain.service.contract.IPessoaService;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements IPessoaService {

    private static final String PESSOA_ID_NOT_FOUND = "Pessoa não encontrada com ID: ";

    private final IPessoaRepository repository;
    private final PessoaMapper mapper;

    @Override
    public Set<PessoaTotalHorasTrabalhadas> getAllPessoa() {
        return repository.findAll()
                .stream()
                .map(this::setTotalHoras)
                .collect(Collectors.toSet());
    }

    @Override
    public Set<PessoaMediaHorasTrabalhadas> getPessoasByNomeEPeriodo(PessoaFiltros filtros) {
        return repository.getPessoasByNomeEPeriodo(filtros.criarPredicado());
    }

    @Override
    public Pessoa getPessoaById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(PESSOA_ID_NOT_FOUND + id));
    }

    @Override
    public PessoaResponse save(PessoaRequest pessoaRequest) {
        return mapper.convertToPessoaResponse(repository
                .save(mapper.convertToPessoa(pessoaRequest)));
    }

    @Override
    public PessoaResponse update(Long id, PessoaRequest pessoaRequest) {
        getPessoaById(id);

        return mapper.convertToPessoaResponse(repository
                .save(mapper.convertToPessoaWithId(id, pessoaRequest)));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    private PessoaTotalHorasTrabalhadas setTotalHoras(Pessoa pessoa) {
        var totalHorasTrabalhadas = pessoa.getTarefas()
                .stream()
                .filter(Tarefa::getFinalizado)
                .mapToDouble(Tarefa::getDuracao)
                .sum();

        return new PessoaTotalHorasTrabalhadas(pessoa.getId(), pessoa.getNome(),
                pessoa.getDepartamento(), totalHorasTrabalhadas);
    }
}
