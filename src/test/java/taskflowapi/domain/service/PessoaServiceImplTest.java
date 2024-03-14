package taskflowapi.domain.service;

import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.mapper.PessoaMapper;
import taskflowapi.domain.repository.IPessoaRepository;
import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.application.dto.response.PessoaResponse;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
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
import jakarta.persistence.EntityNotFoundException;

import static org.mockito.Mockito.*;
import static org.assertj.core.api.Assertions.*;
import static taskflowapi.helper.PessoaHelper.*;
import static taskflowapi.domain.utils.MensagemConstantes.PESSOA_ID_NOT_FOUND;

@ExtendWith(MockitoExtension.class)
class PessoaServiceImplTest {

    private final long pessoaId = 1L;
    private final long inexistenteId = 100;

    @InjectMocks
    private PessoaServiceImpl pessoaService;
    @Mock
    private IPessoaRepository pessoaRepository;
    @Mock
    private PessoaMapper mapper;

    private Pessoa pessoa;
    private PessoaRequest pessoaRequest;
    private PessoaResponse pessoaResponse;
    private List<Pessoa> listaPessoas;
    private Set<PessoaMediaHorasTrabalhadas> setPessoaMediaHorasTrabalhadas;

    @BeforeEach
    void setUp() {
        listaPessoas = umaListaPessoa();
        setPessoaMediaHorasTrabalhadas = umSetPessoaMediaHorasTrabalhadas();
        pessoa = umaPessoa(pessoaId, "Marcos", EDepartamento.DESENVOLVIMENTO, new HashSet<>());
        pessoaRequest = umaPessoaRequest("Marcos", EDepartamento.DESENVOLVIMENTO);
        pessoaResponse = umaPessoaResponse(pessoaId, "Marcos", EDepartamento.DESENVOLVIMENTO, new HashSet<>());
    }

    @Test
    void getAllPessoas_deveRetornarListaDePessoas_quandoSolicitado() {
        when(pessoaRepository.findAll())
                .thenReturn(listaPessoas);

        var resultado = pessoaService.getAllPessoas();

        assertThat(resultado)
                .isInstanceOf(List.class)
                .isNotEmpty()
                .hasSize(listaPessoas.size());

        verify(pessoaRepository).findAll();
    }

    @Test
    void getPessoasByNomeEPeriodo_deveRetornarSetDePessoaMediaHorasTrabalhadas_quandoEncontrado() {
        when(pessoaRepository.getPessoasByNomeEPeriodo(umPessoaFiltro().createPredicate()))
                .thenReturn(setPessoaMediaHorasTrabalhadas);

        var resultado = pessoaService.getPessoasByNomeEPeriodo(umPessoaFiltro());

        assertThat(resultado)
                .isInstanceOf(Set.class)
                .isNotEmpty()
                .hasSize(setPessoaMediaHorasTrabalhadas.size());

        verify(pessoaRepository).getPessoasByNomeEPeriodo(umPessoaFiltro().createPredicate());
    }

    @Test
    void getPessoasByNomeEPeriodo_deveRetornarVazio_quandoNaoEncontrado() {
        when(pessoaRepository.getPessoasByNomeEPeriodo(umPessoaFiltro().createPredicate()))
                .thenReturn(Set.of());

        var resultado = pessoaService.getPessoasByNomeEPeriodo(umPessoaFiltro());

        assertThat(resultado)
                .isInstanceOf(Set.class)
                .isEmpty();

        verify(pessoaRepository).getPessoasByNomeEPeriodo(umPessoaFiltro().createPredicate());
    }

    @Test
    void getPessoaById_deveRetornarPessoa_quandoEncontrada() {
        when(pessoaRepository.findById(pessoaId))
                .thenReturn(Optional.of(pessoa));

        var resultado = pessoaService.getPessoaById(pessoaId);

        assertThat(resultado)
                .isInstanceOf(Pessoa.class)
                .isNotNull();

        verify(pessoaRepository).findById(pessoaId);
    }

    @Test
    void getPessoaById_deveLancarException_quandoPessoaNaoEncontrada() {
        when(pessoaRepository.findById(inexistenteId))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> pessoaService.getPessoaById(inexistenteId))
                .withMessage(PESSOA_ID_NOT_FOUND + inexistenteId);

        verify(pessoaRepository).findById(inexistenteId);
    }

    @Test
    void save_deveRetornarPessoaResponse_quandoSalva() {
        when(pessoaRepository.save(pessoa))
                .thenReturn(pessoa);
        when(mapper.convertToPessoa(pessoaRequest))
                .thenReturn(pessoa);
        when(mapper.convertToPessoaResponse(pessoa))
                .thenReturn(pessoaResponse);

        var resultado = pessoaService.save(pessoaRequest);

        assertThat(resultado)
                .isInstanceOf(PessoaResponse.class)
                .isNotNull();

        verify(mapper).convertToPessoaResponse(pessoa);
        verify(pessoaRepository).save(pessoa);
        verify(mapper).convertToPessoa(pessoaRequest);
    }

    @Test
    void update_deveRetornarPessoaResponse_quandoAtualiza() {
        when(pessoaRepository.findById(pessoaId))
                .thenReturn(Optional.of(pessoa));
        when(pessoaRepository.save(pessoa))
                .thenReturn(pessoa);
        when(mapper.convertToPessoaResponse(pessoa))
                .thenReturn(pessoaResponse);

        var resultado = pessoaService.update(pessoaId, pessoaRequest);

        assertThat(resultado)
                .isInstanceOf(PessoaResponse.class)
                .isNotNull();

        verify(pessoaRepository).findById(pessoaId);
        verify(mapper).convertToPessoaResponse(pessoa);
        verify(pessoaRepository).save(pessoa);
    }

    @Test
    void update_deveLancarException_quandoPessoaNaoEncontrada() {
        when(pessoaRepository.findById(inexistenteId))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> pessoaService.update(inexistenteId, pessoaRequest))
                .withMessage(PESSOA_ID_NOT_FOUND + inexistenteId);

        verify(pessoaRepository).findById(inexistenteId);
        verifyNoInteractions(mapper);
        verify(pessoaRepository, never()).save(pessoa);
    }

    @Test
    void deleteById_deveDeletarPessoa_quandoSolicitado() {
        when(pessoaRepository.findById(pessoaId))
                .thenReturn(Optional.of(pessoa));
        doNothing().when(pessoaRepository)
                .deleteById(pessoaId);

        assertThatCode(() -> pessoaService.deleteById(pessoaId))
                .doesNotThrowAnyException();

        verify(pessoaRepository).findById(pessoaId);
        verify(pessoaRepository).deleteById(pessoaId);
    }

    @Test
    void deleteById_deveLancarException_quandoPessoaNaoEncontrada() {
        when(pessoaRepository.findById(inexistenteId))
                .thenReturn(Optional.empty());

        assertThatExceptionOfType(EntityNotFoundException.class)
                .isThrownBy(() -> pessoaService.deleteById(inexistenteId))
                .withMessage(PESSOA_ID_NOT_FOUND + inexistenteId);

        verify(pessoaRepository).findById(inexistenteId);
        verify(pessoaRepository, never()).deleteById(inexistenteId);
    }
}
