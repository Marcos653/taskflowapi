package taskflowapi.domain.repository;

import taskflowapi.domain.model.Pessoa;

public interface IPessoaRepository {

    Pessoa save(Pessoa pessoa);

    void delete(Long id);
}
