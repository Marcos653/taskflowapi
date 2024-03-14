package taskflowapi.infra.repository.dto;

import taskflowapi.domain.enums.EDepartamento;

public record TarefaCount(EDepartamento departamento, long count) {
}
