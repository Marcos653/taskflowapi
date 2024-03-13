package taskflowapi.domain.service.contract;

import taskflowapi.application.dto.response.DepartamentoResponse;

import java.util.List;

public interface IDepartamentoService {

    List<DepartamentoResponse> findAllDepartamento();
}
