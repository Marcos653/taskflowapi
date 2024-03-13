package taskflowapi.domain.repository;

import taskflowapi.application.dto.response.DepartamentoResponse;

import java.util.List;

public interface IDepartamentoRepository {

    List<DepartamentoResponse> findAllDepartamento();
}
