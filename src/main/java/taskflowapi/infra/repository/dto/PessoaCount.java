package taskflowapi.infra.repository.dto;

import taskflowapi.domain.enums.EDepartamento;

public record PessoaCount(EDepartamento departamento, long count) {
}
