package taskflowapi.application.dto.response;

import taskflowapi.domain.enums.EDepartamento;

import java.time.LocalDateTime;

public record TarefaResponse(
        Long id,
        String titulo,
        String descricao,
        LocalDateTime prazo,
        EDepartamento departamento,
        Double duracao,
        Long pessoaId,
        Boolean finalizado) {
}
