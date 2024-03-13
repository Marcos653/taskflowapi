package taskflowapi.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import taskflowapi.domain.enums.EDepartamento;

import java.time.LocalDateTime;

public record TarefaRequest(
        @NotBlank String titulo,
        @NotBlank String descricao,
        @NotNull LocalDateTime prazo,
        @NotNull EDepartamento departamento,
        @NotNull Double duracao) {
}
