package taskflowapi.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import taskflowapi.application.dto.request.TarefaRequest;
import taskflowapi.application.dto.response.TarefaResponse;
import taskflowapi.domain.model.Tarefa;

@Mapper(componentModel = "spring")
public interface TarefaMapper {

    @Mapping(target = "id", source = "tarefa.id")
    @Mapping(target = "titulo", source = "tarefa.titulo")
    @Mapping(target = "descricao", source = "tarefa.descricao")
    @Mapping(target = "prazo", source = "tarefa.prazo")
    @Mapping(target = "departamento", source = "tarefa.departamento")
    @Mapping(target = "duracao", source = "tarefa.duracao")
    @Mapping(target = "pessoaId", source = "tarefa.pessoa.id")
    @Mapping(target = "finalizado", source = "tarefa.finalizado")
    TarefaResponse convertToTarefaResponse(Tarefa tarefa);

    @Mapping(target = "titulo", source = "tarefaRequest.titulo")
    @Mapping(target = "descricao", source = "tarefaRequest.descricao")
    @Mapping(target = "prazo", source = "tarefaRequest.prazo")
    @Mapping(target = "departamento", source = "tarefaRequest.departamento")
    @Mapping(target = "duracao", source = "tarefaRequest.duracao")
    Tarefa convertToTarefa(TarefaRequest tarefaRequest);
}
