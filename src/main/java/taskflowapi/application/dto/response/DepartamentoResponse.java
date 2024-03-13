package taskflowapi.application.dto.response;

import taskflowapi.domain.enums.EDepartamento;

public record DepartamentoResponse(
        EDepartamento departamento,
        long qtPessoas,
        long qtTarefas) {
}
