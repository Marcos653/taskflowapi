package taskflowapi.infra.repository;

import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.dto.response.DepartamentoResponse;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
import com.querydsl.core.types.Predicate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static taskflowapi.helper.PessoaHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static taskflowapi.helper.DepartamentoHelper.umaListaDepartamentoResponses;

@ExtendWith(MockitoExtension.class)
class PessoaRepositoryAdapterTest {

    private final long pessoaId = 1L;

    @InjectMocks
    private PessoaRepositoryAdapter pessoaRepositoryAdapter;
    @Mock
    private PessoaJpaRepository pessoaJpaRepository;
    @Mock
    private Predicate predicate;

    private Pessoa pessoa;
    private List<Pessoa> listaPessoas;
    private Set<PessoaMediaHorasTrabalhadas> setPessoaMediaHorasTrabalhadas;
    private List<DepartamentoResponse> listaDepartamentoResponses;

    @BeforeEach
    void setUp() {
        listaPessoas = umaListaPessoa();
        setPessoaMediaHorasTrabalhadas = umSetPessoaMediaHorasTrabalhadas();
        listaDepartamentoResponses = umaListaDepartamentoResponses();
        pessoa = umaPessoa(pessoaId, "Marcos", EDepartamento.DESENVOLVIMENTO, new HashSet<>());
    }

    @Test
    void findAll_deveDelegarParaPessoaJpaRepository_quandoSolicitado() {
        when(pessoaJpaRepository.findAll())
                .thenReturn(listaPessoas);

        var resultado = pessoaRepositoryAdapter.findAll();

        assertEquals(listaPessoas, resultado);
        verify(pessoaJpaRepository).findAll();
    }

    @Test
    void getPessoasByNomeEPeriodo_deveDelegarParaPessoaJpaRepository_quandoSolicitado() {
        when(pessoaJpaRepository.getPessoasByNomeEPeriodo(predicate))
                .thenReturn(setPessoaMediaHorasTrabalhadas);

        var resultado = pessoaRepositoryAdapter.getPessoasByNomeEPeriodo(predicate);

        assertEquals(setPessoaMediaHorasTrabalhadas, resultado);
        verify(pessoaJpaRepository).getPessoasByNomeEPeriodo(predicate);
    }

    @Test
    void findById_deveDelegarParaPessoaJpaRepository_quandoSolicitado() {
        when(pessoaJpaRepository.findById(pessoaId))
                .thenReturn(Optional.of(pessoa));

        var resultado = pessoaRepositoryAdapter.findById(pessoaId);

        assertEquals(Optional.of(pessoa), resultado);
        verify(pessoaJpaRepository).findById(pessoaId);
    }

    @Test
    void save_deveDelegarParaPessoaJpaRepository_quandoSolicitado() {
        when(pessoaJpaRepository.save(pessoa))
                .thenReturn(pessoa);

        var resultado = pessoaRepositoryAdapter.save(pessoa);

        assertEquals(pessoa, resultado);
        verify(pessoaJpaRepository).save(pessoa);
    }

    @Test
    void deleteById_deveDelegarParaPessoaJpaRepository_quandoSolicitado() {
        doNothing().when(pessoaJpaRepository)
                .deleteById(pessoaId);

        pessoaRepositoryAdapter.deleteById(pessoaId);

        verify(pessoaJpaRepository).deleteById(pessoaId);
    }

    @Test
    void findAllDepartamento_deveDelegarParaPessoaJpaRepository_quandoSolicitado() {
        when(pessoaJpaRepository.findAllDepartamento())
                .thenReturn(listaDepartamentoResponses);

        var resultado = pessoaRepositoryAdapter.findAllDepartamento();

        assertEquals(listaDepartamentoResponses, resultado);
        verify(pessoaJpaRepository).findAllDepartamento();
    }
}
