package taskflowapi.application.dto.response;

import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.domain.model.Tarefa;

import java.util.Set;

public record PessoaResponse(Long id, String nome, EDepartamento departamento, Set<Tarefa> tarefas) {
}
