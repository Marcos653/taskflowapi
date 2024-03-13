package taskflowapi.application.controller.contract;

import org.springframework.web.bind.annotation.GetMapping;
import taskflowapi.application.dto.response.DepartamentoResponse;

import java.util.List;

public interface IDepartamentoController {

    @GetMapping
    List<DepartamentoResponse> findAllDepartamento();
}
