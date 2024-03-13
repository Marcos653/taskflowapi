package taskflowapi.domain.repository;

import com.querydsl.core.types.Predicate;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
import taskflowapi.domain.model.Pessoa;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IPessoaRepository {

    List<Pessoa> findAll();

    Set<PessoaMediaHorasTrabalhadas> getPessoasByNomeEPeriodo(Predicate predicate);

    Optional<Pessoa> findById(Long id);

    Pessoa save(Pessoa pessoa);

    void deleteById(Long id);
}
