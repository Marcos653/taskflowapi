package taskflowapi.domain.model;

import jakarta.persistence.*;
import lombok.*;
import taskflowapi.domain.enums.EDepartamento;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "tarefa")
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
    private Pessoa pessoaAlocada;

    private Boolean finalizado = false;
}
