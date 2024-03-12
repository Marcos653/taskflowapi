package taskflowapi.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.repository.IPessoaRepository;

@Repository
public interface IPessoaJpaRepository extends JpaRepository<Pessoa, Long>, IPessoaRepository {
}
