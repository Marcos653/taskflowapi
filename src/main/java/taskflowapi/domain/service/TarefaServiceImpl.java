package taskflowapi.domain.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taskflowapi.application.dto.request.TarefaRequest;
import taskflowapi.application.dto.response.TarefaResponse;
import taskflowapi.application.mapper.TarefaMapper;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.domain.model.Tarefa;
import taskflowapi.domain.repository.ITarefaRepository;
import taskflowapi.domain.service.contract.IPessoaService;
import taskflowapi.domain.service.contract.ITarefaService;

import java.util.List;

import static taskflowapi.domain.utils.MensagemConstantes.TAREFA_ID_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class TarefaServiceImpl implements ITarefaService {

    private final ITarefaRepository repository;
    private final IPessoaService pessoaService;
    private final TarefaMapper mapper;

    @Override
    public TarefaResponse save(TarefaRequest tarefaRequest) {
        return mapper.convertToTarefaResponse(repository
                .save(mapper.convertToTarefa(tarefaRequest)));
    }

    @Override
    public TarefaResponse alocar(Long pessoaId) {
        var pessoa = pessoaService.getPessoaById(pessoaId);
        var tarefa = getTopByDepartamento(pessoa.getDepartamento());
        tarefa.setPessoa(pessoa);

        return mapper.convertToTarefaResponse(repository.save(tarefa));
    }

    @Override
    public TarefaResponse finalizar(Long id) {
        var tarefa = getTarefaById(id);
        tarefa.setFinalizado(Boolean.TRUE);

        return mapper.convertToTarefaResponse(repository.save(tarefa));
    }

    @Override
    public List<TarefaResponse> getTarefasAntigasPendentes() {
        return repository.getTarefasAntigasPendentes();
    }

    private Tarefa getTarefaById(Long id) {
        return repository.findById(id)
              .orElseThrow(() -> new EntityNotFoundException(TAREFA_ID_NOT_FOUND + id));
    }

    private Tarefa getTopByDepartamento(EDepartamento departamento) {
        return repository.getTopByDepartamento(departamento);
    }
}
