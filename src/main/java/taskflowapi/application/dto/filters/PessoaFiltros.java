package taskflowapi.application.dto.filters;

import taskflowapi.infra.repository.predicates.PessoaPredicate;
import com.querydsl.core.BooleanBuilder;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.NotEmpty;

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
