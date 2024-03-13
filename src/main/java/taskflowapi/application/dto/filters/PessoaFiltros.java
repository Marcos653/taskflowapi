package taskflowapi.application.dto.filters;

import com.querydsl.core.BooleanBuilder;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import taskflowapi.infra.repository.predicates.PessoaPredicate;

import java.time.LocalDateTime;

public record PessoaFiltros(
        @NotEmpty String nome,
        @NotNull LocalDateTime inicio,
        @NotNull LocalDateTime fim) {

    public BooleanBuilder createPredicate() {
        return new PessoaPredicate()
                .comNome(nome)
                .noPeriodo(inicio, fim)
                .build();
    }
}
