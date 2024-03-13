package taskflowapi.domain.service;

import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.model.Tarefa;
import taskflowapi.application.mapper.PessoaMapper;
import taskflowapi.domain.repository.IPessoaRepository;
import taskflowapi.application.dto.filters.PessoaFiltros;
import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.domain.service.contract.IPessoaService;
import taskflowapi.application.dto.response.PessoaResponse;
import taskflowapi.application.dto.response.PessoaTotalHorasTrabalhadas;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.List;
import jakarta.persistence.EntityNotFoundException;

import static java.util.Comparator.comparing;
import static taskflowapi.domain.utils.MensagemConstantes.PESSOA_ID_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PessoaServiceImpl implements IPessoaService {

    private final IPessoaRepository repository;
    private final PessoaMapper mapper;

    @Override
    public List<PessoaTotalHorasTrabalhadas> getAllPessoa() {
        return repository.findAll()
                .stream()
                .map(this::setTotalHoras)
                .sorted(comparing(PessoaTotalHorasTrabalhadas::nome))
                .toList();
    }

    @Override
    public Set<PessoaMediaHorasTrabalhadas> getPessoasByNomeEPeriodo(PessoaFiltros filtros) {
        return repository.getPessoasByNomeEPeriodo(filtros.createPredicate());
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
        getPessoaById(id);
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
