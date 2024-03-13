package taskflowapi.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import taskflowapi.domain.model.Tarefa;

@Repository
public interface TarefaJpaRepository extends JpaRepository<Tarefa, Long>, TarefaJpaRepositoryCustom {
}
