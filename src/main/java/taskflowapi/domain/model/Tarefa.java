package taskflowapi.domain.model;

import taskflowapi.domain.enums.EDepartamento;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Table(name = "tarefa", indexes = {
        @Index(name = "idx_tarefa_pessoa_alocada", columnList = "pessoa_alocada"),
        @Index(name = "idx_tarefa_finalizado", columnList = "finalizado"),
        @Index(name = "idx_tarefa_prazo", columnList = "prazo")
})
public class Tarefa {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false)
    private String titulo;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "prazo", nullable = false)
    private LocalDateTime prazo;

    @Enumerated(EnumType.STRING)
    @Column(name = "departamento", nullable = false)
    private EDepartamento departamento;

    @Column(name = "duracao")
    private Double duracao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pessoa_alocada")
    private Pessoa pessoa;

    private Boolean finalizado = false;
}
