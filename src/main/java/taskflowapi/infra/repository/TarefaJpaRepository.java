package taskflowapi.infra.repository;

import taskflowapi.domain.model.Tarefa;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface TarefaJpaRepository extends JpaRepository<Tarefa, Long>, TarefaJpaRepositoryCustom {
}
