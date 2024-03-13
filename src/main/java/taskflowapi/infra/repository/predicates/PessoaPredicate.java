package taskflowapi.infra.repository.predicates;

import java.time.LocalDateTime;

import static taskflowapi.domain.model.QPessoa.pessoa;
import static taskflowapi.domain.model.QTarefa.tarefa;

public class PessoaPredicate extends PredicateBase {

    private static final String LIKE_WILDCARD = "%";

    public PessoaPredicate comNome(String nome) {
        if (isNotEmpty(nome)) {
            builder.and(pessoa.nome.likeIgnoreCase(LIKE_WILDCARD + nome + LIKE_WILDCARD));
        }

        return this;
    }

    public PessoaPredicate noPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        if (inicio != null && fim != null) {
            builder.and(tarefa.prazo.between(inicio, fim));
        }

        return this;
    }
}
