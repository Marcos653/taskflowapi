package taskflowapi.helper;

import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.model.Tarefa;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.dto.filters.PessoaFiltros;
import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.application.dto.response.PessoaResponse;
import taskflowapi.application.dto.response.TarefaResponse;
import taskflowapi.application.dto.response.PessoaMediaHorasTrabalhadas;
import taskflowapi.application.dto.response.PessoaTotalHorasTrabalhadas;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.time.LocalDateTime;

import static taskflowapi.helper.TarefaHelper.umaTarefa;

public class PessoaHelper {

    public static Pessoa umaPessoa(Long id, String nome, EDepartamento departamento, Set<Tarefa> tarefas) {
        var pessoa = new Pessoa();

        pessoa.setId(id);
        pessoa.setNome(nome);
        pessoa.setDepartamento(departamento);
        pessoa.setTarefas(tarefas == null ? new HashSet<>() : tarefas);

        return pessoa;
    }

    public static PessoaRequest umaPessoaRequest(String nome, EDepartamento departamento) {
        return new PessoaRequest(nome, departamento);
    }

    public static PessoaMediaHorasTrabalhadas umaPessoaMediaHorasTrabalhadas(Long id, String nome,
                                                                             EDepartamento departamento,
                                                                             double mediaHorasTrabalhadas) {
        return new PessoaMediaHorasTrabalhadas(id, nome, departamento, mediaHorasTrabalhadas);
    }

    public static PessoaTotalHorasTrabalhadas umaPessoaTotalHorasTrabalhadas(Long id, String nome,
                                                                             EDepartamento departamento,
                                                                             double totalHorasTrabalhadas) {
        return new PessoaTotalHorasTrabalhadas(id, nome, departamento, totalHorasTrabalhadas);
    }

    public static PessoaResponse umaPessoaResponse(Long id, String nome,
                                                   EDepartamento departamento,
                                                   Set<TarefaResponse> tarefas) {
        return new PessoaResponse(id, nome, departamento, tarefas);
    }

    public static List<Pessoa> umaListaPessoa() {
        var pessoa1 = umaPessoa(1L, "Marcos", EDepartamento.DESENVOLVIMENTO, new HashSet<>());
        var tarefa1 = umaTarefa(null, "Desenvolver Sistema", "Desenvolvimento do sistema X",
                LocalDateTime.now().plusDays(30), EDepartamento.DESENVOLVIMENTO, 100.0, null, true);
        vincularPessoaTarefa(pessoa1, tarefa1);

        var pessoa2 = umaPessoa(2L, "Carlos", EDepartamento.FINANCAS, new HashSet<>());
        var tarefa2 = umaTarefa(null, "Revisão Financeira", "Revisar os relatórios financeiros do trimestre",
                LocalDateTime.now().plusDays(15), EDepartamento.FINANCAS, 40.0, null, true);
        vincularPessoaTarefa(pessoa2, tarefa2);

        var pessoa3 = umaPessoa(3L, "Antonia", EDepartamento.MARKETING, new HashSet<>());
        var tarefa3 = umaTarefa(null, "Campanha de Marketing", "Preparar a nova campanha de marketing digital",
                LocalDateTime.now().plusDays(20), EDepartamento.MARKETING, 60.0, null, false);
        vincularPessoaTarefa(pessoa3, tarefa3);

        var pessoa4 = umaPessoa(4L, "Anna", EDepartamento.FINANCAS, new HashSet<>());
        var tarefa4 = umaTarefa(null, "Análise de Custos", "Análise detalhada dos custos de produção",
                LocalDateTime.now().plusDays(10), EDepartamento.FINANCAS, 30.0, null, false);
        vincularPessoaTarefa(pessoa4, tarefa4);

        var pessoa5 = umaPessoa(5L, "João", EDepartamento.FINANCAS, new HashSet<>());
        var tarefa5 = umaTarefa(null, "Planejamento Orçamentário", "Planejar o orçamento para o próximo ano",
                LocalDateTime.now().plusDays(25), EDepartamento.FINANCAS, 50.0, null, true);
        vincularPessoaTarefa(pessoa5, tarefa5);

        return List.of(pessoa1, pessoa2, pessoa3, pessoa4, pessoa5);
    }

    public static List<PessoaTotalHorasTrabalhadas> umaListaPessoaTotalHorasTrabalhadas() {
        return List.of(
                umaPessoaTotalHorasTrabalhadas(1L, "Marcos", EDepartamento.DESENVOLVIMENTO, 100.0),
                umaPessoaTotalHorasTrabalhadas(2L, "Carlos", EDepartamento.FINANCAS, 0.0),
                umaPessoaTotalHorasTrabalhadas(3L, "Antonia", EDepartamento.MARKETING, 40.0),
                umaPessoaTotalHorasTrabalhadas(4L, "Anna", EDepartamento.FINANCAS, 0.0),
                umaPessoaTotalHorasTrabalhadas(5L, "João", EDepartamento.FINANCAS, 50.0)
        );
    }

    public static void vincularPessoaTarefa(Pessoa pessoa, Tarefa tarefa) {
        pessoa.getTarefas().add(tarefa);
        tarefa.setPessoa(pessoa);
    }

    public static PessoaFiltros umPessoaFiltro() {
        return new PessoaFiltros("Marcos",
                LocalDateTime.of(2023, 1, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 23, 59));
    }

    public static PessoaFiltros umPessoaFiltroComNomeInexistente() {
        return new PessoaFiltros("inexistente",
                LocalDateTime.of(2023, 1, 1, 0, 0),
                LocalDateTime.of(2024, 12, 31, 23, 59));
    }

    public static PessoaFiltros umPessoaFiltroComCampoNull() {
        return new PessoaFiltros("Marcos",
                null,
                LocalDateTime.of(2024, 12, 31, 23, 59));
    }

    public static Set<PessoaMediaHorasTrabalhadas> umSetPessoaMediaHorasTrabalhadas() {
        return Set.of(
                new PessoaMediaHorasTrabalhadas(1L, "Marcos", EDepartamento.DESENVOLVIMENTO, 4.1),
                new PessoaMediaHorasTrabalhadas(2L, "Maria", EDepartamento.MARKETING, 3.5)
        );
    }
}
