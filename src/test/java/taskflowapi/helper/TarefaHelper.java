package taskflowapi.helper;

import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.model.Tarefa;
import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.dto.request.TarefaRequest;
import taskflowapi.application.dto.response.TarefaResponse;

import java.util.Set;
import java.util.List;
import java.util.HashSet;
import java.time.LocalDateTime;

public class TarefaHelper {

    public static Tarefa umaTarefa(Long id, String titulo, String descricao, LocalDateTime prazo,
                                   EDepartamento departamento, double duracao, Pessoa pessoa, Boolean finalizado) {
        return new Tarefa(id, titulo, descricao, prazo, departamento, duracao, pessoa, finalizado);
    }

    public static TarefaRequest umaTarefaRequest(String titulo, String descricao, LocalDateTime prazo,
                                                 EDepartamento departamento, double duracao) {
        return new TarefaRequest(titulo, descricao, prazo, departamento, duracao);
    }

    public static TarefaResponse umaTarefaResponse(Long id, String titulo, String descricao, LocalDateTime prazo,
                                                   EDepartamento departamento, double duracao, Long pessoaId,
                                                   Boolean finalizado) {
        return new TarefaResponse(id, titulo, descricao, prazo, departamento, duracao, pessoaId, finalizado);
    }

    public static List<TarefaResponse> umaListaTarefaResponse() {
        return List.of(
                umaTarefaResponse(1L, "Desenvolver sistema", "Desenvolvimento do sistema X",
                        LocalDateTime.now().plusDays(5), EDepartamento.DESENVOLVIMENTO, 12.5, null, false),
                umaTarefaResponse(2L, "Revisão financeira", "Revisão dos relatórios financeiros do trimestre",
                        LocalDateTime.now().plusDays(10), EDepartamento.FINANCAS, 8.0, null, false),
                umaTarefaResponse(3L, "Campanha de marketing", "Preparação da nova campanha de marketing digital",
                        LocalDateTime.now().plusDays(15), EDepartamento.MARKETING, 10.0, null, false),
                umaTarefaResponse(4L, "Atualização de segurança", "Implementar atualizações de segurança no sistema Y",
                        LocalDateTime.now().plusDays(20), EDepartamento.VENDAS, 15.0, null, false),
                umaTarefaResponse(5L, "Planejamento orçamentário", "Planejar o orçamento para o próximo ano fiscal",
                        LocalDateTime.now().plusDays(25), EDepartamento.FINANCAS, 20.0, null, false)
        );
    }

    public static Set<TarefaResponse> umSetTarefaResponse() {
        return new HashSet<>(umaListaTarefaResponse());
    }

    public static Set<Tarefa> umSetTarefa() {
        return Set.of(
                umaTarefa(1L, "Desenvolver sistema", "Desenvolvimento do sistema X",
                        LocalDateTime.now().plusDays(5), EDepartamento.DESENVOLVIMENTO, 12.5, null, false),
                umaTarefa(2L, "Revisão financeira", "Revisão dos relatórios financeiros do trimestre",
                        LocalDateTime.now().plusDays(10), EDepartamento.FINANCAS, 8.0, null, false),
                umaTarefa(3L, "Campanha de marketing", "Preparação da nova campanha de marketing digital",
                        LocalDateTime.now().plusDays(15), EDepartamento.MARKETING, 10.0, null, false),
                umaTarefa(4L, "Atualização de segurança", "Implementar atualizações de segurança no sistema Y",
                        LocalDateTime.now().plusDays(20), EDepartamento.VENDAS, 15.0, null, false),
                umaTarefa(5L, "Planejamento orçamentário", "Planejar o orçamento para o próximo ano fiscal",
                        LocalDateTime.now().plusDays(25), EDepartamento.FINANCAS, 20.0, null, false)
        );
    }
}
