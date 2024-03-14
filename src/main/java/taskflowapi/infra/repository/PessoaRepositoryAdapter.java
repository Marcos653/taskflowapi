package taskflowapi.infra.repository;

import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.repository.IPessoaRepository;
import taskflowapi.domain.repository.IDepartamentoRepository;
import taskflowapi.application.dto.response.DepartamentoResponse;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
import lombok.RequiredArgsConstructor;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Repository;

import java.util.Set;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class PessoaRepositoryAdapter implements IPessoaRepository, IDepartamentoRepository {

    private final PessoaJpaRepository pessoaJpaRepository;

    @Override
    public List<Pessoa> findAll() {
        return pessoaJpaRepository.findAll();
    }

    @Override
    public Set<PessoaMediaHorasTrabalhadas> getPessoasByNomeEPeriodo(Predicate predicate) {
        return pessoaJpaRepository.getPessoasByNomeEPeriodo(predicate);
    }

    @Override
    public Optional<Pessoa> findById(Long id) {
        return pessoaJpaRepository.findById(id);
    }

    @Override
    public Pessoa save(Pessoa pessoa) {
        return pessoaJpaRepository.save(pessoa);
    }

    @Override
    public void deleteById(Long id) {
        pessoaJpaRepository.deleteById(id);
    }

    @Override
    public List<DepartamentoResponse> findAllDepartamento() {
        return pessoaJpaRepository.findAllDepartamento();
    }
}
