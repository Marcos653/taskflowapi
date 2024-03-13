package taskflowapi.application.dto.response;

import taskflowapi.domain.enums.EDepartamento;

public record PessoaTotalHorasTrabalhadas(
        Long id,
        String nome,
        EDepartamento departamento,
        double totalHorasTrabalhadas) {
}
