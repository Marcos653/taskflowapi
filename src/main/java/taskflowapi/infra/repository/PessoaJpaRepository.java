package taskflowapi.infra.repository;

import taskflowapi.domain.model.Pessoa;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PessoaJpaRepository extends JpaRepository<Pessoa, Long>, PessoaJpaRepositoryCustom {
}
