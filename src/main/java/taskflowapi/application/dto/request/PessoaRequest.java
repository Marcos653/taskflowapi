package taskflowapi.application.dto.request;

import taskflowapi.domain.enums.EDepartamento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotBlank;

public record PessoaRequest(@NotBlank String nome, @NotNull EDepartamento departamento) {
}
