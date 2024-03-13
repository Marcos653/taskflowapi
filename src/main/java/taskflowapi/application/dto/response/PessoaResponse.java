package taskflowapi.application.dto.response;

import taskflowapi.domain.enums.EDepartamento;

import java.util.Set;

public record PessoaResponse(
        Long id,
        String nome,
        EDepartamento departamento,
        Set<TarefaResponse> tarefas) {
}
