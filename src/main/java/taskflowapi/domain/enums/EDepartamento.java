package taskflowapi.domain.enums;

import lombok.*;

@Getter
@AllArgsConstructor
public enum EDepartamento {

    DESENVOLVIMENTO("Desenvolvimento"),
    RECURSOS_HUMANOS("Recursos Humanos"),
    FINANCAS("Finanças"),
    MARKETING("Marketing"),
    VENDAS("Vendas"),
    SUPORTE_AO_CLIENTE("Suporte ao Cliente"),
    OPERACOES("Operações"),
    TECNOLOGIA_DA_INFORMACAO("Tecnologia da Informação"),
    PESQUISA_E_DESENVOLVIMENTO("Pesquisa e Desenvolvimento"),
    LOGISTICA("Logística"),
    QUALIDADE("Qualidade"),
    JURIDICO("Jurídico");

    private final String descricao;
}
