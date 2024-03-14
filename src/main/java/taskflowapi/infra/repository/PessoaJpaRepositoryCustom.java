package taskflowapi.infra.repository;

import taskflowapi.application.dto.response.DepartamentoResponse;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
import com.querydsl.core.types.Predicate;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;
import java.util.Set;

@NoRepositoryBean
public interface PessoaJpaRepositoryCustom {

    Set<PessoaMediaHorasTrabalhadas> getPessoasByNomeEPeriodo(Predicate predicate);

    List<DepartamentoResponse> findAllDepartamento();
}
