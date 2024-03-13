package taskflowapi.domain.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import taskflowapi.application.dto.response.DepartamentoResponse;
import taskflowapi.domain.repository.IDepartamentoRepository;
import taskflowapi.domain.service.contract.IDepartamentoService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartamentoServiceImpl implements IDepartamentoService {

    private final IDepartamentoRepository repository;

    @Override
    public List<DepartamentoResponse> findAllDepartamento() {
        return repository.findAllDepartamento();
    }
}
