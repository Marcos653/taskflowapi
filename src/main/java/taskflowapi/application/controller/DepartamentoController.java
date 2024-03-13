package taskflowapi.application.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import taskflowapi.application.controller.contract.IDepartamentoController;
import taskflowapi.application.dto.response.DepartamentoResponse;
import taskflowapi.domain.service.contract.IDepartamentoService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/departamentos")
public class DepartamentoController implements IDepartamentoController {

    private final IDepartamentoService service;

    @Override
    public List<DepartamentoResponse> findAllDepartamento() {
        return service.findAllDepartamento();
    }
}
