package taskflowapi.application.annotations;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Parameter(
        in = ParameterIn.QUERY,
        name = "nome",
        description = "Buscar pela nome da pessoa.",
        schema = @Schema(type = "String", example = "João da Silva"))
@Parameter(
        in = ParameterIn.QUERY,
        name = "inicio",
        description = "Data de início para filtrar pessoas",
        schema = @Schema(type = "String", example = "2024-01-01T00:00:00"))
@Parameter(
        in = ParameterIn.QUERY,
        name = "fim",
        description = "Data de fim para filtrar pessoas",
        schema = @Schema(type = "String", example = "2024-12-31T23:59:59"))
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface PessoaFiltrosParameters {
}
