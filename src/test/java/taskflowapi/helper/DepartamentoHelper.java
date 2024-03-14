package taskflowapi.helper;

import taskflowapi.domain.enums.EDepartamento;
import taskflowapi.application.dto.response.DepartamentoResponse;

import java.util.List;

public class DepartamentoHelper {

    public static DepartamentoResponse umDepartamentoResponse(EDepartamento departamento,
                                                              long qtPessoas, long qtTarefas) {
        return new DepartamentoResponse(departamento, qtPessoas, qtTarefas);
    }

    public static List<DepartamentoResponse> umaListaDepartamentoResponses() {
        return List.of(
                umDepartamentoResponse(EDepartamento.DESENVOLVIMENTO, 5, 10),
                umDepartamentoResponse(EDepartamento.MARKETING, 3, 7)
        );
    }
}
