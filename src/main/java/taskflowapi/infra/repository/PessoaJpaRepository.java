package taskflowapi.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taskflowapi.domain.model.Pessoa;

@Repository
public interface PessoaJpaRepository extends JpaRepository<Pessoa, Long>, PessoaJpaRepositoryCustom {
}
