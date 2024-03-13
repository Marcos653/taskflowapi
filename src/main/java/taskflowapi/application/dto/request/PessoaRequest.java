package taskflowapi.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import taskflowapi.domain.enums.EDepartamento;

public record PessoaRequest(@NotBlank String nome, @NotNull EDepartamento departamento) {
}
