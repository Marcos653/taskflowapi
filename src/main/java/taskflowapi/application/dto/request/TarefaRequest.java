package taskflowapi.application.dto.request;

import taskflowapi.domain.enums.EDepartamento;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record TarefaRequest(
        @NotBlank String titulo,
        @NotBlank String descricao,
        @NotNull LocalDateTime prazo,
        @NotNull EDepartamento departamento,
        @NotNull double duracao) {
}
