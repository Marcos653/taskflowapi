package taskflowapi.application.dto.response;

import taskflowapi.domain.enums.EDepartamento;

public record PessoaMediaHorasTrabalhadas(
        Long id,
        String nome,
        EDepartamento departamento,
        double mediaHorasTrabalhadas) {
}
