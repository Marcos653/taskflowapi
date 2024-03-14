package taskflowapi.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import taskflowapi.application.dto.request.PessoaRequest;
import taskflowapi.application.dto.response.PessoaResponse;
import taskflowapi.application.dto.response.TarefaResponse;
import taskflowapi.domain.model.Pessoa;
import taskflowapi.domain.model.Tarefa;

import java.util.Set;

@Mapper(componentModel = "spring", uses = TarefaMapper.class)
public interface PessoaMapper {

    @Mapping(target = "id", source = "pessoa.id")
    @Mapping(target = "nome", source = "pessoa.nome")
    @Mapping(target = "departamento", source = "pessoa.departamento")
    @Mapping(target = "tarefas", source = "pessoa.tarefas")
    PessoaResponse convertToPessoaResponse(Pessoa pessoa);

    @Mapping(target = "nome", source = "pessoaRequest.nome")
    @Mapping(target = "departamento", source = "pessoaRequest.departamento")
    Pessoa convertToPessoa(PessoaRequest pessoaRequest);

    Set<TarefaResponse> tarefasToTarefasResponse(Set<Tarefa> tarefas);
}
